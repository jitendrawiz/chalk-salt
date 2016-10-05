package com.chalk.salt.api.resource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;

import com.chalk.salt.api.model.DiscussionTopicRequestModel;
import com.chalk.salt.api.model.GuestUserModel;
import com.chalk.salt.api.model.ProfilePhotoUploadModel;
import com.chalk.salt.api.model.StudentAchievementFileModel;
import com.chalk.salt.api.model.StudentAchievementModel;
import com.chalk.salt.api.model.TopicImageUploadModel;
import com.chalk.salt.api.model.UserModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.GuestUserDto;
import com.chalk.salt.common.dto.ProfilePhotoUploadDto;
import com.chalk.salt.common.dto.StudentAchievementDto;
import com.chalk.salt.common.dto.StudentAchievementFileDto;
import com.chalk.salt.common.dto.TopicImageUploadDto;
import com.chalk.salt.common.dto.UserDto;
import com.chalk.salt.common.exceptions.StudentAchievementException;
import com.chalk.salt.common.exceptions.UserException;
import com.chalk.salt.common.util.CryptoUtil;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class UserResource.
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
     
    /**
     * Update profile.
     *
     * @param userModel the user model
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @Path("/users/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response updateProfile(final @Valid UserModel userModel) throws UserException {
    	final Map<String, String> response = new HashMap<String, String>();
        Boolean updateStatus = false;
        try {
            final UserDto userDetails = beanMapper.map(userModel, UserDto.class); 
            updateStatus = userFacade.updateProfile(userDetails);            
            response.put("updateStatus", updateStatus.toString());
            return Response.ok(response).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    
    /**
     * Change password.
     *
     * @param userModel the user model
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @Path("/users/changepassword")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response changePassword(final @Valid UserModel userModel) throws UserException {
    	final Map<String, String> response = new HashMap<String, String>();
        Boolean updateStatus = false;
        try {
            final UserDto userDetails = beanMapper.map(userModel, UserDto.class); 
            
            if(!(userDetails.getNewPassword().equals(userDetails.getConfirmPassword()))){
            	throw Utility.buildResourceException(ErrorCode.INCORRECT_CREDENTIALS, "New Password and Confirm Password not matched", Status.INTERNAL_SERVER_ERROR, UserException.class, new UserException());
            }
            
            String encryptedPassword = Utility.getEncodedBCryptHash(userDetails.getNewPassword());
            userDetails.setNewPassword(encryptedPassword);
            
            encryptedPassword = Utility.getEncodedBCryptHash(userDetails.getPassword());
            userDetails.setPassword(encryptedPassword);
            
            updateStatus = userFacade.changePassword(userDetails);            
            response.put("updateStatus", updateStatus.toString());
            return Response.ok(response).build();

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
    
    public Response saveUserInfo(final @Valid UserModel user) throws UserException {

        final Map<String, String> response = new HashMap<String, String>();
        String securUuid = null;
        try {
            final UserDto userDetails = beanMapper.map(user, UserDto.class);
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
    
    /**
     * Gets the students.
     *
     * @return the students
     * @throws UserException the user exception
     */
    @GET
    @Path("/students")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getStudents() throws UserException {

        List<UserDto> students = null;
        try {
        	students = userFacade.getStudents();
            if (students == null) {
                return Response.noContent().build();
            }
            return Response.ok(students).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    /**
     * Delete student.
     *
     * @param securUuid the secur uuid
     * @return the response
     * @throws UserException the user exception
     */
    @GET
    @Path("/students/delete/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteStudent(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {

    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
        try {
        	deleteStatus = userFacade.deleteStudent(securUuid);
            if (deleteStatus == null) {
                return Response.noContent().build();
            }
            response.put("deleteStatus", deleteStatus.toString());
            return Response.ok(response).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    /**
     * Save topic request.
     *
     * @param discussion the discussion
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @Path("/students/topics/request")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response saveTopicRequest(final @Valid DiscussionTopicRequestModel discussion)throws UserException{
    	
    	DiscussionTopicRequestDto discussionDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String securUuid = null;
    	try{
    		discussionDetails = beanMapper.map(discussion, DiscussionTopicRequestDto.class);
    		securUuid = userFacade.saveTopicRequest(discussionDetails);
    		response.put("securUuid", securUuid);
            return Response.ok(response).build();
	    } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    /**
     * Upload profile photo.
     *
     * @param securUuid the secur uuid
     * @param profilePhotoUploadRequest the profile photo upload request
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @RequiresAuthentication
    @Path("/users/update/profile/photo/{securUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadProfilePhoto(@PathParam("securUuid") final String securUuid,
        @MultipartForm final ProfilePhotoUploadModel profilePhotoUploadRequest) throws UserException {
    	 if (profilePhotoUploadRequest == null || StringUtils.isBlank(securUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
    	  final File sourceFile = profilePhotoUploadRequest.getFile();
          final String filename = profilePhotoUploadRequest.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final ProfilePhotoUploadDto documentUploadData = new ProfilePhotoUploadDto();
          documentUploadData.setName(filename);
          documentUploadData.setFile(sourceFile);
          documentUploadData.setFilePath(sourceFile.getPath());
        try
        {
        	final String dmsIdentifier = userFacade.uploadProfilePhoto(securUuid, documentUploadData);
        	 final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("dmsIdentifier", dmsIdentifier);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
        	
	    } catch (final UserException userException) {
	        throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
	    }
    }
    
    /**
     * Gets the user photo.
     *
     * @param securUuid the secur uuid
     * @return the user photo
     * @throws UserException the user exception
     */
    @GET
    @Path("/user/photo/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getUserPhoto(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {
    	final Map<String, String> response = new HashMap<String, String>();
        String photolink = null;
        try {
        	photolink = userFacade.getUserPhotoLink(securUuid);
            if (photolink == null) {
                return Response.noContent().build();
            }
            File file=new File(photolink);
            final String mediaType = Utility.probeContentType(file.getAbsolutePath());
            final String encodedImageString = Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
            response.put("photolink", "data:" + mediaType + ";base64," + encodedImageString);
            return Response.ok(response).build();
        } catch (final UserException userException ) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        } catch (IOException e) {
        	if(e instanceof NoSuchFileException){
                throw Utility.buildResourceException(ErrorCode.RESOURCE_NOT_FOUND, "No Image File exists corresponding to the user", Status.NO_CONTENT, UserException.class, e);
        	}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Response.ok(response).build();
    }
    
    /**
     * Delete user photo.
     *
     * @param securUuid the secur uuid
     * @return the response
     * @throws UserException the user exception
     */
    @DELETE
    @Path("/user/photo/delete/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteUserPhoto(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {
    	final Map<String, String> response = new HashMap<String, String>();
        if (StringUtils.isBlank(securUuid)) {
            return Response.status(Status.BAD_REQUEST).entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID, "Required parameters are invalid or missing"))
                .type(MediaType.APPLICATION_JSON).build();
        }
        try {
        	userFacade.deleteUserPhoto(securUuid);
        	response.put("message", "Profile Photo deleted successfully");
             return Response.ok(response, MediaType.APPLICATION_JSON).build();
        } catch (final UserException userException ) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        } 
    }
    
    
    /**
     * Upload topic image.
     *
     * @param securUuid the secur uuid
     * @param topicImageUploadRequest the topic image upload request
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @RequiresAuthentication
    @Path("/users/update/topic/photo/{securUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTopicImage(@PathParam("securUuid") final String securUuid,
        @MultipartForm final TopicImageUploadModel topicImageUploadRequest) throws UserException {
    	 if (topicImageUploadRequest == null || StringUtils.isBlank(securUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
    	  final File sourceFile = topicImageUploadRequest.getFile();
          final String filename = topicImageUploadRequest.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final TopicImageUploadDto documentUploadData = new TopicImageUploadDto();
          documentUploadData.setName(filename);
          documentUploadData.setFile(sourceFile);
          documentUploadData.setFilePath(sourceFile.getPath());
        try
        {
        	final String TopicSecurUuid = userFacade.uploadTopicImage(securUuid, documentUploadData);
        	 final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("TopicSecurUuid", TopicSecurUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
        	
	    } catch (final UserException userException) {
	        throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
	    }
    }
    
    /**
     * Gets the topic image.
     *
     * @param securUuid the secur uuid
     * @return the topic image
     * @throws UserException the user exception
     */
    @GET
    @Path("/topic/image/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getTopicImage(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {
    	final Map<String, String> response = new HashMap<String, String>();
        String topicImageLink = null;
        try {
        	topicImageLink = userFacade.getTopicImageLink(securUuid);
            if (topicImageLink == null) {
                return Response.noContent().build();
            }
            File file=new File(topicImageLink);
            final String mediaType = Utility.probeContentType(file.getAbsolutePath());
            final String encodedImageString = Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
            response.put("topicImageLink", "data:" + mediaType + ";base64," + encodedImageString);
            return Response.ok(response).build();
        } catch (final UserException userException ) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        } catch (IOException e) {
        	if(e instanceof NoSuchFileException){
                throw Utility.buildResourceException(ErrorCode.RESOURCE_NOT_FOUND, "No Image File exists corresponding to the topic", Status.NO_CONTENT, UserException.class, e);
        	}
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return Response.ok(response).build();
    }
    
    /**
     * Upload topic request image.
     *
     * @param securUuid the secur uuid
     * @param topicImageUploadRequest the topic image upload request
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @RequiresAuthentication
    @Path("/students/topics/request/photo/{securUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTopicRequestImage(@PathParam("securUuid") final String securUuid,
        @MultipartForm final TopicImageUploadModel topicImageUploadRequest) throws UserException {
    	 if (topicImageUploadRequest == null || StringUtils.isBlank(securUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
    	  final File sourceFile = topicImageUploadRequest.getFile();
          final String filename = topicImageUploadRequest.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final TopicImageUploadDto documentUploadData = new TopicImageUploadDto();
          documentUploadData.setName(filename);
          documentUploadData.setFile(sourceFile);
          documentUploadData.setFilePath(sourceFile.getPath());
        try
        {
        	final String TopicSecurUuid = userFacade.uploadTopicRequestImage(securUuid, documentUploadData);
        	 final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("TopicSecurUuid", TopicSecurUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
        	
	    } catch (final UserException userException) {
	        throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
	    }
    }
    
    /**
     * Save guest user details.
     *
     * @param userModel the user model
     * @return the response
     * @throws UserException the user exception
     */
    @POST
    @Path("/guest/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)    
    public Response saveGuestUserDetails(final @Valid GuestUserModel userModel) throws UserException {
        try {
            final GuestUserDto userDetails = beanMapper.map(userModel, GuestUserDto.class); 
            GuestUserDto userDto = beanMapper.map(userDetails, GuestUserDto.class);
            GuestUserDto response = userFacade.saveGuestUserDetails(userDto);           
            return Response.ok(response).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    /**
     * Reset password.
     *
     * @param securUuid the secur uuid
     * @return the response
     * @throws UserException the user exception
     */
    @GET
    @Path("/users/reset-password/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response resetPassword(@NotBlank @PathParam("securUuid") final String securUuid) throws UserException {

    	final Map<String, String> responseMap = new HashMap<String, String>();
    	Boolean response;
        try {
        	String tempPassword = new CryptoUtil().generateRandomString(); 
        	final String encryptedTempPassword = Utility.getEncodedBCryptHash(tempPassword);
        	response = userFacade.resetPassword(securUuid, tempPassword, encryptedTempPassword);
        	responseMap.put("response", response.toString());
            return Response.ok(responseMap).build();

        } catch (final UserException userException) {
            throw Utility.buildResourceException(userException.getErrorCode(), userException.getMessage(), Status.INTERNAL_SERVER_ERROR, UserException.class, userException);
        }
    }
    
    /*Achievement Details data*/
    
    /**
     * Save student achievement details.
     *
     * @param studentAchievementModel the student achievement model
     * @return the response
     * @throws StudentAchievementException the student achievement exception
     */
    @POST
    @Path("students/achievement-details/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response saveStudentAchievementDetails(final @Valid StudentAchievementModel studentAchievementModel)throws StudentAchievementException{
        StudentAchievementDto studentAchievementDto = null;
        final Map<String, String> response = new HashMap<String, String>();
        String achievementUuid = null;
        try{
        studentAchievementDto = beanMapper.map(studentAchievementModel, StudentAchievementDto.class);
            achievementUuid = userFacade.saveStudentAchievementDetails(studentAchievementDto);
            response.put("achievementUuid", achievementUuid);
            return Response.ok(response).build();
        } catch (final StudentAchievementException studentAchievementException) {
            throw Utility.buildResourceException(studentAchievementException.getErrorCode(), studentAchievementException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudentAchievementException.class, studentAchievementException);
        }
    }
    
   
   
    /**
     * Upload student achievement file.
     *
     * @param achievementUuid the achievement uuid
     * @param achievedFileModel the achieved file model
     * @return the response
     * @throws StudentAchievementException the student achievement exception
     */
    @POST
    @RequiresAuthentication
    @Path("/students/achievement-details/file/{achievementUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadStudentAchievementFile(@PathParam("achievementUuid") final String achievementUuid,
        @MultipartForm final StudentAchievementFileModel achievedFileModel) throws StudentAchievementException {
         if (achievedFileModel == null || StringUtils.isBlank(achievementUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
          final File sourceFile = achievedFileModel.getFile();
          final String filename = achievedFileModel.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final StudentAchievementFileDto achvData = new StudentAchievementFileDto();
          achvData.setName(filename);
          achvData.setFile(sourceFile);
          achvData.setFilePath(sourceFile.getPath());
        try
        {
            final String resAchievedUuid = userFacade.uploadStudentImageFile(achievementUuid, achvData);
             final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("resAchievedUuid", resAchievedUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
            
        } catch (final StudentAchievementException studentAchievementException) {
            throw Utility.buildResourceException(studentAchievementException.getErrorCode(), studentAchievementException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudentAchievementException.class, studentAchievementException);
        }
    }
    
    /**
     * Gets the student achievment list using ids.
     *
     * @param classId the class id
     * @param studentId the student id
     * @return the student achievment list using ids
     * @throws StudentAchievementException the student achievement exception
     */
    @GET
    @Path("/students/achievement-details/{classId}/{studentId}")   
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getStudentAchievmentListUsingIds(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("studentId") final String studentId)throws StudentAchievementException{
    
        List<StudentAchievementModel> stuAchvContent = null;
        List<StudentAchievementDto> stuAchvList = null;
        try{
            stuAchvList = userFacade.getStudentAchievmentListUsingIds(classId,studentId);
            stuAchvContent = DozerMapperUtil.mapCollection(beanMapper, stuAchvList, StudentAchievementModel.class);
            return Response.ok(stuAchvContent).build();
        } catch (final StudentAchievementException studentAchievementException) {
            throw Utility.buildResourceException(studentAchievementException.getErrorCode(), studentAchievementException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudentAchievementException.class, studentAchievementException);
        }
    }
    
    
    /**
     * Delete student achievement content data.
     *
     * @param achievementUuid the achievement uuid
     * @return the response
     * @throws StudentAchievementException the student achievement exception
     */
    @DELETE
    @Path("/students/achievement-details/delete/{achievementUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteStudentAchievementContentData(@NotBlank @PathParam("achievementUuid") final String achievementUuid)throws StudentAchievementException{
        final Map<String, String> response = new HashMap<String, String>();
        Boolean deleteStatus = false;
        try{
            deleteStatus = userFacade.deleteStudentAchievementContentData(achievementUuid);
            response.put("deleteStatus", deleteStatus.toString());
            return Response.ok(deleteStatus).build();
        } catch (final StudentAchievementException studentAchievementException) {
            throw Utility.buildResourceException(studentAchievementException.getErrorCode(), studentAchievementException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudentAchievementException.class, studentAchievementException);
        }
    }
    
    /**
     * Gets the student achievment list.
     *
     * @return the student achievment list
     * @throws StudentAchievementException the student achievement exception
     */
    @GET
    @Path("/students/achievement-details")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentAchievmentList()throws StudentAchievementException{
   
        List<StudentAchievementModel> stuAchvContent = null;
        List<StudentAchievementDto> stuAchvList = null;
        try{
            stuAchvList = userFacade.getStudentAchievmentList();
            stuAchvContent = DozerMapperUtil.mapCollection(beanMapper, stuAchvList, StudentAchievementModel.class);
            for(int i=0;i<stuAchvContent.size();i++){
                StudentAchievementModel studModel=stuAchvContent.get(i);
                File file=new File(studModel.getFilePath());
                final String mediaType = Utility.probeContentType(file.getAbsolutePath());
                final String encodedImageString = Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
                studModel.setImageLink("data:" + mediaType + ";base64," + encodedImageString);
            }
            return Response.ok(stuAchvContent).build();
            
        } catch (final StudentAchievementException studentAchievementException) {
            throw Utility.buildResourceException(studentAchievementException.getErrorCode(), studentAchievementException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudentAchievementException.class, studentAchievementException);
        }catch (IOException e) {
            if(e instanceof NoSuchFileException){
            throw Utility.buildResourceException(ErrorCode.RESOURCE_NOT_FOUND, "No Image File exists corresponding to the user", Status.NO_CONTENT, StudentAchievementException.class, e);
            }
            e.printStackTrace();
        }
        return Response.ok(stuAchvContent).build();
    }
    
}
