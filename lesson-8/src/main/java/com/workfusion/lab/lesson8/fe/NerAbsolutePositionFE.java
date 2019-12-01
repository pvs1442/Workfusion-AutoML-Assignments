/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Determines if focus annotation is NER and the last NER in the document
 */
public class NerAbsolutePositionFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "lastnerFeature";
    /**
     * Determines if focus annotation is NER and the last NER in the document
     * @param document the Document containing the focus
     * @param element the focus being checked for it position in  the covering annotation
     * @return "lastnerFeature" if focus annotation is NER and the last NEM in the document, nothing otherwise
     */
   
    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        List<NamedEntity> check = document.findNext(NamedEntity.class,element,1);
        List<NamedEntity> Name = new ArrayList<>(document.findAll(NamedEntity.class));
        	if(check.size() == 0) {
        		for(NamedEntity nm : Name) {
        			if(nm.getText().equals(element.getText())) {
        	        	result.add(new Feature(FEATURE_NAME,1.0));
        			}
        		}
        		
        		}
        	

        return result;
    }

}