package com.chalk.salt.api.security.cdi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;

import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.web.env.DefaultWebEnvironment;
import org.apache.shiro.web.env.EnvironmentLoaderListener;
import org.apache.shiro.web.env.WebEnvironment;


@WebListener
public class ShiroCDIEnvironmentLoader extends EnvironmentLoaderListener {

    /** The realms. */
    @Inject
    @ShiroApiConfig
    List<Realm> realms = new ArrayList<Realm>();
    
    /*
     * (non-Javadoc)
     *
     * @see org.apache.shiro.web.env.EnvironmentLoader#createEnvironment(javax.servlet.ServletContext)
     */
    @Override
    protected WebEnvironment createEnvironment(final ServletContext pServletContext) {
        final DefaultWebEnvironment environment = (DefaultWebEnvironment) super.createEnvironment(pServletContext);
        final RealmSecurityManager securityManager = (RealmSecurityManager) environment.getSecurityManager();

        securityManager.setRealms(realms);
        environment.setSecurityManager(securityManager);

        return environment;
    }

}
