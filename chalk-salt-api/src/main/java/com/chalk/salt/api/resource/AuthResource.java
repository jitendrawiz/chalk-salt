/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.api.resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.jboss.resteasy.spi.validation.ValidateRequest;
import org.slf4j.Logger;

import com.chalk.salt.api.model.AuthRequestModel;
import com.chalk.salt.api.model.DomainAuthTokenModel;
import com.chalk.salt.api.model.security.DomainUserPrincipal;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.dto.SaveLoginRequestDto;
import com.chalk.salt.common.exceptions.CoreException;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.user.UserFacade;


/**
 * The Class AuthResource.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class AuthResource extends AbstractResource {

    /** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;

    /** The user facade. */
    @Inject
    private UserFacade userFacade;

    /** The Constant REG_EXP. */
    private static final String REGEX_EXP = "[,; ]";

    /**
     * Login.
     *
     * @param request the request
     * @param authRequest the auth request
     * @return the list
     * @throws CoreException the core exception
     */
    @POST
    @Path("/login")
    @ValidateRequest
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(@Context final HttpServletRequest request, @Valid final AuthRequestModel authRequest) throws CoreException {
        final String username = authRequest.getUsername();
        final String password = authRequest.getPassword();
        final String remoteAddress = request.getRemoteAddr();

        logger.info("About to authenticate the user '{}' , remoteAddress '{}'", username, remoteAddress);
        final List<String> clientIpAddresses = Arrays.asList(remoteAddress.split(REGEX_EXP));
        final DomainAuthTokenModel authenticationToken = new DomainAuthTokenModel(username, password, clientIpAddresses);

        final Subject currentUser = SecurityUtils.getSubject();
        final Map<String, String> response = new HashMap<String, String>();
        
        DomainUserPrincipal domainUserPrincipal;
        
        try {
            currentUser.login(authenticationToken);
    
            AuthStatus authenticationStatus = AuthStatus.REJECTED;
            if (currentUser.isAuthenticated()) {
                authenticationStatus = AuthStatus.ACCEPTED;
            }
            domainUserPrincipal = (DomainUserPrincipal) currentUser.getPrincipal();
        
        
            final SaveLoginRequestDto saveLoginRequest = new SaveLoginRequestDto(domainUserPrincipal.getUserId(), username, clientIpAddresses,
                domainUserPrincipal.getOfficeJndi(), domainUserPrincipal.getMaxAllowedFailureLoginAttempts(), authenticationStatus);
            userFacade.saveLoginStatus(saveLoginRequest);
            response.put("securUuid", domainUserPrincipal.getSecurUuid());
            response.put("fullName", domainUserPrincipal.getFullName());
            
        } catch (final UserException userException) {
            final ErrorCode errorCode = userException.getErrorCode();
            throw Utility.buildResourceException(errorCode, userException, Status.UNAUTHORIZED, CoreException.class);
        }
        catch(final Exception exception){
            String msg = null;
            if(exception instanceof IncorrectCredentialsException){
                msg = "Username OR password is incorrect!";
            } else {
                msg = exception.getMessage();
            }
            
            
            if(userFacade.saveRejectedUserDetails(username, clientIpAddresses, AuthStatus.REJECTED)){
                throw Utility.buildResourceException(ErrorCode.LOGIN_COUNT_PER_USER_EXCEEDED,"Due to multiple incorrect login attempts, your account has been locked. Please click 'Unblock account' to continue.",Status.UNAUTHORIZED, CoreException.class);
            }
            
            throw Utility.buildResourceException(ErrorCode.AUTHENTICATION_FAILURE,msg,Status.UNAUTHORIZED, CoreException.class);
            
        }
        return Response.ok().entity(response).build();
    }
}
