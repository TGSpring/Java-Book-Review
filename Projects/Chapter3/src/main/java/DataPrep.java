import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.tokenizers.NGramTokenizer;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class DataPrep {
    public Instances prepData(String filePath) throws Exception {
        DataSource src = new DataSource(filePath);
        Instances data = src.getDataSet();
        data.setClassIndex(data.numAttributes() - 1);

        StringToWordVector filter = new StringToWordVector();
        filter.setTokenizer(new NGramTokenizer());
        filter.setInputFormat(data);
        Instances filteredData = Filter.useFilter(data, filter);

        return filteredData;
    }
}
