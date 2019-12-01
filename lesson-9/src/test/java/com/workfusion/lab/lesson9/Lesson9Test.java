/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson9;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.junit.Test;
import com.workfusion.lab.lesson9.fe.*;
import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.lab.lesson9.config.Assignment1ModelConfiguration;
import com.workfusion.lab.lesson9.config.Assignment2ModelConfiguration;
import com.workfusion.lab.lesson9.run.Assignment1ModelExecutionRunner;
import com.workfusion.lab.lesson9.run.Assignment1ModelTrainingRunner;
import com.workfusion.lab.lesson9.run.Assignment2ModelExecutionRunner;
import com.workfusion.lab.lesson9.run.Assignment2ModelTrainingRunner;
import com.workfusion.lab.model.TestTokenFeatures;
import com.workfusion.lab.utils.BaseLessonTest;
import com.workfusion.vds.nlp.model.configuration.ConfigurationData;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldInfo;
import com.workfusion.vds.sdk.api.nlp.configuration.FieldType;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Token;

public class Lesson9Test extends BaseLessonTest {

    /**
     * Assignment 1:
     * Provide annotators, FEs and post-processor(for 'date' field) configuration for fields:
     * - "invoice_number".
     * - "date"
     * Use solutions from the previous lessons.
     * The configuration must be the fields content depended (revise the Lesson 5).
     * Define field configuration and provided paths in the provided Assignment1ModelExecutionRunner.
     * <p>
     * The test runs the model training runner, and check the avg-evaluation-results.txt.
     * You need to provide the annotators/FE configuration that gives:
     * - "invoice_number": P>0.9 and R>0.6
     * - "date": P>0.9 and R>0.6
     * <p>
     * Then test runs the model execution runner, and check the per-field-statistics.csv.
     * - "invoice_number": P>0.9 and R>0.6
     * - "date": P>0.9 and R>0.6
     */
    @Test
    public void assignment1() throws Exception {
    	
    	IeDocument document = getDocument("documents/invoice106.html");
    	
    	// Obtains model configuration for FIELD_INVOICE_NUMBER
    	 ConfigurationData configurationData = buildConfiguration(Assignment1ModelConfiguration.class,
                 new FieldInfo.Builder(Assignment1ModelConfiguration.FIELD_DATE).type(FieldType.DATE).build());
    	 
     
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData);
        processAnnotators(document, annotators);
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));
        
        System.out.println("*****Tokens");
        for(Token token : tokens) {
        	System.out.println("**"+token);
        }
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));
        System.out.println("*****Named Entity");
        for(NamedEntity NM : ners) {
        	System.out.println("**"+NM);
        }
        
        /*
        document = getDocument("documents/lesson_9_assignment_1.html");
        ConfigurationData configurationData1 = buildConfiguration(Assignment1ModelConfiguration.class,
                new FieldInfo.Builder(Assignment1ModelConfiguration.FIELD_DATE).type(FieldType.DATE).build());
        annotators = getAnnotatorsFromConfiguration(configurationData1);
        processAnnotators(document, annotators);
        ners = new ArrayList<>(document.findAll(NamedEntity.class));*/
        
    	// Obtains defined annotators list for field "total_amount".
        //List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, );
        // Process annotators for field "total_amount"
        //processAnnotators(document, annotators);
        // Gets all Tokens provided by the annotator to check for field "total_amount"
        //List<Token> tokens = new ArrayList<>(document.findAll(Token.class));
       // List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));
        
        
     
 // Obtains training statistics
      //  executeRunner(Assignment1ModelTrainingRunner.class);
       // Map<String, FieldStatistic> trainingStatistics = getTrainingFieldStatistics(Assignment1ModelTrainingRunner.OUTPUT_DIR_PATH);

        // Check the field statistics
     //   checkFieldStatistics(trainingStatistics, Assignment1ModelConfiguration.FIELD_INVOICE_NUMBER, 0.9, 0.6);
     //   checkFieldStatistics(trainingStatistics, Assignment1ModelConfiguration.FIELD_DATE, 0.9, 0.6);

     //   executeRunner(Assignment1ModelExecutionRunner.class);
     //   Map<String, FieldStatistic> executionStatistics = getExecutionFieldStatistics(Assignment1ModelTrainingRunner.OUTPUT_DIR_PATH + "/extract");

        // Check the field statistics
    //    checkFieldStatistics(executionStatistics, Assignment1ModelConfiguration.FIELD_INVOICE_NUMBER, 0.9, 0.6);
      //  checkFieldStatistics(executionStatistics, Assignment1ModelConfiguration.FIELD_DATE, 0.9, 0.6);

    }
/*
    *//**
     * Assignment 2:
     * Check the provided data and provide the configuration for custom model
     * The test runs the model training runner, and check the avg-evaluation-results.txt.
     * You need to provide the annotators/FE/processors configuration that gives:
     * for all fields: P>0.9 and R>0.6
     * <p>
     * Then test runs the model execution runner, and check the per-field-statistics.csv.
     * for all fields: P>0.9 and R>0.6
     * List of fields:
     * - "price"
     * - "product"
     * - "client_name"
     *//*
    @Test
    public void assignment2() throws Exception {
    	
    	IeDocument document = getDocument("documents/lesson_9_assignment_1.html");

		ConfigurationData configurationData = buildConfiguration(Assignment2ModelConfiguration.class,
                new FieldInfo.Builder(Assignment2ModelConfiguration.FIELD_PRICE).type(FieldType.PRICE).build());
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData);
        processAnnotators(document, annotators);
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));
		
		ConfigurationData configurationData1 = buildConfiguration(Assignment2ModelConfiguration.class,
                new FieldInfo.Builder(Assignment2ModelConfiguration.FIELD_CLIENT_NAME).type(FieldType.FREE_TEXT).build());
        annotators = getAnnotatorsFromConfiguration(configurationData1);
        processAnnotators(document, annotators);
        tokens = new ArrayList<>(document.findAll(Token.class));
        ners = new ArrayList<>(document.findAll(NamedEntity.class));
		
    
		List<TestTokenFeatures> providedElementFeatures = processFeatures(document,
	               new Assignment4ColumnIndexFE() //Assignment FE to check
	       );
		
        // Obtains training statistics
        executeRunner(Assignment2ModelTrainingRunner.class);
        Map<String, FieldStatistic> trainingStatistics = getTrainingFieldStatistics(Assignment2ModelTrainingRunner.OUTPUT_DIR_PATH);

        // Check the field statistics
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_PRICE, 0.9, 0.6);
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_PRODUCT, 0.9, 0.6);
        checkFieldStatistics(trainingStatistics, Assignment2ModelConfiguration.FIELD_CLIENT_NAME, 0.9, 0.6);

        executeRunner(Assignment2ModelExecutionRunner.class);
        Map<String, FieldStatistic> executionStatistics = getExecutionFieldStatistics(Assignment2ModelTrainingRunner.OUTPUT_DIR_PATH + "/extract");

        // Check the field statistics
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_PRICE, 0.9, 0.6);
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_PRODUCT, 0.9, 0.6);
        checkFieldStatistics(executionStatistics, Assignment2ModelConfiguration.FIELD_CLIENT_NAME, 0.9, 0.6);
   
    }
*/
}