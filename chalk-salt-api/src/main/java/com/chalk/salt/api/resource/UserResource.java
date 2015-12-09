/*
* Copyright 2015, Techblue. All Rights Reserved.
* No part of this content may be used without Techblue's express consent.
*/
package com.chalk.salt.api.resource;

import java.util.ArrayList;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.api.model.DomainDetailModel;
import com.chalk.salt.api.model.UserIconModel;
import com.chalk.salt.api.model.UserModel;
import com.chalk.salt.api.model.security.DomainUserPrincipal;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DomainDetailDto;
import com.chalk.salt.common.dto.DomainUserPrincipalDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.dto.UserIconDto;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.ErrorCode;
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
    
    @Inject
    @BeanMapper
    protected Mapper beanMapper;

    /** The user facade. */
    @Inject
    private UserFacade userFacade;

    /**
     * Register.
     *
     * @param user the user
     * @return the response
     * @throws UserException the user exception
     */
    /*
     * @Inject private Mapper beanMapper;
     */
    @POST
    @Path("/user/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response register(final @Valid UserModel user) throws UserException {
        logger.info("About to register the user");
        final Map<String, String> errors = validateUserRegistrationRequest(user);
        if (errors != null && errors.size() > 0) {
            throw Utility.buildInvalidParameterException(errors, UserException.class);
        }
        final String username = user.getUsername();
        boolean userExists = false;
        try {
            userExists = userFacade.isUserExist(username);
        } catch (final UserException userException) {
            throw Utility.buildInternalServerError(UserException.class, userException);
        }
        if (userExists) {            
            throw Utility.buildResourceException(ErrorCode.USER_ALREADY_EXITS, "User already exist!", Status.NOT_MODIFIED, UserException.class);
        }

        final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final DomainUserPrincipalDto domainDto = beanMapper.map(domainUserPrincipal, DomainUserPrincipalDto.class);
        final String senderEmail = domainUserPrincipal.getEmail();
        final String encryptedPassword = Utility.getEncodedBCryptHash(user.getPassword());
        final UserDto userDetails = beanMapper.map(user, UserDto.class);
        userDetails.setPassword(encryptedPassword);
        String securUuid = null;
        try {
            securUuid = userFacade.registerUser(domainDto, userDetails, senderEmail);
        } catch (final UserException userException) {        
            throw Utility.buildInternalServerError(UserException.class, userException);
        }
        final Map<String, String> response = new HashMap<String, String>();
        response.put("securUuid", securUuid);
        return Response.ok(response).build();
    }

    /**
     * Validate user registration request.
     *
     * @param user the user
     * @return the map
     */
    private Map<String, String> validateUserRegistrationRequest(final UserModel user) {
        final Map<String, String> errors = new HashMap<String, String>();
        if (StringUtils.isBlank(user.getUsername())) {
            errors.put("username", "username can not be blank/null");
        }
        if (StringUtils.isBlank(user.getPassword())) {
            errors.put("password", "password can not be blank/null");
        }
        if (StringUtils.isBlank(user.getConfirmPassword())) {
            errors.put("confirm password", "confirm password can not be blank/null");
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.put("password mismatch", "password and confirm password should be same");
        }
        if (StringUtils.isBlank(user.getForename())) {
            errors.put("forename", "forename can not be blank/null");
        }
        if (StringUtils.isBlank(user.getSurname())) {
            errors.put("surname", "surname can not be blank/null");
        }
        if (StringUtils.isBlank(user.getJobTitle())) {
            errors.put("jobtitle", "jobtitle can not be blank/null");
        }
        if (StringUtils.isBlank(user.getDisplayAs())) {
            errors.put("displayAs", "displayAs can not be blank/null");
        }
        if (StringUtils.isBlank(user.getEmail())) {
            errors.put("emailAddress", "email address can not be blank/null");
        }
        if (CollectionUtils.isEmpty(user.getDomainDetails())) {
            errors.put("domainDetials", "domainDetails can not be blank/null");
        }
        return errors;
    }
    
    /**
     * Gets the domains list.
     *
     * @return the domains list
     *//*
    @GET
    @Path("/user/domains")
    @Produces(MediaType.APPLICATION_JSON)   
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getDomainList() {

        final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final String securUuid = domainUserPrincipal.getSecurUuid();
        final String systemJndi = domainUserPrincipal.getSystemJndi();
        List<DomainDetailModel> domainModel = null;
        try {
            final List<DomainDetailDto> domains = userFacade.fetchDomains(securUuid, systemJndi);
            domainModel = getDomainDetailModel(domains);
        } catch (final UserException userException) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Utility.buildInternalServerError(UserException.class, userException))
                .build();
        }
        if (CollectionUtils.isEmpty(domainModel)) {
            return Response.noContent().build();
        }
        return Response.ok().entity(domainModel).build();
    }

    *//**
     * Gets the domain detail model.
     *
     * @param domains the domains
     * @return the domain detail model
     *//*
    private List<DomainDetailModel> getDomainDetailModel(List<DomainDetailDto> domains) {
        final List<DomainDetailModel> domainModel = new ArrayList<DomainDetailModel>();
        for (DomainDetailDto domain : domains) {
            DomainDetailModel domainDetailModel = new DomainDetailModel();
            domainDetailModel.setDomainName(domain.getDomainName());
            domainDetailModel.setIrefUuid(domain.getIrefUuid());
            domainModel.add(domainDetailModel);
        }
        return domainModel;
    }

    *//**
     * Gets the icon list.
     *
     * @return the icon list
     * @throws UserException the user exception
     *//*
    @GET
    @Path("user/icons")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getIconList() throws UserException {
        final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final String securUuid = domainUserPrincipal.getSecurUuid();
        final String officeJndi = domainUserPrincipal.getOfficeJndi();
        List<UserIconModel> userIcons = null;
        try {
            final List<UserIconDto> icons = userFacade.fetchIcons(securUuid, officeJndi);
            userIcons = getUserIconModel(icons);
            if (CollectionUtils.isEmpty(userIcons)) {
                return Response.noContent().build();
            }
        } catch (final UserException userException) {
            throw Utility.buildInternalServerError(UserException.class, userException);
        }
        return Response.ok().entity(userIcons).build();
    }

    *//**
     * Gets the user icon.
     *
     * @param icon the icon
     * @return the user icon
     *//*
    private List<UserIconModel> getUserIconModel(final List<UserIconDto> icon) {
        List<UserIconModel> userIcons = new ArrayList<UserIconModel>();
        for (UserIconDto iconDto : icon) {
            UserIconModel iconModel = new UserIconModel();
            iconModel.setIcon(iconDto.getIcon());
            iconModel.setLink(iconDto.getLink());
            iconModel.setName(iconDto.getName());
            userIcons.add(iconModel);
        }
        return userIcons;
    }

    *//**
     * Save icon list.
     *
     * @param userIcon the user icon
     * @return the response
     *//*
    @POST
    @Path("user/icons")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response saveIconList(final @Valid List<UserIconModel> userIcon) {
        final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final String securUuid = domainUserPrincipal.getSecurUuid();
        final String officeJndi = domainUserPrincipal.getOfficeJndi();
        Boolean iconSavedSuccessfully = false;
        try {
            List<UserIconDto> icons = getUserIconDto(userIcon);
            iconSavedSuccessfully = userFacade.saveIcons(securUuid, officeJndi, icons);
        } catch (final UserException userException) {
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(Utility.buildInternalServerError(UserException.class, userException))
                .build();
        }
        if (!iconSavedSuccessfully) {
            return Response
                .status(Response.Status.NOT_MODIFIED)
                .entity(
                    Utility.buildErrorResponse(ErrorCode.FAIL_TO_SAVE_USER_ICONS,
                        "Fail to add home screen icons against user.")).build();
        }
        return Response.ok().entity(iconSavedSuccessfully).build();
    }

    *//**
     * Gets the user icon.
     *
     * @param icons the icons
     * @return the user icon
     *//*
    private List<UserIconDto> getUserIconDto(final List<UserIconModel> icons) {
        List<UserIconDto> userIcons = new ArrayList<UserIconDto>();
        for (UserIconModel iconModel : icons) {
            UserIconDto iconDto = new UserIconDto();
            iconDto.setIcon(iconModel.getIcon());
            iconDto.setLink(iconModel.getLink());
            iconDto.setName(iconModel.getName());
            userIcons.add(iconDto);
        }
        return userIcons;
    }
    
    *//**
     * Gets the user list.
     *
     * @return the user list
     * @throws UserException the user exception
     *//*
    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserList() throws UserException{
                
        final DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final String systemJndi = domainUserPrincipal.getSystemJndi();
        final String officeJndi = domainUserPrincipal.getOfficeJndi();
        List<UserDto> users = null;
        List<Map<String, String>> userList = null;
       
        try {
             users = userFacade.fetchUsers(officeJndi, systemJndi);
             if (CollectionUtils.isEmpty(users)) {
                 return Response.noContent().build();
             }
             userList = generateUserDetailList(users);
        } catch (final UserException userException) {        
            throw Utility.buildInternalServerError(UserException.class, userException);
        }               
        return Response.ok(userList).build();
        
    }

    private List<Map<String, String>> generateUserDetailList(List<UserDto> users) {
        List<Map<String, String>> userList = new ArrayList<Map<String, String>>();
        for (UserDto user : users) {
            Map<String, String> userMap = new HashMap<String , String>();
            userMap.put("securUuid", user.getSecurUuid());
            userMap.put("forename", user.getForename());
            userMap.put("middle", user.getMiddle());
            userMap.put("surname", user.getSurname());
            userMap.put("jobtitle", user.getJobTitle());
            userList.add(userMap);
        }
        return userList;
    }
    
    @POST
    @Path("/user/disable")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response disableUser(UserModel user) throws UserException{
        final String securUuid = user.getSecurUuid();
        final String disableDate = user.getDisableDate();
        
        try {            
             userFacade.disableUser(securUuid, disableDate);
        } catch (final UserException userException) {        
            throw Utility.buildInternalServerError(UserException.class, userException);
        }               
        return Response.ok().build();        
    }
    
    @GET
    @Path("/user/info/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserInfo(@PathParam("securUuid") String securUuid) throws UserException{
        DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final String officeJndi = domainUserPrincipal.getOfficeJndi();
        UserDto userDetails = null;
        
        try {
             userDetails = userFacade.getUserInfo(securUuid, officeJndi);
             if(userDetails == null){
                 return Response.noContent().build();
             }
             
        } catch (final UserException userException) {        
            throw Utility.buildInternalServerError(UserException.class, userException);
        }               
        return Response.ok(userDetails).build();        
    }
    
    @POST
    @Path("/user/info")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserInfo(UserModel user) throws UserException{
        DomainUserPrincipal domainUserPrincipal = (DomainUserPrincipal) getUserPrincipal();
        final DomainUserPrincipalDto domainDto = beanMapper.map(domainUserPrincipal, DomainUserPrincipalDto.class);
        final UserDto userDetails = beanMapper.map(user, UserDto.class);
        String securUuid = null;
            
        try {
             securUuid = userFacade.saveUserInfo(userDetails, domainDto);
             if(securUuid == null){
                 return Response.noContent().build();
             }
             
        } catch (final UserException userException) {        
            throw Utility.buildInternalServerError(UserException.class, userException);
        }               
        return Response.ok(userDetails).build();        
    }*/
}
