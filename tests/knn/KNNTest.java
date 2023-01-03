package knn;

import java.util.ArrayList;
import java.util.List;

import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;
import org.junit.jupiter.api.Test;

import distance.Distance;
import distance.DistanceEuclidienne;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class KNNTest {

	@Test
	void testChargerCSVLignePokemon(){
		Pokemon p = new Pokemon();
		List<KNN> list = p.charger("ressources/pokemon_train.csv");
		assertEquals("Swablu,40,5120,255.0,60,600000,45,75,50,normal,flying,1.2,false", p.toStringTest(list, 0));
		assertEquals("Aggron,140,8960,45.0,230,1250000,70,80,50,steel,rock,360.0,false", p.toStringTest(list, 17));
		assertNotEquals("Sewaddle,53,3840,255.0,70,1059860,45,60,42,bug,grass,2.5,false", p.toStringTest(list, 29));
		assertNotEquals("Poochyena,55,3840,255.0,35,1000000,35,30,35,dark,,13.6,false", p.toStringTest(list, 250));
	}
	
	@Test
	void test_CSV_loading_for_iris(){
		Iris i = new Iris();
		List<KNN> list = i.charger("ressources/iris.csv");
		assertEquals("5.1,3.3,1.7,0.5,Setosa", i.toStringTest(list, 23));
		assertEquals("6.5,3.0,5.8,2.2,Virginica", i.toStringTest(list, 104));
		assertNotEquals("5.6,3.0,4.1,1.3,Versicolor", i.toStringTest(list, 48));
		assertNotEquals("6.8,3.2,5.9,2.3,Virginica", i.toStringTest(list, 13));
	}
	
	@Test
	void test_CSV_loading_for_titanic(){
		Titanic t = new Titanic();
		List<KNN> list = t.charger("ressources/titanic.csv");
		assertEquals("1,0,3,Braund, Mr. Owen Harris,male,22.0,1,0,A/5 21171,7.25,,S", t.toStringTest(list, 0));
		assertEquals("74,0,3,Chronopoulos, Mr. Apostolos,male,26.0,1,0,2680,14.4542,,C", t.toStringTest(list, 73));
		assertEquals("113,0,3,Barton, Mr. David John,male,22.0,0,0,324669,8.05,,S", t.toStringTest(list, 112));
		assertEquals("153,0,3,Meo, Mr. Alfonzo,male,55.5,0,0,A.5. 11206,8.05,,S", t.toStringTest(list, 152));
		assertEquals("228,0,3,Lovell, Mr. John Hall (\"Henry\"),male,20.5,0,0,A/5 21173,7.25,,S", t.toStringTest(list, 227));
		assertEquals("350,0,3,Dimic, Mr. Jovan,male,42.0,0,0,315088,8.6625,,S", t.toStringTest(list, 349));
	}
	
	
	
	@Test
	void test_closest_pokemon() {
		KNN n1 = new Pokemon("Swablu",40,5120,255.0,60,600000,45,75,50,"normal","flying",1.2,false);
		KNN n2 = new Pokemon("Aggron",140,8960,45.0,230,1250000,70,80,50,"steel","rock",360.0,false);
		KNN n3 = new Pokemon("Sewaddle",53,3840,255.0,70,1059860,45,60,42,"bug","grass",2.5,false);
		KNN n4 = new Pokemon();
		List<KNN> list = new ArrayList<KNN>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		list.add(n4);
		Distance d = new DistanceEuclidienne();
		assertEquals(n3, n1.getClosestKNN(n2, list, d));
	}
	
	@Test
	void test_closest_iris() {
		KNN n1 = new Iris(5.1,3.5,1.4,0.2,"Setosa");
		KNN n2 = new Iris(6.5,3.0,5.8,2.2,"Virginica");
		KNN n3 = new Iris(5.1,3.3,1.7,0.5,"Setosa");
		KNN n4 = new Iris();
		List<KNN> list = new ArrayList<KNN>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		list.add(n4);
		Distance d = new DistanceEuclidienne();
		assertEquals(n3, n1.getClosestKNN(n2, list, d));
	}
	
	@Test
	void test_generated_type_from_closest_neighbors() {
		Iris i1 = new Iris(5.1,3.5,1.4,0.2,"Setosa");
		Iris i2 = new Iris(6.5,3.0,5.8,2.2,"Virginica");
		Iris i3 = new Iris(5.1,3.3,1.7,0.5,"Setosa");
		Iris i4 = new Iris(4.9, 3.2, 1.2,0.3, "");
		Distance dist = new DistanceEuclidienne();
		i4.getGeneratedType(1, i4, dist);
		assertEquals(i1.getVariety(), i4.getVariety());
	}
	
	@Test
	void test_calcul_Accuracy() {
		KNN n1 = new Iris(5.1,3.5,1.4,0.2,"Setosa");
		KNN n2 = new Iris(6.5,3.0,5.8,2.2,"Virginica");
		KNN n3 = new Iris(5.1,3.3,1.7,0.5,"Setosa");
		KNN n4 = new Iris();
		List<KNN> list = new ArrayList<KNN>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		list.add(n4);
		Distance dist = new DistanceEuclidienne();
		assertEquals(75.0, n1.accuracy(list, dist, 9));
	}
	
	@Test
	void test_Calcul_Amplitude() {
		KNN p1 = new Pokemon("Swablu",40,5120,255.0,60,600000,45,75,50,"normal","flying",1.2,false);
		KNN p2 = new Pokemon("Aggron",140,8960,45.0,230,1250000,70,80,50,"steel","rock",360.0,false);
		List<KNN> listP = new ArrayList<KNN>();
		listP.add(p1);
		listP.add(p2);
		
		KNN i1 = new Iris(5.1,3.5,1.4,0.2,"Setosa");
		KNN i2 = new Iris(6.5,3.0,5.8,2.2,"Virginica");
		List<KNN> listI = new ArrayList<KNN>();
		listI.add(i1);
		listI.add(i2);
		
		KNN t1 = new Titanic(1,false,3,"Braund, Mr. Owen Harris","male",22,1,0,"A/5 21171",7.25," ",'S');
		KNN t2 = new Titanic(350,false,3,"Dimic, Mr. Jovan","male",42,0,0,"315088",8.6625," ",'S');
		List<KNN> listT = new ArrayList<KNN>();
		listT.add(t1);
		listT.add(t2);
		
		
		p1.generateAmplitude(listP);
		i1.generateAmplitude(listI);
		t1.generateAmplitude(listT);
		
		//Pokemon
		assertEquals(3840, Pokemon.base_egg_steps_amplitude);
		assertEquals(210, Pokemon.capture_rate_amplitude);
		assertEquals(650000, Pokemon.experience_growth_amplitude);
		assertEquals(358.8, Pokemon.speed_amplitude);
		
		//Iris
		assertEquals(4.4, Iris.petal_length_amplitude);
		assertEquals(2.0, Iris.petal_width_amplitude);
		assertEquals(1.4, Iris.sepal_length_amplitude, 0.1);
		assertEquals(0.5, Iris.sepal_width_amplitude);
		
		//Titanic
		assertEquals(20, Titanic.age_amplitude);
		assertEquals(1.412, Titanic.fare_amplitude, 0.001);
		assertEquals(0, Titanic.parch_amplitude);
		assertEquals(0, Titanic.pClass_amplitude);
		assertEquals(1, Titanic.sibsp_amplitude);
		
	}
}
