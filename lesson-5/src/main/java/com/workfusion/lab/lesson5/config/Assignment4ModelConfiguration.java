/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryKeywordProvider;

/**
 * Assignment 4
 */
@ModelConfiguration
public class Assignment4ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "\\W+";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE = "country";

    // TODO:  PUT YOU CODE HERE
    
    @Named("annotator")
    public List<Annotator> annotator(IeConfigurationContext context){
    	List<Annotator> annotator = new ArrayList<>();
    	
    	annotator.add(new SplitterTokenAnnotator(TOKEN_REGEX));
    	annotator.add(new EntityBoundaryAnnotator());
    	annotator.add(new AhoCorasickDictionaryNerAnnotator(NER_TYPE,
    			new CsvDictionaryKeywordProvider(context.getResource("classpath:dictionary/countries.csv"))));
    	
    	
    	return annotator;
    }

}
