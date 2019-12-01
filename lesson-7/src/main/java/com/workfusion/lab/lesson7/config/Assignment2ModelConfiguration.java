/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson7.fe.Assignment2FE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment2ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[\\w@.,$%’-]+";

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Regex pattern to match an invoice number.
     */
    private final static String INVOICE_NUMBER_REGEX = "\\d{10}";

    // TODO:  PUT YOU CODE HERE
    @Named("annotator")
    public List<Annotator> annotator(){
    	
    	List<Annotator> annotator = new ArrayList<>();
    	annotator.add(new MatcherTokenAnnotator(TOKEN_REGEX));
    	annotator.add(new EntityBoundaryAnnotator());
    	annotator.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(FIELD_INVOICE_NUMBER,INVOICE_NUMBER_REGEX));
    	
    	return annotator;
    }
    
    @Named("featureExtractors")
    public List<FeatureExtractor> featureExtractor(){
    	List<FeatureExtractor> fes = new ArrayList<>();
    	fes.add(new Assignment2FE());
    	return fes;
    }

}