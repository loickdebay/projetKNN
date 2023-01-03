package knn;

import distance.Distance;
import java.util.List;

/**
 * This class is a abstract class used to create a list of methods used by the KNN algorithm.
 */
public abstract class KNN {
    public abstract String nom();
    public abstract List<KNN> charger();
    public abstract List<KNN> charger(String path);
    public abstract List<KNN> getKClosestKNN(int k, KNN knn, Distance distanceObject);
    public abstract KNN getClosestKNN(KNN knn, List<KNN> list, Distance distanceObject);
    public abstract double accuracy(List<KNN> knn, Distance distanceObject, int k);
    public abstract void generateAmplitude(List<KNN> knns);
    public abstract String toStringJFX();
}
