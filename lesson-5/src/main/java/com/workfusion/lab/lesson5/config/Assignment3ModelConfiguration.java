/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;

/**
 * Assignment 3
 */
@ModelConfiguration
public class Assignment3ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "\\W+";

    /**
     * Regex pattern to match an invoice.
    */
    private final static String INVOICE_REGEX = "[0-9]{11}";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE = "invoice";

    // TODO:  PUT YOU CODE HERE
    
    @Named("annotator")
    public List<Annotator> annotator(){
    	
    	List<Annotator> annotator = new ArrayList<>();
    	
    	annotator.add(new SplitterTokenAnnotator(TOKEN_REGEX));
    	annotator.add(new EntityBoundaryAnnotator());
    	annotator.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(NER_TYPE,INVOICE_REGEX));
    	return annotator;
    }

}
