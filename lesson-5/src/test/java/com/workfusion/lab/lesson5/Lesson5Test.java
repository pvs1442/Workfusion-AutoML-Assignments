/*
 * Copyright (C) WorkFusion 2018. All rights reserved.
 */
package com.workfusion.lab.lesson5;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Test;

import com.workfusion.lab.lesson5.config.Assignment1ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment2ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment3ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment4ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment5ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment6ModelConfiguration;
import com.workfusion.lab.lesson5.config.Assignment7ModelConfiguration;
import com.workfusion.lab.model.TestTokenFeatures;
import com.workfusion.lab.utils.BaseLessonTest;
import com.workfusion.vds.nlp.model.configuration.ConfigurationData;
import com.workfusion.vds.sdk.api.nlp.annotator.Annotator;
import com.workfusion.vds.sdk.api.nlp.fe.FeatureExtractor;
import com.workfusion.vds.sdk.api.nlp.model.EntityBoundary;
import com.workfusion.vds.sdk.api.nlp.model.IeDocument;
import com.workfusion.vds.sdk.api.nlp.model.NamedEntity;
import com.workfusion.vds.sdk.api.nlp.model.Sentence;
import com.workfusion.vds.sdk.api.nlp.model.Token;
import com.workfusion.vds.sdk.nlp.component.annotator.EntityBoundaryAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.AhoCorasickDictionaryNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.ner.BaseRegexNerAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.MatcherTokenAnnotator;
import com.workfusion.vds.sdk.nlp.component.annotator.tokenizer.SplitterTokenAnnotator;

import static org.assertj.core.api.Assertions.assertThat;

public class Lesson5Test extends BaseLessonTest {

    /**
     * Assignment 1:
     * <p>
     * Provide a model configuration. You need to specify the @Named("annotators") method results to have:
     * <p>
     * Annotators:
     * - a token provider annotator, that injects Token elements into document. Use your Assignment1TokenAnnotator's
     * code from from lesson 1 to update the provided Assignment1TokenAnnotator.java template, and use the
     * instance in the configuration.
     * <p>
     * - a sentences provider annotator, that injects sentences elements into document. Use your Assignment2SentenceAnnotator's
     * code from from lesson 1 to update the provided Assignment1SentenceAnnotator.java template, and use the
     * instance in the configuration.
     */
    @Test
    public void assignment1() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_1.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment1ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 2);

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 1 pattern
        checkElements(tokens, "lesson_5_assignment_1_check_token.json");

        // Gets all Sentence provided by the annotator to check
        List<Sentence> sentences = new ArrayList<>(document.findAll(Sentence.class));

        // Checks the provided sentences with the assignment 1 pattern
        checkElements(sentences, "lesson_5_assignment_1_check_sentences.json");
    }

    /**
     * Assignment 2:
     * <p>
     * Provide a model configuration. You need to specify the @Named("annotators") method results to have:
     * <p>
     * Annotators:
     * - a token provider annotator, that injects Token elements into document. Let's consider token as a word that
     * contains on one or more word character. Regexp:  \w+.
     * Use MatcherTokenAnnotator  annotator to return an implementation instance.
     * <p>
     * - a sentences provider annotator, that injects Sentences elements into document.
     * Sentence elements covering every line of the text Regexp:  '\n'.
     * Use SplitterTokenAnnotator annotator to return an implementation instance.
     */
    @Test
    public void assignment2() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_2.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment2ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 2);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(MatcherTokenAnnotator.class);

        log("Checking that the second annotator is MatcherTokenAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(SplitterTokenAnnotator.class);

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 2 pattern
        checkElements(tokens, "lesson_5_assignment_2_check_token.json");

        // Gets all Sentence provided by the annotator to check
        List<Sentence> sentences = new ArrayList<>(document.findAll(Sentence.class));

        // Checks the provided sentences with the assignment 2 pattern
        checkElements(sentences, "lesson_5_assignment_2_check_sentences.json");
    }

    /**
     * Assignment 3:
     * <p>
     * Provide a model configuration. You need to specify the @Named("annotators") method results to have:
     * <p>
     * Annotators:
     * - a token provider annotator, that injects Token elements into document.
     * Use SplitterTokenAnnotator annotator to return an implementation instance. Regexp: \W+.
     * <p>
     * - Annotator to create {@link EntityBoundary} elements in document.
     * Use EntityBoundaryAnnotator() to return an implementation instance.
     * <p>
     * - a NER provider annotator. Use getJavaPatternRegexNerAnnotator annotator to create NERs in document with
     * mentionType 'invoice number' and regex '[0-9]{11}'.
     */
    @Test
    public void assignment3() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_3.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment3ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 3);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(SplitterTokenAnnotator.class);

        log("Checking that the second annotator is EntityBoundaryAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(EntityBoundaryAnnotator.class);

        log("Checking that the third annotator is getJavaPatternRegexNerAnnotator.");
        assertThat(annotators.get(2).getClass()).isEqualTo(BaseRegexNerAnnotator.class);

        log("Checking that the third annotator has mentionType 'invoice'.");
        String mentionType = (String) PropertyUtils.getSimpleProperty(annotators.get(2), "mentionType");
        assertThat(mentionType).isEqualTo("invoice");

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 3 pattern
        checkElements(tokens, "lesson_5_assignment_3_check_token.json");

        // Gets all ners provided by the annotator to check
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));

        // Checks the provided ners with the assignment 3 pattern
        checkElements(ners, "lesson_5_assignment_3_check_ners.json");
    }

    /**
     * Assignment 4:
     * <p>
     * Provide a model configuration. You need to specify the @Named("annotators") method results to have:
     * <p>
     * Annotators:
     * - a token provider annotator, that injects Token elements into document.
     * Use SplitterTokenAnnotator annotator to return an implementation instance. Regexp: \W+.
     * <p>
     * - Annotator to create {@link EntityBoundary} elements in document.
     * Use EntityBoundaryAnnotator() to return an implementation instance.
     * <p>
     * - a NER provider annotator. Use AhoCorasickDictionaryNerAnnotator annotator, that injects Token elements into document
     * with mentionType 'country' and dictionary "classpath:dictionary/countries.csv".
     */
    @Test
    public void assignment4() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_4.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment4ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 3);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(SplitterTokenAnnotator.class);

        log("Checking that the second annotator is EntityBoundaryAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(EntityBoundaryAnnotator.class);

        log("Checking that the third annotator is AhoCorasickDictionaryNerAnnotator.");
        assertThat(annotators.get(2).getClass()).isEqualTo(AhoCorasickDictionaryNerAnnotator.class);

        log("Checking that the third annotator has mentionType 'country'.");
        String mentionType = (String) PropertyUtils.getSimpleProperty(annotators.get(2), "mentionType");
        assertThat(mentionType).isEqualTo("country");

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 4 pattern
        checkElements(tokens, "lesson_5_assignment_4_check_token.json");

        // Gets all ners provided by the annotator to check
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));

        // Checks the provided ners with the assignment 4 pattern
        checkElements(ners, "lesson_5_assignment_4_check_ners.json");
    }

    /**
     * Assignment 5:
     * <p>
     * Provide a model configuration. You need to specify the @Named("annotators") and @Named("featureExtractors") method results to have:
     * <p>
     * Annotators:
     * - a token provider annotator, that injects Token elements into document. Let's consider token as a word that
     * contains on one or more word character. Regexp: [@\w\.]+. Use MatcherTokenAnnotator() to return an implementation instance.
     * <p>
     * - Annotator to create {@link EntityBoundary} elements in document.
     * Use EntityBoundaryAnnotator() to return an implementation instance.
     * <p>
     * - a NER provider annotator. Use getJavaPatternRegexNerAnnotator annotator to create NERs in document with
     * mentionType 'email' and regex '[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,4}'.
     * FE:
     * - add the IsNerPresentFE() to create features.
     */
    @Test
    public void assignment5() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_5.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment5ModelConfiguration.class);
        String s = configurationData.toString();
        
        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 3);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(MatcherTokenAnnotator.class);

        log("Checking that the second annotator is EntityBoundaryAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(EntityBoundaryAnnotator.class);

        log("Checking that the third annotator is getJavaPatternRegexNerAnnotator.");
        assertThat(annotators.get(2).getClass()).isEqualTo(BaseRegexNerAnnotator.class);

        log("Checking that the third annotator has mentionType 'email'.");
        String mentionType = (String) PropertyUtils.getSimpleProperty(annotators.get(2), "mentionType");
        assertThat(mentionType).isEqualTo("email");

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 4 pattern
        checkElements(tokens, "lesson_5_assignment_5_check_token.json");

        // Gets all Sentence provided by the annotator to check
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));

        // Checks the provided ners with the assignment 4 pattern
        checkElements(ners, "lesson_5_assignment_5_check_ners.json");

        // Obtains defined FEs list.
        List<FeatureExtractor> fes = getFEsFromConfiguration(configurationData, 1);
        // Gives all features provided by custom FEs
        List<TestTokenFeatures> providedElementFeatures = processFeatures(document, fes);
        checkElementFeatures(providedElementFeatures, "lesson_5_assignment_5_check_fe.json");
    }

    /**
     * Assignment 6:
     * <p>
     * Provide import model configuration from lesson5 assignment5
     * <p>
     * FE:
     * - override IsNerPresentFE() from previous step to Assignment6IsNerPresentFE().
     * Implement a code in the provided Assignment6IsNerPresentFE. The FE should have the same functionality
     * as fin assignment5 5, but only provide the feature only in case if email text is finished with ".com"
     */
    @Test
    public void assignment6() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_6.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment6ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 3);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(MatcherTokenAnnotator.class);

        log("Checking that the second annotator is EntityBoundaryAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(EntityBoundaryAnnotator.class);

        log("Checking that the third annotator is getJavaPatternRegexNerAnnotator.");
        assertThat(annotators.get(2).getClass()).isEqualTo(BaseRegexNerAnnotator.class);

        log("Checking that the third annotator has mentionType 'email'.");
        String mentionType = (String) PropertyUtils.getSimpleProperty(annotators.get(2), "mentionType");
        assertThat(mentionType).isEqualTo("email");

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 6 pattern
        checkElements(tokens, "lesson_5_assignment_6_check_token.json");

        // Gets all ners provided by the annotator to check
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));

        // Checks the provided ners with the assignment 6 pattern
        checkElements(ners, "lesson_5_assignment_6_check_ners.json");

        // Obtains defined FEs list.
        List<FeatureExtractor> fes = getFEsFromConfiguration(configurationData, 1);

        // Gives all features provided by custom FEs
        List<TestTokenFeatures> providedElementFeatures = processFeatures(document, fes);
        checkElementFeatures(providedElementFeatures, "lesson_5_assignment_6_check_fe.json");
    }

    /**
     * * Assignment 7:
     * <p>
     * Import Assignment5ModelConfiguration model configuration your created in  assignment5.
     * <p>
     * FE:
     * - add the IsNumberIncludedFE() fe to your configuration
     */
    @Test
    public void assignment7() throws Exception {
        // Creates ML-SDK Document to process
        IeDocument document = getDocument("documents/lesson_5_assignment_7.txt");

        // Obtains model configuration
        ConfigurationData configurationData = buildConfiguration(Assignment7ModelConfiguration.class);

        // Obtains defined annotators list.
        List<Annotator> annotators = getAnnotatorsFromConfiguration(configurationData, 3);

        log("Checking that the first annotator is SplitterTokenAnnotator.");
        assertThat(annotators.get(0).getClass()).isEqualTo(MatcherTokenAnnotator.class);

        log("Checking that the second annotator is EntityBoundaryAnnotator.");
        assertThat(annotators.get(1).getClass()).isEqualTo(EntityBoundaryAnnotator.class);

        log("Checking that the third annotator is getJavaPatternRegexNerAnnotator.");
        assertThat(annotators.get(2).getClass()).isEqualTo(BaseRegexNerAnnotator.class);

        log("Checking that the third annotator has mentionType 'email'.");
        String mentionType = (String) PropertyUtils.getSimpleProperty(annotators.get(2), "mentionType");
        assertThat(mentionType).isEqualTo("email");

        // Process annotators
        processAnnotators(document, annotators);

        // Gets all Tokens provided by the annotator to check
        List<Token> tokens = new ArrayList<>(document.findAll(Token.class));

        // Checks the provided token with the assignment 7 pattern
        checkElements(tokens, "lesson_5_assignment_7_check_token.json");

        // Gets all ners provided by the annotator to check
        List<NamedEntity> ners = new ArrayList<>(document.findAll(NamedEntity.class));

        // Checks the provided ners with the assignment 6 pattern
        checkElements(ners, "lesson_5_assignment_7_check_ners.json");

        // Obtains defined FEs list.
        List<FeatureExtractor> fes = getFEsFromConfiguration(configurationData, 2);

        // Gives all features provided by custom FEs
        List<TestTokenFeatures> providedElementFeatures = processFeatures(document, fes);
        checkElementFeatures(providedElementFeatures, "lesson_5_assignment_7_check_fe.json");

    }

}
