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
import com.workfusion.vds.sdk.api.nlp.model.Cell;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

/**
 * Assignment  4
 */
@FeatureName(Assignment4ColumnIndexFE.FEATURE_NAME)
public class Assignment4ColumnIndexFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "column_index";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        List<Cell> cells = document.findCovering(Cell.class,element);
        	
        	for(Cell newCell : cells) {
        		if(newCell.getColumnIndex() == 0 ) {
        			  List<NamedEntity> NM = document.findCovering(NamedEntity.class,element);
        	          
        			  for(NamedEntity NM1 : NM) {
        	        	  if(newCell.getText().toString().contains(NM1.getText().toString())) {
        	        		  result.add(new Feature(FEATURE_NAME,1.0));
        	        	  }
        	          }
        			  
        			 	
        		}

        	}
        return result;
    }

}