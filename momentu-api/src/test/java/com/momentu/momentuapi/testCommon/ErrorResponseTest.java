package com.momentu.momentuapi.testCommon;

import com.momentu.momentuapi.common.ErrorCode;
import com.momentu.momentuapi.common.ErrorResponse;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Date;

import static org.junit.Assert.*;

//This is a test class for Error Response class
public class ErrorResponseTest {

    //This is a test object from Error Response class
    ErrorResponse errorResponseTest;

    //Testing Error Response constructor
    @Test
    public void testErrorResponse(){
        try {
            assertNull(errorResponseTest);
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            errorResponseTest = new ErrorResponse(message, errorCode, httpStatus);
            assertNotNull(errorResponseTest);
        }catch (Exception e){
            fail("Error response constructor doesn't work properly");
        }
    }

    //Testing get http status method in Error Response class
    @Test
    public void testGetHttpStatus() {
        try {
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            errorResponseTest = new ErrorResponse(message, errorCode, httpStatus);
            assertEquals(httpStatus.OK, errorResponseTest.getHttpStatus());

        } catch (Exception e) {
            fail("it is not getting http status value correctly");
        }
    }

    //Testing get message method in Error Response class
    @Test
    public void testGetMessage(){
        try {
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            errorResponseTest = new ErrorResponse(message, errorCode, httpStatus);
            assertEquals("Accepted", errorResponseTest.getMessage());
        }catch (Exception e){
            fail("it is not getting message value correctly");
        }
    }

    //Testing get error code method in Error Response class
    @Test
    public void testGetErrorCode(){
        try {
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            errorResponseTest = new ErrorResponse(message, errorCode, httpStatus);
            assertEquals(ErrorCode.JWT_TOKEN_EXPIRED, errorResponseTest.getErrorCode());
        }catch (Exception e){
            fail("it is not getting error code value correctly");
        }
    }

    //Testing get time stamp method in Error Response class
    @Test
    public void testGetTimestamp(){
        try {
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            Date timestamp = new Date();
            errorResponseTest = new ErrorResponse(message, errorCode, httpStatus);
            assertEquals(timestamp,errorResponseTest.getTimestamp());
        }catch (Exception e){
            fail("it is not creating date object correctly");
        }
    }

    //Testing of method in Error Response class
    @Test
    public void testOf(){
        try {
            ErrorResponse errorResponseTest2;
            HttpStatus httpStatus = HttpStatus.OK;
            String message = "Accepted";
            ErrorCode errorCode = ErrorCode.JWT_TOKEN_EXPIRED;
            errorResponseTest2 = new ErrorResponse(message, errorCode, httpStatus);
            assertEquals(errorResponseTest2.getMessage(), errorResponseTest.of(message, errorCode, httpStatus).getMessage());
        }catch (Exception e){
            fail("static of method doesn't work properly in Error Response class");
        }
    }


}
