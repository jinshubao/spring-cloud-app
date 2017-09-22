package com.jean.auto.global;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@ControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHelper {

    protected Logger logger = LoggerFactory.getLogger(GlobalExceptionHelper.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ApiSimpleResultHelper<Object> exception(Exception exception, HttpServletResponse response) {
        logger.error("出错了", exception);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SYSTEM_ERROR, exception.getMessage());
    }

    @ExceptionHandler(RestClientException.class)
    @ResponseBody
    public ApiSimpleResultHelper<Object> restClientException(RestClientException exception) {
        logger.error("出错了", exception);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SYSTEM_ERROR, exception.getMessage());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class})
    @ResponseBody
    public ApiSimpleResultHelper<Object> constraintViolationException(Exception exception, HttpServletResponse response) {
        List<String> message = new ArrayList<>();
        if (exception instanceof ConstraintViolationException) {
            Set<ConstraintViolation<?>> constraintViolations = ((ConstraintViolationException) exception).getConstraintViolations();
            if (constraintViolations != null) {
                for (ConstraintViolation violation : constraintViolations) {
                    message.add(violation.getMessage());
                }
            }
        } else if (exception instanceof MethodArgumentNotValidException) {
            //TODO
        }
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, message);

    }

}
