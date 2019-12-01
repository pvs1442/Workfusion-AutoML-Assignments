/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;


import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson5.fe.Assignment6IsNerPresentFE;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Import;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Element;

/**
 * Assignment 6
 */
@ModelConfiguration
@Import(configurations = {
		@Import.Configuration(Assignment5ModelConfiguration.class)
})
// TODO:  PUT YOU CODE HERE
public class Assignment6ModelConfiguration {

    @Named("featureExtractors") 
    public List<FeatureExtractor> featureExtractors (){
    	List<FeatureExtractor> fes = new ArrayList<>();
    	fes.add(new Assignment6IsNerPresentFE());
    	return fes;
    }

}
