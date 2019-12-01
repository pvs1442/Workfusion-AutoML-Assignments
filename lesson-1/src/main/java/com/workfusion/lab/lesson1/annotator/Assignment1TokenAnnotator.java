/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson1.annotator;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Assignment 1
 */
public class Assignment1TokenAnnotator implements Annotator<Document> {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private static final String TOKEN_REGEXP = "\\w+";

    @Override
    public void process(Document document) {
    	
       Pattern pattern = Pattern.compile(TOKEN_REGEXP); 
       Matcher matcher = pattern.matcher(document.getText()); // Matches the words in the document
       while(matcher.find()) {  // loop through all the available occurance
    	   document.add(Token.descriptor().setBegin(matcher.start()).setEnd(matcher.end())); //gives the first and end index of the found occurance & enjects or aonnotes it in the document.
       }
    }

}
