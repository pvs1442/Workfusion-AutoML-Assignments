/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson8.processing;

import java.util.Optional;
import com.workfusion.lab.lesson8.config.Assignment1ModelConfiguration;
import com.workfusion.vds.sdk.api.nlp.model.Field;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.processing.Processor;
import com.workfusion.vds.sdk.nlp.component.processing.normalization.OcrAmountNormalizer;

public class TotalAmountPostProcessor implements Processor<IeDocument> {

    private static final String REGEX_TOTAL_AMOUNT_WRONG_CHARS  = "[,($]";

    @Override
    public void process(IeDocument document) {
    	OcrAmountNormalizer amountNormalizer = new OcrAmountNormalizer();
    	Optional<Field> fields = document.findField(Assignment1ModelConfiguration.FIELD_TOTAL_AMOUNT);
    	if(fields.isPresent())
        {
     	   Field field = fields.get();
     	   String total_price = field.getValue();
     	   String finalAmount = amountNormalizer.normalize(total_price)+" USD";
     	   field.setValue(finalAmount);
        }

    }

}