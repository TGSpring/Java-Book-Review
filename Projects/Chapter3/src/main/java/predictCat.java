import weka.core.Instances;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Attribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class predictCat {

    private NaiveBayes model;
    private Instances dataStruct;

    public predictCat() throws Exception {
        this.model = new NaiveBayes();

        // Define attributes for the instance header.
        ArrayList<Attribute> attributes = new ArrayList<>();

        // A basic Bag-of-Words feature extractor (e.g., check for the presence of
        // certain keywords)
        ArrayList<String> keywords = new ArrayList<>(Arrays.asList("restaurant", "grocery", "bus", "train", "movie"));
        for (String word : keywords) {
            attributes.add(new Attribute(word)); // Each keyword is a binary feature (1 if present, 0 if not)
        }

        // The class attribute with possible values
        ArrayList<String> classValues = new ArrayList<>(Arrays.asList("Food", "Entertainment", "Transport"));
        attributes.add(new Attribute("category", classValues));

        // Create the Instances header with initial capacity of 0.
        // "ExpenseData" is the dataset name.
        this.dataStruct = new Instances("ExpenseData", attributes, 0);
        this.dataStruct.setClassIndex(attributes.size() - 1); // Set class index to last attribute
    }

    public predictCat(NaiveBayes model, Instances dataStruct) {
        this.model = model;
        this.dataStruct = dataStruct;
    }

    public String predictCat(String description) throws Exception {
        // Create a new instance for prediction
        Instance inst = new DenseInstance(dataStruct.numAttributes());
        inst.setDataset(dataStruct);

        // Initialize all the keyword attributes as 0 (absence of the word)
        for (int i = 0; i < dataStruct.numAttributes() - 1; i++) {
            String keyword = dataStruct.attribute(i).name(); // Get keyword
            if (description.toLowerCase().contains(keyword.toLowerCase())) {
                inst.setValue(i, 1); // Set 1 if keyword is present in the description
            } else {
                inst.setValue(i, 0); // Set 0 otherwise
            }
        }

        // Use the trained model to classify the instance
        double labelIndx = model.classifyInstance(inst);

        // Return the predicted category
        return dataStruct.classAttribute().value((int) labelIndx);
    }

    public String predictCatFromFilteredInstance(Instance instance) throws Exception {
        double predictedIndx = model.classifyInstance(instance);
        return dataStruct.classAttribute().value((int) predictedIndx);
    }
}
