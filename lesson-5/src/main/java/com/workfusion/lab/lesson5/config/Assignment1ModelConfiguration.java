/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5.config;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.lab.lesson5.annotator.Assignment1SentenceAnnotator;
import com.workfusion.lab.lesson5.annotator.Assignment1TokenAnnotator;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;

/**
 * Assignment 1
 */
@ModelConfiguration
public class Assignment1ModelConfiguration {

@Named("annotators")
public List<Annotator> annotators(){
	List<Annotator> annotator = new ArrayList<>();
	annotator.add(new Assignment1TokenAnnotator());
	annotator.add(new Assignment1SentenceAnnotator());
	return annotator;
}

}