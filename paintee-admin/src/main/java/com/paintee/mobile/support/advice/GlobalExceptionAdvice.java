package com.paintee.mobile.support.advice;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.paintee.mobile.support.exception.UnauthorizedException;

/**
@class GlobalExceptionAdvice
com.paintee.mobile.support.advice \n
   ㄴ GlobalExceptionAdvice.java
 @section 클래스작성정보
	|	항  목	   |	  내  용	   |
	| :-------------: | -------------   |
	| Company | Paintee |
	| Author | Administrator |
	| Date | 2016. 3. 8. 오후 12:49:18 |
	| Class Version | v1.0 |
	| 작업자 | Administrator |
 @section 상세설명
 - 인증오류
*/
@ControllerAdvice
public class GlobalExceptionAdvice {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "messageSource")
    private MessageSource messageSource;
    
    private final String messageCodePrefix = "sec.api.error.";
//
//    /**
//     @fn getErrorMessage
//     @brief 함수 간략한 설명 : HttpMediaTypeNotSupportedException 예외에 대한 처리 메소드
//     @remark
//     - 함수의 상세 설명 : HttpMediaTypeNotSupportedException 예외에 대한 처리 메소드
//     @param ex
//     @return 
//    */
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//    @ResponseBody
//    public Map<String, Object> getErrorMessage(HttpMediaTypeNotSupportedException ex) {
//        logger.error("============================ HttpMediaTypeNotSupportedException ============================");
//        logger.error("Exception [\n{}\n]", ex);
//
//        Map<String, Object> responseMap = new HashMap<String, Object>();
//        responseMap.put("errorNo", 415);
//        responseMap.put("errorMsg", "Unsupported content type: " + ex.getContentType()+", Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes()));
//
//        return responseMap;
//    }
//
//    /**
//     @fn getErrorMessage
//     @brief 함수 간략한 설명 : HttpMessageNotReadableException 예외에 대한 처리 메소드
//     @remark
//     - 함수의 상세 설명 : HttpMessageNotReadableException 예외에 대한 처리 메소드
//     @param ex
//     @return 
//    */
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ResponseBody
//    public Map<String, Object> getErrorMessage(HttpMessageNotReadableException ex) {
//        logger.error("============================ HttpMessageNotReadableException ============================");
//        logger.error("Exception [\n{}\n]", ex);
//
//        Throwable mostSpecificCause = ex.getMostSpecificCause();
//
//        Map<String, Object> responseMap = new HashMap<String, Object>();
//
//        if (mostSpecificCause != null) {
//            responseMap.put("errorNo", 400);
//            responseMap.put("errorMsg", mostSpecificCause.getMessage());
//        }
//
//        return responseMap;
//    }

    /**
     @fn handleInternalServerMessageException
     @brief 함수 간략한 설명 : 
     @remark
     - 함수의 상세 설명 : 
     @param request
     @param ce
     @return 
    */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Map<String, Object> handleUnauthorizedException(HttpServletRequest request, HttpServletResponse reponse, UnauthorizedException ue) {
    	logger.error("============================ handleUnauthorizedException ============================");
    	logger.error("Exception [\n{}\n]", ue);

    	reponse.setHeader("Access-Control-Allow-Origin", "*");

        Map<String, Object> responseMap = new HashMap<String, Object>();

        responseMap.put("errorNo", ue.getErrorNo());
        responseMap.put("errorMsg", ue.getMessage());
        responseMap.put("contextPath", request.getContextPath());

    	return responseMap;
    }

    /**
     @fn handleException
     @brief 함수 간략한 설명 : Exception 예외에 대한 처리 메소드
     @remark
     - 함수의 상세 설명 : Exception 예외에 대한 처리 메소드
     @param request
     @param ex
     @return 
    */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Map<String, Object> handleException(HttpServletRequest request, HttpServletResponse reponse, Exception ex) {
        logger.error("============================ Exception ============================");
        logger.error("Exception [\n{}\n]", ex);

    	reponse.setHeader("Access-Control-Allow-Origin", "*");

        Map<String, Object> responseMap = new HashMap<String, Object>();

        responseMap.put("errorNo", HttpStatus.INTERNAL_SERVER_ERROR);
        responseMap.put("errorMsg", ex.getMessage());

        return responseMap;
    }
}