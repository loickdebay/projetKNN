package KNN;

import distance.Distance;
import distance.DistanceEuclidienne;
import distance.DistanceManhattan;
import distance.DistanceManhattanNormalisee;
import knn.KNNValuesConverter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ValuesConverterTest {
    private static KNNValuesConverter converter;

    @BeforeAll
    public static void setUp() {
        converter = new KNNValuesConverter();
    }

    @Test
    public void testPokemonType() {
        assertEquals(converter.pokemonType("grass"), 10);
        assertEquals(converter.pokemonType("fire"), 7);
        assertNotEquals(converter.pokemonType("fire"), 10);
        assertEquals(converter.pokemonType("explosif"), 0);
    }

    @Test
    public void testVariety(){
        assertEquals(converter.getVarietyFromString("Setosa"), 1);
        assertEquals(converter.getVarietyFromString("Versicolor"), 2);
        assertNotEquals(converter.getVarietyFromString("Versicolor"), 1);
        assertEquals(converter.getVarietyFromString("explosif"), 0);
    }

    @Test
    public void testIntFromPort(){
        assertEquals(converter.getIntFromPort('C'), 1);
        assertEquals(converter.getIntFromPort('Q'), 3);
        assertNotEquals(converter.getIntFromPort('Q'), 1);
        assertEquals(converter.getIntFromPort('Z'), 0);
    }

    @Test
    public void testIntFromSex(){
        assertEquals(converter.getIntFromSex("female"), 2);
        assertNotEquals(converter.getIntFromSex("female"), 1);
        assertEquals(converter.getIntFromSex("explosif"), 0);
    }

    @Test
    public void testIntFromSurvived(){
        assertEquals(converter.getIntFromSurvived(true), 2);
        assertEquals(converter.getIntFromSurvived(false), 1);
        assertNotEquals(converter.getIntFromSurvived(true), 1);
    }

    @Test
    public void testDistanceFromString(){
        assertEquals(converter.getDistanceFromString("Euclidean").getClass(), DistanceEuclidienne.class);
        assertEquals(converter.getDistanceFromString("Manhattan").getClass(), DistanceManhattan.class);
        assertEquals(converter.getDistanceFromString("Normalized Manhattan").getClass(), DistanceManhattanNormalisee.class);
        assertEquals(converter.getDistanceFromString("explosif").getClass(), DistanceEuclidienne.class);
    }
}
