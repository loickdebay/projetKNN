package knn;

import distance.*;

/**
 * Class made to convert some values efficiently
 */
public class KNNValuesConverter {
    /**
     * allows a pokemon to get a value from it's "isLegendary" boolean value
     * @param legendary boolean to convert
     * @return the converted value
     */
    public int isLegendaryToInt(boolean legendary){
        if(legendary)return 2;
        else{return 1;}
    }

    /**
     * allows a pokemon to get a value from a Type
     * @param parameter the type to convert
     * @return the converted value
     */
    public int pokemonType(String parameter){
        switch (parameter){
            case "bug":return 1;
            case "dark":return 2;
            case "dragon":return 3;
            case "electric":return 4;
            case "fairy":return 5;
            case "fighting":return 6;
            case "fire":return 7;
            case "flying":return 8;
            case "ghost":return 9;
            case "grass":return 10;
            case "ground":return 11;
            case "ice":return 12;
            case "normal":return 13;
            case "poison":return 14;
            case "psychic":return 15;
            case "rock":return 16;
            case "steel":return 17;
            case "water":return 18;
        }
        return 0;
    }

    /**
     * converts an iris variety to a numeric value
     * @param string the iris variety
     * @return the converted value
     */
    public int getVarietyFromString(String string){
        switch(string){
            case "Setosa":return 1;
            case "Versicolor":return 2;
            case "Virginica":return 3;
        }
        return 0;
    }

    /**
     * Returns an integer from the embarkation port identifier of a titanic object
     * @param character the port identifier
     * @return the converted value
     */
    public int getIntFromPort(char character){
        if(character=='C')return 1;
        if(character=='S')return 2;
        if(character=='Q')return 3;
        return 0;
    }

    /**
     * allows a titanic object to get a numeric value from it's sex
     * @param sex the sex value
     * @return the converted value
     */
    public int getIntFromSex(String sex){
        if(sex.equals("male"))return 1;
        if(sex.equals("female"))return 2;
        return 0;
    }

    /**
     * gets a numeric value from the "survived" parameter of a titanic object
     * @param survived the titanic's value
     * @return the converted value
     */
    public int getIntFromSurvived (boolean survived){
        if(survived)return 2;
        return 1;
    }

    /**
     * Gets a Distance value from the corresponding string
     * @param distance the type in a  string
     * @return the actual type
     */
    public Distance getDistanceFromString(String distance){
        switch (distance){
            case "Euclidean" : return new DistanceEuclidienne();
            case "Normalized Euclidean" : return new DistanceEuclidienneNormalisee();
            case "Manhattan" : return new DistanceManhattan();
            case "Normalized Manhattan" : return new DistanceManhattanNormalisee();
        }
        return new DistanceEuclidienne();
    }

}
