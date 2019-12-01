/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson7.fe.Assignment1CoveringByLineFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;

/**
 * The model configuration class.
 * Here you can configure set of Feature Extractors, Annotators.
 */
@ModelConfiguration
public class Assignment1ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "[\\w]+";

    @Named("annotator")
    public List<Annotator> annotator(){
    	List<Annotator> annotator = new ArrayList<>();
    	annotator.add(new MatcherTokenAnnotator(TOKEN_REGEX));
    	return annotator;
    }
    
    @Named("featureExtractors")
    public List<FeatureExtractor> featureExtractor(){
    	List<FeatureExtractor> fes = new ArrayList<>();
    	
    	fes.add(new Assignment1CoveringByLineFE());
    	
    	return fes;
    }

}