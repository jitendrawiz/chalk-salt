/**
 * Copyright 2014, Technology Blueprint Limited. All Rights Reserved.
 * No part of this content may be used without TBL's express consent.
 */
package com.chalk.salt.api.security;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.model.DomainAuthTokenModel;
import com.chalk.salt.api.model.security.DomainUserPrincipal;
import com.chalk.salt.common.dto.AuthInfoDto;
import com.chalk.salt.common.dto.AuthRequest;
import com.chalk.salt.common.dto.UserDetailDto;
import com.chalk.salt.common.exceptions.CoreException;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.core.security.AuthFacade;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class DatabaseAuthRealm.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 *
 */
public class DomainAuthRealm extends AuthorizingRealm {

    /** The user facade. */
    private final AuthFacade authenticationFacade;

    /** The user facade. */
    private final UserFacade userFacade;

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /** The Constant HASHING_ALGORITHM_NAME. */
    private static final String HASHING_ALGORITHM_NAME = "BCrypt";
    
    private static Map<String, Integer> loggedInUsers;

    /**
     * Instantiates a new database auth realm.
     *
     * @param authenticationFacade the authentication facade
     * @param userFacade the user facade
     * @param cachingEnabled the caching enabled
     */
    public DomainAuthRealm(final AuthFacade authenticationFacade, final UserFacade userFacade, final boolean cachingEnabled) {
        this.authenticationFacade = authenticationFacade;
        this.userFacade = userFacade;
        setName("Database-Auth-Realm");
        setCachingEnabled(cachingEnabled);
        setAuthenticationCachingEnabled(cachingEnabled);
        setAuthorizationCachingEnabled(cachingEnabled);
        setCredentialsMatcher(getBCryptCredentialsMatcher());
        setAuthenticationTokenClass(DomainAuthTokenModel.class);
    }

    /**
     * Instantiates a new o auth realm.
     *
     * @param authenticationFacade the authentication facade
     * @param userFacade the user facade
     * @param cacheManager the cache manager
     * @param authenticationCacheName the authentication cache name
     * @param authorizationCacheName the authorization cache name
     */
    public DomainAuthRealm(final AuthFacade authenticationFacade, final UserFacade userFacade, final CacheManager cacheManager, final String authenticationCacheName,
        final String authorizationCacheName) {
        this(authenticationFacade, userFacade, cacheManager != null);
        if (cacheManager != null) {
            logger.info("Configuring Caching on security realm '{}'. Authentication Cache Name is set as '{}'. Authorization Cache Name is set as '{}'",
                getName(), authenticationCacheName, authorizationCacheName);
            setCacheManager(cacheManager);
            setAuthenticationCacheName(authenticationCacheName);
            setAuthorizationCacheName(authorizationCacheName);
        } else {
            logger.warn("Caching is not configured on the realm '{}'. Consider it, will improve the performance significantly!");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        final Object primaryPrincipal = principals.getPrimaryPrincipal();
        if (!(primaryPrincipal instanceof DomainUserPrincipal)) {
            return null;
        }
        final DomainUserPrincipal consumerPrincipal = (DomainUserPrincipal) principals.getPrimaryPrincipal();
        final Long userId = consumerPrincipal.getUserId();
        List<String> permissions = null;
        try {
            logger.info("Domain user '{}' authenticated successfully against user Id '{}'. Fetching Authorization info", consumerPrincipal.getUserName(),
                userId);
            permissions = userFacade.fetchAllUserPermissions(userId);
        } catch (final Exception exception) {
            final AuthenticationException authException = new AuthenticationException(
                "An error occurred while getting authorization info (permissions) against user Id:'" + userId + "'", exception);
            throw authException;
        }
        if (permissions == null) {
            permissions = Collections.emptyList();
        }
        final SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        authInfo.addStringPermissions(permissions);
        return authInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(final AuthenticationToken token) throws AuthenticationException {
        final DomainAuthTokenModel domainAuthToken = (DomainAuthTokenModel) token;
        final String username = domainAuthToken.getUsername();
        final String password = String.valueOf(domainAuthToken.getPassword());
        final List<String> clientIpAddresses = domainAuthToken.getClientIpAddresses();

        final AuthRequest authenticationRequest = new AuthRequest(username, password, clientIpAddresses);
        AuthInfoDto authInfo = null;
        try {
            authInfo = authenticationFacade.getUserAuthenticationDetails(authenticationRequest);
        } catch (final UserException coreException) {
            throw new AuthenticationException("Error occurred while checking the authentication details against the user '" + username + "', Error ",
                coreException);
        }
        
        if(!checkAllowedLicense(authInfo.getOfficeDatabase(), authInfo.getNumberOfLicenses())){
            throw new AuthenticationException("Error occurred while checking allowed License against Domain '" + authInfo.getOfficeDatabase() + "'", new CoreException("Current license usage has exceeded. Please contact administrator."));            
           
        }
        
        final DomainUserPrincipal userPrincipal = getDomainUserPrincipal(authInfo);
        final UserDetailDto userDetail = authInfo.getUserDetail();
        final String base64DecodedPassword = Base64.decodeToString(userDetail.getPassword().getBytes());
        final SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(userPrincipal, userDetail.getPassword(), getName());
        authenticationInfo.setCredentialsSalt(new SimpleByteSource(base64DecodedPassword));
        return authenticationInfo;
    }

    private boolean checkAllowedLicense(final String officeDatabase, int numberOfLicenses) {
        
        int licenseCount = 0;
        if(loggedInUsers== null){
            loggedInUsers = new HashMap<String, Integer>();
        } 
        
        if(loggedInUsers.containsKey(officeDatabase) && loggedInUsers.get(officeDatabase)>=0){
            licenseCount = loggedInUsers.get(officeDatabase).intValue()+1;
            loggedInUsers.put(officeDatabase, licenseCount);
            if(licenseCount>numberOfLicenses){                
                return false;
            }
        }
        else{
            loggedInUsers.put(officeDatabase, 0);
        }        
        return true;
    }

    /**
     * Gets the domain user principal.
     *
     * @param authenticationInfo the authentication info
     * @return the domain user principal
     */
    private DomainUserPrincipal getDomainUserPrincipal(final AuthInfoDto authenticationInfo) {
        final UserDetailDto user = authenticationInfo.getUserDetail();
        final DomainUserPrincipal userPrincipal = new DomainUserPrincipal(user.getUserId(), user.getUsername());
        userPrincipal.setEmail(user.getEmail());
        userPrincipal.setFullName(user.getDisplayAs());
        userPrincipal.setOfficeJndi(authenticationInfo.getOfficeJndi());
        userPrincipal.setSystemJndi(authenticationInfo.getSystemJndi());
        userPrincipal.setMaxAllowedFailureLoginAttempts(authenticationInfo.getMaxAllowedFailureLoginAttempts());
        userPrincipal.setSecurUuid(user.getSecurUuid());
        return userPrincipal;
    }

    /**
     * Gets the b crypt credentials matcher.
     *
     * @return the b crypt credentials matcher
     */
    private CredentialsMatcher getBCryptCredentialsMatcher() {
        final BCryptCredentialMatcher bCryptCredentialMatcher = new BCryptCredentialMatcher();
        bCryptCredentialMatcher.setHashAlgorithmName(HASHING_ALGORITHM_NAME);
        bCryptCredentialMatcher.setStoredCredentialsHexEncoded(false);
        return bCryptCredentialMatcher;
    }
}
