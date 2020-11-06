/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import collections.*;
import utilities.Pair;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.SplittableRandom;

public class DataBaseManager {
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
	private ArrayList<Pair<Integer,Double>> loadedAges;
	
	private SplittableRandom sr;
	
	//------------------------------------------------------------------------------------

	//------------------------------------------------------------------------------------

	//Constants of the DataBaseManager class

	public static final int MAX_PEOPLE_NUMBER = Integer.MAX_VALUE;

	public static final String PICTURE_URL = "https://thispersondoesnotexist.com/";

	public static final String GENERATION_DATA_PATH = "data/generation_data/";

	public static final String PEOPLE_DATA_PATH = "data/people_data/";

	//------------------------------------------------------------------------------------

	//Attributes of the DataBaseManager class

	private int savedPeopleNumber;

	private BufferedReader br;

	private ArrayList<String> countries;

	private long generationTime;

	//------------------------------------------------------------------------------------

	//Relations of the DataBaseManager class

	private BinarySearchTreeInterface<String,Person> namesTree;

	private BinarySearchTreeInterface<String,Person> surnamesTree;

	private TrieInterface namesTrie;

	private TrieInterface surnamesTrie;

	private TrieInterface fullNamesTrie;

	private BinarySearchTreeInterface<String,Person> fullNamesTree;

	private HashTableInterface<Long,Person> idsHashTable;

	private Person currentPerson;

	//------------------------------------------------------------------------------------

	// Constructor method of the DataBaseManager class

	public DataBaseManager() {

		surnamesTree = new AVLTree<>();

		namesTree = new AVLTree<>();

		namesTrie = new Trie();

		surnamesTrie = new Trie();

		fullNamesTrie = new Trie();

		idsHashTable = new HashTable<>();

		fullNamesTree = new AVLTree<>();

		countries = new ArrayList<>();
		
		sr = new SplittableRandom();

	}

	//------------------------------------------------------------------------------------

	// Get's methods of the DataBaseManager class

	public int getSavedPeopleNumber() {
		return savedPeopleNumber;
	}

	public long getGenerationTime() {
		return generationTime;
	}
	
	public ArrayList<String> getCountries(){
		return countries;
	}

	//------------------------------------------------------------------------------------

	// Get's methods of the DataBaseManager class

	public void setCurrentPerson(Person p) {
		currentPerson = p;
	}
	//------------------------------------------------------------------------------------

	//Operations of class DataBaseManager

	//Creates new Person receives all parameters
	public boolean create(String name, String ln, Sex s, LocalDate ld, Double height, String nat) {

		if(savedPeopleNumber == MAX_PEOPLE_NUMBER) {

			return false;

		} else {
			
			Person p = new Person(name, ln, id(), s, ld, height, nat);

			savePerson(p);

			savedPeopleNumber++;

			return true;

		}	

	}

	public void addPerson(Person p) {
		savePerson(p);
	}
	
	//------------------------------------------------------------------------------------

	// Save person on the system

	private void savePerson(Person newPerson) {

		namesTree.add(newPerson.getName(), newPerson);

		surnamesTree.add(newPerson.getSurname(), newPerson);

		fullNamesTree.add(newPerson.getName() + " " + newPerson.getSurname(), newPerson);

		idsHashTable.insert(newPerson.getId(), newPerson);

		namesTrie.add(newPerson.getName());

		surnamesTrie.add(newPerson.getSurname());

		fullNamesTrie.add(newPerson.getName() + " " + newPerson.getSurname());
		
		System.out.println("Tree weight: " + namesTree.getWeight());
		System.out.println("Trie stored words: " + ((Trie)namesTrie).getStoredWordsCount());
	}

	//------------------------------------------------------------------------------------

	// Read method

	public boolean read() {

		return readPeopleData() & readGenerationData();

	}

	//------------------------------------------------------------------------------------

	// Read people database

	public boolean readPeopleData() {	

		try {

			File file = new File(PEOPLE_DATA_PATH + "people.dat");
			
						
			if(file.exists()) {
				
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));	

				Object[] people = (Object[]) ois.readObject();
				
				System.out.println(people.length);
				
				for (int i = 0; i < people.length; i++) {
					savePerson((Person) people[i]);
				} 								
				
				ois.close();				
				
				return true;				
			}
			return true;			

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}

	}

	//------------------------------------------------------------------------------------

	// Read generation data

	public boolean readGenerationData() {

		loadedAges = loadAges();

		if(loadedAges == null)
			return false;		

		loadedNames = loadNames();

		if(loadedNames == null)
			return false;

		loadedSurnames = loadSurnames();

		if(loadedSurnames == null)
			return false;

		
		
		loadedCountries = loadCountries();

		if(loadedCountries == null)
			return false;

		return true;

	}

	//------------------------------------------------------------------------------------

	// Load ages method

	private ArrayList<Pair<Integer, Double>> loadAges() {

		ArrayList<Pair<Integer,Double>> ages;

		try {

			ages =  new ArrayList<>();

			br = new BufferedReader(new FileReader(new File(GENERATION_DATA_PATH + "ages.csv")));

			br.readLine();//Ignores first line

			double acumulatedProbability = 0;

			String line = br.readLine();

			String[] splitLine = null;

			while(line != null && !line.isEmpty()) {

				splitLine = line.split(",");

				int maxAge = Integer.parseInt(splitLine[0]);

				acumulatedProbability += Double.parseDouble(splitLine[1]);

				ages.add(new Pair<Integer,Double>(maxAge, Double.valueOf(acumulatedProbability)));


				line = br.readLine();

			}

			br.close();

			return ages;

		} catch(Exception ex) {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// Load names method

	private ArrayList<String> loadNames() {

		ArrayList<String> loadedNames;

		try {

			loadedNames = new ArrayList<>();

			br = new BufferedReader(new FileReader(new File(GENERATION_DATA_PATH + "names.csv")));

			String line = br.readLine();			

			while(line != null && !line.isEmpty()) {

				loadedNames.add(line);		

				line = br.readLine();			
			}

			br.close();

			return loadedNames;

		} catch(Exception ex) {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// Load surnames method

	private ArrayList<String> loadSurnames() {

		ArrayList<String> loadedSurnames;

		try {

			loadedSurnames = new ArrayList<>();

			br = new BufferedReader(new FileReader(new File(GENERATION_DATA_PATH + "surnames.csv")));

			String line = br.readLine();		

			while(line != null && !line.isEmpty()) {

				loadedSurnames.add(line);		

				line = br.readLine();			
			}

			br.close();

			return loadedSurnames;

		} catch(Exception ex) {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// Load countries method

	private ArrayList<Pair<String, Double>> loadCountries() {

		ArrayList<Pair<String,Double>> loadedCountries;

		try {

			loadedCountries = new ArrayList<>();

			br = new BufferedReader(new FileReader(new File(GENERATION_DATA_PATH + "countries_population.csv")));

			double acumulatedSum = 0;

			String line = br.readLine();

			String[] splitLine = null;

			while(line != null && !line.isEmpty()) {

				splitLine = line.split(",");

				String country = splitLine[0];

				double population = Double.parseDouble(splitLine[1]);

				acumulatedSum += population;

				loadedCountries.add(new Pair<String,Double>(country, Double.valueOf(population)));

				countries.add(country);

				line = br.readLine();				

			}

			double probability = 0.0;

			for (Pair<String,Double> pair: loadedCountries) {

				probability = pair.getValue().doubleValue()/acumulatedSum;

				pair.setValue(Double.valueOf(probability));

			}

			br.close();

			return loadedCountries;

		} catch(Exception ex) {

			return null;

		}

	}

	//------------------------------------------------------------------------------------

	// Update method

	public void update(String n, String sn, Sex s, LocalDate bd, Double height, String nat) {

		if(n != null || sn != null) {

			String prevName = currentPerson.getName();

			String prevSurname = currentPerson.getSurname();

			String prevFullName = prevName + " " + prevSurname;

			fullNamesTree.remove(prevFullName, currentPerson);

			fullNamesTrie.remove(prevFullName);

			if(n != null) { 		

				namesTree.remove(prevName, currentPerson);

				namesTrie.remove(prevName);

				currentPerson.setName(n);

				namesTree.add(n, currentPerson);

				namesTrie.add(n);

			}

			if(sn != null) {

				surnamesTree.remove(prevSurname, currentPerson);

				surnamesTrie.remove(prevSurname);

				currentPerson.setSurname(sn);

				surnamesTree.add(sn, currentPerson);

				surnamesTrie.add(sn);		
			}

			String newFullName = currentPerson.getName() + " " + currentPerson.getSurname();

			fullNamesTree.add(newFullName, currentPerson);

			fullNamesTrie.add(newFullName);		
			
			System.out.println("Tree weight: " + namesTree.getWeight());
			System.out.println("Trie stored words: " + ((Trie)namesTrie).getStoredWordsCount());

		}

		if(s!=null) currentPerson.setSex(s);

		if(bd != null) currentPerson.setBirthday(bd);

		if(height != null) currentPerson.setHeight(height);

		if(nat != null) currentPerson.setNationality(nat);

	}

	//------------------------------------------------------------------------------------

	//Serialize data

	public void write() throws FileNotFoundException, IOException {
		
		new File(PEOPLE_DATA_PATH + "people.dat").createNewFile();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PEOPLE_DATA_PATH + "people.dat"));

		oos.writeObject(idsHashTable.getAll());
		
		oos.close();
		
		
	}

	//------------------------------------------------------------------------------------

	//removes current person

	public boolean delete() {

		if(currentPerson != null) {

			String name = currentPerson.getName();

			String surname = currentPerson.getSurname();

			String fullName = name + " " + surname;

			long id = currentPerson.getId();

			namesTree.remove(name, currentPerson);

			surnamesTree.remove(surname, currentPerson);

			fullNamesTree.remove(fullName, currentPerson);

			idsHashTable.delete(id);

			namesTrie.remove(name);

			surnamesTrie.remove(surname);

			fullNamesTrie.remove(fullName);

			clearCurrentPerson();

			return true;

		} else {

			return false;

		}

	}

	//------------------------------------------------------------------------------------

	// Search method

	public ArrayList<Person> search(SearchCriteria criteria, String data) {

		ArrayList<Person> persons = null;

		switch(criteria) {

		case NAME:

			persons = (ArrayList<Person>) namesTree.search(data);
			break;		

		case SURNAME:

			persons = (ArrayList<Person>) surnamesTree.search(data);
			break;

		case FULL_NAME:

			persons = (ArrayList<Person>) fullNamesTree.search(data);
			break;

		case ID:
			persons = new ArrayList<Person>();
			Person p = idsHashTable.search(Long.parseLong(data));
			
			if(p!= null) {
				persons.add(p);
			}
			
			break;

		}

		return persons;		
	}

	//------------------------------------------------------------------------------------

	// Get predictions method

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

	//------------------------------------------------------------------------------------

	// Clear current person method

	public void clearCurrentPerson() {
		currentPerson = null;
	}

	//------------------------------------------------------------------------------------

	// Generate people method	
	
	public boolean generatePeople(int n) {

		if(MAX_PEOPLE_NUMBER - savedPeopleNumber - n >= 0) {

			int currentCountry = 0;
			
			long t1 = System.currentTimeMillis();
		
			int countryAmount = 0;
			String countryName = "";
			
			for (int i = 0; i < n; i++) {
				
				if(currentCountry == loadedCountries.size()) {
					countryAmount = n - i;
					countryName = loadedCountries.get(currentCountry-1).getKey();
				}
				else {
					countryAmount = (int) Math.round(loadedCountries.get(currentCountry).getValue()*n);
					countryName = loadedCountries.get(currentCountry).getKey();
				}			
				
				int j;
				
				for (j = 0; j < countryAmount; j++) {
					
					Person newPerson = generatePerson(countryName);

					savePerson(newPerson);
					
					System.out.println(i+j);					
					
				}

				currentCountry++;
				
				i += j - 1;		
			}

			savedPeopleNumber += n;

			long t2 = System.currentTimeMillis();

			generationTime = (t2-t1)/1000;

			return true;

		} else {

			return false;

		}

	}

	//------------------------------------------------------------------------------------

	// Generate person method

	private Person generatePerson(String countryName) {

		String name = name();

		String surname = surname();

		long id = id();

		Sex sex = sex();

		LocalDate birthday = birthday();

		double height = height();

		String nationality = countryName;	

		return new Person(name, surname, id, sex, birthday, height, nationality);

	}	
	
	//------------------------------------------------------------------------------------
	
	// GET CURRENT PERSON

	public Person getCurrentPerson() {
		return currentPerson;
	}

	//------------------------------------------------------------------------------------

	//------------------------------------------------------------------------------------
	
	//Operations of the RandomFieldsGenerator class
	
	public String name() {
		return loadedNames.get((int)(sr.nextDouble()*loadedNames.size()));
	}
	
	
	public String surname() {
		return loadedSurnames.get((int)(sr.nextDouble()*loadedSurnames.size()));
	}
	
	//------------------------------------------------------------------------------------
	
	// Local date method

	public LocalDate birthday() {
		
		double random = sr.nextDouble();

		int minAge = - 1;
		
		int maxAge = 0;
		
		boolean check = false;

		//
		for (int i = 0; i < loadedAges.size() && !check; i++) {
			
			minAge = maxAge;
			
			maxAge = loadedAges.get(i).getKey();
			
			if(random < loadedAges.get(i).getValue()) {
				
				check = true;
				
			}
			
		}

		int age = (int) Math.round(sr.nextDouble()*(maxAge - minAge + 1) + minAge);

		LocalDate now = LocalDate.now();

		LocalDate birthday = now.minusYears(age);

		if(birthday.isLeapYear()) {
			
			birthday.minusDays((long)random);
			
		} else {
			
			birthday.minusDays((long)random);
			
		}

		return birthday;
	}
	
	//------------------------------------------------------------------------------------
	
	// Id method

	public long id() {
		
		return System.currentTimeMillis();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Gender method

	public Sex sex() {
		
		return(sr.nextDouble() < 0.5)? Sex.MALE:Sex.FEMALE;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Height method

	public double height() {
		
		return (sr.nextDouble()*(MAX_HEIGHT-MIN_HEIGHT+1) + MIN_HEIGHT);
		
	}
	
	//------------------------------------------------------------------------------------
}
