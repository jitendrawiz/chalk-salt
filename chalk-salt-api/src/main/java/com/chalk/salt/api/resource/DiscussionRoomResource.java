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
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.salt.api.model.DiscussionModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.util.DozerMapperUtil;
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
    @Path("/discussion/topic")
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
    
    @GET
    @Path("/discussion/topics")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response getTopics()throws DiscussionException{
    	
    	List<DiscussionModel> discussionTopics = null;
    	List<DiscussionDto> discussionTopicList = null;
    	String securUuid = null;
    	try{
    		discussionTopicList = discussionRoomFacade.getTopics();
    		discussionTopics = DozerMapperUtil.mapCollection(beanMapper, discussionTopicList, DiscussionModel.class);
            return Response.ok(discussionTopics).build();
	    } catch (final DiscussionException discussionException) {
	        throw Utility.buildResourceException(discussionException.getErrorCode(), discussionException.getMessage(), Status.INTERNAL_SERVER_ERROR, DiscussionException.class, discussionException);
	    }
    }
}
