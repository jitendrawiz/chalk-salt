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
import javax.ws.rs.PUT;
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

import com.chalk.salt.api.model.AnswersModel;
import com.chalk.salt.api.model.DashBoardDataModel;
import com.chalk.salt.api.model.QuestionImageUploadModel;
import com.chalk.salt.api.model.QuestionModel;
import com.chalk.salt.api.model.ScheduleTestModel;
import com.chalk.salt.api.model.TestTypeModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.AnswersDto;
import com.chalk.salt.common.dto.DashBoardDataDto;
import com.chalk.salt.common.dto.QuestionDto;
import com.chalk.salt.common.dto.QuestionImageUploadDto;
import com.chalk.salt.common.dto.QuestionListDto;
import com.chalk.salt.common.dto.ResultContentDto;
import com.chalk.salt.common.dto.ResultMasterDto;
import com.chalk.salt.common.dto.ScheduleTestDto;
import com.chalk.salt.common.dto.TestTypeDto;
import com.chalk.salt.common.exceptions.ExamException;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.exam.ExamFacade;

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
    
    /**
     * Save question.
     *
     * @param questionModel the question model
     * @return the response
     * @throws ExamException the exam exception
     */
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
    		response.put("questionSecuruuid", questionSecurUuid);
            return Response.ok(response).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Gets the questions.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the questions
     * @throws ExamException the exam exception
     */
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
    
    /**
     * Update question details.
     *
     * @param questionDetail the question detail
     * @return the response
     * @throws ExamException the exam exception
     */
    @POST
    @Path("/exam/questions/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication  
    public Response updateQuestionDetails(QuestionModel questionDetail) throws ExamException{
    	QuestionDto question = null;
    	Map<String, String> response= new HashMap<String,String>();
    	try{
    		question = beanMapper.map(questionDetail, QuestionDto.class);
    		String securUuid = examFacade.updateQuestionDetails(question);
    		response.put("questionSecuruuid",securUuid);
    		return Response.ok(response).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Delete question.
     *
     * @param questionSecuruuid the question securuuid
     * @return the response
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/exam/questions/delete/{questionSecuruuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteQuestion(@NotBlank @PathParam("questionSecuruuid") final String questionSecuruuid) throws ExamException {

    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
        try {
        	deleteStatus = examFacade.deleteQuestion(questionSecuruuid);
            if (deleteStatus == null) {
                return Response.noContent().build();
            }
            response.put("deleteStatus", deleteStatus.toString());
            return Response.ok(response).build();

        } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Upload question image.
     *
     * @param securUuid the secur uuid
     * @param questionImageUploadRequest the question image upload request
     * @return the response
     * @throws ExamException the exam exception
     */
    @POST
    @RequiresAuthentication
    @Path("/exam/questions/update/photo/{securUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadQuestionImage(@PathParam("securUuid") final String securUuid,
        @MultipartForm final QuestionImageUploadModel questionImageUploadRequest) throws ExamException {
    	 if (questionImageUploadRequest == null || StringUtils.isBlank(securUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
    	  final File sourceFile = questionImageUploadRequest.getFile();
          final String filename = questionImageUploadRequest.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final QuestionImageUploadDto documentUploadData = new QuestionImageUploadDto();
          documentUploadData.setName(filename);
          documentUploadData.setFile(sourceFile);
          documentUploadData.setFilePath(sourceFile.getPath());
        try
        {
        	final String QuestionSecurUuid = examFacade.uploadQuestionImage(securUuid, documentUploadData);
        	 final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("QuestionSecuruuid", QuestionSecurUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
        	
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Gets the dash board data by subject.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the dash board data by subject
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/exam/data/{classId}/{subjectId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getDashBoardData(@NotBlank @PathParam("classId") final String classId,
    		@NotBlank @PathParam("subjectId") final String subjectId) throws ExamException{
    	DashBoardDataModel dashBoardDataModel=null;
    	DashBoardDataDto dashBoardDataDto=null;
    	try{
    		dashBoardDataDto = examFacade.getDashBoardData(classId, subjectId);
    		//Need to check this
    		//DozerMapperUtil.mapCollection(beanMapper, dashBoardDataDto, DashBoardDataModel.class);
    		//dashBoardDataModel=	beanMapper.map(dashBoardDataDto, DashBoardDataModel.class);
            return Response.ok(dashBoardDataDto).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    
    /**
     * Gets the test questions list.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param type the type
     * @param scheduleTestUuid the schedule test uuid
     * @return the test questions list
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/test/list/{classId}/{subjectId}/{type}/{scheduleTestUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getTestQuestionsList(@NotBlank @PathParam("classId") final String classId,
            @NotBlank @PathParam("subjectId") final String subjectId,
            @NotBlank @PathParam("type") final String type,
            @NotBlank @PathParam("scheduleTestUuid") final String scheduleTestUuid) throws ExamException{

        List<QuestionListDto> questionList = null;
        try{
            questionList = examFacade.getQuestionsUsingType(classId,subjectId,type,scheduleTestUuid);
            
            for(int i=0;i<questionList.size();i++){
            File file=new File(questionList.get(i).getQuestionImage());
            final String mediaType = Utility.probeContentType(file.getAbsolutePath());
            final String encodedImageString = Base64.encodeBase64String(Files.readAllBytes(file.toPath()));
            questionList.get(i).setQuestionImage("data:" + mediaType + ";base64," + encodedImageString);
            }         
        } catch (final ExamException examException) {
            throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
        }
        catch (IOException e) {
        if(e instanceof NoSuchFileException){
            throw Utility.buildResourceException(ErrorCode.RESOURCE_NOT_FOUND, "No Image File exists corresponding to the question", Status.NO_CONTENT, ExamException.class, e);
        }
        e.printStackTrace();
        }
        return Response.ok(questionList).build();
    }
    
    
    /**
     * Save answer details.
     *
     * @param answerModel the answer model
     * @return the response
     * @throws ExamException the exam exception
     */
    @PUT
    @Path("/test/answers/saveOnSubmit")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication  
    public Response saveAnswerDetails(AnswersModel answerModel) throws ExamException{
    	AnswersDto answer = null;
    	Map<String, String> response= new HashMap<String,String>();
    	try{
    		answer = beanMapper.map(answerModel, AnswersDto.class);
    		String securUuid = examFacade.saveAnswerDetails(answer);
    		response.put("answerSecurUuid",securUuid);
    		return Response.ok(response).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    
    /**
     * Gets the exam test type.
     *
     * @return the exam test type
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/exam/testType/details")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getExamTestType()throws ExamException{
    	List<TestTypeModel> testType = null;
    	List<TestTypeDto> testTypeList = null;
    	try{
    		testTypeList = examFacade.getTestTypeList();
    		testType = DozerMapperUtil.mapCollection(beanMapper, testTypeList, TestTypeModel.class);
            return Response.ok(testType).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * *******SCHEDULE TEST WORK BEGINS FROM HERE***************.
     *
     * @param scheduleTestModel the schedule test model
     * @return the response
     * @throws ExamException the exam exception
     */
    
    @POST
    @Path("/schedule-test/details/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response saveScheduleTestData(final @Valid ScheduleTestModel scheduleTestModel)throws ExamException{
    	ScheduleTestDto scheduleTestDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String scheduleTestUuid = null;
    	try{
    		scheduleTestDetails = beanMapper.map(scheduleTestModel, ScheduleTestDto.class);
    		scheduleTestUuid = examFacade.saveScheduleTestData(scheduleTestDetails);
    		response.put("scheduleTestUuid", scheduleTestUuid);
            return Response.ok(response).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Gets the schedule tests list using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the schedule tests list using ids
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/schedule-test/details/{classId}/{subjectId}")   
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getScheduleTestsListUsingIds(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws ExamException{
    	
    	List<ScheduleTestModel> scheduleTestContent = null;
    	List<ScheduleTestDto> scheduleTestContentList = null;
    	try{
    		scheduleTestContentList = examFacade.getScheduleTestsListUsingIds(classId,subjectId);
    		scheduleTestContent = DozerMapperUtil.mapCollection(beanMapper, scheduleTestContentList, ScheduleTestModel.class);
            return Response.ok(scheduleTestContent).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    
    /**
     * Gets the schedule test content by id.
     *
     * @param scheduleTestUuid the schedule test uuid
     * @return the schedule test content by id
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/schedule-test/details/edit/{scheduleTestUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getScheduleTestContentById(@NotBlank @PathParam("scheduleTestUuid") final String scheduleTestUuid)throws ExamException{
    	ScheduleTestModel scheduleTestContent = null;
    	ScheduleTestDto scheduleTestContentDetails = null;
    	try{
    		scheduleTestContentDetails = examFacade.getScheduleTestContentById(scheduleTestUuid);
    		scheduleTestContent = beanMapper.map(scheduleTestContentDetails, ScheduleTestModel.class);
   			return Response.ok(scheduleTestContent).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Update schedule test content details.
     *
     * @param scheduleTestContentModel the schedule test content model
     * @return the response
     * @throws ExamException the exam exception
     */
    @PUT
    @Path("/schedule-test/details/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response updateScheduleTestContentDetails(final @Valid ScheduleTestModel scheduleTestContentModel)throws ExamException{
    	ScheduleTestDto scheduleTestContentDetails = null;
    	try{
    		scheduleTestContentDetails = beanMapper.map(scheduleTestContentModel, ScheduleTestDto.class);
    		examFacade.updateScheduleTestContentDetails(scheduleTestContentDetails);    		
            return Response.ok().build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Delete schedule test content data.
     *
     * @param scheduleTestUuid the schedule test uuid
     * @return the response
     * @throws ExamException the exam exception
     */
    @DELETE
    @Path("/schedule-test/details/delete/{scheduleTestUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteScheduleTestContentData(@NotBlank @PathParam("scheduleTestUuid") final String scheduleTestUuid)throws ExamException{
    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
    	try{
    		deleteStatus = examFacade.deleteScheduleTestContentData(scheduleTestUuid);
    		response.put("deleteStatus", deleteStatus.toString());
    		return Response.ok(deleteStatus).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    /**
     * Gets the nofification list of scheduled tests.
     *
     * @param classId the class id
     * @param studentId the student id
     * @return the nofification list of scheduled tests
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/schedule-test/notification/list/{classId}/{studentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getNofificationListOfScheduledTests(@NotBlank @PathParam("classId") final String classId,
            @NotBlank @PathParam("studentId") final String studentId)throws ExamException{
    List<ScheduleTestModel> scheduleTestContent = null;
    List<ScheduleTestDto> scheduleTestContentList = null;
    try{
        scheduleTestContentList = examFacade.getScheduleTestsListUsingClassId(classId,studentId);
        scheduleTestContent = DozerMapperUtil.mapCollection(beanMapper, scheduleTestContentList, ScheduleTestModel.class);
        return Response.ok(scheduleTestContent).build();
    } catch (final ExamException examException) {
        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
    }
    }
    
    /**
     * Gets the results by class subject.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @param securUuid the secur uuid
     * @return the results by class subject
     * @throws ExamException the exam exception
     */
    @GET
    @Path("/test/results/list/{classId}/{subjectId}/{securUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getResultsByClassSubject(
    		@NotBlank @PathParam("classId") final String classId,
            @NotBlank @PathParam("subjectId") final String subjectId, 
            @NotBlank @PathParam("securUuid") final String securUuid)throws ExamException{
	    List<ResultMasterDto> resultList = null;
	    try{
	    	logger.info("getResultsByClassSubject service called.........");
	    	resultList = examFacade.getResultsByClassSubject(classId, subjectId, securUuid);
	        return Response.ok(resultList).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
    
    @GET
    @Path("/test/results/{classId}/{subjectId}/{securUuid}/{testUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getResultDetailsByTestUuid(
    		@NotBlank @PathParam("classId") final String classId,
            @NotBlank @PathParam("subjectId") final String subjectId, 
            @NotBlank @PathParam("securUuid") final String securUuid,
            @NotBlank @PathParam("testUuid") final String testUuid)throws ExamException{
	    List<ResultContentDto> resultDetails = null;
	    try{
	    	logger.info("GetResultDetailsByTestUuid service called.........");
	    	resultDetails = examFacade.getResultDetailsByTestUuid(classId, subjectId, securUuid, testUuid);
	        return Response.ok(resultDetails).build();
	    } catch (final ExamException examException) {
	        throw Utility.buildResourceException(examException.getErrorCode(), examException.getMessage(), Status.INTERNAL_SERVER_ERROR, ExamException.class, examException);
	    }
    }
}
