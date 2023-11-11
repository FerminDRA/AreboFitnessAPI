/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Helpers;

import com.arebofitness.Response.ResponseHandling;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author fermin
 */
public class ApiResponseHelper {
    public static ResponseEntity<Object> ok(String message,HttpStatus httpStatus, Object data) {
        //ResponseEntity<Object> response= ResponseHandling.responseBuilder(message, httpStatus, data);
        //return new ResponseEntity<>(response);
        return ResponseHandling.responseBuilder(message, httpStatus, data);
    }

    public static ResponseEntity<Object> error(String message, HttpStatus httpStatus,Object data) {
        //ResponseHandling response = new ResponseHandling(status.value(), message, null);
        //ResponseEntity<Object> response= ResponseHandling.responseBuilder(message, httpStatus, data);
        //return new ResponseEntity<>(response, httpStatus);
        return ResponseHandling.responseBuilder(message, httpStatus, data);
    }
}
