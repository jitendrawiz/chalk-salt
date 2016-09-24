package com.chalk.salt.api.resource;

import java.io.File;
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

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.dozer.Mapper;
import org.hibernate.validator.constraints.NotBlank;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;
import org.slf4j.Logger;

import com.chalk.salt.api.model.NotesContentModel;
import com.chalk.salt.api.model.NotesFileModel;
import com.chalk.salt.api.model.NotesModel;
import com.chalk.salt.api.model.VideoContentModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.NotesContentDto;
import com.chalk.salt.common.dto.NotesDto;
import com.chalk.salt.common.dto.NotesFileDto;
import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;
import com.chalk.salt.common.util.DozerMapperUtil;
import com.chalk.salt.common.util.ErrorCode;
import com.chalk.salt.core.studymaterial.StudyMaterialFacade;

/**
 * The Class StudyMaterialResource.
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class StudyMaterialResource {
	
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
    private StudyMaterialFacade studyMaterialFacade;
    
    /**
     * Save vedio data.
     *
     * @param videoContentModel the video content model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @POST
    @Path("/video-master/details/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response saveVedioData(final @Valid VideoContentModel videoContentModel)throws StudyMaterialException{
    	VideoContentDto videoContentDetails = null;
    	final Map<String, String> response = new HashMap<String, String>();
    	String videoSecurUuid = null;
    	try{
    		videoContentDetails = beanMapper.map(videoContentModel, VideoContentDto.class);
    		videoSecurUuid = studyMaterialFacade.saveVedioData(videoContentDetails);
    		response.put("videoSecurUuid", videoSecurUuid);
            return Response.ok(response).build();
	    } catch (final StudyMaterialException studyMaterialException) {
	        throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
	    }
    }
    
    /**
     * Gets the videos list using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the videos list using ids
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/video-master/details/{classId}/{subjectId}")   
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getVideosListUsingIds(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws StudyMaterialException{
    	
    	List<VideoContentModel> videoContent = null;
    	List<VideoContentDto> videoContentList = null;
    	try{
    		videoContentList = studyMaterialFacade.getVideosListUsingIds(classId,subjectId);
    		videoContent = DozerMapperUtil.mapCollection(beanMapper, videoContentList, VideoContentModel.class);
            return Response.ok(videoContent).build();
	    } catch (final StudyMaterialException studyMaterialException) {
	        throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
	    }
    }
    
    /**
     * Gets the video content by id.
     *
     * @param videoUuid the video uuid
     * @return the video content by id
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/video-master/details/edit/{videoUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getVideoContentById(@NotBlank @PathParam("videoUuid") final String videoUuid)throws StudyMaterialException{
    	VideoContentModel videoContent = null;
        VideoContentDto videoContentDetails = null;
    	try{
    		videoContentDetails = studyMaterialFacade.getVideoContentById(videoUuid);
    		videoContent = beanMapper.map(videoContentDetails, VideoContentModel.class);
   			return Response.ok(videoContent).build();
	    } catch (final StudyMaterialException StudyMaterialException) {
	        throw Utility.buildResourceException(StudyMaterialException.getErrorCode(), StudyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, StudyMaterialException);
	    }
    }
    

    /**
     * Update video content details.
     *
     * @param videoContentModel the video content model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @POST
    @Path("/video-master/details/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response updateVideoContentDetails(final @Valid VideoContentModel videoContentModel)throws StudyMaterialException{
    	VideoContentDto videoContentDetails = null;
    	try{
    		videoContentDetails = beanMapper.map(videoContentModel, VideoContentDto.class);
    		studyMaterialFacade.updateVideoContentDetails(videoContentDetails);    		
            return Response.ok().build();
	    } catch (final StudyMaterialException studyMaterialException) {
	        throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
	    }
    }

    /**
     * Delete video content data.
     *
     * @param videoUuid the video uuid
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/video-master/details/delete/{videoUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteVideoContentData(@NotBlank @PathParam("videoUuid") final String videoUuid)throws StudyMaterialException{
    	final Map<String, String> response = new HashMap<String, String>();
    	Boolean deleteStatus = false;
    	try{
    		deleteStatus = studyMaterialFacade.deleteVideoContentData(videoUuid);
    		response.put("deleteStatus", deleteStatus.toString());
    		return Response.ok(deleteStatus).build();
	    } catch (final StudyMaterialException studyMaterialException) {
	        throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
	    }
    }
    
    /**
     * **********Notes code starts from here***************.
     *
     * @param notesContentModel the notes content model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    
    @POST
    @Path("/notes-master/details/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response saveNotes(final @Valid NotesContentModel notesContentModel)throws StudyMaterialException{
        NotesContentDto notesContentDto = null;
        final Map<String, String> response = new HashMap<String, String>();
        String notesUuid = null;
        try{
            notesContentDto = beanMapper.map(notesContentModel, NotesContentDto.class);
            notesUuid = studyMaterialFacade.saveNotes(notesContentDto);
            response.put("notesUuid", notesUuid);
            return Response.ok(response).build();
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
   
    /**
     * Upload notes file.
     *
     * @param notesUuid the notes uuid
     * @param notesFileModel the notes file model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @POST
    @RequiresAuthentication
    @Path("/notes-master/details/save/notes/file/{notesUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadNotesFile(@PathParam("notesUuid") final String notesUuid,
        @MultipartForm final NotesFileModel notesFileModel) throws StudyMaterialException {
         if (notesFileModel == null || StringUtils.isBlank(notesUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
          final File sourceFile = notesFileModel.getFile();
          final String filename = notesFileModel.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final NotesFileDto notesFileData = new NotesFileDto();
          notesFileData.setName(filename);
          notesFileData.setFile(sourceFile);
          notesFileData.setFilePath(sourceFile.getPath());
        try
        {
            final String resNotesUuid = studyMaterialFacade.uploadNotesFile(notesUuid, notesFileData);
             final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("notesUuid", resNotesUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
            
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
    
    /**
     * Gets the notes list using ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the notes list using ids
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/notes-master/details/{classId}/{subjectId}")   
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication    
    public Response getNotesListUsingIds(@NotBlank @PathParam("classId") final String classId,@NotBlank @PathParam("subjectId") final String subjectId)throws StudyMaterialException{
        
        List<NotesContentModel> notesContent = null;
        List<NotesContentDto> notesContentList = null;
        try{
            notesContentList = studyMaterialFacade.getNotesListUsingIds(classId,subjectId);
            notesContent = DozerMapperUtil.mapCollection(beanMapper, notesContentList, NotesContentModel.class);
            return Response.ok(notesContent).build();
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
    
   
    /**
     * Gets the notes content by id.
     *
     * @param notesUuid the notes uuid
     * @return the notes content by id
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/notes-master/details/edit/{notesUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response getNotesContentById(@NotBlank @PathParam("notesUuid") final String notesUuid)throws StudyMaterialException{
        NotesContentModel notesContent = null;
        NotesContentDto notesContentDetails = null;
        try{
            notesContentDetails = studyMaterialFacade.getNotesContentById(notesUuid);
            notesContent = beanMapper.map(notesContentDetails, NotesContentModel.class);
            return Response.ok(notesContent).build();
        } catch (final StudyMaterialException StudyMaterialException) {
            throw Utility.buildResourceException(StudyMaterialException.getErrorCode(), StudyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, StudyMaterialException);
        }
    }
    
    /**
     * Update notes content details.
     *
     * @param notesContentModel the notes content model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @POST
    @Path("/notes-master/details/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response updateNotesContentDetails(final @Valid NotesContentModel notesContentModel)throws StudyMaterialException{
        NotesContentDto notesContentDetails = null;
        try{
            notesContentDetails = beanMapper.map(notesContentModel, NotesContentDto.class);
            studyMaterialFacade.updateNotesContentDetails(notesContentDetails);         
            return Response.ok().build();
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
    /**
     * Update andupload notes file.
     *
     * @param notesUuid the notes uuid
     * @param notesFileModel the notes file model
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @POST
    @RequiresAuthentication
    @Path("/notes-master/details/update/notes/file/{notesUuid}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAnduploadNotesFile(@PathParam("notesUuid") final String notesUuid,
        @MultipartForm final NotesFileModel notesFileModel) throws StudyMaterialException {
         if (notesFileModel == null || StringUtils.isBlank(notesUuid)) {
             return Response
                 .status(Status.BAD_REQUEST)
                 .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                     "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
         }
         
          final File sourceFile = notesFileModel.getFile();
          final String filename = notesFileModel.getName();
          if (sourceFile == null || StringUtils.isBlank(filename)) {
              return Response
                  .status(Status.BAD_REQUEST)
                  .entity(Utility.buildErrorResponse(ErrorCode.PARAMETER_MISSING_INVALID,
                      "Required parameters are invalid or missing")).type(MediaType.APPLICATION_JSON).build();
          }
          
          final NotesFileDto notesFileData = new NotesFileDto();
          notesFileData.setName(filename);
          notesFileData.setFile(sourceFile);
          notesFileData.setFilePath(sourceFile.getPath());
        try
        {
            final String resNotesUuid = studyMaterialFacade.updateAnduploadNotesFile(notesUuid, notesFileData);
             final Map<String, String> responseMap = new HashMap<String, String>();
             responseMap.put("notesUuid", resNotesUuid);
             return Response.ok(responseMap, MediaType.APPLICATION_JSON).build();
            
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
    
    /**
     * Delete notes content data.
     *
     * @param notesUuid the notes uuid
     * @return the response
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/notes-master/details/delete/{notesUuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @RequiresAuthentication
    public Response deleteNotesContentData(@NotBlank @PathParam("notesUuid") final String notesUuid)throws StudyMaterialException{
        final Map<String, String> response = new HashMap<String, String>();
        Boolean deleteStatus = false;
        try{
            deleteStatus = studyMaterialFacade.deleteNotesContentData(notesUuid);
            response.put("deleteStatus", deleteStatus.toString());
            return Response.ok(deleteStatus).build();
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
    
    /**
     * Gets the notes list using class ids.
     *
     * @param classId the class id
     * @param subjectId the subject id
     * @return the notes list using class ids
     * @throws StudyMaterialException the study material exception
     */
    @GET
    @Path("/notes-master/details/{classId}")   
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesListUsingClassIds(@NotBlank @PathParam("classId") final String classId)throws StudyMaterialException{
        
        List<NotesModel> notesContent = null;
        List<NotesDto> notesContentList = null;
        try{
            notesContentList = studyMaterialFacade.getNotesListUsingClassIds(classId);
            notesContent = DozerMapperUtil.mapCollection(beanMapper, notesContentList, NotesModel.class);
            return Response.ok(notesContent).build();
        } catch (final StudyMaterialException studyMaterialException) {
            throw Utility.buildResourceException(studyMaterialException.getErrorCode(), studyMaterialException.getMessage(), Status.INTERNAL_SERVER_ERROR, StudyMaterialException.class, studyMaterialException);
        }
    }
}
