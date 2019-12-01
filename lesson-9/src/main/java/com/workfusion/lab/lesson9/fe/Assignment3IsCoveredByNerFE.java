/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Cell;
/**
 * Assignment 3
 */

public class Assignment3IsCoveredByNerFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Type of {@link NamedEntity}.
     */
    private String type;

    /**
     * Create an instance of {@link FeatureExtractor} that detects if a token is inside the {@link NamedEntity} of the specified {@code type}.
     * @param type type of {@link NamedEntity}
     */
    public Assignment3IsCoveredByNerFE(String fieldName) {
        this.type = fieldName;
    }

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        List<NamedEntity> nem = document.findCovering(NamedEntity.class,element);
      
       
        	if(nem.size() != 0) {
        		
            	if(nem.get(0).getType().toString().equals("invoice_number")) {
            		
            		result.add(new Feature("invoice_number",1.0));
            	}
            	if(nem.get(0).getType().toString().equals("date")) {
            		result.add(new Feature("date",1.0));	
            	}
            	if(nem.get(0).getType().toString().equals("price")) {
            		
            		List<Cell> cells = document.findCovering(Cell.class,element);
            		if(cells.size() != 0) {
            			if(cells.get(0).getColumnIndex() == 1) {
            				result.add(new Feature("price",1.0));
            			}
            		}
            		
            			
            	}
            }
        	
        	
        return result;
    }

}