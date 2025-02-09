import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.core.tokenizers.NGramTokenizer;

public class PredictCatTest {

    @Test
    public void testPredictCatFood() throws Exception {
        // 1. Build the attribute list.
        //    First attribute "description" as a string,
        //    Second attribute "category" as a nominal attribute.
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("description", (ArrayList<String>) null));
        ArrayList<String> classValues = new ArrayList<>(Arrays.asList("Food", "Entrainment", "Transport"));
        attributes.add(new Attribute("category", classValues));
        
        // 2. Create the header for our training dataset.
        Instances trainingData = new Instances("ExpenseData", attributes, 0);
        trainingData.setClassIndex(1);
        
        // 3. Add training instances.
        // Instance 1: description "pasta for dinner", category "Food".
        double[] vals1 = new double[trainingData.numAttributes()];
        vals1[0] = trainingData.attribute(0).addStringValue("pasta for dinner");
        vals1[1] = classValues.indexOf("Food");
        trainingData.add(new DenseInstance(1.0, vals1));
        
        // Instance 2: description "rock concert", category "Entrainment".
        double[] vals2 = new double[trainingData.numAttributes()];
        vals2[0] = trainingData.attribute(0).addStringValue("rock concert");
        vals2[1] = classValues.indexOf("Entrainment");
        trainingData.add(new DenseInstance(1.0, vals2));
        
        // Instance 3: description "bus ticket", category "Transport".
        double[] vals3 = new double[trainingData.numAttributes()];
        vals3[0] = trainingData.attribute(0).addStringValue("bus ticket");
        vals3[1] = classValues.indexOf("Transport");
        trainingData.add(new DenseInstance(1.0, vals3));
        
        // 4. Apply the StringToWordVector filter. This converts the "description" string into numeric attributes.
        StringToWordVector filter = new StringToWordVector();
        // Set up a basic tokenizer (here using unigrams)
        NGramTokenizer tokenizer = new NGramTokenizer();
        tokenizer.setNGramMinSize(1);
        tokenizer.setNGramMaxSize(1);
        filter.setTokenizer(tokenizer);
        filter.setInputFormat(trainingData);
        Instances filteredTrainingData = Filter.useFilter(trainingData, filter);
        
        // 5. Build and train the NaiveBayes classifier on the filtered data.
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(filteredTrainingData);
        
        // 6. Create the predictor using our injected classifier and the filtered data header.
        // (predictCat will use the filtered header for prediction.)
        predictCat predictor = new predictCat(nb, filteredTrainingData);
        
        // 7. For prediction, create a new instance using the same filter transformation.
        double[] instanceValues = new double[filteredTrainingData.numAttributes()];
        // Add the new string value to the original (unfiltered) "description" attribute from trainingData.
        // Then, apply the same filter transformation to create an instance in the filtered space.
        double stringVal = trainingData.attribute(0).addStringValue("I had pasta tonight");
        // Create a temporary instance in the original space to filter it:
        double[] tempVals = new double[trainingData.numAttributes()];
        tempVals[0] = stringVal;
        // The category value is irrelevant here; set to missing.
        tempVals[1] = Double.NaN;
        DenseInstance tempInstance = new DenseInstance(1.0, tempVals);
        tempInstance.setDataset(trainingData);
        
        // Create an Instances object with one instance.
        Instances tempDataset = new Instances(trainingData, 0);
        tempDataset.add(tempInstance);
        // Apply the same StringToWordVector filter to the new instance.
        Instances filteredTestInst = Filter.useFilter(tempDataset, filter);
        
        // 8. Predict the category using the first (and only) instance.
        String predictedCategory = predictor.predictCatFromFilteredInstance(filteredTestInst.firstInstance());
        
        // 9. Assert that the predicted category is "Food".
        assertEquals("Food", predictedCategory);
    }
}
