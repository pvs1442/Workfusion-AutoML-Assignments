/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.Content;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;
/**
 * Assignment 2
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private static final String TOKEN_REGEX = "\\w+";

    /**
     * Regex pattern to use for splitting a document into {@link Sentence} elements.
     */
    private static final String SENTENCE_REGEX = "[.!?]";

    // TODO:  PUT YOU CODE HERE
    @Named("annotator")
    public List<Annotator> annotator(){
    	List<Annotator> annotator = new ArrayList<>();
    	annotator.add(new MatcherTokenAnnotator(TOKEN_REGEX));
    	annotator.add(new SplitterTokenAnnotator(Sentence.class,Content.class,SENTENCE_REGEX));
    	
    	return annotator;
    }
}
