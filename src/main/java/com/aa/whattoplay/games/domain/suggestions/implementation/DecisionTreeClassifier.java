package com.aa.whattoplay.games.domain.suggestions.implementation;

import com.aa.ddd.common.annotations.DomainService;
import lombok.extern.slf4j.Slf4j;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

@DomainService
@Slf4j
public class DecisionTreeClassifier {
    private static final int classIndex = 0;
    private final Classifier classifier;

    public DecisionTreeClassifier() {
        /** Classifier here is Linear Regression */
        classifier = new J48();
    }

    public void process(String trainingDataSetFileName) throws Exception {
        Instances trainingDataSet = getDataSet(trainingDataSetFileName);
        classifier.buildClassifier(trainingDataSet);
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(classifier);
    }

    public void process(String trainingDataSetFileName, String testingDataSetFileName) throws Exception {
        Instances trainingDataSet = getDataSet(trainingDataSetFileName);
        Instances testingDataSet = getDataSet(testingDataSetFileName);
        classifier.buildClassifier(trainingDataSet);
        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        System.out.println("** Decision Tress Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(classifier);
    }


    public static Instances getDataSet(String fileName) throws Exception {
        ConverterUtils.DataSource loader = new ConverterUtils.DataSource(fileName);
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(classIndex);
        return dataSet;
    }

}
