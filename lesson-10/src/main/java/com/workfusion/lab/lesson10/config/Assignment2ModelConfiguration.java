/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.config;

import com.workfusion.vds.sdk.api.hpo.ParameterSpace;
import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.annotation.Named;
import com.workfusion.vds.sdk.api.nlp.configuration.IeConfigurationContext;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import java.util.concurrent.TimeUnit;
import com.workfusion.vds.sdk.api.hpo.*;
import com.workfusion.lab.lesson10.fe.*;
import com.workfusion.lab.lesson10.processing.*;
import com.workfusion.vds.sdk.api.hypermodel.annotation.*;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.nlp.hypermodel.ie.generic.config.GenericIeHypermodelConfiguration;
import com.workfusion.vds.sdk.api.hypermodel.*;
/**
 * The model configuration class.
 */
@ModelConfiguration
@Import(
       configurations = {
               @Import.Configuration(GenericIeHypermodelConfiguration.class)
       },
       resources = {
               @Import.Resource(value="/parameters/invoice_number/parameters.json",
                                condition = @Filter(expression = "field.code eq 'invoice_number'"))
       }     
)
public class Assignment2ModelConfiguration {

    /**
     * Name of {@link Field} representing an invoice number.
     */
    public final static String FIELD_INVOICE_NUMBER = "invoice_number";

    /**
     * Name of {@link Field} representing a product.
     */
    public final static String FIELD_PRODUCT = "product";
   
    @Named("fes1")
    public FeatureExtractor getFeatureExtractors1() {
        return new RowIndexFE();
    }
    @Named("fes2")
    public FeatureExtractor getFeatureExtractors2() {
        return new TableNumberFE();
    }
    @Named("fes3")
    public FeatureExtractor getFeatureExtractors3() {
        return new ColumnIndexFE();
    }
    @Named("hpoConfiguration")
    public HpoConfiguration hpoConfiguration(ConfigurationContext context) {
        return new HpoConfiguration.Builder()
                .timeLimit(600, TimeUnit.SECONDS)
                .maxExperimentsWithSameScore(5)
                .build();
    }

    @Named("basePostProcessors")
    public Processor<IeDocument> postProcessors() {
        return new ExpandPostProcessor();
    }
    
    
    
    @Named("parameterSpace")
    public ParameterSpace configure(IeConfigurationContext context) {
        ParameterSpace.Builder builder = new ParameterSpace.Builder();

        String type = context.getField().getCode();
        switch(type) {
        case FIELD_PRODUCT : {
        	builder.add(Dimensions.selectOne("fes1", "fes2"));
        	break;
        }
        case FIELD_INVOICE_NUMBER : {
        	builder.add(Dimensions.required("fes3"));
        	break;
        }
        }
       

        return builder.build();
    }

}