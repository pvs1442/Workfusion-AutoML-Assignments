/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;
import java.util.Optional;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;
import java.util.*;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

/**
 * Assignment 3
 */
public class Assignment3IBANPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing an IBAN number.
     */
    public static final String FIELD_NAME = "iban";

    /**
     * IBAN Code checker to use.
     */
    private IBANCheckDigit checker = new IBANCheckDigit();

    @Override
    public void process(IeDocument document) {

      List<Field> allFields = new ArrayList<>(document.findFields(FIELD_NAME));
      
      for(Field field : allFields) {
    	  
    	  if(! checker.isValid(field.getValue())) {
    		  document.remove(field);
    	  }
      }
    }

}