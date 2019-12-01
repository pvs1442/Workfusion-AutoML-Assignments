/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.annotator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;

/**
 * Assignment 1
 */
public class Assignment1SentenceAnnotator implements Annotator<Document> {

    /**
     * Regex pattern to use for splitting a document into {@link Sentence} elements.
     */
    private static final String SENTENCE_REGEXP = "[.!?]";

    @Override
    public void process(Document document) {

    	Pattern pattern = Pattern.compile(SENTENCE_REGEXP);
        Matcher matcher = pattern.matcher(document.getText());
        int index=0;
        while(matcher.find()) {
        	
        	document.add(Sentence.descriptor().setBegin(index).setEnd(matcher.start()));
        	index = matcher.end();
        }

    }

}