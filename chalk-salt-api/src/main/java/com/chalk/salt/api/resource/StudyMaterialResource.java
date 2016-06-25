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

import com.chalk.salt.api.model.VideoContentModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.VideoContentDto;
import com.chalk.salt.common.exceptions.StudyMaterialException;
import com.chalk.salt.common.util.DozerMapperUtil;
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
    
}
