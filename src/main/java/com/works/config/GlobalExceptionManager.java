package com.works.config;

import com.works.utils.Enums;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.UnexpectedTypeException;
import javax.xml.bind.ValidationException;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionManager extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { IllegalArgumentException.class, UnexpectedTypeException.class, IllegalStateException.class, TransactionSystemException.class, ValidationException.class} )
    protected ResponseEntity<Object> myFnc(Exception ex ) {

        if ( ex instanceof IllegalStateException ) {
            IllegalStateException il = (IllegalStateException) ex;
            System.out.println( il.getMessage() );
        }

        return null;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        return super.handleMethodArgumentNotValid(ex, headers, status, request);
        Map<Enums,Object> hm = new LinkedHashMap<>();
        List<FieldError> errors=ex.getFieldErrors(); //aynı anda birden fazla hata olabilir.
        List<Map<String,String>> lss= new ArrayList<>();
        for (FieldError item:errors){
            Map<String,String> hmx=new HashMap<>();
            String fieldName=item.getField();  //adını verir fieldın
            String message= item.getDefaultMessage(); //mesajı veriri
//            System.out.println(fieldName+" "+message);

            hmx.put(fieldName,message);
            lss.add(hmx);

        }
        hm.put(Enums.status,false);
        hm.put(Enums.error,lss);
        return new ResponseEntity<>(hm,HttpStatus.BAD_REQUEST);
    }


}
