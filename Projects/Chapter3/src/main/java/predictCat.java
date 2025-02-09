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

        // Description attribute as a string attribute
        attributes.add(new Attribute("description", (List<String>) null));

        // The class attribute with possible values
        ArrayList<String> classValues = new ArrayList<>(Arrays.asList("Food", "Entrainment", "Transport"));
        attributes.add(new Attribute("category", classValues));

        // Create the Instances header with initial capacity of 0.
        // "ExpenseData" is the dataset name.
        this.dataStruct = new Instances("ExpenseData", attributes, 0);

        // Set the class index to the second attributes.
        this.dataStruct.setClassIndex(1);
    }

    public predictCat(NaiveBayes model, Instances dataStruct) {
        this.model = model;
        this.dataStruct = dataStruct;

    }

    public String predictCat(String description) throws Exception {
        Instance inst = new DenseInstance(dataStruct.numAttributes());
        inst.setDataset(dataStruct);
        inst.setValue(0, description);

        double labelIndx = model.classifyInstance(inst);
        return dataStruct.classAttribute().value((int) labelIndx);
    }

    public String predictCatFromFilteredInstance(Instance instance) throws Exception {
        double predictedIndx = model.classifyInstance(instance);
        return dataStruct.classAttribute().value((int) predictedIndx);
    }

}
