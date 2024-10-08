package com.EducationSystem.Enrollement.Response;

import org.aspectj.bridge.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.awt.geom.RectangularShape;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    public ResponseEntity<Object>ResponseBulider(String message , HttpStatus httpStatus, Object ResponseObject ){
        Map<String,Object> Response=new HashMap<>();
        Response.put("message", message);
        Response.put("Status",httpStatus);
        Response.put("Response",ResponseObject);
        return new ResponseEntity<>(Response,httpStatus);







    }
}
