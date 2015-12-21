/*
 * Copyright 2015, Techblue. All Rights Reserved.
 * No part of this content may be used without Techblue's express consent.
 */
package com.chalk.salt.api.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;

import com.chalk.salt.api.model.UserModel;
import com.chalk.salt.api.model.security.DomainUserPrincipal;
import com.chalk.salt.api.service.UserService;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class UserResource.
 *
 * @author <a href="mailto:preeti.barthwal@techblue.co.uk">Preeti Barthwal</a>
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class UserResource extends AbstractResource {

    /** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;

    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;

    /** The user facade. */
    @Inject
    private UserFacade userFacade;

    /** The user service. */
    @Inject
    private UserService userService;

    /**
     * Gets the user list.
     *
     * @return the user list
     * @throws UserException the user exception
     */
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserList() throws UserException {
        List<UserDto> users = null;
        List<Map<String, String>> userList = null;

        try {
            users = userFacade.fetchUsers();
            if (CollectionUtils.isEmpty(users)) {
                return Response.noContent().build();
            }
            userList = userService.generateUserDetailList(users);
            return Response.ok(userList).build();
        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }

    /**
     * Disable user.
     *
     * @param securUuid the secur uuid
     * @param disableFrom the disable from
     * @return the response
     * @throws UserException the user exception
     */
    @GET
    @Path("/users/{userId}/disable/{from}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response disableUser(@NotBlank @PathParam("userId") final String securUuid, @NotBlank @PathParam("from") final String disableFrom) throws UserException {
        try {
            userFacade.disableUser(securUuid, disableFrom);
            return Response.ok().build();
        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }

    /**
     * Gets the user info.
     *
     * @param securUuid the secur uuid
     * @return the user info
     * @throws UserException the user exception
     */
    @GET
    @Path("/users/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserInfo(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {

        UserDto userDetails = null;
        try {
            userDetails = userFacade.getUserInfo(securUuid);
            if (userDetails == null) {
                return Response.noContent().build();
            }
            return Response.ok(userDetails).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }

    /**
     * Save user info.
     *
     * @param user the user
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @Path("/users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
   // @RequiresAuthentication
    public Response saveUserInfo(final @Valid UserModel user) throws UserException {

        final Map<String, String> response = new HashMap<String, String>();
        String securUuid = null;
        try {
         //   final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
           // final DomainUserPrincipalDto domainDto = beanMapper.map(domainUserPrincipal, DomainUserPrincipalDto.class);
            final UserDto userDetails = beanMapper.map(user, UserDto.class);
         //   final String senderEmail = domainUserPrincipal.getEmail();
            final String encryptedPassword = Utility.getEncodedBCryptHash(user.getPassword());
            userDetails.setPassword(encryptedPassword);
            securUuid = userFacade.saveUserInfo(userDetails);
            if (securUuid == null) {
                return Response.noContent().build();
            }
            response.put("securUuid", securUuid);
            return Response.ok(response).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
}
