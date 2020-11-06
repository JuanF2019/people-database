/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import java.time.LocalDate;
import java.util.ArrayList;
import utilities.Pair;
import java.util.SplittableRandom;

public class RandomFieldsGenerator {

	//------------------------------------------------------------------------------------

	//Constants of the RandomFieldsGenerator class

	private static final double MIN_HEIGHT = 140;

	private static final double MAX_HEIGHT = 200;
	
	//------------------------------------------------------------------------------------
	
	//Attributes of the RandomFieldsGenerator class

	private ArrayList<String> loadedNames;

	private ArrayList<String> loadedSurnames;

	//------------------------------------------------------------------------------------
	
	//Relations of the RandomFieldsGenerator class
	
	//Saves country name and accumulative probability
	private ArrayList<Pair<String,Double>> loadedCountries;	

	//Age stored represents the maximum age and accumulative probability
	private ArrayList<Pair<Integer,Double>> ages;
	
	private SplittableRandom sr;
	
	//------------------------------------------------------------------------------------
	
	//Constructor of the RandomFieldsGenerator class

	public RandomFieldsGenerator(ArrayList<String> ln, ArrayList<String> ls, ArrayList<Pair<String,Double>> lc, ArrayList<Pair<Integer,Double>> a) {
		
		loadedNames = ln;
		
		loadedSurnames = ls;
		
		loadedCountries = lc;
		
		ages = a;	
		
		
	}



}
