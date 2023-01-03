package utils;

import knn.KNN;

public class KNNSubject extends Subject {
    private KNN knn;

    public KNNSubject(KNN knn) {
        this.knn = knn;
    }

    public void setKNN(KNN knn){
        this.knn = knn;
        notifyObservers();
    }

    public KNN getKNN(){
        return knn;
    }
}
