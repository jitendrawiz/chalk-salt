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
import com.chalk.salt.api.service.UserService;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.dto.AuthStatus;
import com.chalk.salt.common.exceptions.CoreException;
import com.chalk.salt.common.util.ErrorCode;
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class AuthResource extends AbstractResource {

    /** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;

    /** The user facade. */
    @Inject
    private UserService userService;

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
        final Boolean rememberMe = authRequest.getRememberMe();

        logger.info("About to authenticate the user '{}' , remoteAddress '{}'", username, remoteAddress);
        final List<String> clientIpAddresses = Arrays.asList(remoteAddress.split(REGEX_EXP));
        final DomainAuthTokenModel authenticationToken = new DomainAuthTokenModel(username, password, rememberMe, clientIpAddresses);

        final Subject currentUser = SecurityUtils.getSubject();
        final Map<String, String> response = new HashMap<String, String>();
        
        DomainUserPrincipal domainUserPrincipal;
        
        try {
            currentUser.login(authenticationToken);
    
            AuthStatus authenticationStatus = AuthStatus.REJECTED;
            if (currentUser.isAuthenticated()) {
                authenticationStatus = AuthStatus.ACCEPTED;
            }
            
            if(authenticationStatus == AuthStatus.REJECTED){
            	throw Utility.buildResourceException(ErrorCode.AUTHENTICATION_FAILURE,"Username OR password is incorrect!",Status.UNAUTHORIZED, CoreException.class);
            }
            domainUserPrincipal = (DomainUserPrincipal) currentUser.getPrincipal();
        
            response.put("securUuid", domainUserPrincipal.getSecurUuid());
            response.put("fullName", domainUserPrincipal.getFullName());
            response.put("userName", domainUserPrincipal.getUserName());
            
        } 
        catch(final Exception exception){
            String msg = null;
            if(exception instanceof IncorrectCredentialsException){
                msg = "Username OR password is incorrect!";
            } else {
                msg = exception.getMessage();
            }
            throw Utility.buildResourceException(ErrorCode.AUTHENTICATION_FAILURE,msg,Status.UNAUTHORIZED, CoreException.class);
        }
        return Response.ok().entity(response).build();
    }
    
    /**
     * Logout.
     *
     * @return the response
     */
    @POST
    @Path("/logout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response logout() {
    	logger.info("User Logout service ....");
        final boolean result = userService.logout();
        final Map<String, String> response = new HashMap<String, String>();
        
        if (result) {
        response.put("response","User Logged Out Successfully");
            return Response.ok().entity(response).build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON)
            .entity(Utility.buildErrorResponse(ErrorCode.GENERIC_SERVER_ERROR, "An internal server error occurred")).build();
    }
}
