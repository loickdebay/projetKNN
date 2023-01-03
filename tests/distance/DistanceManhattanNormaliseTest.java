package distance;

import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceManhattanNormaliseTest {
    static Pokemon p;
    static Iris i;
    static Titanic t;

    @BeforeAll
    static void setup(){
        p = new Pokemon();
        p.charger();

        i = new Iris();
        i.charger();

        t = new Titanic();
        t.charger();
    }

    @Test
    void testDistancePokemon() {
        KNN p1 = new Pokemon("Swablu",40,5120,255.0,60,600000,45,75,50,"normal","flying",1.2,false);
        KNN p2 = new Pokemon("Aggron",140,8960,45.0,230,1250000,70,80,50,"steel","rock",360.0,false);

        DistanceManhattanNormalisee den = new DistanceManhattanNormalisee();
        assertEquals(den.distance(p1,p2), 1.966, 0.001);
    }

    @Test
    void testDistanceIris() {
        KNN i1 = new Iris(7.0, 3.2, 4.7, 1.4, "versicolor");
        KNN i2 = new Iris(3.2, 4.7, 1.4, 0.2, "setosa");

        DistanceManhattanNormalisee den = new DistanceManhattanNormalisee();
        System.out.println(den.distance(i1,i2));
        assertEquals(den.distance(i1,i2), 0.491, 0.001);
    }

    @Test
    void testDistanceTitanic() {
        KNN t1 = new Titanic(1,false,3,"Braund, Mr. Owen Harris","male",22,1,0,"A/5 21171",7.25," ",'S');
        KNN t2 = new Titanic(350,false,3,"Dimic, Mr. Jovan","male",42,0,0,"315088",8.6625," ",'S');

        DistanceManhattanNormalisee den = new DistanceManhattanNormalisee();
        assertEquals(den.distance(t1,t2), 0.491, 0.001);
    }
}
