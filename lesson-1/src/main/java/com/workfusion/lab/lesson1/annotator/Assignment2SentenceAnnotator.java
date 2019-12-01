/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson1.annotator;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Assignment 2
 */
public class Assignment2SentenceAnnotator implements Annotator<Document> {

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
