package com.workfusion.lab.lesson9.fe;


import com.workfusion.vds.sdk.api.nlp.fe.Feature;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.Document;
import com.workfusion.vds.sdk.api.nlp.model.Element;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;


/**
 * Create a feature if data is covered with particular Ner.
 *
 * @param <T>
 */
public class CertInvoiceDateFeatureExtractor<T extends Element> implements FeatureExtractor<T> {

    private final String nerType;
 

    public CertInvoiceDateFeatureExtractor(String nerType) {
        this.nerType = nerType;
    }
    

    @Override
    public Collection<Feature> extract(Document document, T element) {
    	List<Feature> result = new ArrayList<>();
    	List<NamedEntity> namedEntity = document.findCovering(NamedEntity.class,element);
    	
    
    	if(namedEntity.size() != 0) {
    		
        	
        	if(namedEntity.get(0).getType().toString().equals("date")) {
        		
        		result.add(new Feature("invoice_date_feature",1.0));	
        	}
        	
        		
        			
        	}
    	return result;
        }
    	
    	
                
    
}
