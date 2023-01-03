package javafx;

import distance.Distance;
import distance.DistanceEuclidienne;
import distance.DistanceManhattan;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import knn.KNN;
import knn.Iris;
import knn.KNNValuesConverter;
import utils.KNNSubject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Controller for the iris window, every iris related javafx method is here
 */
public class IrisWindowController {
    private KNNSubject model;
    List<Iris> customIris = new ArrayList<>();
    @FXML
    private ScatterChart<Number,Number> graph ;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;
    @FXML
    private ChoiceBox xChoice;
    @FXML
    private ChoiceBox yChoice;
    @FXML
    private TextField sepalLengthField;
    @FXML
    private TextField sepalWidthField;
    @FXML
    private TextField petalLengthField;
    @FXML
    private TextField petalWidthField;
    @FXML
    private Button addCustomIris;
    @FXML
    private Label varietyLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private TextField knnField;
    private KNNValuesConverter knnValuesConverter = new KNNValuesConverter();
    @FXML
    private ChoiceBox distanceTypeChooser;
    String regex = "\\d{0,7}([\\.]\\d{0,2})?";


    /**
     * Fills every choice box on the interface
     */
    public void listChoiceBoxesChoices(){
        List<String> xList = Arrays.asList("Sepal Length","Sepal Width","Petal Length", "Petal Width","Variety");
        List<String> yList = Arrays.asList("Sepal Length","Sepal Width","Petal Length", "Petal Width","Variety");
        List<String> distanceList = Arrays.asList("Euclidean","Normalized Euclidean","Manhattan","Normalized Manhattan");
        distanceTypeChooser.getItems().addAll(distanceList);
        xChoice.getItems().addAll(xList);
        yChoice.getItems().addAll(yList);
    }

    /**
     * Allows the choiceBoxes to return a String and to get the associated value
     * @param parameter the given String
     * @param iris the Iris object, necessary to return the actual value
     * @return the value as a double
     */
    public double getValueFromString(String parameter, Iris iris){
        switch(parameter){
            case "Sepal Length": return iris.getSepalLength()+0.0;
            case "Sepal Width": return iris.getSepalWidth()+0.0;
            case "Petal Length": return iris.getPetalLength()+0.0;
            case "Petal Width": return iris.getPetalWidth()+0.0;
            case "Variety": return knnValuesConverter.getVarietyFromString(iris.getVariety());

        }
        return 0.0;
    }

    /**
     * this method is used to place the iris data on the scatterChart
     */
    public void loadNewChart() {
        if(knnField.getText().matches(regex)&&!knnField.getText().equals("")&&distanceTypeChooser.getValue()!=null) {
            fillKnnFields();
            String firstP = xChoice.getSelectionModel().getSelectedItem().toString();
            String secondP = yChoice.getSelectionModel().getSelectedItem().toString();
            xAxis.setLabel(firstP);
            yAxis.setLabel(secondP);
            KNN knn = new Iris();
            model = Mainfx.getKNNSubject();
            ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
            XYChart.Series<Number, Number> setosa = new XYChart.Series<>();
            setosa.setName("Setosa");
            XYChart.Series<Number, Number> versicolor = new XYChart.Series<>();
            versicolor.setName("Versicolor");
            XYChart.Series<Number, Number> virginica = new XYChart.Series<>();
            virginica.setName("Virginica");
            for (KNN iris : test) {
                Iris p = (Iris) iris;
                if (p.getVariety().equals("Setosa")) {
                    setosa.getData().add(new XYChart.Data(getValueFromString(firstP, p), getValueFromString(secondP, p), p));
                }
                if (p.getVariety().equals("Versicolor")) {
                    versicolor.getData().add(new XYChart.Data(getValueFromString(firstP, p), getValueFromString(secondP, p), p));
                }
                if (p.getVariety().equals("Virginica")) {
                    virginica.getData().add(new XYChart.Data(getValueFromString(firstP, p), getValueFromString(secondP, p), p));
                }
            }
            graph.getData().clear();
            graph.getData().add(setosa);
            graph.getData().add(versicolor);
            graph.getData().add(virginica);
            placeCustomIris(firstP, secondP);
            showTooltipOnHover(virginica);
            showTooltipOnHover(setosa);
            showTooltipOnHover(versicolor);
        }else {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the knn fields");
            alert.show();
        }
    }

    /**
     * Creates a custom iris from the textfields and choiceBoxes values
     */
    public void addCustomIrisToScatterChart(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        if (!sepalLengthField.getText().matches(regex)||!sepalWidthField.getText().matches(regex)||!petalLengthField.getText().matches(regex)||!petalWidthField.getText().matches(regex)){
            alert.setContentText("Wrong type, please stick to numeric values");
            alert.show();
        }
        if(knnField.getText().equals("")||distanceTypeChooser.getValue()==null||sepalLengthField.getText().equals("")||sepalWidthField.getText().equals("")||petalLengthField.getText().equals("")||petalWidthField.getText().equals("")){
            alert.setContentText("Please fill every field");
            alert.show();
        }
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceTypeChooser.getSelectionModel().getSelectedItem().toString());
        int knnK = Integer.parseInt(knnField.getText());
        Iris newIris = new Iris(Double.parseDouble(sepalLengthField.getText()),Double.parseDouble(sepalWidthField.getText()),Double.parseDouble(petalLengthField.getText()),Double.parseDouble(petalWidthField.getText()));
        Iris customIrisObject = newIris.getGeneratedType(knnK,newIris, distanceKNN);
        customIris.add(customIrisObject);
        varietyLabel.setText("Variety : "+customIrisObject.getVariety());
        if(!customIris.isEmpty()){
            loadNewChart();
        }
    }
    public void fillKnnFields(){
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceTypeChooser.getSelectionModel().getSelectedItem().toString());
        int knnK = Integer.parseInt(knnField.getText());
        Iris newIris = new Iris();
        DecimalFormat df = new DecimalFormat("0.00");
        List <KNN> irisTest = newIris.charger("ressources/iris_test.csv");
        df.setRoundingMode(RoundingMode.DOWN);
        double accuracy = newIris.accuracy(irisTest,distanceKNN,knnK);
        accuracyLabel.setText("Accuracy : "+df.format(accuracy)+"%");
    }

    /**
     * places a custom iris on the scatterchart according to the given parameters
     * @param firstP the X axis parameter
     * @param secondP the Y axis parameter
     */
    public void placeCustomIris(String firstP, String secondP) {
        XYChart.Series<Number, Number> setosa = new XYChart.Series<>();
        setosa.setName("Custom Setosa");
        XYChart.Series<Number, Number> versicolor = new XYChart.Series<>();
        versicolor.setName("Custom Versicolor");
        XYChart.Series<Number, Number> virginica = new XYChart.Series<>();
        virginica.setName("Custom Virginica");
        for (Iris iris : customIris) {
            if (iris.getVariety().equals("Setosa")) {
                setosa.getData().add(new XYChart.Data(getValueFromString(firstP, iris), getValueFromString(secondP, iris), iris));
                graph.getData().add(setosa);
            }
            if (iris.getVariety().equals("Versicolor")) {
                if(!versicolor.getData().contains(new XYChart.Data(getValueFromString(firstP, iris), getValueFromString(secondP, iris), iris))) {
                    versicolor.getData().add(new XYChart.Data(getValueFromString(firstP, iris), getValueFromString(secondP, iris), iris));
                }
            }
            if (iris.getVariety().equals("Virginica")) {
                virginica.getData().add(new XYChart.Data(getValueFromString(firstP, iris), getValueFromString(secondP, iris), iris));
            }
            graph.getData().add(versicolor);
            graph.getData().add(virginica);
            clearFields();
            showTooltipOnHover(virginica);
            showTooltipOnHover(setosa);
            showTooltipOnHover(versicolor);

        }
    }

    /**
     * clears every textfields after the custom iris is added
     */
    public void clearFields(){
        sepalWidthField.clear();sepalLengthField.clear();petalWidthField.clear();petalLengthField.clear();
    }
    /**
     * Allows to see the details of any Node on the scatterChart when hovered
     * @param series The placed nodes in a "list"
     */
    public void showTooltipOnHover (XYChart.Series<Number,Number> series){
        for(var data : series.getData()){
            var dataNode = data.getNode();
            Iris iris = (Iris) data.getExtraValue();
            var tooltip = new Tooltip(iris.toStringJFX());
            Tooltip.install(dataNode,tooltip);
            dataNode.setOnMouseClicked(MouseEvent -> model.setKNN((KNN) data.getExtraValue()));

        }
    }
    public void fillTestsFields(){
        sepalWidthField.setText("7");
        sepalLengthField.setText("7");
        petalLengthField.setText("7");
        petalWidthField.setText("7");
    }
}
