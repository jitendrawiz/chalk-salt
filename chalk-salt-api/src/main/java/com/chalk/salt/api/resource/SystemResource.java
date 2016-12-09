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

import com.chalk.dust.core.system.SystemFacade;
import com.chalk.salt.api.model.NotificationModel;
import com.chalk.salt.api.model.SystemEnquiryModel;
import com.chalk.salt.api.model.TestGroupModel;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.NotificationDto;
import com.chalk.salt.common.dto.StudentsDto;
import com.chalk.salt.common.dto.SubjectDto;
import com.chalk.salt.common.dto.SystemEnquiryDto;
import com.chalk.salt.common.dto.TestGroupDto;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;
import com.chalk.salt.common.util.DozerMapperUtil;

// TODO: Auto-generated Javadoc
/**
 * The Class SystemResource.
 */
@Path(ApiConstants.API_PRIVATE_BASEPATH)
public class SystemResource extends AbstractResource {

	/** The logger. */
	@Inject
	@AppLogger
	protected Logger logger;

	/** The bean mapper. */
	@Inject
	@BeanMapper
	protected Mapper beanMapper;

	/** The system facade. */
	@Inject
	private SystemFacade systemFacade;

	/**
	 * Gets the user classes list.
	 *
	 * @return the user classes list
	 * @throws SystemException
	 *             the system exception
	 */
	@GET
	@Path("/classes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserClassesList() throws SystemException {
		List<UserClassDto> classes = null;
		try {
			classes = systemFacade.getUserClassesList();
			if (CollectionUtils.isEmpty(classes)) {
				return Response.noContent().build();
			}
			return Response.ok(classes).build();
		} catch (final SystemException systemException) {
			throw Utility.buildResourceException(
					systemException.getErrorCode(),
					systemException.getMessage(), Status.INTERNAL_SERVER_ERROR,
					SystemException.class, systemException);
		}
	}
	
	/**
	 * Gets the subjects list by class id.
	 *
	 * @param classId the class id
	 * @return the subjects list by class id
	 * @throws SystemException the system exception
	 */
	@GET
	@Path("/subjects/{classId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSubjectsListByClassId(@NotBlank @PathParam("classId") final String classId) throws SystemException {
		List<SubjectDto> subjects = null;
		try {
			subjects = systemFacade.getSubjectsListByClassId(classId);
			if (CollectionUtils.isEmpty(subjects)) {
				return Response.noContent().build();
			}
			return Response.ok(subjects).build();
		} catch (final SystemException systemException) {
			throw Utility.buildResourceException(
					systemException.getErrorCode(),
					systemException.getMessage(), Status.INTERNAL_SERVER_ERROR,
					SystemException.class, systemException);
		}
	}
	
	
	/**
	 * System enquiry.
	 *
	 * @param systemEnquiryModel the system enquiry model
	 * @return the response
	 * @throws SystemException the system exception
	 */
	@POST
    @Path("/enquiry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response systemEnquiry(final @Valid SystemEnquiryModel systemEnquiryModel)throws SystemException{

        final Map<String, String> response = new HashMap<String, String>();
        Boolean status = false;
        try {
            final SystemEnquiryDto systemEnquiryDetails = beanMapper.map(systemEnquiryModel, SystemEnquiryDto.class);
            status = systemFacade.systemEnquiry(systemEnquiryDetails);
            response.put("status", status.toString());
            return Response.ok(response).build();

        } catch (final SystemException systemException) {
            throw Utility.buildResourceException(systemException.getErrorCode(), systemException.getMessage(), Status.INTERNAL_SERVER_ERROR, SystemException.class, systemException);
        }
    }
	
	/**
	 * Gets the students list by class id.
	 *
	 * @param classId the class id
	 * @return the students list by class id
	 * @throws SystemException the system exception
	 */
	@GET
    @Path("/students/{classId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentsListByClassId(@NotBlank @PathParam("classId") final String classId) throws SystemException {
        List<StudentsDto> students = null;
        try {
            students = systemFacade.getStudentsListByClassId(classId);
            if (CollectionUtils.isEmpty(students)) {
                return Response.noContent().build();
            }
            return Response.ok(students).build();
        } catch (final SystemException systemException) {
            throw Utility.buildResourceException(
                    systemException.getErrorCode(),
                    systemException.getMessage(), Status.INTERNAL_SERVER_ERROR,
                    SystemException.class, systemException);
        }
    }
	
	@POST
    @Path("/notification-student/details/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveNotification(final @Valid NotificationModel notificationModel)throws SystemException{

        final Map<String, String> response = new HashMap<String, String>();
        Long notificationUuid = null;
        try {
            final NotificationDto notificationdto = beanMapper.map(notificationModel, NotificationDto.class);
            notificationUuid = systemFacade.saveNotification(notificationdto);
            response.put("status", notificationUuid.toString());
            return Response.ok(response).build();

        } catch (final SystemException systemException) {
            throw Utility.buildResourceException(systemException.getErrorCode(), systemException.getMessage(), Status.INTERNAL_SERVER_ERROR, SystemException.class, systemException);
        }
    }
	
	 @GET
	    @Path("/notification-student/details/list")   
	    @Produces(MediaType.APPLICATION_JSON)
	    @RequiresAuthentication    
	    public Response getStudentNotificationList()throws SystemException{
	    
	        List<NotificationModel> notificationContent = null;
	        List<NotificationDto> notificationList = null;
	        try{
	            notificationList = systemFacade.getStudentNotificationList();
	            notificationContent = DozerMapperUtil.mapCollection(beanMapper, notificationList, NotificationModel.class);
	            return Response.ok(notificationContent).build();
	        } catch (final SystemException systemException) {
            throw Utility.buildResourceException(systemException.getErrorCode(), systemException.getMessage(), Status.INTERNAL_SERVER_ERROR, SystemException.class, systemException);
	        }
	    }
	 
	 
	 
	    @POST
	    @Path("/testGroupAdmin/details/save")
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Produces(MediaType.APPLICATION_JSON)
	    public Response saveTestGroup(final @Valid TestGroupModel testGroupModel)throws SystemException{

	        final Map<String, String> response = new HashMap<String, String>();
	        Long testGroupUuid = null;
	        try {
	            final TestGroupDto testGroupDto = beanMapper.map(testGroupModel, TestGroupDto.class);
	            testGroupUuid = systemFacade.saveTestGroup(testGroupDto);
	            response.put("status", testGroupUuid.toString());
	            return Response.ok(response).build();

	        } catch (final SystemException systemException) {
	            throw Utility.buildResourceException(systemException.getErrorCode(), systemException.getMessage(), Status.INTERNAL_SERVER_ERROR, SystemException.class, systemException);
	        }
	    }	 
	    
	    
	    
	    @GET
        @Path("/testGroup/details/list")   
        @Produces(MediaType.APPLICATION_JSON)
        @RequiresAuthentication    
        public Response getTestGroupList()throws SystemException{
        
            List<TestGroupModel> groupModel = null;
            List<TestGroupDto> groupList = null;
            try{
            groupList = systemFacade.getTestGroupList();
                groupModel = DozerMapperUtil.mapCollection(beanMapper, groupList, TestGroupModel.class);
                return Response.ok(groupModel).build();
            } catch (final SystemException systemException) {
            throw Utility.buildResourceException(systemException.getErrorCode(), systemException.getMessage(), Status.INTERNAL_SERVER_ERROR, SystemException.class, systemException);
            }
        }
       
	
}
