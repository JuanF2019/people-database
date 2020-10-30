/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import collections.*;
import javafx.util.converter.LocalDateStringConverter;
import utilities.Pair;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;

public class DataBaseManager {

	//------------------------------------------------------------------------------------

	//Constants of the DataBaseManager class

	public static final int MAX_PEOPLE_NUMBER = Integer.MAX_VALUE;
	
	public static final String PICTURE_URL = "https://thispersondoesnotexist.com/";

	//------------------------------------------------------------------------------------

	//Attributes of the DataBaseManager class

	private int savedPeopleNumber;
	
	private BufferedReader br;
	
	private BufferedWriter bw;
	
	private ArrayList<String> countries;
	
	//------------------------------------------------------------------------------------

	//Relations of the DataBaseManager class

	private BinarySearchTreeInterface<String,Person> namesTree;
	
	private BinarySearchTreeInterface<String,Person> surnamesTree;

	private TrieInterface namesTrie;

	private TrieInterface surnamesTrie;

	private TrieInterface fullNamesTrie;

	private BinarySearchTreeInterface<String,Person> fullNamesTree;

	private HashTableInterface<Integer,Person> idsHashTable;

	private Person currentPerson;
	
	private RandomFieldsGenerator randomFieldsGenerator;
	
	
	
	//------------------------------------------------------------------------------------

	// Constructor method of the DataBaseManager class

	public DataBaseManager() {
		surnamesTree = new AVLTree<>();
		namesTree = new AVLTree<>();
		namesTrie = new Trie();
		surnamesTrie = new Trie();
		fullNamesTrie = new Trie();
		idsHashTable = new HashTable<>();
		fullNamesTree = new RedBlackTree<>();
		countries = new ArrayList<>();
	}

	//------------------------------------------------------------------------------------

	// Get's methods of the DataBaseManager class

	public int getSavedPeopleNumber() {
		return savedPeopleNumber;
	}

	public ArrayList<String> getCountries(){
		return countries;
	}

	//------------------------------------------------------------------------------------

	// Set's methods of the DataBaseManager class

	public void setSavedPeopleNumber(int savedPeopleNumber) {
		this.savedPeopleNumber = savedPeopleNumber;
	}

	//------------------------------------------------------------------------------------

	//Operations of class DataBaseManager
	
	//Creates new Person receives all parameters
	public boolean create() {
		return false;
	}

	// *****************************************************

	public boolean read() {
		return readPeopleData() && readGenerationData();
	}
	
	
	public boolean readPeopleData(){
		
		
		//loads all data from people
		
		
		
		return false;
	}
	
	// *****************************************************
	
	public boolean readGenerationData() {
						
		ArrayList<Pair<Integer,Double>> loadedAges = loadAges();
		
		if(loadedAges == null)
			return false;
		
		ArrayList<String> loadedNames = loadNames();
		
		if(loadedNames == null)
			return false;
		
		ArrayList<String> loadedSurnames = loadSurnames();
		
		if(loadedSurnames == null)
			return false;
		
		ArrayList<Pair<String,Double>> loadedCountries = loadCountries();
		
		if(loadedCountries == null)
			return false;
		
		randomFieldsGenerator = new RandomFieldsGenerator(loadedSurnames, loadedSurnames, loadedCountries, loadedAges, (HashTable<Integer,Person>)idsHashTable);
				
		return true;				
	}

	// *****************************************************
	
	private ArrayList<Pair<Integer, Double>> loadAges() {
		ArrayList<Pair<Integer,Double>> ages;
		try {
			
			ages =  new ArrayList<>();
			
			br = new BufferedReader(new FileReader(new File("data/generation_data/ages.csv")));
			
			br.readLine();//Ignores first line
			
			double acumulatedProbability = 0;
			String line = br.readLine();
			String[] splitLine = null;
			
			
			while(line != null && !line.isEmpty()) {
									
				splitLine = line.split(",");
						
				int maxAge = Integer.parseInt(splitLine[1]);
					
				acumulatedProbability += Integer.parseInt(splitLine[2]);
					
				ages.add(new Pair<Integer,Double>(maxAge, Double.valueOf(acumulatedProbability)));
				
							
				line = br.readLine();
				
			}
			
			br.close();
			return ages;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	// *****************************************************
	
	private ArrayList<String> loadNames() {
		ArrayList<String> loadedNames;
		
		try {
			loadedNames = new ArrayList<>();
			
			br = new BufferedReader(new FileReader(new File("data/generation_data/names.csv")));
			
			String line = br.readLine();			
			
			while(line != null && !line.isEmpty()) {
													
				loadedNames.add(line);		
							
				line = br.readLine();			
			}
			
			br.close();
			
			return loadedNames;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	// *****************************************************
	
	private ArrayList<String> loadSurnames() {
		ArrayList<String> loadedSurnames;
		try {
			
			loadedSurnames = new ArrayList<>();
			
			br = new BufferedReader(new FileReader(new File("data/generation_data/surnames.csv")));
			
			String line = br.readLine();		
			
			while(line != null && !line.isEmpty()) {
													
				loadedSurnames.add(line);		
							
				line = br.readLine();			
			}
			
			br.close();
			
			return loadedSurnames;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	// *****************************************************
	
	private ArrayList<Pair<String, Double>> loadCountries() {
		
		ArrayList<Pair<String,Double>> loadedCountries;
		
		try {
			
			loadedCountries = new ArrayList<>();
			
			br = new BufferedReader(new FileReader(new File("data/generation_data/countries_population.csv")));
			
			br.readLine();//Ignores first line
			
			double acumulatedSum = 0;
			String line = br.readLine();
			String[] splitLine = null;
			
			
			while(line != null && !line.isEmpty()) {
									
				splitLine = line.split(",");
					
				String country = splitLine[0];
				
				int population = Integer.parseInt(splitLine[1]);
					
				acumulatedSum += population;
					
				loadedCountries.add(new Pair<String,Double>(country, Double.valueOf(population)));
				countries.add(country);
											
				line = br.readLine();				
								
			}
			
			double accumulatedProbability = 0;
			double probability = 0;;
			
			for (int i = 0 ; i < loadedCountries.size() ; i++) {
				Pair<String,Double> pair = loadedCountries.get(i);
				
				probability = pair.getValue()/acumulatedSum;
				accumulatedProbability += probability;
								
				pair.setValue(Double.valueOf(accumulatedProbability));
			}
			
			
			br.close();
			return loadedCountries;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	// *****************************************************

	
	//[name,surname,sex,birthday,height,nationality]
	public void update(String[] fieldsToUpdate) {
		if(!fieldsToUpdate[0].equals("")) {
			currentPerson.setName(fieldsToUpdate[0]);
		}
		
		if(!fieldsToUpdate[1].equals("")) {
			currentPerson.setSurname(fieldsToUpdate[1]);
		}
		
		if(!fieldsToUpdate[2].equals("")) {
			if(!fieldsToUpdate[2].equalsIgnoreCase(Sex.FEMALE.toString()))
			currentPerson.setSex(Sex.FEMALE);
			else
				currentPerson.setSex(Sex.MALE);
		}
		
		if(!fieldsToUpdate[3].equals("")) {
			String[] aux = fieldsToUpdate[3].split("-");
			int year = Integer.parseInt(aux[0]);
			int month = Integer.parseInt(aux[1]);
			int day = Integer.parseInt(aux[2]);
			
			currentPerson.setBirthday(LocalDate.of(year, month, day));
		}
		
		if(!fieldsToUpdate[4].equals("")) {
			currentPerson.setHeight(Double.parseDouble(fieldsToUpdate[4]));
		}
		
		if(!fieldsToUpdate[5].equals("")) {
			currentPerson.setSurname(fieldsToUpdate[5]);
		}
		
	}
	
	// *****************************************************
	
	//Serialize data
	public boolean write() {
		
		
		//Saves a file for each structure
		return false;
	}

	// *****************************************************
	
	//removes current person
	public boolean delete() {
		return false;
	}

	// *****************************************************

	public Person search(SearchCriteria criteria, String data) {
		Person aux = null;

		switch(criteria) {

		case NAME:
			aux = searchByName(data);
			break;		

		case SURNAME:
			aux = searchBySurname(data);
			break;

		case FULL_NAME:
			aux = searchByFullName(data);
			break;

		case ID:
			aux = searchById(Integer.parseInt(data));
			break;
		}
		return aux;
	}

	// *****************************************************

	private Person searchByName(String name) {
		currentPerson = namesTree.search(name);
		return currentPerson;
	}

	// *****************************************************

	private Person searchBySurname(String surname) {
		currentPerson =  surnamesTree.search(surname);
		return currentPerson;
	}

	// *****************************************************

	private Person searchByFullName(String fn) {
		currentPerson = fullNamesTree.search(fn);
		return currentPerson;
	}

	// *****************************************************

	private Person searchById(int id) {
		currentPerson = idsHashTable.search(id);
		return currentPerson;
	}

	// *****************************************************

	public ArrayList<String> getPredictions(String prefix, SearchCriteria criteria) {
		switch(criteria) {
		case NAME:
			return namesTrie.getPredictions(prefix);
			
		case SURNAME:
			return surnamesTrie.getPredictions(prefix);
			
		case FULL_NAME:
			return fullNamesTrie.getPredictions(prefix);
			
		default:
			return null;
		}		
	}

	// *****************************************************

	public void clearCurrentPerson() {
		currentPerson = null;
	}

	// *****************************************************

	public boolean generatePeople(int n) {
		
		if(MAX_PEOPLE_NUMBER - savedPeopleNumber - n >= 0) {
			for (int i = 0; i < n; i++) {
				Person newPerson = generatePerson();
				
				String name = newPerson.getName();
				String surname = newPerson.getSurname();
				int id = newPerson.getId();
				
				namesTrie.add(name);
				namesTree.add(name, newPerson);
				
				surnamesTrie.add(surname);
				surnamesTree.add(surname, newPerson);
				
				String fullName = name + " " + surname;
				
				fullNamesTrie.add(fullName);
				fullNamesTree.add(fullName,newPerson);
								
				idsHashTable.insert(id,newPerson);				
			}
			savedPeopleNumber += n;
			return true;		
		} 
		else {
			return false;
		}		
	}

	// *****************************************************

	private Person generatePerson() {
		String name = randomFieldsGenerator.name();
		String surname = randomFieldsGenerator.surname();
		int id = randomFieldsGenerator.id();
		Sex sex = randomFieldsGenerator.sex();
		LocalDate birthday = randomFieldsGenerator.birthday();
		int height = randomFieldsGenerator.height();
		String nationality = randomFieldsGenerator.nationality();	
		
		return new Person(name, surname, id, sex, birthday, height, nationality);
	}
	
	//------------------------------------------------------------------------------------

}
