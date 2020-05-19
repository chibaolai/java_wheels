package com.bolly.springboot.controller;

import com.bolly.springboot.exception.InValidTimeWindowException;
import com.bolly.springboot.exception.InValidTokenException;
import com.bolly.support.dto.Result;
import com.bolly.support.dto.Status;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/**
 * WebApiControllerAdvice
 */
@ControllerAdvice
public class WebApiControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApiControllerAdvice.class);

    @Autowired
    private MessageSource messageSource;

    /**
     * 全局错误处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Void> defaultErrorHandler(Exception e) {
        LOGGER.error(e.getMessage(), e);
        return Result.status(Status.SERVER_ERROR);
    }

    /**
     * StatusException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {Status.StatusException.class})
    @ResponseBody
    public Result<String> handleException(Status.StatusException ex) {
        LOGGER.error(ex.getMessage(), ex);
        Result<String> result = Result.status(ex.getStatus());
        if (StringUtils.isNotBlank(ex.getMessage())) {
            result.setMsg(ex.getMessage());
        }
        return result;
    }

    /**
     * 数据绑定-ValidationException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ValidationException.class})
    @ResponseBody
    public Result<String> handleException(ValidationException ex) {
        LOGGER.warn(ex.getMessage(), ex);
        String message = ex.getMessage();
        return Result.status(Status.SERVER_VALIDATION_ERROR, message);
    }

    /**
     * 数据绑定-BindException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {BindException.class})
    @ResponseBody
    public Result<String> handleException(BindException ex) {
        LOGGER.warn(ex.getMessage(), ex);
        String message = extraceMessage(ex);
        return Result.status(Status.SERVER_VALIDATION_ERROR, message);
    }

    /**
     * 数据绑定-BindException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseBody
    public Result<String> handleException(MethodArgumentNotValidException ex) {
        LOGGER.warn(ex.getMessage(), ex);
        String message = extraceMessage(ex.getBindingResult());
        return Result.status(Status.SERVER_VALIDATION_ERROR, message);
    }

    private String extraceMessage(BindingResult bindingResult) {
        // 抽取错误
        StringBuilder sb = new StringBuilder();
        List<ObjectError> globalErrors = bindingResult.getGlobalErrors();
        for (ObjectError objectError : globalErrors) {
            sb.append(messageSource.getMessage(objectError, Locale.CHINA));
            sb.append(StringUtils.SPACE);
        }
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            String msg = messageSource.getMessage(fieldError, Locale.CHINA);
            sb.append(fieldError.getField()).append(msg);
            sb.append(StringUtils.SPACE);
        }
        String message = sb.toString();
        LOGGER.warn(message);
        return message;
    }

    /**
     * 数据绑定-ServletRequestBindingException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ServletRequestBindingException.class})
    @ResponseBody
    public Result<String> handleException(ServletRequestBindingException ex) {
    	LOGGER.warn(ex.getMessage());
        return Result.status(Status.SERVER_VALIDATION_ERROR);
    }

    /**
     * 数据绑定-TypeMismatchException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {TypeMismatchException.class})
    @ResponseBody
    public Result<String> handleException(TypeMismatchException ex) {
        String code = ex.getErrorCode() + "." + ex.getRequiredType().getName();
        String message = ex.getValue() + messageSource.getMessage(code, new Object[] {}, Locale.CHINA);
        LOGGER.warn(message + ex.getMessage());
        return Result.status(Status.SERVER_VALIDATION_ERROR, message);
    }

    /**
     * 验证异常
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    public Result<String> handleException(ConstraintViolationException ex) {
    	LOGGER.warn(ex.getMessage());
        Set<ConstraintViolation<?>> volations = ex.getConstraintViolations();
        for (ConstraintViolation<?> violation : volations) {
            return Result.status(Status.SERVER_VALIDATION_ERROR, violation.getMessage());
        }
        return Result.status(Status.SERVER_VALIDATION_ERROR);
    }

    /**
     * InValidTokenException
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {InValidTokenException.class})
    @ResponseBody
    public Result<String> handleException(InValidTokenException ex) {
        LOGGER.warn(ex.getMessage(), ex);
        if (ex instanceof InValidTimeWindowException) {
            return Result.status(Status.INVALID_TIME_WINDOW);
        }
        return Result.status(Status.INVALID_TOKEN);
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
