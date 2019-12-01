/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.Collection;
import java.util.Collections;
import java.util.*;


import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentComplete;
import com.workfusion.vds.sdk.api.nlp.annotation.OnDocumentStart;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
/**
 * Assignment 5
 */
public class Assignment5FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";
    private List<Sentence> sentence = new ArrayList<>();
    
    private List<Integer> first_token_start= new ArrayList<>();
   

    /**
     * The method is called once in the beginning of document processing.
     * @param document  a document is being processed.
     * @param focusElement  a type of {@link Element} the feature extractor will accept in the {@code extract()} method.
     */
    @OnDocumentStart
    public void documentStart(Document document, Class<T> focusElement) {

    	
    	 
         Collection<Sentence> sentences = document.findAll(Sentence.class);
         sentence.addAll(sentences);
         for(Sentence sc : sentence)
         {
        	
        	 first_token_start.add(sc.getBegin());
         }
        

    }

    @Override
    public Collection<Feature> extract(Document document, T element) {

       List<Feature> feature = new ArrayList<>();
       
       if(first_token_start.contains(element.getBegin())) {
       
       feature.add(new Feature(FEATURE_NAME,1.0));
       }
        return feature;
    }

    /**
     * The method is called once in the end of document processing. The main purpose of the method is to release all resources allocated
     * while the feature extractor was working.
     */
    @OnDocumentComplete
    public void documentComplete() {

        //TODO: PUT YOUR CODE HERE
    	first_token_start.clear();
    }

}