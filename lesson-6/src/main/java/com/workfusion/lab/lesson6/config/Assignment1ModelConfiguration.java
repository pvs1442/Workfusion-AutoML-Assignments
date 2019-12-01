/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson6.config;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;

import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.dictionary.CsvDictionaryKeywordProvider;
import java.util.*;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
/**
 * Assignment 1
 */
@ModelConfiguration
public class Assignment1ModelConfiguration {

    /**
     * Regex pattern to use for matching {@link Token} elements.
     */
    private final static String TOKEN_REGEX = "\\w+";

    /**
     * Regex pattern to match an invoice.
     */
    private final static String INVOICE_REGEX = "[0-9]{11}";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE_INVOICE = "invoice";

    /**
     * Type for {@link NamedEntity} to use.
     */
    private final static String NER_TYPE_COUNTRY = "country";

    /**
     * Name of {@link Field} representing an invoice number.
     */
    private final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Name of {@link Field} representing an client address.
     */
    private final static String FIELD_CLIENT_ADDRESS = "client_address";

    /**
     * Path to dictionary of countries.
     */
    private final static String COUNTRY_DICTIONARY_PATH = "classpath:dictionary/countries.csv";

    // TODO:  PUT YOU CODE HERE
    
    @Named("annotators")
    public List<Annotator> annotator(IeConfigurationContext context){
    	List<Annotator> annotator = new ArrayList<>();
    	annotator.add(new MatcherTokenAnnotator(TOKEN_REGEX));
    	annotator.add(new EntityBoundaryAnnotator());
    	String codeType = context.getField().getCode();
    	switch(codeType) {
    	
    	case FIELD_INVOICE_NUMBER : {
    		annotator.add(BaseRegexNerAnnotator.getJavaPatternRegexNerAnnotator(NER_TYPE_INVOICE,INVOICE_REGEX));
    		break;
    	}
    	case FIELD_CLIENT_ADDRESS: {
    		annotator.add(new AhoCorasickDictionaryNerAnnotator(NER_TYPE_COUNTRY,
        			new CsvDictionaryKeywordProvider(context.getResource(COUNTRY_DICTIONARY_PATH))));
    		break;
    	}
    	}
    	
    	return annotator;
    
    	
    }

}