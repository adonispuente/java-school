package com.lambdaschool.schools.handlers;


import com.lambdaschool.schools.models.ValidationError;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HelpFunctions {
    public List<ValidationError> getConstraintViolation(Throwable cause){
        while((cause !=null) && !(cause instanceof ConstraintViolationException)){
            cause = cause.getCause();
        }
        List<ValidationError> listVE = new ArrayList<>();
        if (cause != null){
            ConstraintViolationException ex =(ConstraintViolationException) cause;
            for (ConstraintViolation cv : ex.getConstraintViolations()){
                ValidationError newVE = new ValidationError();
                //this is the actual thing that shows me what code is the problem
                newVE.setCode(cv.getInvalidValue().toString());
                //the actual message
                newVE.setMessage((cv.getMessage()));
                listVE.add(newVE);
            }
        }
        return listVE;
    }
}
