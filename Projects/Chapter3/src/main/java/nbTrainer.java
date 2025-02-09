import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import java.util.Random;

public class nbTrainer {

    public NaiveBayes trainM(Instances trainingData) throws Exception {
        NaiveBayes nb = new NaiveBayes();
        nb.buildClassifier(trainingData);

        Evaluation eval = new Evaluation(trainingData);
        eval.crossValidateModel(nb, trainingData, 10, new Random(1));

        System.out.println(eval.toSummaryString("\nResults\n======\n", false));

        return nb;
    }
}
