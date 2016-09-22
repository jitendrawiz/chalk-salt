package com.chalk.salt.api.exceptions.mapper;

import javax.validation.ConstraintViolation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.http.HttpStatus;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.chalk.salt.api.util.Utility;
import com.chalk.salt.common.dto.ErrorResponse;
import com.chalk.salt.common.util.ErrorCode;


@Provider
public class MethodValidationExceptionMapper implements ExceptionMapper<MethodConstraintViolationException> {

    /** The logger. */
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(final MethodConstraintViolationException exception) {
        logger.debug("Validation failed for one or more method parameters", exception);
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.PARAMETER_MISSING_INVALID);
        errorResponse.setMessage("Required parameters are invalid or missing");
        for (final ConstraintViolation<?> constraintViolation : exception.getConstraintViolations()) {
            final String[] errorInfo = Utility.parseErrorMessage(constraintViolation);
            errorResponse.addError(errorInfo[0].trim(), errorInfo[1].trim());
        }
        return Response.status(HttpStatus.SC_BAD_REQUEST).entity(errorResponse)
            .type(MediaType.APPLICATION_JSON_TYPE)
            .build();
    }

}