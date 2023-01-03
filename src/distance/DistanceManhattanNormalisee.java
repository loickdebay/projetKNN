package distance;


import knn.Iris;
import knn.KNN;
import knn.Pokemon;
import knn.Titanic;

/**
 * This class is used to calculate the distance Manhattan normalisee between two points.
 */
public class DistanceManhattanNormalisee implements Distance {

	public double distance(Pokemon p1, Pokemon p2) {
		double distanceEgg = (Math.abs(p1.getBaseEggSteps()-p2.getBaseEggSteps()))/ Pokemon.base_egg_steps_amplitude;
		double distanceCapture = (Math.abs(p1.getCaptureRate() - p2.getCaptureRate()))/ Pokemon.capture_rate_amplitude;
		double distanceExperience = (Math.abs(p1.getExperienceGrowth()-p2.getExperienceGrowth()))/ Pokemon.experience_growth_amplitude;
		double distanceSpeed = (Math.abs(p1.getSpeed()-p2.getSpeed()))/ Pokemon.speed_amplitude;
		
		return Math.abs(distanceSpeed) + Math.abs(distanceExperience) + Math.abs(distanceEgg) + Math.abs(distanceCapture)/4;
	}
	/**
	 * This method is used to calculate the distance Manhattan normalisee between iris 1 and iris 2.
	 * @param i1 is the first iris.
	 * @param i2 is the second iris.
	 * @return the distance between iris 1 and iris 2.
	 */
	public double distance(Iris i1, Iris i2) {
		double distancePetalLength = (i1.getPetalLength()-i2.getPetalLength())/ Iris.petal_length_amplitude;
		double distancePetalWidth = (i1.getPetalWidth()-i2.getPetalWidth())/Iris.petal_width_amplitude;
		double distanceSepalLength = (i1.getSepalLength()-i2.getSepalLength())/Iris.sepal_length_amplitude;
		double distanceSepalWidth = (i1.getSepalWidth()-i2.getSepalWidth())/Iris.sepal_width_amplitude;

		return Math.abs(distancePetalLength) + Math.abs(distancePetalWidth) + Math.abs(distanceSepalLength) + Math.abs(distanceSepalWidth) /4;
	}
	/**
	 * This method is used to calculate the distance Manhattan normalisee between titanic 1 and titanic 2.
	 * @param t1 is the first titanic.
	 * @param t2 is the second titanic.
	 * @return the distance between titanic 1 and titanic 2.
	 */
	public double distance(Titanic t1, Titanic t2){
		double distanceAge = (t1.getAge()-t2.getAge())/Titanic.age_amplitude;
		double distanceFare = (t1.getFare()-t2.getFare())/Titanic.fare_amplitude;
		double distanceParch = (t1.getParch()-t2.getParch())/Titanic.parch_amplitude;
		double distancePclass = (t1.getpClass()-t2.getpClass())/Titanic.pClass_amplitude;
		double distanceSibSp = (t1.getSibSp()-t2.getSibSp())/Titanic.sibsp_amplitude;
		double distanceSurvived = (t1.isSurvivedInt(t1.isSurvived())-t2.isSurvivedInt(t2.isSurvived()));

		return Math.abs(distanceAge) + Math.abs(distanceFare) + Math.abs(distanceParch) + Math.abs(distancePclass) + Math.abs(distanceSibSp) + Math.abs(distanceSurvived) /6;
	}
	/**
	 * This method is used to calculate the distance Manhattan normalisee between two points.
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
