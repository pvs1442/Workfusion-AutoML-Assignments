/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.io.*;
import java.util.Collection;
import java.util.*;
import org.apache.commons.io.IOUtils;

import com.workfusion.vds.sdk.api.nlp.annotation.OnDestroy;
import com.workfusion.vds.sdk.api.nlp.annotation.OnInit;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.exception.SdkException;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
/**
 * Assignment 2
 */
public class Assignment2NerFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The {@link NamedEntity} type to use.
     */
    public final static String NER_TYPE = "state";

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "stateFeature";
    private String pathToResource;
    private List<String> keywords = new ArrayList<>();
 
    public Assignment2NerFE() {
        pathToResource = "states.csv";
    }
    
    @OnInit
    public void init() {
        // read resource from classpath and collect keywords for future processing.
       
    }
    
    
    @Override
    public Collection<Feature> extract(Document document, T element) {
    	 try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(pathToResource))) {
             keywords = IOUtils.readLines(reader);
         } catch (Exception e) {
             throw new SdkException(String.format("Error reading file %s.", pathToResource), e);
         }
    	 
    	 
    	List<Feature> feature = new ArrayList<>();
        
    	
    		List<NamedEntity> TS = document.findCovering(NamedEntity.class,element);
    		
    		int p = TS.size();
    		String S="";
    		if(p != 0)
    		S= TS.get(0).getText();
    		
    		if(keywords.contains(S))
    	    feature.add(new Feature(FEATURE_NAME,1.0));
    	
    	
        return feature;
    }
    
    @OnDestroy
    public void destroy() {
        // clean collection after usage
        keywords.clear();
    }
   
}