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

import com.chalk.salt.api.model.DiscussionTopicModel;
import com.chalk.salt.api.model.QuestionModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.DiscussionTopicDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.exceptions.DiscussionException;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.core.exam.ExamFacade;
import com.chalk.salt.core.user.UserFacade;

/**
 * The Class ExamResource.
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class ExamResource extends AbstractResource {
	/** The logger. */
    @Inject
    @AppLogger
    protected Logger logger;

    /** The bean mapper. */
    @Inject
    @BeanMapper
    protected Mapper beanMapper;
    
    /** The exam facade. */
    @Inject
    private ExamFacade examFacade;
    
    @POST
    @Path("/exam/questions/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    
    public Response saveQuestion(final @Valid QuestionModel questionModel)throws ExamException{
    	
    	QuestionDto questionDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String questionSecurUuid = null;
    	try{
    		questionDetails = beanMapper.map(questionModel, QuestionDto.class);
    		questionSecurUuid = examFacade.saveQuestion(questionDetails);
    		response.put("questionSecurUuid", questionSecurUuid);
            return Response.ok(response).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    @GET
    @Path("/exam/questions/{classId}/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getQuestions(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws ExamException{
    	
    	List<QuestionModel> questions = null;
    	List<QuestionDto> questionList = null;
    	try{
    		questionList = examFacade.getQuestions(classId,subjectId);
    		questions = DozerMapperUtil.mapCollection(beanMapper, questionList, QuestionModel.class);
            return Response.ok(questions).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    @GET
    @Path("/exam/questions/update")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response updateQuestionDetails(QuestionModel questionDetail) throws ExamException{
    	QuestionDto question = null;
    	try{
    		beanMapper.map(questionDetail, question);
    		String securUuid = examFacade.updateQuestionDetails(question);
    		return Response.ok(questionDetail).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
}
