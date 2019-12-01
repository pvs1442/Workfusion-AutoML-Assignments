/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 6
 */
public class Assignment6IsNerPresentFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "isNer";

    /**
     * Email extension to identify emails we need to add features for.
     */
    private static final String EMAIL_EXTENSION = ".com";

    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();

        if(element.getText().contains(EMAIL_EXTENSION)) {
        	result.add(new Feature(FEATURE_NAME,1.0));
        }

        return result;
    }

}