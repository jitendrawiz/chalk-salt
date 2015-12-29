package com.chalk.salt.api.resource;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;

import com.chalk.dust.core.system.SystemFacade;
import com.chalk.salt.api.util.ApiConstants;
import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.cdi.annotations.AppLogger;
import com.chalk.salt.common.cdi.annotations.BeanMapper;
import com.chalk.salt.common.dto.UserClassDto;
import com.chalk.salt.common.exceptions.SystemException;

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
		List<Map<String, String>> userList = null;

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

}
