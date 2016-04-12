package com.chalk.salt.api.resource;


import java.io.File;
import java.nio.file.Files;
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

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;

import com.chalk.salt.api.model.DiscussionCommentModel;
import com.chalk.salt.api.model.DiscussionTopicModel;
import com.chalk.salt.api.model.DiscussionTopicRequestModel;
import com.chalk.salt.api.model.TopicDetailsModel;
import com.chalk.salt.api.model.TopicStatisticsModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionCommentDto;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.DiscussionTopicRequestDto;
import com.chalk.salt.common.dto.TopicDetailsDto;
import com.chalk.salt.common.dto.TopicStatisticsDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.discussion.DiscussionRoomFacade;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class DiscussionRoomResource.
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class DiscussionRoomResource extends AbstractResource {
	/** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;

    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;
    
    /** The discussion room facade. */
    @Inject
    private DiscussionRoomFacade discussionRoomFacade;
    
    /** The user facade. */
    @Inject
    private UserFacade userFacade;
    
    /**
     * Save topic.
     *
     * @param discussion the discussion
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @POST
    @Path("/discussion/topics")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response saveTopic(final @Valid DiscussionTopicModel discussion)throws DiscussionException{
    	
    	DiscussionTopicDto discussionDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String securUuid = null;
    	try{
    		discussionDetails = beanMapper.map(discussion, DiscussionTopicDto.class);
    		securUuid = discussionRoomFacade.saveTopic(discussionDetails);
    		response.put("securUuid", securUuid);
            return Response.ok(response).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topics.
     *
     * @return the topics
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response getTopics()throws DiscussionException{
    	
    	List<DiscussionTopicModel> discussionTopics = null;
    	List<DiscussionTopicDto> discussionTopicList = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopics();
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, DiscussionTopicModel.class);
            return Response.ok(discussionTopics).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topic.
     *
     * @param securUuid the secur uuid
     * @return the topic
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/edittopic/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response getTopic(@NotBlank @PathParam("securUuid") final String securUuid)throws DiscussionException{
    	
    	DiscussionTopicModel discussionTopic = null;
    	DiscussionTopicDto discussionTopicDetails = null;
    	try{
    		discussionTopicDetails = discussionRoomFacade.getTopic(securUuid);
    		
    		discussionTopic = beanMapper.map(discussionTopicDetails, DiscussionTopicModel.class);
    		if(discussionTopic!=null)
    			return Response.ok(discussionTopic).build();
    		else
    			throw new DiscussionException(ErrorCode.FAIL_TO_SAVE_DISCUSSION_TOPIC, "Fail to fetch discussion topic.");
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Delete topic.
     *
     * @param securUuid the secur uuid
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/deletetopic/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response deleteTopic(@NotBlank @PathParam("securUuid") final String securUuid)throws DiscussionException{
    	
    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
    	try{
    		deleteStatus = discussionRoomFacade.deleteTopic(securUuid);
    		response.put("deleteStatus", deleteStatus.toString());
    		return Response.ok(deleteStatus).build();
    		
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topics using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the topics using ids
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics/{classId}/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getTopicsUsingIds(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws DiscussionException{
    	
    	List<DiscussionTopicModel> discussionTopics = null;
    	List<DiscussionTopicDto> discussionTopicList = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopics(classId,subjectId);
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, DiscussionTopicModel.class);
            return Response.ok(discussionTopics).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }

    /**
     * Update topic.
     *
     * @param discussion the discussion
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @POST
    @Path("/discussion/topics/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response updateTopic(final @Valid DiscussionTopicModel discussion)throws DiscussionException{
    	
    	DiscussionTopicDto discussionDetails = null;
    	try{
    		discussionDetails = beanMapper.map(discussion, DiscussionTopicDto.class);
    		discussionRoomFacade.updateTopic(discussionDetails);    		
            return Response.ok().build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topics count.
     *
     * @param classId the class id
     * @return the topics count
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics/subjects/{studentClassId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response getTopicsCount(@NotBlank @PathParam("studentClassId") final String classId)throws DiscussionException{
    	
    	List<TopicStatisticsModel> topicCounts=null;
    	List<TopicStatisticsDto> topicsStatistics=null;
    	try{
    		topicsStatistics = discussionRoomFacade.getTopicsCount(classId);
    		topicCounts = DozerMapperUtil.mapCollection(beanMapper, topicsStatistics, TopicStatisticsModel.class);
    		return Response.ok(topicCounts).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topic details.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the topic details
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics/statistics/{classId}/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getTopicDetails(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws DiscussionException{
    	
    	List<TopicDetailsModel> discussionTopics = null;
    	List<TopicDetailsDto> discussionTopicList = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopicDetails(classId,subjectId);
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, TopicDetailsModel.class);
            return Response.ok(discussionTopics).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    
    /**
     * Gets the topic comment details.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param topicId the topic id
     * @return the topic comment details
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/comments/statistics/{classId}/{subjectId}/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getTopicCommentDetails(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId,
    		@NotBlank @PathParam("topicId") final String topicId)throws DiscussionException{
    	
    	List<DiscussionCommentModel> discussionComments = null;
    	List<DiscussionCommentDto> discussionCommentsList = null;
    	try{
    		discussionCommentsList = discussionRoomFacade.getTopicCommentDetails(classId,subjectId,topicId);
    		discussionComments = DozerMapperUtil.mapCollection(beanMapper, discussionCommentsList, DiscussionCommentModel.class);
            return Response.ok(discussionComments).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    
    
    
    /**
     * Save comments.
     *
     * @param discussion the discussion
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @POST
    @Path("/discussion/topics/comments")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response saveComments(final @Valid DiscussionCommentModel discussion)throws DiscussionException{
    	
    	DiscussionCommentDto discussionComment = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String commentUuid = null;
    	try{
    		discussionComment = beanMapper.map(discussion, DiscussionCommentDto.class);
    		commentUuid = discussionRoomFacade.saveComments(discussionComment);
    		response.put("commentUuid", commentUuid);
            return Response.ok(response).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the single topic details.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param topicId the topic id
     * @return the single topic details
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topic/{classId}/{subjectId}/{topicId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getSingleTopicDetails(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId,
    		@PathParam("topicId") final String topicId)throws DiscussionException{
    	final Map<String, Object> response = new HashMap<String, Object>();
    	TopicDetailsModel discussionTopics = null;
    	TopicDetailsDto discussionTopic = null;
    	String topicImage=null;
    	try{
    		discussionTopic = discussionRoomFacade.getSingleTopicDetails(classId,subjectId,topicId);
    		try{
    		topicImage=userFacade.getTopicImageLink(discussionTopic.getSecurUuid());
    		if (topicImage != null) {
    		File file=new File(topicImage);
    	    final String mediaType = Utility.probeContentType(file.getAbsolutePath());
    	    final String encodedImageString = Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
    	    response.put("photolink", "data:" + mediaType + ";base64," + encodedImageString);
            }else{
        	response.put("photolink", "noImage");
            }
            }catch(Exception e){
    			e.printStackTrace();
    		}
    		discussionTopics = beanMapper.map(discussionTopic, TopicDetailsModel.class);
    		response.put("discussionTopics", discussionTopics);
            return Response.ok(response).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }

    /**
     * Gets the subject name.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the subject name
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/subject/{classId}/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getSubjectName(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws DiscussionException{
    	
    	String subjectName = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	try{
    		subjectName = discussionRoomFacade.getSubjectName(classId,subjectId);
    		response.put("subjectName", subjectName);
            return Response.ok(response).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }

    /**
     * Gets the topic comment info.
     *
     * @param commentUuid the comment uuid
     * @return the topic comment info
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/editComment/{commentUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getTopicCommentInfo(@NotBlank @PathParam("commentUuid") final String commentUuid)throws DiscussionException{
    	
    	DiscussionCommentModel discussionCommentModel = null;
    	DiscussionCommentDto discussionCommentDto = null;
    	try{
    		discussionCommentDto = discussionRoomFacade.getTopicCommentInfo(commentUuid);
    		discussionCommentModel = beanMapper.map(discussionCommentDto, DiscussionCommentModel.class);
            return Response.ok(discussionCommentModel).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Update comment.
     *
     * @param discussionComment the discussion comment
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @POST
    @Path("/discussion/comment/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response updateComment(final @Valid DiscussionCommentModel discussionComment)throws DiscussionException{
    	DiscussionCommentDto discussionCommentDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String commentSecurUuid = null;
    	try{
    		discussionCommentDetails = beanMapper.map(discussionComment, DiscussionCommentDto.class);
    		commentSecurUuid = discussionRoomFacade.updateComment(discussionCommentDetails);
    		response.put("securUuid", commentSecurUuid);
            return Response.ok(response).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Delete comment.
     *
     * @param commentUuid the comment uuid
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/deletecomment/{commentUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response deleteComment(@NotBlank @PathParam("commentUuid") final String commentUuid)throws DiscussionException{
    	
    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
    	try{
    		deleteStatus = discussionRoomFacade.deleteComment(commentUuid);
    		response.put("deleteStatus", deleteStatus.toString());
    		return Response.ok(deleteStatus).build();
    		
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Approve topic requests.
     *
     * @param requestSecurUuid the request secur uuid
     * @return the response
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics/requests/{requestSecurUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response approveTopicRequests(@NotBlank @PathParam("requestSecurUuid") final String requestSecurUuid)throws DiscussionException{
    	try{
    		discussionRoomFacade.approveTopicRequests(requestSecurUuid);
    		return Response.ok().build();
    		
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
    
    /**
     * Gets the topic requests.
     *
     * @return the topic requests
     * @throws DiscussionException the discussion exception
     */
    @GET
    @Path("/discussion/topics/requests")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response getTopicRequests()throws DiscussionException{
    	List<DiscussionTopicRequestModel> topicRequestList;
    	List<DiscussionTopicRequestDto> topicRequests;
    	try{
    		topicRequests = discussionRoomFacade.getTopicRequests();
    		topicRequestList = DozerMapperUtil.mapCollection(beanMapper, topicRequests, DiscussionTopicRequestModel.class);
    		return Response.ok(topicRequestList).build();
    		
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
}
