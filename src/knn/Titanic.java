package knn;

import com.opencsv.bean.CsvBindByName;

import com.opencsv.bean.CsvToBeanBuilder;
import distance.Distance;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


/**
 * This class is used to create a list of Titanic.
 */
public class Titanic extends KNN {
	
	@CsvBindByName(column = "PassengerId")
	private int passengerId;
	
	@CsvBindByName(column = "Survived")
	private boolean survived;
	
	@CsvBindByName(column = "PClass")
	private int pClass;
	
	@CsvBindByName(column = "Name")
	private String name;
	
	@CsvBindByName(column = "Sex")
	private String sexe;
	
	@CsvBindByName(column = "Age")
	private double age;

	@CsvBindByName(column = "SibSp")
	private int sibSp;
	
	@CsvBindByName(column = "Parch")
	private int parch;
	
	@CsvBindByName(column = "Ticket")
	private String ticket;
	
	@CsvBindByName(column = "Fare")
	private double fare;
	
	@CsvBindByName(column = "Cabin")
	private String cabin;

	@CsvBindByName(column = "Embarked")
	private char embarked;

	public static double age_amplitude;
	public static double fare_amplitude;
	public static double pClass_amplitude;
	public static double sibsp_amplitude;
	public static double parch_amplitude;
	
	public Titanic() {
		
	}

	/**
	 * This constructor is used to create an Titanic object without survived.
	 * @param passengerId The passengerId of the Titanic.
	 * @param pClass The pClass of the Titanic.
	 * @param name The name of the Titanic.
	 * @param sexe The sexe of the Titanic.
	 * @param age The age of the Titanic.
	 * @param sibSp The sibSp of the Titanic.
	 * @param parch The parch of the Titanic.
	 * @param ticket The ticket of the Titanic.
	 * @param fare The fare of the Titanic.
	 * @param cabin The cabin of the Titanic.
	 * @param embarked The embarked of the Titanic.
	 */
	public Titanic(int passengerId, int pClass, String name, String sexe, int age, int sibSp,int parch, String ticket, double fare, String cabin, char embarked) {
		this.passengerId = passengerId;
		this.pClass = pClass;
		this.name = name;
		this.sexe = sexe;
		this.age = age;
		this.sibSp = sibSp;
		this.parch = parch;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		this.embarked = embarked;
	}

	/**
	 * This constructor is used to create an Titanic object.
	 * @param passengerId The passengerId of the Titanic.
	 * @param survived The survived of the Titanic.
	 * @param pClass The pClass of the Titanic.
	 * @param name The name of the Titanic.
	 * @param sexe The sexe of the Titanic.
	 * @param age The age of the Titanic.
	 * @param sibSp The sibSp of the Titanic.
	 * @param parch The parch of the Titanic.
	 * @param ticket The ticket of the Titanic.
	 * @param fare The fare of the Titanic.
	 * @param cabin The cabin of the Titanic.
	 * @param embarked The embarked of the Titanic.
	 */
	public Titanic(int passengerId, boolean survived, int pClass, String name, String sexe, int age, int sibSp,int parch, String ticket, double fare, String cabin, char embarked) {
		this.passengerId = passengerId;
		this.pClass = pClass;
		this.name = name;
		this.sexe = sexe;
		this.age = age;
		this.sibSp = sibSp;
		this.parch = parch;
		this.ticket = ticket;
		this.fare = fare;
		this.cabin = cabin;
		this.embarked = embarked;
		this.survived = survived;
	}

	/**
	 * This method is used to get a toString of Titanic.
	 * @return a toString of Titanic.
	 */
	public String toString() {
		return "Titanic [Name =" + name + "]";
	}

	/**
	 * toString of the Titanic class (ONLY FOR TEST METHODS).
	 * @param data List of Titanic.
	 * @param idx Index of the Titanic.
	 * @return a toString of Titanic object.
	 */
	public String toStringTest(List<KNN> data, int idx) {
		Titanic t = (Titanic)data.get(idx);
		return t.getPassengerId() + "," + t.isSurvivedInt(isSurvived()) + "," + t.getpClass() + "," + t.getName() + "," + t.getSexe() + "," + t.getAge() + "," + t.getSibSp()
				+ "," + t.getParch() + "," + t.getTicket() + "," + t.getFare() + "," + t.getCabin() + "," + t.getEmbarked();
	}

	/**
	 * This method is used to get the passengerId of Titanic.
	 * @return the passengerId of Titanic.
	 */
	public int getPassengerId() {
		return passengerId;
	}

	/**
	 * This method is used to set the passengerId of Titanic.
	 * @param passengerId the passengerId of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	/**
	 * This method is used to get the survived of Titanic.
	 * @return the survived of Titanic.
	 */
	public boolean isSurvived() {
		return survived;
	}

	/**
	 * This method is used to set the survived of Titanic.
	 * @param survived the survived of Titanic.
	 * @return the survived of Titanic.
	 */
	public int isSurvivedInt(boolean survived) {
		int res = 0;
		if(survived) {
			res = 1;
		}
		return res;
	}

	/**
	 * This method is used to set the survived of Titanic.
	 * @param survived the survived of Titanic.
	 */
	public void setSurvived(boolean survived) {
		this.survived = survived;
	}

	/**
	 * This method is used to get the pClass of Titanic.
	 * @return the pClass of Titanic.
	 */
	public int getpClass() {
		return pClass;
	}

	/**
	 * This method is used to set the pClass of Titanic.
	 * @param pClass the pClass of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setpClass(int pClass) {
		this.pClass = pClass;
	}

	/**
	 * This method is used to get the name of Titanic.
	 * @return the name of Titanic.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This method is used to set the name of Titanic.
	 * @param name the name of Titanic.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method is used to get the sexe of Titanic.
	 * @return the sexe of Titanic.
	 */
	public String getSexe() {
		return sexe;
	}

	/**
	 * This method is used to set the sexe of Titanic.
	 * @param sexe the sexe of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	/**
	 * This method is used to get the age of Titanic.
	 * @return the age of Titanic.
	 */
	public double getAge() {
		return age;
	}

	/**
	 * This method is used to set the age of Titanic.
	 * @param age the age of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setAge(double age) {

		this.age = age;
	}

	/**
	 * This method is used to get the sibSp of Titanic.
	 * @return the sibSp of Titanic.
	 */
	public int getSibSp() {
		return sibSp;
	}

	/**
	 * This method is used to set the sibSp of Titanic.
	 * @param sibSp the sibSp of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setSibSp(int sibSp) {
		this.sibSp = sibSp;
	}

	/**
	 * This method is used to get the parch of Titanic.
	 * @return the parch of Titanic.
	 */
	public int getParch() {
		return parch;
	}

	/**
	 * This method is used to set the parch of Titanic.
	 * @param parch the parch of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setParch(int parch) {
		this.parch = parch;
	}

	/**
	 * This method is used to get the ticket of Titanic.
	 * @return the ticket of Titanic.
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * This method is used to set the ticket of Titanic.
	 * @param ticket the ticket of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * This method is used to get the fare of Titanic.
	 * @return the fare of Titanic.
	 */
	public double getFare() {
		return fare;
	}

	/**
	 * This method is used to set the fare of Titanic.
	 * @param fare the fare of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setFare(double fare) {
		this.fare = fare;
	}

	/**
	 * This method is used to get the cabin of Titanic.
	 * @return the cabin of Titanic.
	 */
	public String getCabin() {
		return cabin;
	}

	/**
	 * This method is used to set the cabin of Titanic.
	 * @param cabin the cabin of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setCabin(String cabin) {
		this.cabin = cabin;
	}

	/**
	 * This method is used to get the embarked of Titanic.
	 * @return the embarked of Titanic.
	 */
	public char getEmbarked() {
		return embarked;
	}

	/**
	 * This method is used to set the embarked of Titanic.
	 * @param embarked the embarked of Titanic.
	 */
	@SuppressWarnings("unused")
	public void setEmbarked(char embarked) {
		this.embarked = embarked;
	}

	/**
	 * This method is used to get the name of titanic.
	 * @return the name of titanic.
	 */
	@Override
	public String nom() {
		return "Titanic";
	}

	/**
	 * This method is used to get charge the titanic csv file.
	 * @return the list of titanic.
	 */
	@Override
	public List<KNN> charger() {
		try{
			return new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get("ressources/titanic.csv"))).withSeparator(',')
					.withType(Titanic.class).build().parse();
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * This method is used to get charge the titanic csv file with a string path.
	 * @return the list of titanic.
	 */
	@Override
	public List<KNN> charger(String path) {
		List<KNN> datas;
		try {
			datas = new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(Titanic.class).build().parse();
		} catch (Exception e) {
			e.printStackTrace();
			datas = null;
		}
		if(datas != null) {
			generateAmplitude(datas);
		}
		return datas;
	}

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

	@Override
	public KNN getClosestKNN(KNN knn, List<KNN> list, Distance distanceObject) {
		KNN res = list.get(0);
		double min = distanceObject.distance(knn, res);
		int emplacement = 0;
		for (int i = 1; i < list.size(); i++) {
			KNN current = list.get(i);
			double distance = distanceObject.distance(knn, current);
			if (distance < min) {
				Titanic t1 = (Titanic) knn;
				Titanic t2 = (Titanic) current;
				if(!t1.name.equals(t2.name)) {
					min = distance;
					res = current;
					emplacement = i;
				}
			}
		}
		list.remove(emplacement);
		return res;
	}

	public Titanic getGeneratedType(int knnInt, Titanic t, Distance distanceObject) {
		List<KNN> knns = getKClosestKNN(knnInt, t, distanceObject);
		int nb = 0;
		for(KNN knn : knns){
			Titanic t2 = (Titanic) knn;
			if(t2.isSurvived()){
				nb++;
			}
		}
		// Si plus grand que 2, alors c'est un survivant
		if(nb >= 2){
			if(t.isSurvived()){
				t.setSurvived(true);
			}
			// Sinon, c'est un mec mort (pas de pot)
		}else {
			if(!t.isSurvived()){
				t.setSurvived(false);
			}
		}
		return t;
	}

	/**
	 * This method is used to get the accuracy of the titanic.
	 * @return the accuracy of the titanic.
	 */
	@Override
	public double accuracy(List<KNN> m, Distance distanceObject, int k) {
		int res = 0;
		for(KNN test : m){
			Titanic p = (Titanic) test;
			List<KNN> knns = getKClosestKNN(k, test, distanceObject);
			int nb = 0;
			for(KNN knn : knns){
				Titanic p2 = (Titanic) knn;
				if(p2.isSurvived()){
					nb++;
				}
			}
			// Si plus grand que 2, alors c'est un survivant
			if(nb >= k/2){
				if(p.isSurvived()){
					res++;
				}
				// Sinon, c'est un mec mort (pas de pot)
			}else {
				if(!p.isSurvived()){
					res++;
				}
			}
		}
		return (res * 1.0 / m.size()) * 100.0;
	}

	/**
	 * Method to generate the amlitude of the titanic.
	 */
	@Override
	public void generateAmplitude(List<KNN> knns) {
		Titanic titanic = (Titanic) charger().get(0);
		int passengerId_min = titanic.getPassengerId();
		int passengerId_max =titanic.getPassengerId(); 
		int pClass_min = titanic.getpClass();
		int pClass_max = titanic.getpClass();
		double age_min = titanic.getAge();
		double age_max = titanic.getAge();
		int sibsp_min = titanic.getSibSp();
		int sibsp_max = titanic.getSibSp();
		double fare_min = titanic.getFare();
		double fare_max = titanic.getFare();
		int parch_min = titanic.getParch();
		int parch_max = titanic.getParch();

		for(KNN t : knns) {
			titanic = (Titanic) t;
			if (titanic.getPassengerId() < passengerId_min) {
				passengerId_min = titanic.getPassengerId();
			}
			if (titanic.getPassengerId() > passengerId_max) {
				passengerId_max = titanic.getPassengerId();
			}
			if (titanic.getpClass() < pClass_min) {
				pClass_min = titanic.getpClass();
			}
			if (titanic.getpClass() > pClass_max) {
				pClass_max = titanic.getpClass();
			}
			if (titanic.getAge() < age_min) {
				age_min = titanic.getAge();
			}
			if (titanic.getAge() > age_max) {
				age_max = titanic.getAge();
			}
			if (titanic.getSibSp() < sibsp_min) {
				sibsp_min = titanic.getSibSp();
			}
			if (titanic.getSibSp() > sibsp_max) {
				sibsp_max = titanic.getSibSp();
			}
			if (titanic.getFare() > fare_max) {
				fare_max = titanic.getFare();
			}
			if (titanic.getParch() > parch_max) {
				parch_max = titanic.getParch();
			}
			pClass_amplitude = pClass_max - pClass_min;
			age_amplitude = age_max - age_min;
			sibsp_amplitude = sibsp_max - sibsp_min;
			fare_amplitude = fare_max - fare_min;
			parch_amplitude = parch_max - parch_min;
		}
	}


	@Override
	public String toStringJFX() {
		return "Passenger Id : "+this.passengerId+"\n\nSurvived : "+this.survived+"\nPassenger Class : "+this.pClass+"\nName : "+this.name+"\nSex : "+this.sexe+"\nAge : "+this.age+"\nSiblings/Spouses : "+this.sibSp+"\nParents/Children : "+this.parch+"\nTicket : "+this.ticket+"\nPassenger Fare : "+this.fare+"\nCabin : "+this.cabin+"\nPort of Embarkation : "+getPort(this.embarked);
	}

	/**
	 * Method to get the port of embarkation.
	 * @param portIdentifier the port of embarkation.
	 * @return the port of embarkation.
	 */
	public String getPort(char portIdentifier){
		if(portIdentifier=='C')return "Cherbourg";
		if(portIdentifier=='Q')return "Queenstown";
		if(portIdentifier=='S')return "Southampton";
		return "No port specified";
	}
}
