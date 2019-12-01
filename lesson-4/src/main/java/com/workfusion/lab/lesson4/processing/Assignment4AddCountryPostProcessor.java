/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson4.processing;

import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import java.util.*;
/**
 * Assignment 4
 */
public class Assignment4AddCountryPostProcessor implements Processor<IeDocument> {

    /**
     * Name of {@link Field} representing an IBAN number.
     */
    public static final String FIELD_IBAN = "iban";

    /**
     * Name of {@link Field} representing a country.
     */
    public static final String FIELD_COUNTRY = "country";

    /**
     * IBAN Code checker to use.
     */
    private IBANCheckDigit checker = new IBANCheckDigit();

    @Override
    public void process(IeDocument document) {

        List<Field> allFields = new ArrayList<>(document.findFields(FIELD_IBAN));
        
        for(Field field : allFields) {
        	
        	if(checker.isValid(field.getValue())) {
        		String countryCode = field.getValue().substring(0,2);
        		document.add(Field.descriptor().setName(FIELD_COUNTRY).setValue(countryCode).setScore(0.5));
        	}
        }

    }

}