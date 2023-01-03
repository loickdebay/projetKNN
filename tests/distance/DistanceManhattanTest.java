package distance;

import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceManhattanTest {

    @Test
    void testDistancePokemon() {
        KNN t1 = new Pokemon("Swablu",40,5120,255.0,60,600000,45,75,50,"normal","flying",1.2,false);
        KNN t2 = new Pokemon("Aggron",140,8960,45.0,230,1250000,70,80,50,"steel","rock",360.0,false);

        assertEquals(new DistanceManhattan().distance(t1, t2), 654408.8, 0.01);
    }

    @Test
    void testDistanceIris() {
        KNN i1 = new Iris(7.0, 3.2, 4.7, 1.4, "versicolor");
        KNN i2 = new Iris(3.2, 4.7, 1.4, 0.2, "setosa");

        assertEquals(new DistanceManhattan().distance(i1, i2), 9.8, 0.01);
    }

    @Test
    void testDistanceTitanic() {
        KNN t1 = new Titanic(1,false,3,"Braund, Mr. Owen Harris","male",22,1,0,"A/5 21171",7.25," ",'S');
        KNN t2 = new Titanic(350,false,3,"Dimic, Mr. Jovan","male",42,0,0,"315088",8.6625," ",'S');

        assertEquals(new DistanceManhattan().distance(t1, t2), 371.41, 0.01 );
    }
}
