package distance;

import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;

/**
 * This class is used to calculate the distance Euclidienne between two points.
 */

public class DistanceEuclidienne implements Distance {

	/**
	 * This method is used to calculate the distance between pokemon 1 and pokemon 2.
	 * @param p1 is the first pokemon.
	 * @param p2 is the second pokemon.
	 * @return the distance between pokemon 1 and pokemon 2.
	 */
	public double distance(Pokemon p1, Pokemon p2) {
		 return Math.sqrt(square(p2.getBaseEggSteps()-p1.getBaseEggSteps()) 
				 		+ square(p2.getCaptureRate() - p1.getCaptureRate())
				 		+ square(p2.getExperienceGrowth()-p1.getExperienceGrowth())	
				 		+ square(p2.getSpeed()-p1.getSpeed()));
	}
	/**
	 * This method is used to calculate the distance Euclidienne between iris 1 and iris 2.
	 * @param i1 is the first iris.
	 * @param i2 is the second iris.
	 * @return the distance between iris 1 and iris 2.
	 */
	public double distance(Iris i1, Iris i2) {
		 return Math.sqrt(square(i2.getPetalLength() - i1.getPetalLength()) 
				 		+ square(i2.getPetalWidth() - i1.getPetalWidth())
				 		+ square(i2.getSepalLength() - i1.getSepalLength())	
				 		+ square(i2.getSepalWidth() - i1.getSepalWidth()));
	}
	/**
	 * This method is used to calculate the distance Euclidienne between titanic 1 and titanic 2.
	 * @param t1 is the first titanic.
	 * @param t2 is the second titanic.
	 * @return the distance between titanic 1 and titanic 2.
	 */
	public double distance(Titanic t1, Titanic t2) {
		 return Math.sqrt(square(t2.getPassengerId() - t1.getPassengerId()) 
				 		+ square(t2.getAge() - t1.getAge())
				 		+ square(t2.getFare() - t1.getFare())	
				 		+ square(t2.getParch() - t1.getParch())
				 		+ square(t2.getpClass() - t1.getpClass())
				 		+ square(t2.getSibSp() - t1.getSibSp())
				 		+ square(t2.isSurvivedInt(t2.isSurvived()) - t1.isSurvivedInt(t1.isSurvived())));
	}
	/**
	 * This method is used to calculate the square of a number.
	 * @param number is the number to square.
	 * @return the square of a number.
	 */
	public static double square(double number) {
		return number*number;
	}
	/**
	 * This method is used to calculate the distance Euclidienne between two points.
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
