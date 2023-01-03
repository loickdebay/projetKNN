package javafx;
import distance.Distance;
import distance.DistanceEuclidienne;
import javafx.fxml.FXML;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import knn.KNN;
import knn.KNNValuesConverter;
import knn.Pokemon;
import utils.KNNSubject;

import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * every method related to the Pokemon data viewer
 */
public class PokemonWindowController {
    List<Pokemon> customPokemons = new ArrayList<Pokemon>();
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
    private Button generateNewChartButton;
    @FXML
    private Button generateTestValues;
    @FXML
    private TextField nameField;
    @FXML
    private TextField attackField;
    @FXML
    private TextField defenseField;
    @FXML
    private TextField spAttackField;
    @FXML
    private TextField spDefenseField;
    @FXML
    private TextField speedField;
    @FXML
    private TextField hpField;
    @FXML
    private TextField captureRateField;
    @FXML
    private TextField experienceGrowthField;
    @FXML
    private TextField baseEggStepsField;
    @FXML
    private ChoiceBox firstTypeChooser;
    @FXML
    private ChoiceBox secondaryTypeChooser;
    @FXML
    private Button addCustomPokemonButton;
    @FXML
    private TextField knnField;
    @FXML
    private Label accuracyLabel;
    @FXML
    private Label isLegendaryLabel;
    private KNNSubject model;
    private KNNValuesConverter knnValuesConverter = new KNNValuesConverter();
    @FXML
    private ChoiceBox distanceChooser;
    String regex = "\\d{0,7}([\\.]\\d{0,2})?";

    /**
     * Fills the choiceBoxes with every pokemon parameter you can visualize on the scatterChart
     */
    public void listChoiceBoxesChoices(){
        List<String> xList = Arrays.asList("Attack","Base Egg Steps","Capture Rate","Defense","Experience Growth","HP","Special Attack","Special Defense","Is Legendary","Speed","First Type","Secondary Type");
        List<String> yList = Arrays.asList("Attack","Base Egg Steps","Capture Rate","Defense","Experience Growth","HP","Special Attack","Special Defense","Is Legendary","Speed","First Type","Secondary Type");
        List<String> firstTypeList = Arrays.asList("Bug","Dark","Dragon","Electric","Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic","Rock","Steel","Water");
        List<String> secondaryTypeList = Arrays.asList("Bug","Dark","Dragon","Electric","Fairy","Fighting","Fire","Flying","Ghost","Grass","Ground","Ice","Normal","Poison","Psychic","Rock","Steel","Water");
        List<String> distanceList = Arrays.asList("Euclidean","Normalized Euclidean","Manhattan","Normalized Manhattan");
        distanceChooser.getItems().addAll(distanceList);
        xChoice.getItems().addAll(xList);
        yChoice.getItems().addAll(yList);
        firstTypeChooser.getItems().addAll(firstTypeList);
        secondaryTypeChooser.getItems().addAll(secondaryTypeList);

    }
    /**
     * allow to get the value from a string and to get a specific value from a pokemon according to the string
     * @param parameter the String that contains the value name
     * @param poke the Pokemon object where we want to get the value from
     * @return the double value of the parameter
     */
    public double getValueFromString(String parameter, Pokemon poke){
        switch(parameter){
            case "Attack": return poke.getAttack();
            case "Base Egg Steps": return poke.getBaseEggSteps();
            case "Capture Rate": return poke.getCaptureRate();
            case "Defense": return poke.getDefense();
            case "Experience Growth": return poke.getExperienceGrowth();
            case "HP": return poke.getHp();
            case "Special Attack": return poke.getSpAttack();
            case "Special Defense": return poke.getSpDefense();
            case "Speed": return poke.getSpeed();
            case "Is Legendary":return knnValuesConverter.isLegendaryToInt(poke.isLegendary());
            case "First Type":return knnValuesConverter.pokemonType(poke.getType1());
            case "Secondary Type":return knnValuesConverter.pokemonType(poke.getType2());
        }
        return 0.0;
    }

    /**
     * this method is used to place the pokemons datas on the scatterChart
     */
    public void loadNewChart() {
        if(knnField.getText().matches(regex)&&!knnField.getText().equals("")&&distanceChooser.getValue()!=null) {
            fillKnnFields();
            String firstP = xChoice.getSelectionModel().getSelectedItem().toString();
            String secondP = yChoice.getSelectionModel().getSelectedItem().toString();
            xAxis.setLabel(firstP);
            yAxis.setLabel(secondP);
            KNN knn = new Pokemon();
            model = Mainfx.getKNNSubject();
            ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
            XYChart.Series<Number, Number> nonLegendary = new XYChart.Series<>();
            nonLegendary.setName("Non Legendary");
            XYChart.Series<Number, Number> legendary = new XYChart.Series<>();
            legendary.setName("Legendary");
            for (KNN poke : test) {
                Pokemon p = (Pokemon) poke;
                if (!p.isLegendary()) {
                    nonLegendary.getData().add(new XYChart.Data(getValueFromString(firstP, p), getValueFromString(secondP, p), p));
                } else {
                    legendary.getData().add(new XYChart.Data(getValueFromString(firstP, p), getValueFromString(secondP, p), p));
                }
            }
            graph.getData().clear();
            graph.getData().add(nonLegendary);
            graph.getData().add(legendary);
            placeCustomPokemon(firstP, secondP);
            showTooltipOnHover(legendary);
            showTooltipOnHover(nonLegendary);
        }else{
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setAlertType(Alert.AlertType.WARNING);
            alert.setContentText("Please fill the knn fields");
            alert.show();
        }
    }
    /**
     * Creates a custom pokemon from the textfields and choiceBoxes values
     */
    public void addCustomPokemonToScatterChart(){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setAlertType(Alert.AlertType.WARNING);
        if (!attackField.getText().matches(regex)||!defenseField.getText().matches(regex)||!spAttackField.getText().matches(regex)||!spDefenseField.getText().matches(regex)||!speedField.getText().matches(regex)||!hpField.getText().matches(regex)||!captureRateField.getText().matches(regex)||!experienceGrowthField.getText().matches(regex)||!baseEggStepsField.getText().matches(regex)) {
            alert.setContentText("Wrong type, please stick to numeric values");
            alert.show();
        }
        if(distanceChooser.getValue()==null||nameField.getText().equals("")||attackField.getText().equals("")||defenseField.getText().equals("")||spAttackField.getText().equals("")||spDefenseField.getText().equals("")||speedField.getText().equals("")||hpField.getText().equals("")||captureRateField.getText().equals("")||experienceGrowthField.getText().equals("")||baseEggStepsField.getText().equals("")||firstTypeChooser.getValue()==null||secondaryTypeChooser.getValue()==null){
            alert.setContentText("Please fill every field");
            alert.show();
        }
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceChooser.getSelectionModel().getSelectedItem().toString());
        int knnK = Integer.parseInt(knnField.getText());
        Pokemon newPoke = new Pokemon(nameField.getText(),Integer.parseInt(attackField.getText()),Integer.parseInt(baseEggStepsField.getText()),Double.parseDouble(captureRateField.getText()),Integer.parseInt(defenseField.getText()),Integer.parseInt(experienceGrowthField.getText()),Integer.parseInt(hpField.getText()),Integer.parseInt(spAttackField.getText()),Integer.parseInt(spDefenseField.getText()),firstTypeChooser.getSelectionModel().getSelectedItem().toString(),secondaryTypeChooser.getSelectionModel().getSelectedItem().toString(),Double.parseDouble(speedField.getText()));
        Pokemon customPoke = newPoke.getGeneratedType(knnK,newPoke,distanceKNN);
        isLegendaryLabel.setText("isLegendary : "+customPoke.isLegendary());
        customPokemons.add(customPoke);
        if(!customPokemons.isEmpty()){
           loadNewChart();
        }
    }
    public void fillKnnFields(){
        Distance distanceKNN = knnValuesConverter.getDistanceFromString(distanceChooser.getSelectionModel().getSelectedItem().toString());
        int knnK = Integer.parseInt(knnField.getText());
        Pokemon newPoke = new Pokemon();
        DecimalFormat df = new DecimalFormat("0.00");
        List <KNN> pokeTest = newPoke.charger("ressources/pokemon_test.csv");
        df.setRoundingMode(RoundingMode.DOWN);
        double accuracy = newPoke.accuracy(pokeTest,distanceKNN,knnK);
        accuracyLabel.setText("Accuracy : "+df.format(accuracy)+"%");
    }

    /**
     * places a custom pokemon on the scatterchart according to the given parameters
     * @param firstP the X axis parameter
     * @param secondP the Y axis parameter
     */
    public void placeCustomPokemon(String firstP, String secondP){
        XYChart.Series<Number,Number> nonLegendary = new XYChart.Series<>();
        nonLegendary.setName("Custom Non Legendary");
        XYChart.Series<Number,Number> legendary = new XYChart.Series<>();
        legendary.setName("Custom Legendary");
        for (Pokemon poke : customPokemons) {
            if(!poke.isLegendary()) {
                nonLegendary.getData().add(new XYChart.Data(getValueFromString(firstP, poke), getValueFromString(secondP, poke),poke));
            }else {
                legendary.getData().add(new XYChart.Data(getValueFromString(firstP, poke), getValueFromString(secondP, poke),poke));
            }
        }
        graph.getData().add(legendary);
        graph.getData().add(nonLegendary);
        clearField();
        showTooltipOnHover(legendary);
        showTooltipOnHover(nonLegendary);
    }

    /**
     * Allows to see the details of any Node on the scatterChart when hovered
     * @param series The placed nodes in a "list"
     */
    public void showTooltipOnHover (XYChart.Series<Number,Number> series){
        for(var data : series.getData()){
            var dataNode = data.getNode();
            Pokemon pokemon = (Pokemon) data.getExtraValue();
            var tooltip = new Tooltip(pokemon.toStringJFX());
            Tooltip.install(dataNode,tooltip);
            dataNode.setOnMouseClicked(MouseEvent -> model.setKNN((KNN) data.getExtraValue()));
        }
    }

    /**
     * clears every textfield and choiceboxes after the custom pokemon is added
     */
    public void clearField(){
        baseEggStepsField.clear();nameField.clear();attackField.clear();defenseField.clear();spAttackField.clear();spDefenseField.clear();speedField.clear();hpField.clear();captureRateField.clear();experienceGrowthField.clear();
        firstTypeChooser.setValue("");secondaryTypeChooser.setValue("");
    }
    public void fillTestsFields(){
        nameField.setText("customPokemon");
        attackField.setText("255");defenseField.setText("255");speedField.setText("255");
        spDefenseField.setText("255");spAttackField.setText("255");
        hpField.setText("300");
        captureRateField.setText("1000");
        experienceGrowthField.setText("1000000");
        baseEggStepsField.setText("25000");
        firstTypeChooser.setValue("Fire");
        secondaryTypeChooser.setValue("Ground");
    }
}