/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

import com.workfusion.vds.sdk.api.nlp.annotation.OnInit;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;

/**
 * Assignment 1
 */
public class Assignment1DatePostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing a date.
     */
	private DateTimeFormatter inputDate;
	private DateTimeFormatter outputDate;
	public static final String FIELD_NAME = "date";
	private static final String OUTPUT_DATE_FORMAT = "MM/dd/yy";

	    /**
	     * A format to which a date needs to be converted in the output.
	     */
	    
	@OnInit
	public void init()
	{
	
		inputDate = DateTimeFormatter.ofPattern("MMMM dd, yyyy", Locale.ENGLISH);
		outputDate = DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT);
	}
 

    @Override
    public void process(IeDocument document) {
    	
       Optional<Field> fields = document.findField(FIELD_NAME);
       
       if(fields.isPresent())
       {
    	   Field field = fields.get();
    	   LocalDate date = LocalDate.parse(field.getValue(), inputDate);
    	   field.setValue((date).format(outputDate));
       }

    }

}