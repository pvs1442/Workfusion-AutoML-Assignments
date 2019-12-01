/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson3.fe;

import java.util.Collection;
import java.util.Collections;
import java.util.*;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Line;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;
/**
 * Assignment 4
 */
public class Assignment4FirstInSentenceFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    private static final String FEATURE_NAME = "firstInSentenceFeature";

    @Override
    public Collection<Feature> extract(Document document, T element) {

        List<Sentence> lines = document.findCovering(Sentence.class,element);
        List<Token> Tokens = document.findCovered(Token.class,lines.get(0).getBegin(),lines.get(0).getEnd());
        
       
        int size = Tokens.size();
        String S = ""+size;
    	List<Feature> feature = new ArrayList<>();
    	int no1 = Tokens.get(0).getBegin();
    	int no2 = element.getBegin();
    	if(no1 == no2) {
        feature.add(new Feature(FEATURE_NAME,1.0));
    	}
    	return feature;
    }

}