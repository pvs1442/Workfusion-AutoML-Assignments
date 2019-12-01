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
import com.workfusion.vds.sdk.api.nlp.model.Table;
import com.workfusion.vds.sdk.api.nlp.model.*;
/**
 * Assignment 3
 */
public class Assignment3TableFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * The "row index" feature name.
     */
    private static final String ROW_INDEX_FEATURE_NAME = "rowIndex";

    /**
     * The "cell index" feature name.
     */
    private static final String COLUMN_INDEX_FEATURE_NAME = "columnIndex";

    @Override
    public Collection<Feature> extract(Document document, T element) {
    	Collection<Cell> cell = document.findAll(Cell.class);
    	List<Feature> feature = new ArrayList<>();   
        List<Cell> cellsp = new ArrayList<>();
        cellsp.addAll(cell);       
        for(Cell test : cellsp) {
        	String testm = test.getText();
        	
        	if(testm.contains(element.getText())) {
        		
        		feature.add(new Feature(ROW_INDEX_FEATURE_NAME,test.getRowIndex()));
                feature.add(new Feature(COLUMN_INDEX_FEATURE_NAME,test.getColumnIndex()));
                break;
        	}
        }
        return feature;
    }

}