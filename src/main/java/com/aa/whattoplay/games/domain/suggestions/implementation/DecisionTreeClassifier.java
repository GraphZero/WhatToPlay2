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
    public static final String TRAINING_DATA_SET_FILENAME="./storage/csv/1/attributes.csv";
    public static final String TESTING_DATA_SET_FILENAME="./storage/csv/1/attributes.csv";

    public static Instances getDataSet(String fileName) throws Exception {
        ConverterUtils.DataSource loader = new ConverterUtils.DataSource(fileName);
        Instances dataSet = loader.getDataSet();
        dataSet.setClassIndex(classIndex);
        return dataSet;
    }

    public static void process() throws Exception {

        Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
        Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);

        System.out.println("************************** J48 *************************");
        /** Classifier here is Linear Regression */
        Classifier classifier = new J48();


        //J48,Id3
        /** */
        classifier.buildClassifier(trainingDataSet);
        /**
         * train the alogorithm with the training data and evaluate the
         * algorithm with testing data
         */
        Evaluation eval = new Evaluation(trainingDataSet);
        eval.evaluateModel(classifier, testingDataSet);
        /** Print the algorithm summary */
        System.out.println("** Decision Tress Evaluation with Datasets **");
        System.out.println(eval.toSummaryString());
        System.out.print(" the expression for the input data as per alogorithm is ");
        System.out.println(classifier);
        System.out.println(eval.toMatrixString());
        System.out.println(eval.toClassDetailsString());
    }

}
