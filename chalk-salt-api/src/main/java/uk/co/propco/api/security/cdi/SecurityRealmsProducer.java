package com.chalk.salt.api.security.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.shiro.realm.Realm;

import com.chalk.salt.api.security.DomainAuthRealm;
import com.chalk.salt.core.security.AuthFacade;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class SecurityRealmsProducer.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 */
@Singleton
public class SecurityRealmsProducer {

    /** The user facade. */
    @Inject
    private AuthFacade authenticationFacade;

    /** The user facade. */
    @Inject
    private UserFacade userFacade;

    /** The default cache manager. */
    // @Produces
    // @Resource(lookup = "java:jboss/infinispan/container/propco-cache-container")
    // private EmbeddedCacheManager defaultCacheManager;

    /** The Constant AUTHENTICATION_CACHE_NAME. */
    private static final String AUTHENTICATION_CACHE_NAME = "rest-authentication-cache";

    /** The Constant AUTHORIZATION_CACHE_NAME. */
    private static final String AUTHORIZATION_CACHE_NAME = "rest-authorization-cache";

    /**
     * Gets the session.
     *
     * @return the session
     */
    @Produces
    @ShiroApiConfig
    public List<Realm> getSession() {
        final List<Realm> realmList = new ArrayList<Realm>();
        realmList.add(new DomainAuthRealm(authenticationFacade, userFacade, null, AUTHENTICATION_CACHE_NAME, AUTHORIZATION_CACHE_NAME));
        return realmList;
    }
}
