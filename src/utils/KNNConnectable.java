package utils;

import knn.KNN;

public class KNNConnectable extends ConnectableProperty{
    private KNN knn;

    public KNNConnectable(KNN knn) {
        super();
        this.knn = knn;
    }

    @Override
    public void update(Subject other, Object data) {
        this.knn = (KNN) data;
        setValue(data);
    }

    public KNN getKNN() {
        return knn;
    }

    public void setKNN(KNN knn) {
        System.out.println(knn);
        this.knn = knn;
        this.setValue(knn);
    }

}
