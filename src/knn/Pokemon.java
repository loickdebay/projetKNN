package knn;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvToBeanBuilder;
import distance.Distance;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/** Class representing a Pokémon.*/
public class Pokemon extends KNN {
	@CsvBindByName(column = "name")
	private String name;

	@CsvBindByName(column = "attack")
	private int attack;

	@CsvBindByName(column = "base_egg_steps")
	private int baseEggSteps;

	@CsvBindByName(column = "capture_rate")
	private double captureRate;

	@CsvBindByName(column = "defense")
	private int defense;

	@CsvBindByName(column = "experience_growth")
	private int experienceGrowth;

	@CsvBindByName(column = "hp")
	private int hp;

	@CsvBindByName(column = "sp_attack")
	private int spAttack;

	@CsvBindByName(column = "sp_defense")
	private int spDefense;

	@CsvBindByName(column = "type1")
	private String type1;

	@CsvBindByName(column = "type2")
	private String type2;

	@CsvBindByName(column = "speed")
	private double speed;

	@CsvBindByName(column = "is_legendary")
	private boolean isLegendary;

	public static double base_egg_steps_amplitude;
	public static double capture_rate_amplitude;
	public static double experience_growth_amplitude;
	public static double speed_amplitude;

	public Pokemon() {

	}

	/**
	 * This method is used to create a Pokémon object without legendary args.
	 * @param name Name of the Pokémon.
	 * @param attack Attack of the Pokémon.
	 * @param baseEggSteps Base egg steps of the Pokémon.
	 * @param captureRate Capture rate of the Pokémon.
	 * @param defense Defense of the Pokémon.
	 * @param experienceGrowth Experience growth of the Pokémon.
	 * @param hp HP of the Pokémon.
	 * @param spAttack Special attack of the Pokémon.
	 * @param spDefense Special defense of the Pokémon.
	 * @param type1 Type 1 of the Pokémon.
	 * @param type2 Type 2 of the Pokémon.
	 * @param speed Speed of the Pokémon.
	 */
	public Pokemon(String name, int attack, int baseEggSteps, double captureRate, int defense, int experienceGrowth, int hp, int spAttack, int spDefense, String type1, String type2, double speed) {
		this.name = name;
		this.attack = attack;
		this.baseEggSteps = baseEggSteps;
		this.captureRate = captureRate;
		this.defense = defense;
		this.experienceGrowth = experienceGrowth;
		this.hp = hp;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.type1 = type1;
		this.type2 = type2;
		this.speed = speed;
	}

	/**
	 * This method is used to create a Pokémon object.
	 * @param name Name of the Pokémon.
	 * @param attack Attack of the Pokémon.
	 * @param baseEggSteps Base egg steps of the Pokémon.
	 * @param captureRate Capture rate of the Pokémon.
	 * @param defense Defense of the Pokémon.
	 * @param experienceGrowth Experience growth of the Pokémon.
	 * @param hp HP of the Pokémon.
	 * @param spAttack Special attack of the Pokémon.
	 * @param spDefense Special defense of the Pokémon.
	 * @param type1 Type 1 of the Pokémon.
	 * @param type2 Type 2 of the Pokémon.
	 * @param speed Speed of the Pokémon.
	 * @param isLegendary Is the Pokémon legendary?
	 */
	public Pokemon(String name, int attack, int baseEggSteps, double captureRate, int defense, int experienceGrowth, int hp, int spAttack, int spDefense, String type1, String type2, double speed, boolean isLegendary) {
		this.name = name;
		this.attack = attack;
		this.baseEggSteps = baseEggSteps;
		this.captureRate = captureRate;
		this.defense = defense;
		this.experienceGrowth = experienceGrowth;
		this.hp = hp;
		this.spAttack = spAttack;
		this.spDefense = spDefense;
		this.type1 = type1;
		this.type2 = type2;
		this.speed = speed;
		this.isLegendary = isLegendary;
	}

	/**
	 * toString of the Pokemon class.
	 * @return String representing the Pokemon.
	 */
	@Override
	public String toString() {
		return "Pokemon [name=" + name + "]";
	}
	
	/**
	 * toString of the Pokemon class (ONLY FOR TEST METHODS).
	 * @param data list of Pokemon.
	 * @param idx index of the Pokemon.
	 * @return String representing the Pokemon.
	 */
	public String toStringTest(List<KNN> data, int idx) {
		Pokemon poke = (Pokemon)data.get(idx);
		return poke.getName() + "," + poke.getAttack() + "," + poke.getBaseEggSteps() + "," + poke.getCaptureRate() + "," + poke.getDefense() + "," + poke.getExperienceGrowth() + "," + poke.getHp()
				+ "," + poke.getSpAttack() + "," + poke.getSpDefense() + "," + poke.getType1() + "," + poke.getType2()
				+ "," + poke.getSpeed() + "," + poke.isLegendary();
	}

	/**
	 * Method to retrieve the name of the Pokémon.
	 * @return the name of Pokémon.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Method to set the nom of Pokémon.
	 * @param name the name of Pokémon.
	 */
	public void setName(String name) {
		this.name = name;
	}

/**
	 *Method to retrieve the Pokémon's attack.
	 * @return the Pokémon's attack.
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Method to set the Pokémon's attack.
	 * @param attack the Pokémon's attack.
	 */
	@SuppressWarnings("unused")
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Method to recover the number of eggs of the Pokémon.
	 * @return the number of eggs of the Pokémon.
	 */
	public int getBaseEggSteps() {
		return baseEggSteps;
	}

	/**
	 * Method to set the number of eggs of the Pokémon.
	 * @param baseEggSteps the number of eggs of the Pokémon.
	 */
	public void setBaseEggSteps(int baseEggSteps) {
		this.baseEggSteps = baseEggSteps;
	}

	/**
	 * Method to recover the capture rate of the Pokémon.
	 * @return the capture rate of the Pokémon.
	 */
	public double getCaptureRate() {
		return captureRate;
	}

	/**
	 * Method to set the capture rate of the Pokémon.
	 * @param captureRate the capture rate of the Pokémon.
	 */
	public void setCaptureRate(double captureRate) {
		this.captureRate = captureRate;
	}

	/**
	 * Method to recover the Pokémon's defense.
	 * @return the defense of the Pokémon.
	 */
	public int getDefense() {
		return defense;
	}

	/**
	 * Method to set the Pokémon's defense.
	 * @param defense the defense of the Pokémon.
	 */
	@SuppressWarnings("unused")
	public void setDefense(int defense) {
		this.defense = defense;
	}

	/**
	 * Method of recovering the Pokémon's experience.
	 * @return the Pokémon's experience.
	 */
	public int getExperienceGrowth() {
		return experienceGrowth;
	}

	/**
	 * Method to set the Pokémon experience.
	 * @param experienceGrowth the Pokémon's experience.
	 */
	public void setExperienceGrowth(int experienceGrowth) {
		this.experienceGrowth = experienceGrowth;
	}

	/**
	 * A method of recovering a Pokémon's hit points.
	 * @return the life points of the Pokémon.
	 */
	public int getHp() {
		return hp;
	}

	/**
	 * Method to set the life points of the Pokémon.
	 * @param hp the life points of the Pokémon.
	 */
	@SuppressWarnings("unused")
	public void setHp(int hp) {
		this.hp = hp;
	}

	/**
	 * A method of recovering the Pokémon's special attack.
	 * @return the Pokémon's special attack.
	 */
	public int getSpAttack() {
		return spAttack;
	}

	/**
	 * Method to set the Pokémon's special attack.
	 * @param spAttack the Pokémon's special attack.
	 */
	@SuppressWarnings("unused")
	public void setSpAttack(int spAttack) {
		this.spAttack = spAttack;
	}

	/**
	 * Method of recovering the Pokémon's special defense.
	 * @return the Pokémon's special defense.
	 */
	public int getSpDefense() {
		return spDefense;
	}

	/**
	 * Method to set the special defense of the Pokémon.
	 * @param spDefense the special defense of the Pokémon.
	 */
	@SuppressWarnings("unused")
	public void setSpDefense(int spDefense) {
		this.spDefense = spDefense;
	}

	/**
	 * Method to recover the type 1 of the Pokémon.
	 * @return the type 1 of the Pokémon.
	 */
	public String getType1() {
		return type1;
	}

	/**
	 * Method to set the type 1 of the Pokémon.
	 * @param type1 the type 1 of the Pokémon.
	 */
	@SuppressWarnings("unused")
	public void setType1(String type1) {
		this.type1 = type1;
	}

	/**
	 * Method to recover the type 2 of the Pokémon.
	 * @return the type 2 of the Pokémon.
	 */
	public String getType2() {
		return type2;
	}

	/**
	 * Method to set the type 2 of the Pokémon.
	 * @param type2 the type 2 of the Pokémon.
	 */
	@SuppressWarnings("unused")
	public void setType2(String type2) {
		this.type2 = type2;
	}

	/**
	 * Method to recover the speed of the Pokémon.
	 * @return the speed of the Pokémon.
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Method to set the speed of the Pokémon.
	 * @param speed the speed of the Pokémon.
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Method to find out if the Pokémon is legendary or not.
	 * @return whether the Pokémon is legendary or not.
	 */
	public boolean isLegendary() {
		return isLegendary;
	}

	/**
	 * Method to set whether the Pokémon is legendary or not.
	 * @param isLegendary whether the Pokémon is legendary or not.
	 */
	public void setLegendary(boolean isLegendary) {
		this.isLegendary = isLegendary;
	}

	/**
	 * Method to retrieve the Pokémon's ID.
	 * @return the ID of Pokémon.
	 */
	@Override
	public String nom() {
		return "Pokemon";
	}

	/**
	 * Method to generate the amlitude of the Pokémon.
	 * @param datas list of Pokémon.
	 */
	public void generateAmplitude(List<KNN> datas) {
		Pokemon poke = (Pokemon) datas.get(0);
		int egg_min = poke.getBaseEggSteps();
		int egg_max = poke.getBaseEggSteps();
		double capture_min = poke.getCaptureRate();
		double capture_max = poke.getCaptureRate();
		int experience_min = poke.getExperienceGrowth();
		int experience_max = poke.getExperienceGrowth();
		double speed_min = poke.getSpeed();
		double speed_max = poke.getSpeed();

		for (KNN p : datas) {
			poke = (Pokemon) p;
			if (poke.getBaseEggSteps() < egg_min) {
				egg_min = poke.getBaseEggSteps();
			}
			if (poke.getBaseEggSteps() > egg_max) {
				egg_max = poke.getBaseEggSteps();
			}
			if (poke.getCaptureRate() < capture_min) {
				capture_min = poke.getCaptureRate();
			}
			if (poke.getCaptureRate() > capture_max) {
				capture_max = poke.getCaptureRate();
			}
			if (poke.getExperienceGrowth() < experience_min) {
				experience_min = poke.getExperienceGrowth();
			}
			if (poke.getExperienceGrowth() > experience_max) {
				experience_max = poke.getExperienceGrowth();
			}
			if (poke.getSpeed() < speed_min) {
				speed_min = poke.getSpeed();
			}
			if (poke.getSpeed() > speed_max) {
				speed_max = poke.getSpeed();
			}
			base_egg_steps_amplitude = egg_max - egg_min;
			capture_rate_amplitude = capture_max - capture_min;
			experience_growth_amplitude = experience_max - experience_min;
			speed_amplitude = speed_max - speed_min;
		}
	}
	/**
	 * Method to load data from CSV file.
	 * @return a list containing the data from the CSV file.
	 */
	@Override
	public List<KNN> charger() {
		List<KNN> datas;
		try {
			datas = new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get("ressources/pokemon_train.csv"))).withSeparator(',')
					.withType(Pokemon.class).build().parse();
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
	 * Method to load data from CSV file with a specific path.
	 * @return the distance between two Pokémon.
	 */
	@Override
	public List<KNN> charger(String path) {
		List<KNN> datas;
		try {
			datas = new CsvToBeanBuilder<KNN>(Files.newBufferedReader(Paths.get(path))).withSeparator(',')
					.withType(Pokemon.class).build().parse();
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
	 * This method is used to get closest Pokemon.
	 * @return the distance between two Pokémon in a arraylist.
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
	 * This method is used to get closest Pokemon. with a list of pokémon.
	 * @return the distance between two Pokémon in a arraylist.
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
				Pokemon p1 = (Pokemon) knn;
				Pokemon p2 = (Pokemon) current;
				if(!p1.getName().equals(p2.getName())) {
					min = distance;
					res = current;
					emplacement = i;
				}
			}
		}
		list.remove(emplacement);
		return res;
	}

	/**
	 * This method is used to guess the legendary of a Pokemon.
	 * @param knnInt the number of neighbors.
	 * @param p the Pokemon.
	 * @param distanceObject the distance object.
	 * @return the pokemon with the legendary guessed.
	 */
	public Pokemon getGeneratedType(int knnInt, Pokemon p, Distance distanceObject) {
		List<KNN> knns = getKClosestKNN(knnInt, p, distanceObject);
		int nb = 0;
		for(KNN knn : knns){
			Pokemon p2 = (Pokemon) knn;
			if(p2.isLegendary()){
				nb++;
			}
		}
		// Si plus grand que 2, alors c'est un survivant
		if(nb >= 2){
			if(p.isLegendary()){
				p.setLegendary(true);
			}
			// Sinon, c'est un mec mort (pas de pot)
		}else {
			if(!p.isLegendary()){
				p.setLegendary(false);
			}
		}
		return p;
	}

	/**
	 * This method is used to get the accuracy of the pokemon.
	 * @return the accuracy of the pokemon.
	 */
	public double accuracy(List<KNN> m, Distance distanceObject, int k) {
		int res = 0;
		for(KNN test : m){
			Pokemon p = (Pokemon) test;
			List<KNN> knns = getKClosestKNN(k, test, distanceObject);
			int nb = 0;
			for(KNN knn : knns){
				Pokemon p2 = (Pokemon) knn;
				if(p2.isLegendary){
					nb++;
				}
			}
			// Si plus grand que 2, alors c'est un pokemon légendaire
			if(nb >= k/2){
				if(p.isLegendary){
					res++;
				}
			// Sinon, ce n'est pas un pokemon légendaire
			}else {
				if(!p.isLegendary){
					res++;
				}
			}
		}
		return (res * 1.0 / m.size()) * 100.0;
	}
	public String toStringJFX(){
		return this.name+"\n\n"+"is Legendary : "+this.isLegendary+"\nAttack : "+this.attack+"\nDefense : "+this.defense+"\nSpecial attack : "+this.spAttack+"\nSpecial Defense : "+this.spDefense+"\nSpeed : "+this.speed+"\nBase egg steps : "+this.baseEggSteps+"\nCapture rate : "+this.captureRate+"\nExperience growth : "+this.experienceGrowth+"\nHP : "+this.hp+"\nFirst type : "+this.type1+"\nSecond type : "+this.type2;
	}
}

