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

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;

import com.chalk.salt.api.model.DiscussionModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.discussion.DiscussionRoomFacade;

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
    
    public Response saveTopic(final @Valid DiscussionModel discussion)throws DiscussionException{
    	
    	DiscussionDto discussionDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String securUuid = null;
    	try{
    		discussionDetails = beanMapper.map(discussion, DiscussionDto.class);
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
    	
    	List<DiscussionModel> discussionTopics = null;
    	List<DiscussionDto> discussionTopicList = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopics();
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, DiscussionModel.class);
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
    	
    	DiscussionModel discussionTopic = null;
    	DiscussionDto discussionTopicDetails = null;
    	try{
    		discussionTopicDetails = discussionRoomFacade.getTopic(securUuid);
    		
    		discussionTopic = beanMapper.map(discussionTopicDetails, DiscussionModel.class);
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
    @Path("/discussion/topics/{securUuid}")
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
    	
    	List<DiscussionModel> discussionTopics = null;
    	List<DiscussionDto> discussionTopicList = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopics(classId,subjectId);
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, DiscussionModel.class);
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
    
    public Response updateTopic(final @Valid DiscussionModel discussion)throws DiscussionException{
    	
    	DiscussionDto discussionDetails = null;
    	try{
    		discussionDetails = beanMapper.map(discussion, DiscussionDto.class);
    		discussionRoomFacade.updateTopic(discussionDetails);    		
            return Response.ok().build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
}
