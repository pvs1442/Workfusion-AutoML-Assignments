/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson2.annotator;

import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment 1
 */
public class Assignment1KeywordNerAnnotator implements Annotator<Document> {

    /**
     * Keywords list to use.
     */
    private static final List<String> STATES = Arrays.asList(
            "Missouri",
            "Nevada",
            "Alaska",
            "Hawaii",
            "Texas",
            "Maryland",
            "Vermont"
    );

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE = "state";
    private final static String EXP = "\\w+";

    @Override
    public void process(Document document) {
    	
    	Pattern pattern = Pattern.compile(EXP);
    	Matcher matcher = pattern.matcher(document.getText());
    	
    	while(matcher.find()) {
    		String ftoken = document.getText().substring(matcher.start(),matcher.end());
    		if(STATES.contains(ftoken)) {
    			document.add(NamedEntity.descriptor().setType(NER_TYPE).setBegin(matcher.start()).setEnd(matcher.end()));
    			}
    	}
    	
    }

}