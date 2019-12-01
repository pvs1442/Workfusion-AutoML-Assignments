/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import java.util.*;
/**
 * Assignment 5
 */
public class Assignment5PricePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a price.
     */
    public static final String FIELD_NAME = "price";

    @Override
    public void process(IeDocument document) {

        
    	List<Field> allPriceFields = new ArrayList<>(document.findFields(FIELD_NAME));
    	for(Field fields : allPriceFields) {
    		
    		String fieldString = fields.getValue();
    		fieldString = fieldString.replaceAll(",","");
    		fieldString = fieldString.replace("$","");
    	/*	fieldString = fieldString.replaceAll("I","1");
    		fieldString = fieldString.replaceAll("[|]","1");
    		fieldString = fieldString.replaceAll("i","1");
    		fieldString = fieldString.replaceAll("l","1");
    		
    		
    		fieldString = fieldString.replaceAll("G","6");
    		fieldString = fieldString.replaceAll("b","6");
    		fieldString = fieldString.replaceAll("B","8");
    		fieldString = fieldString.replaceAll("O","0");*/
    		
    		fieldString = fieldString+" USD";
    		fields.setValue(fieldString);
    	}
    
    }

}