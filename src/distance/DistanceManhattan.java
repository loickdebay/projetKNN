package distance;

import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;

/**
 * This class is used to calculate the distance Manhattan between two points.
 */

public class DistanceManhattan implements Distance {
	/**
	 * This method is used to calculate the distance Manhattan between pokemon 1 and pokemon 2.
	 * @param p1 is the first pokemon.
	 * @param p2 is the second pokemon.
	 * @return the distance between pokemon 1 and pokemon 2.
	 */
	public double distance(Pokemon p1, Pokemon p2) {
		 return (Math.abs(p2.getBaseEggSteps()-p1.getBaseEggSteps())
			   +(Math.abs(p2.getCaptureRate() - p1.getCaptureRate()))
		       +(Math.abs(p2.getExperienceGrowth()-p1.getExperienceGrowth()))
		       +(Math.abs(p2.getSpeed()-p1.getSpeed())));
	}
	/**
	 * This method is used to calculate the distance Manhattan between iris 1 and iris 2.
	 * @param i1 is the first iris.
	 * @param i2 is the second iris.
	 * @return the distance between iris 1 and iris 2.
	 */
	public double distance(Iris i1, Iris i2) {
		 return (Math.abs(i2.getPetalLength() - i1.getPetalLength())
			   +(Math.abs(i2.getPetalWidth() - i1.getPetalWidth())
		       +(Math.abs(i2.getSepalLength() - i1.getSepalLength()))
		       +(Math.abs(i2.getSepalWidth() - i1.getSepalWidth()))));
	}
	/**
	 * This method is used to calculate the distance Manhattan between titanic 1 and titanic 2.
	 * @param t1 is the first titanic.
	 * @param t2 is the second titanic.
	 * @return the distance between titanic 1 and titanic 2.
	 */
	public double distance(Titanic t1, Titanic t2) {
		 return (Math.abs(t2.getPassengerId() - t1.getPassengerId())
			   +(Math.abs(t2.getAge() - t1.getAge())
		       +(Math.abs(t2.getFare() - t1.getFare()))
		       +(Math.abs(t2.getParch() - t1.getParch()))
		       +(Math.abs(t2.getpClass() - t1.getpClass()))
		       +(Math.abs(t2.getSibSp() - t1.getSibSp()))
		       +(Math.abs(t2.isSurvivedInt(t2.isSurvived()) - t1.isSurvivedInt(t1.isSurvived())))));
	}
	/**
	 * This method is used to calculate the distance Manhattan between two points.
	 * @param p1 is the first point.
	 * @param p2 is the second point.
	 * @return the distance between two points.
	 */
	@Override
	public double distance(KNN p1, KNN p2) {
		if(p1.nom().equals("Pokemon") && p2.nom().equals("Pokemon")) {
			return distance((Pokemon) p1, (Pokemon) p2);
		}
		else if(p1.nom().equals("Iris") && p2.nom().equals("Iris")) {
			return distance((Iris) p1, (Iris) p2);
		}
		else if(p1.nom().equals("Titanic") && p2.nom().equals("Titanic")) {
			return distance((Titanic) p1, (Titanic) p2);
		}
		return 0;
	}
	
}
