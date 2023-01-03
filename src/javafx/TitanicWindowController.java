package javafx;

import distance.Distance;
import distance.DistanceEuclidienne;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import knn.KNNValuesConverter;
import knn.Pokemon;
import knn.Titanic;
import knn.KNN;
import utils.KNNSubject;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * every javafx method related to titanic
 */
public class TitanicWindowController {
    private final KNNValuesConverter knnValuesConverter = new KNNValuesConverter();
    private KNNSubject model;
    List<KNN> customTitanic = new ArrayList<>();
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
    private TextField nameField;
    @FXML
    private TextField passengerIdField;
    @FXML
    private TextField passengerClassField;
    @FXML
    private TextField cabinField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField sibpField;
    @FXML
    private TextField parchField;
    @FXML
    private TextField ticketField;
    @FXML
    private TextField passengerFareField;
    @FXML
    private ChoiceBox sexChooser;
    @FXML
    private ChoiceBox portOfEmbarkationChooser;
    @FXML
    private Button addCustomTitanicButton;
    @FXML
    private Label survivedLabel;
    @FXML
    private Label accuracyLabel;
    @FXML
    private TextField knnTextField;
    @FXML
    private ChoiceBox distanceChooser;
    String regex = "\\d{0,7}([\\.]\\d{0,2})?";
    /**
     * clears every field when a custom titanic is added
     */
    public void clearFields(){
        nameField.clear();passengerIdField.clear();passengerClassField.clear();cabinField.clear();ageField.clear();sibpField.clear();parchField.clear();ticketField.clear();passengerFareField.clear();
    }

    /**
     * fills the scatter chart with the selected axis parameter
     */
    public void loadNewChart() {
        if(knnTextField.getText().matches(regex)&&!knnTextField.getText().equals("")&&distanceChooser.getValue()!=null) {
            fillKnnFields();
            String firstP = xChoice.getSelectionModel().getSelectedItem().toString();
            String secondP = yChoice.getSelectionModel().getSelectedItem().toString();
            xAxis.setLabel(firstP);
            yAxis.setLabel(secondP);
            KNN knn = new Titanic();
            model = Mainfx.getKNNSubject();
            ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
            XYChart.Series<Number, Number> survived = new XYChart.Series<>();
            survived.setName("Survived");
            XYChart.Series<Number, Number> notSurvived = new XYChart.Series<>();
            notSurvived.setName("Died ");
            for (KNN iris : test) {
                Titanic titanic = (Titanic) iris;
                if (titanic.isSurvived()) {
                    survived.getData().add(new XYChart.Data(getValueFromString(firstP, titanic), getValueFromString(secondP, titanic), titanic));
                }
                if (!titanic.isSurvived()) {
                    notSurvived.getData().add(new XYChart.Data(getValueFromString(firstP, titanic), getValueFromString(secondP, titanic), titanic));
                }
            }
            graph.getData().clear();
            graph.getData().add(survived);
            graph.getData().add(notSurvived);
            clearFields();
            placeCustomTitanic(firstP, secondP);
            showTooltipOnHover(survived);
            showTooltipOnHover(notSurvived);

        }else{
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the knn fields");
            alert.show();
        }
    }

    /**
     * returns the associated double from a string in a titanic (used mostly for the choiceboxes)
     * @param parameter the String we want to convert
     * @param titanic the titanic we need to get the values
     * @return the corresponding double
     */
    public double getValueFromString(String parameter, Titanic titanic){
        switch(parameter){
            case "Passenger Id":
                return titanic.getPassengerId();
            case "Survived":
                return knnValuesConverter.getIntFromSurvived(titanic.isSurvived());
            case "Passenger class":
                return titanic.getpClass();
            case "Sex":
                return knnValuesConverter.getIntFromSex(titanic.getSexe());
            case "Age":
                return titanic.getAge();
            case "Siblings/Spouses":
                return titanic.getSibSp();
            case "Parents/Children":
                return titanic.getParch();
            case "Passenger Fare":
                return titanic.getFare();
            case "Port of Embarkation":
                return knnValuesConverter.getIntFromPort(titanic.getEmbarked());
        }
        return 0.0;
    }

    /**
     * fills the choiceboxes with the needed values
     */
    public void listChoiceBoxesChoices(){
        List<String> xList = Arrays.asList("Passenger Id","Survived","Passenger class","Sex","Age","Siblings/Spouses","Parents/Children","Passenger Fare","Port of Embarkation");
        List<String> yList = Arrays.asList("Passenger Id","Survived","Passenger class","Sex","Age","Siblings/Spouses","Parents/Children","Passenger Fare","Port of Embarkation");
        List<String> sexList = Arrays.asList("Male","Female");
        List<String> portList = Arrays.asList("Cherbourgh","Queenstown","Southampton");
        List<String> distanceList = Arrays.asList("Euclidean","Normalized Euclidean","Manhattan","Normalized Manhattan");
        distanceChooser.getItems().addAll(distanceList);
        sexChooser.getItems().addAll(sexList);
        portOfEmbarkationChooser.getItems().addAll(portList);
        xChoice.getItems().addAll(xList);
        yChoice.getItems().addAll(yList);
    }
    /**
     * Allows to see the details of any Node on the scatterChart when hovered
     * @param series The placed nodes in a "list"
     */
    public void showTooltipOnHover (XYChart.Series<Number,Number> series){
        for(var data : series.getData()){
            var dataNode = data.getNode();
            Titanic titanic = (Titanic) data.getExtraValue();
            var tooltip = new Tooltip(titanic.toStringJFX());
            Tooltip.install(dataNode,tooltip);
            dataNode.setOnMouseClicked(MouseEvent -> model.setKNN((KNN) data.getExtraValue()));

        }
    }

    /**
     * creates a custom pokemon from the textfields and choiceboxes values
     */
    public void addCustomTitanicToScatterChart(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        if (!passengerIdField.getText().matches(regex)||!isPassengerFieldCorrect(passengerClassField.getText())||!ageField.getText().matches(regex)||!sibpField.getText().matches(regex)||!parchField.getText().matches(regex)||!passengerFareField.getText().matches(regex)){
            alert.setContentText("Wrong type, please stick to correct numeric values");
            alert.show();
            return;
        }
        if(distanceChooser.getValue()==null||passengerFareField.getText().equals("")||ticketField.getText().equals("")||parchField.getText().equals("")||sibpField.getText().equals("")||ageField.getText().equals("")||passengerIdField.getText().equals("")||nameField.getText().equals("")||passengerClassField.getText().equals("")||cabinField.getText().equals("")||portOfEmbarkationChooser.getValue()==null||sexChooser.getValue()==null){
            alert.setContentText("Please fill every required field");
            alert.show();
            return;

        }
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceChooser.getSelectionModel().getSelectedItem().toString());
        int knnAmount = Integer.parseInt(knnTextField.getText());
        Titanic titanic =new Titanic(Integer.parseInt(passengerIdField.getText()),Integer.parseInt(passengerClassField.getText()),nameField.getText(),sexChooser.getSelectionModel().getSelectedItem().toString(),Integer.parseInt(ageField.getText()),Integer.parseInt(sibpField.getText()),Integer.parseInt(parchField.getText()),ticketField.getText(),Double.parseDouble(passengerFareField.getText()),cabinField.getText(),getPortCharFromString(portOfEmbarkationChooser.getSelectionModel().getSelectedItem().toString()));
        Titanic newTitanic = titanic.getGeneratedType(8,titanic,distanceKNN);
        customTitanic.add(newTitanic);
        survivedLabel.setText("Survived : "+newTitanic.isSurvived());
        if(!customTitanic.isEmpty()){
            loadNewChart();
        }
    }
    public void fillKnnFields(){
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceChooser.getSelectionModel().getSelectedItem().toString());
        int knnK = Integer.parseInt(knnTextField.getText());
        Titanic newTitanic = new Titanic();
        DecimalFormat df = new DecimalFormat("0.00");
        List <KNN> titanicTest = newTitanic.charger("ressources/titanic_test.csv");
        df.setRoundingMode(RoundingMode.DOWN);
        double accuracy = newTitanic.accuracy(titanicTest,distanceKNN,knnK);
        accuracyLabel.setText("Accuracy : "+df.format(accuracy)+"%");
    }
    /**
     * Get the char of the embarkation port from the full name
     * @param portIdentifier the port's name
     * @return the corresponding char
     */
    public char getPortCharFromString (String portIdentifier){
        if(portIdentifier.equals("Cherbourg"))return 'C';
        if(portIdentifier.equals("Queenstown"))return 'Q';
        if(portIdentifier.equals("Southampton"))return 'S';
        return 'C';
    }

    /**
     * checks is the class picked is between 1,2 and 3
     * @param string the textfield value
     * @return true if it's right, false otherwise
     */
    public boolean isPassengerFieldCorrect (String string){
        if(!string.equals("")) {
            return Integer.parseInt(string) > 0 && Integer.parseInt(string) < 4;
        }else return false;
    }

    /**
     * places a custom titanic on the scatterchart from the X and Y axis' parameters
     * @param firstP X axis parameter
     * @param secondP Y axis parameter
     */
    public void placeCustomTitanic(String firstP, String secondP) {
        XYChart.Series<Number,Number> survived = new XYChart.Series<>();
        survived.setName("Custom Survived");
        XYChart.Series<Number,Number> notSurvived = new XYChart.Series<>();
        notSurvived.setName("Custom Died ");
        for (KNN boat : customTitanic) {
            Titanic titanic = (Titanic) boat;
            if(titanic.isSurvived()) {
                survived.getData().add(new XYChart.Data(getValueFromString(firstP, titanic), getValueFromString(secondP, titanic), titanic));
                graph.getData().add(survived);
            }
            if(!titanic.isSurvived()){
                notSurvived.getData().add(new XYChart.Data(getValueFromString(firstP, titanic), getValueFromString(secondP, titanic),titanic));
                graph.getData().add(notSurvived);
            }
        }
        showTooltipOnHover(survived);
        showTooltipOnHover(notSurvived);
    }
    public void fillTestsFields(){
        nameField.setText("Mr. Chlebowski");
        passengerIdField.setText("465");
        passengerClassField.setText("1");//Mr. Chlebowski ne voyage tout de même pas n'importe comment !
        cabinField.setText("56");
        ageField.setText("26");
        sibpField.setText("0");
        parchField.setText("0");
        ticketField.setText("4569b4ac41");
        passengerFareField.setText("25.99"); //grâce à la formule crous
        sexChooser.setValue("Male");
        portOfEmbarkationChooser.setValue("Queenstown");
    }
}
