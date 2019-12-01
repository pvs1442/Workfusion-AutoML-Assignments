/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson10.config;

import com.workfusion.vds.sdk.api.hypermodel.annotation.ModelConfiguration;
import com.workfusion.vds.nlp.hypermodel.ie.generic.config.GenericIeHypermodelConfiguration;
import com.workfusion.vds.sdk.api.nlp.fe.annotation.*;
import com.workfusion.vds.sdk.api.hypermodel.annotation.*;
/**
 * The model configuration class.
 */
@ModelConfiguration
@Import(configurations = {
       @Import.Configuration(GenericIeHypermodelConfiguration.class)
})
public class Assignment1ModelConfiguration {

}