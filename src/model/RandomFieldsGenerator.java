/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import java.time.LocalDate;
import java.util.ArrayList;

import collections.HashTable;
import collections.HashTableInterface;
import utilities.Pair;

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

	private HashTableInterface<Integer,Person> idsHashTable;
	
	//------------------------------------------------------------------------------------
	
	//Constructor of the RandomFieldsGenerator class

	public RandomFieldsGenerator(ArrayList<String> ln, ArrayList<String> ls, ArrayList<Pair<String,Double>> lc, ArrayList<Pair<Integer,Double>> a, HashTable<Integer,Person> idsHT) {
		
		loadedNames = ln;
		loadedSurnames = ls;
		
		loadedCountries = lc;
		
		ages = a;
		
		idsHashTable = idsHT;
		
	}

	//------------------------------------------------------------------------------------
	
	//Operations of the RandomFieldsGenerator class
	
	public String name() {
		return loadedNames.get((int)(Math.random()*loadedNames.size()));
	}
	
	
	public String surname() {
		return loadedSurnames.get((int)(Math.random()*loadedSurnames.size()));
	}
	
	//------------------------------------------------------------------------------------
	
	// Local date method

	public LocalDate birthday() {
		
		double random = Math.random();

		int minAge = - 1;
		
		int maxAge = 0;
		
		boolean check = false;

		for (int i = 0; i < ages.size() && !check; i++) {
			
			minAge = maxAge;
			
			maxAge = ages.get(i).getKey();
			
			if(random < ages.get(i).getValue()) {
				
				check = true;
				
			}
			
		}

		int age = (int) Math.round(Math.random()*(maxAge - minAge + 1) + minAge);

		LocalDate now = LocalDate.now();

		LocalDate birthday = now.minusYears(age);

		if(birthday.isLeapYear()) {
			
			birthday.minusDays((long)Math.random()*366);
			
		} else {
			
			birthday.minusDays((long)Math.random()*365);
			
		}

		return birthday;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Id method

	public int id() {
		
		int id = -1;
		
		do {
			
			id = (int) Math.round((Math.random()*(DataBaseManager.MAX_PEOPLE_NUMBER+0.0)));
			
		} while(idsHashTable.search(id) == null);

		return id;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Gender method

	public Sex sex() {
		
		return(Math.random() < 0.5)? Sex.MALE:Sex.FEMALE;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Height method

	public double height() {
		
		return (Math.random()*(MAX_HEIGHT-MIN_HEIGHT+1) + MIN_HEIGHT);
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Nationality

	public String nationality() {
		
		double random = Math.random();

		for (int i = 0; i < loadedCountries.size(); i++) {
			
			Pair<String, Double> country = loadedCountries.get(i);
			
			if(random < country.getValue()) {
				
				return country.getKey();
				
			}
			
		}

		return null;
		
	}
	
	//------------------------------------------------------------------------------------

}
