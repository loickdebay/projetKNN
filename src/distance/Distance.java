package distance;

import knn.KNN;

/**
 * This class is used to calculate the distance between two points.
 */

public interface Distance {
	double distance(KNN p1, KNN p2);
}
