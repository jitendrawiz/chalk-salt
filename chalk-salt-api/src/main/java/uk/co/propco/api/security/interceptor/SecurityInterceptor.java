package com.chalk.salt.api.security.interceptor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

import com.chalk.salt.api.exceptions.security.ApplicationAuthenticationException;
import com.chalk.salt.api.exceptions.security.PermissionDeniedException;
import com.chalk.salt.api.security.cdi.Secured;
import com.chalk.salt.api.util.AnnotationUtils;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.util.ErrorCode;

/**
 * The Class SecurityInterceptor.
 *
 * @author <a href="mailto:ajay.deshwal@techblue.co.uk">Ajay Deshwal</a>
 *
 */
@Secured
@Interceptor
public class SecurityInterceptor {

    /** The subject. */
    @Inject
    private Subject subject;

    /** The auth token. */
    @Inject
    private AuthenticationToken authToken;

    /**
     * Check.
     *
     * @param ctx the ctx
     * @return the object
     * @throws Exception the exception
     */
    @AroundInvoke
    public Object check(final InvocationContext ctx) throws Exception {
        if (!subject.isAuthenticated()) {
            authenticate(ctx);
        }
        checkAuthentication(ctx);
        checkPermissions(ctx);
        return ctx.proceed();
    }

    /**
     * Authenticate.
     *
     * @param ctx the ctx
     */
    public void authenticate(final InvocationContext ctx) {
        subject.login(authToken);
    }

    /**
     * Check permissions.
     *
     * @param ctx the ctx
     */
    private void checkPermissions(final InvocationContext ctx) {
        final RequiresPermissions anno = AnnotationUtils.findAnnotation(ctx.getMethod(), RequiresPermissions.class);
        if (anno == null) {
            return;
        }
        final boolean[] permsChecks = subject.isPermitted(anno.value());

        switch (anno.logical()) {
            case AND:
                for (final boolean b : permsChecks) {
                    if (!b) {
                        failPermission(anno.value(), permsChecks, true);
                    }
                }
                break;
            case OR:
                for (final boolean b : permsChecks) {
                    if (b) {
                        return;
                    }
                }
                failPermission(anno.value(), permsChecks, false);
                break;
        }
    }

    /**
     * Fail permission.
     *
     * @param values the values
     * @param checks the checks
     * @param allRequired the all required
     */
    protected void failPermission(final String[] values, final boolean[] checks, final boolean allRequired) {
        final Set<String> results = new LinkedHashSet<String>();
        for (int i = 0; i < values.length; ++i) {
            if (!checks[i]) {
                results.add(values[i]);
            }
        }

        throw new PermissionDeniedException("User " + subject.getPrincipal() + " does not have necessary roles for this resource.", results, allRequired);
    }

    /**
     * Check authentication.
     *
     * @param ctx the ctx
     */
    private void checkAuthentication(final InvocationContext ctx) {
        final RequiresAuthentication anno = AnnotationUtils.findAnnotation(ctx.getMethod(), RequiresAuthentication.class);
        if (anno == null || subject.isAuthenticated()) {
            return;
        }
        final ErrorResponse errorResponse = Utility.buildErrorResponse(ErrorCode.AUTHENTICATION_REQUIRED, "Authentication Required");
        final ApplicationAuthenticationException authException = new ApplicationAuthenticationException("Method " + ctx.getMethod().getName() + " in class "
            + ctx.getTarget().getClass().getName() + " invocation request received. Aborting it as it requires authentication.");
        authException.setErrorResponse(errorResponse);
        throw authException;
    }

}
