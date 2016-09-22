package com.chalk.salt.api.security.cdi;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;


@Singleton
public class SecurityProducer {

    /**
     * Produce security manager.
     *
     * @return the security manager
     */
    @Produces
    @RequestScoped
    public SecurityManager produceSecurityManager() {
        return SecurityUtils.getSecurityManager();
    }

    /**
     * Gets the subject.
     *
     * @return the subject
     */
    @Produces
    @RequestScoped
    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * Gets the session.
     *
     * @return the session
     */
    @Produces
    @RequestScoped
    public Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }
}
