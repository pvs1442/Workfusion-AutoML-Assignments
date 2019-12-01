/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson7.fe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.FeatureName;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.Token;

@FeatureName(Assignment4NextTokenIsNumberFE.FEATURE_NAME)
public class Assignment4NextTokenIsNumberFE<T extends Element> implements FeatureExtractor<T> {

    /**
     * Name of {@link Feature} the feature extractor produces.
     */
    public static final String FEATURE_NAME = "next_token_number";
    Pattern pat = Pattern.compile("^[0-9]+$");
    @Override
    public Collection<Feature> extract(Document document, T element) {
        List<Feature> result = new ArrayList<>();
        
        List<Element> prevToken = (List<Element>) document.findPrevious(element.getClass(),element,1);
        if(prevToken.size() != 0) {
        	Matcher mat = pat.matcher(prevToken.get(0).getText());
        	while(mat.find()) {
        		
        		result.add(new Feature(FEATURE_NAME,1.0));
        	}
        }
        return result;
    }
}