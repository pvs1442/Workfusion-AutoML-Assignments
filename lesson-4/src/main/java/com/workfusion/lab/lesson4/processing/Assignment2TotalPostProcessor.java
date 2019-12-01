/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

import java.util.*;
/**
 * Assignment 2
 */
public class Assignment2TotalPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a total.
     */
    public static final String FIELD_NAME = "total";
   // private List <Field> test = new ArrayList<>();
    @Override
    public void process(IeDocument document) {

    	List<Field> fields = new ArrayList<>(document.findFields(FIELD_NAME));
    	OcrAmountNormalizer amountNormalizer = new OcrAmountNormalizer();
    	
    	for(Field f1 : fields) {
    	
    		String amountString = f1.getValue();
    		String finalAmount = amountNormalizer.normalize(amountString)+" USD";
    		f1.setValue(finalAmount);
    	}
    	  
    }

}