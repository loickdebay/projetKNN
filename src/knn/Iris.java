package knn;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import distance.Distance;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class is used to create a list of Iris.
 */
public class Iris extends KNN {
	
	@CsvBindByName(column = "sepal.length")
	private double sepalLength;
	
	@CsvBindByName(column = "sepal.width")
	private double sepalWidth;
	
	@CsvBindByName(column = "petal.length")
	private double petalLength;
	
	@CsvBindByName(column = "petal.width")
	private double petalWidth;
	
	@CsvBindByName(column = "variety")
	private String variety;


	public static double sepal_length_amplitude;
	public static double sepal_width_amplitude;
	public static double petal_length_amplitude;
	public static double petal_width_amplitude;
	
	public Iris() {
		
	}

	/**
	 * This constructor is used to create an Iris object.
	 * @param sepalLength The sepal length of the Iris.
	 * @param sepalWidth The sepal width of the Iris.
	 * @param petalLength The petal length of the Iris.
	 * @param petalWidth The petal width of the Iris.
	 */
	public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth) {
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
	}

	/**
	 * This method is a constructor of Iris.
	 * @param sepalLength Sepal length of the Iris.
	 * @param sepalWidth Sepal width of the Iris.
	 * @param petalLength Petal length of the Iris.
	 * @param petalWidth Petal width of the Iris.
	 * @param variety Variety of the Iris.
	 */
	public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String variety) {
		this.sepalLength = sepalLength;
		this.sepalWidth = sepalWidth;
		this.petalLength = petalLength;
		this.petalWidth = petalWidth;
		this.variety = variety;
		
	}

	/**
	 * This method is used to get the sepal length of an iris.
	 * @return a string of a iris variety.
	 */
	public String toString() {
		return "Iris [variety =" + variety + "]";
	}

	/**
	 * toString of the Iris class (ONLY FOR TEST METHODS).
	 * @param data The data list for Iris.
	 * @param idx The index of the Iris.
	 * @return a string of a iris variety.
	 */
	public String toStringTest(List<KNN> data, int idx) {
		Iris i = (Iris)data.get(idx);
		return i.getSepalLength() + "," + i.getSepalWidth() + "," + i.getPetalLength() + "," + i.getPetalWidth() + "," + i.getVariety();
	}

	/**
	 * This method is used to get the variety of an iris.
	 * @return sepal length of an iris.
	 */
	public double getSepalLength() {
		return sepalLength;
	}

	/**
	 * This method is used to set the sepal length of an iris.
	 * @param sepalLength Sepal length of an iris.
	 */
	public void setSepalLength(double sepalLength) {
		this.sepalLength = sepalLength;
	}

	/**
	 * This method is used to get the sepal width of an iris.
	 * @return sepal width of an iris.
	 */
	public double getSepalWidth() {
		return sepalWidth;
	}

	/**
	 * This method is used to set the sepal width of an iris.
	 * @param sepalWidth Sepal width of an iris.
	 */
	public void setSepalWidth(double sepalWidth) {
		this.sepalWidth = sepalWidth;
	}

	/**
	 * This method is used to get the petal length of an iris.
	 * @return petal length of an iris.
	 */
	public double getPetalLength() {
		return petalLength;
	}

	/**
	 * This method is used to set the petal length of an iris.
	 * @param petalLength Petal length of an iris.
	 */
	public void setPetalLength(double petalLength) {
		this.petalLength = petalLength;
	}

	/**
	 * This method is used to get the petal width of an iris.
	 * @return petal width of an iris.
	 */
	public double getPetalWidth() {
		return petalWidth;
	}

	/**
	 * This method is used to set the petal width of an iris.
	 * @param petalWidth Petal width of an iris.
	 */
	public void setPetalWidth(double petalWidth) {
		this.petalWidth = petalWidth;
	}

	/**
	 * This method is used to get the variety of an iris.
	 * @return a string of a iris variety.
	 */
	public String getVariety() {
		return variety;
	}

	/**
	 * This method is used to set the variety of an iris.
	 * @param variety Variety of an iris.
	 */
	public void setVariety(String variety) {
		this.variety = variety;
	}

	/**
	 * This method is used to get the name of iris.
	 * @return a string of a iris name.
	 */
	@Override
	public String nom() {
		return "Iris";
	}

	/**
	 * This method is used to charge the csv file.
	 * @return a list of iris.
	 */
	@Override
	public List<KNN> charger() {
		try{
			return new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get("ressources/iris.csv"))).withSeparator(',')
					.withType(Iris.class).build().parse();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is used to charge the csv file with the path.
	 * @return a list of iris.
	 */
	@Override
	public List<KNN> charger(String path) {
		List<KNN> datas;
		try {
			datas = new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(Iris.class).build().parse();
		} catch (Exception e) {
			e.printStackTrace();
			datas = null;
		}
		if(datas != null) {
			generateAmplitude(datas);
		}
		return datas;
	}

	/**
	 * This method is used to get closest iris.
	 * @return a list of iris.
	 */
	@Override
	public List<KNN> getKClosestKNN(int k, KNN knn, Distance distanceObject) {
		ArrayList<KNN> knns;
		knns = (ArrayList<KNN>) this.charger();
		if (k > knns.size())
			return knns;
		List<KNN> copy = new ArrayList<>(knns);
		List<KNN> res = new ArrayList<>();
		for (int i = 0; i < k; i++) {
			res.add(getClosestKNN(knn, copy, distanceObject));
		}
		return res;
	}

	/**
	 * This method is used to get closest iris with a list of iris.
	 * @return a list of iris.
	 */
	@Override
	public KNN getClosestKNN(KNN knn, List<KNN> list, Distance distanceObject) {
		KNN res = list.get(0);
		double min = distanceObject.distance(knn, res);
		int emplacement = 0;
		for (int i = 1; i < list.size(); i++) {
			KNN current = list.get(i);
			double distance = distanceObject.distance(knn, current);
			if (distance < min) {
				Iris i1 = (Iris) knn;
				Iris i2 = (Iris) current;
				if(!i1.equals(i2)) {
					min = distance;
					res = current;
					emplacement = i;
				}
			}
		}
		list.remove(emplacement);
		return res;
	}
	
	public Iris getGeneratedType(int knnInt, Iris i, Distance distanceObject) {
		List<KNN> knns = getKClosestKNN(knnInt, i, distanceObject);
		HashMap<String, Integer> count = new HashMap<>();
		for(KNN knn : knns){
			Iris i2 = (Iris) knn;
			//Ajoute à la HashMap la valeur, si elle existe déjà faire +1 à la valeur
			count.put(i2.getVariety(), count.getOrDefault(i2.getVariety(), 0) + 1);
		}
		int max = 0;
		String type = "";
		for(String key : count.keySet()){
			if(count.get(key) > max){
				max = count.get(key);
				type = key;
			}
		}
		i.setVariety(type);
		return i;
	}

	/**
	 * This method is used to get the accuracy of the iris.
	 * @return a double of the accuracy.
	 */
	@Override
	public double accuracy(List<KNN> m, Distance distanceObject, int k) {
		int res = 0;
		for(KNN test : m){
			Iris p = (Iris) test;
			List<KNN> knns = getKClosestKNN(k, test, distanceObject);
			int nb = 0;
			for(KNN knn : knns){
				Iris p2 = (Iris) knn;
				if(p2.variety.equals(p.variety)){
					nb++;
				}
			}
			if(nb >= k/2){
				res++;
			}
		}
		return (res * 1.0 / m.size()) * 100.0;
	}

	/**
	 * This method is used to generate the amplitude of the iris.
	 */
	@Override
	public void generateAmplitude(List<KNN> knns) {
		Iris iris = (Iris) charger().get(0);
		double sepal_length_min = iris.getSepalLength();
		double sepal_length_max = iris.getSepalLength();
		double sepal_width_min = iris.getSepalWidth();
		double sepal_width_max = iris.getSepalWidth();
		double petal_length_min = iris.getPetalLength();
		double petal_length_max = iris.getPetalLength();
		double petal_width_min = iris.getPetalWidth();
		double petal_width_max = iris.getPetalWidth();

		for(KNN i : knns) {
			iris = (Iris) i;
			if (iris.getSepalLength() < sepal_length_min) {
				sepal_length_min = iris.getSepalLength();
			}
			if (iris.getSepalLength() > sepal_length_max) {
				sepal_length_max = iris.getSepalLength();
			}
			if (iris.getSepalWidth() < sepal_width_min) {
				sepal_width_min = iris.getSepalWidth();
			}
			if (iris.getSepalWidth() > sepal_width_max) {
				sepal_width_max = iris.getPetalWidth();
			}
			if (iris.getPetalLength() < petal_length_min) {
				petal_length_min = iris.getPetalLength();
			}
			if (iris.getPetalLength() > petal_length_max) {
				petal_length_max = iris.getPetalLength();
			}
			if (iris.getPetalWidth() < petal_width_min) {
				petal_width_min = iris.getPetalWidth();
			}
			if (iris.getPetalWidth() > petal_width_max) {
				petal_width_max = iris.getPetalWidth();
			}

			sepal_length_amplitude = sepal_length_max - sepal_length_min;
			sepal_width_amplitude = sepal_width_max - sepal_width_min;
			petal_length_amplitude = petal_length_max - petal_length_min;
			petal_width_amplitude = petal_width_max - petal_width_min;
		}
	}
	public String toStringJFX(){
		return "Variety : "+this.getVariety()+"\n\nSepal Length : "+this.getSepalLength()+"\nSepal Width : "+this.getPetalWidth()+"\nPetal Length : "+this.getPetalLength()+"\nPetal Width : "+this.getPetalWidth();
	}
}
