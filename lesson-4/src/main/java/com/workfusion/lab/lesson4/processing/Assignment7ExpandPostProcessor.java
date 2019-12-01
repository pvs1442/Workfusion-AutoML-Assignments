/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

import java.math.BigDecimal;
import java.util.*;
import com.workfusion.vds.sdk.api.nlp.model.Line;
/**
 * Assignment 7
 */
public class Assignment7ExpandPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a product.
     */
    public static final String FIELD_NAME = "product";

    @Override
    public void process(IeDocument document) {

        List<Field> allProductName = new ArrayList<>(document.findFields(FIELD_NAME));
        
        for(Field fields : allProductName) {
        	String fieldString = fields.getValue();
        	
        	List<Line> all = document.findCovering(Line.class,fields);
        	Line test = all.get(0);
        	
        	fields.setValue(test.getText());
        	BigDecimal old_score = fields.getScore();
        	
        	document.remove(fields);
        	document.add(Field.descriptor().setName(FIELD_NAME).setBegin(test.getBegin()).setEnd(test.getEnd()).setScore(old_score).setValue(test.getText()));
        }
    }

}