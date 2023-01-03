package distance;

import knn.*;

/**
 * This class is used to calculate the distance Euclidienne normalisee between two points.
 */

public class DistanceEuclidienneNormalisee implements Distance {
	/**
	 * This method is used to calculate the distance between pokemon 1 and pokemon 2.
	 * @param p1 is the first pokemon.
	 * @param p2 is the second pokemon.
	 * @return the distance between pokemon 1 and pokemon 2.
	 */
	public double distance(Pokemon p1, Pokemon p2) {

		double distanceEgg = (Math.sqrt(square(p1.getBaseEggSteps()/Pokemon.base_egg_steps_amplitude-p2.getBaseEggSteps()/Pokemon.base_egg_steps_amplitude)));
		double distanceCapture = (Math.sqrt(square(p1.getCaptureRate()/Pokemon.capture_rate_amplitude-p2.getCaptureRate()/Pokemon.capture_rate_amplitude)));
		double distanceExperience = (Math.sqrt(square(p1.getExperienceGrowth()/Pokemon.experience_growth_amplitude-p2.getExperienceGrowth()/Pokemon.experience_growth_amplitude)));
		double distanceSpeed = (Math.sqrt(square(p1.getSpeed()/Pokemon.speed_amplitude-p2.getSpeed()/Pokemon.speed_amplitude)));
		return (distanceSpeed + distanceExperience + distanceEgg + distanceCapture)/4;
	}
	/**
	 * This method is used to calculate the distance Euclidienne normalisee between iris 1 and iris 2.
	 * @param i1 is the first iris.
	 * @param i2 is the second iris.
	 * @return the distance between iris 1 and iris 2.
	 */
	public double distance(Iris i1, Iris i2) {
		double distancePetalLength = (Math.abs(i1.getPetalLength()-i2.getPetalLength()))/ Iris.petal_length_amplitude;
		double distancePetalWidth = (Math.abs(i1.getPetalWidth()-i2.getPetalWidth()))/Iris.petal_width_amplitude;
		double distanceSepalLength = (Math.abs(i1.getSepalLength()-i2.getSepalLength()))/Iris.sepal_length_amplitude;
		double distanceSepalWidth = (Math.abs(i1.getSepalWidth()-i2.getSepalWidth()))/Iris.sepal_width_amplitude;

		return Math.sqrt(square(distancePetalLength)+square(distancePetalWidth)+square(distanceSepalLength)+square(distanceSepalWidth));
	}
	/**
	 * This method is used to calculate the distance Euclidienne normalisee between titanic 1 and titanic 2.
	 * @param t1 is the first titanic.
	 * @param t2 is the second titanic.
	 * @return the distance between titanic 1 and titanic 2.
	 */
	public double distance(Titanic t1, Titanic t2){
		double distanceAge = (Math.abs(t1.getAge()-t2.getAge()))/Titanic.age_amplitude;
		double distanceFare = (Math.abs(t1.getFare()-t2.getFare()))/Titanic.fare_amplitude;
		double distanceParch = (Math.abs(t1.getParch()-t2.getParch()))/Titanic.parch_amplitude;
		double distancePclass = (Math.abs(t1.getpClass()-t2.getpClass()))/Titanic.pClass_amplitude;
		double distanceSibSp = (Math.abs(t1.getSibSp()-t2.getSibSp()))/Titanic.sibsp_amplitude;
		double distanceSurvived = (Math.abs(t1.isSurvivedInt(t1.isSurvived())-t2.isSurvivedInt(t2.isSurvived())));

		return Math.sqrt(square(distanceAge)+square(distanceFare)+square(distanceParch)+square(distancePclass)+square(distanceSibSp)+square(distanceSurvived));
	}
	/**
	 * This method is used to calculate the distance Euclidienne normalisee between KNN 1 and KNN 2.
	 * @param p1 is the first KNN.
	 * @param p2 is the second KNN.
	 * @return the distance between KNN 1 and KNN 2.
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
	/**
	 * This method is used to calculate the square of a number.
	 * @param number is the number.
	 * @return the square of a number.
	 */
	public static double square(double number) {
		return number*number;
	}

}
