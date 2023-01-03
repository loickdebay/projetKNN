package javafx;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeAll;

import javafx.DataViewerController;
import knn.KNN;
import knn.Titanic;
import knn.Iris;
import knn.Pokemon;
import knn.KNNValuesConverter;


public class DataControllerTest {
	
	private static IrisWindowController irisControl;
	private static TitanicWindowController titanicControl;
	private static PokemonWindowController pokeControl;
	private static KNNValuesConverter converter;

    @BeforeAll
    public static void setUp() {
        irisControl = new IrisWindowController();
        titanicControl = new TitanicWindowController();
        pokeControl = new PokemonWindowController();
        converter = new KNNValuesConverter();
    }
    
    @Test
    public void Datas_getValueToStringTest() {
    	Iris i1 = new Iris(5.1,3.5,1.4,0.2,"Setosa");
    	Titanic t1 = new Titanic(350,false,3,"Dimic, Mr. Jovan","male",42,0,0,"315088",8.6625," ",'S');
    	Pokemon p1 = new Pokemon("Aggron",140,8960,45.0,230,1250000,70,80,50,"steel","rock",360.0,false);
    	
    	double variety = converter.getVarietyFromString(i1.getVariety()) + 0.0;
    	double type1 = converter.pokemonType(p1.getType1());
    	double type2 = converter.pokemonType(p1.getType2());
    	
    	//Iris
    	assertEquals(5.1, irisControl.getValueFromString("Sepal Length", i1));
    	assertEquals(3.5, irisControl.getValueFromString("Sepal Width", i1));
    	assertEquals(1.4, irisControl.getValueFromString("Petal Length", i1));
    	assertEquals(0.2, irisControl.getValueFromString("Petal Width", i1));
    	assertEquals(variety, irisControl.getValueFromString("Variety", i1)); 
    	assertNotEquals(6.8, irisControl.getValueFromString("autrechose", i1));
    	
    	//Titanic
    	assertEquals(350, titanicControl.getValueFromString("Passenger Id", t1));
    	assertEquals(1, titanicControl.getValueFromString("Survived", t1));
    	assertEquals(3, titanicControl.getValueFromString("Passenger class", t1));
    	assertEquals(42, titanicControl.getValueFromString("Age", t1));
    	assertEquals(1, titanicControl.getValueFromString("Sex", t1));
    	assertEquals(0, titanicControl.getValueFromString("Siblings/Spouses", t1));
    	assertEquals(0, titanicControl.getValueFromString("Parents/Children", t1));
    	assertEquals(8.6625, titanicControl.getValueFromString("Passenger Fare", t1));
    	assertEquals(2, titanicControl.getValueFromString("Port of Embarkation", t1));
    	assertNotEquals(22, titanicControl.getValueFromString("Stringquelquechosed'autre", t1));
    	
    	//Pokemon
    	assertEquals(140, pokeControl.getValueFromString("Attack", p1));
    	assertEquals(8960, pokeControl.getValueFromString("Base Egg Steps", p1));
    	assertEquals(45.0, pokeControl.getValueFromString("Capture Rate", p1));
    	assertEquals(230, pokeControl.getValueFromString("Defense", p1));
    	assertEquals(1250000, pokeControl.getValueFromString("Experience Growth", p1));
    	assertEquals(70, pokeControl.getValueFromString("HP", p1));
    	assertEquals(80, pokeControl.getValueFromString("Special Attack", p1));
    	assertEquals(50, pokeControl.getValueFromString("Special Defense", p1));
    	assertEquals(360.0, pokeControl.getValueFromString("Speed", p1));
    	assertEquals(type1, pokeControl.getValueFromString("First Type", p1));
    	assertEquals(type2, pokeControl.getValueFromString("Secondary Type", p1));
    	assertNotEquals(340, pokeControl.getValueFromString("n'importequoid'autre", p1));
    }
}
