package main;

import distance.DistanceEuclidienneNormalisee;
import knn.*;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    public static void main(String[] args) {
        //mainPokemon();
        //mainIris();
        //mainTitanic();
        //accuracyPokemon();
        //accuracyIris();
        //accuracyTitanic();
    }

    private static void mainPokemon(){
        KNN knn = new Pokemon();
        ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
        KNN test1 = test.get(6);
        DistanceEuclidienneNormalisee distance = new DistanceEuclidienneNormalisee();
        List<KNN> res = knn.getKClosestKNN(5, test1, distance);
        for(KNN k : res){
            System.out.println(k);
        }
    }

    private static void mainIris(){
        KNN knn = new Iris();
        ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
        test.add(new Iris(5.1, 3.5, 1.4, 0.2, "setosa"));
        KNN test1 = test.get(6);
        DistanceEuclidienneNormalisee distance = new DistanceEuclidienneNormalisee();
        List<KNN> res = knn.getKClosestKNN(5, test1, distance);
        for(KNN k : res){
            System.out.println(k);
        }
    }

    private static void mainTitanic(){
        KNN knn = new Titanic();
        ArrayList<KNN> test = (ArrayList<KNN>) knn.charger();
        KNN test1 = test.get(6);
        DistanceEuclidienneNormalisee distance = new DistanceEuclidienneNormalisee();
        List<KNN> res = knn.getKClosestKNN(5, test1, distance);
        for(KNN k : res){
            System.out.println(k);
        }
    }

    private static void accuracyPokemon(){
        Pokemon p = new Pokemon();
        p.charger();
        List<KNN> testPoke = new Pokemon().charger("ressources/pokemon_test.csv");
        System.out.println(p.accuracy(testPoke, new DistanceEuclidienneNormalisee(),8));
    }

    private static void accuracyIris(){
        Iris p = new Iris();
        p.charger();
        List<KNN> testIris = new Iris().charger("ressources/iris_test.csv");
        System.out.println(p.accuracy(testIris, new DistanceEuclidienneNormalisee(),8));
    }

    private static void accuracyTitanic(){
        Titanic p = new Titanic();
        p.charger();
        List<KNN> testTitanic = new Titanic().charger("ressources/titanic_test.csv");
        System.out.println(p.accuracy(testTitanic, new DistanceEuclidienneNormalisee(),8));
    }
}