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

public class DataBaseManager {

	//------------------------------------------------------------------------------------

	//Constants of the DataBaseManager class

	public static final int MAX_PEOPLE_NUMBER = Integer.MAX_VALUE;
	
	public static final String PICTURE_URL = "https://thispersondoesnotexist.com/";

	public static final String GENERATION_DATA_PATH = "data/generation_data/";
	
	public static final String PEOPLE_DATA_PATH = "data/people_data/people.dat";
	
	//------------------------------------------------------------------------------------

	//Attributes of the DataBaseManager class

	private int savedPeopleNumber;
	
	private BufferedReader br;
	
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
		}
		else {
			Person p = new Person(name, ln, randomFieldsGenerator.id(), s, ld, height, nat);
			
			savePerson(p);
			
			savedPeopleNumber++;
			
			return true;
		}		
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
		
	}

	//------------------------------------------------------------------------------------
	
	// Read method

	public boolean read() {
		
		return readPeopleData() && readGenerationData();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Read people database
	
	public boolean readPeopleData() {	
		
		try {
			
			File file = new File(PEOPLE_DATA_PATH);
			
			if(file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				
				ObjectInputStream ois = new ObjectInputStream(fis);	
							
				
				Object[] rawPeople = (Object[]) ois.readObject();
				
				for (Object object : rawPeople) {
					
					Person person = (Person) object;
					
					String name = person.getName();
					
					String surname =person.getSurname();
					
					String fullName = name + " " + surname;
					
					int id = person.getId();
					
					namesTree.add(name, person);
					
					surnamesTree.add(surname, person);
					
					fullNamesTree.add(fullName, person);
					
					idsHashTable.insert(id, person);
					
					namesTrie.add(name);
					
					surnamesTrie.add(surname);
					
					fullNamesTrie.add(fullName);
					
				}
				
				ois.close();
			}
			return true;
			
		}
		catch (Exception e) {
			return false;			
		}
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Read generation data
	
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
			
			double accumulatedProbability = 0.0;
			
			double probability = 0.0;
			
			for (Pair<String,Double> pair: loadedCountries) {
								
				probability = pair.getValue().doubleValue()/acumulatedSum;
				
				accumulatedProbability += probability;
									
				pair.setValue(Double.valueOf(accumulatedProbability));
				
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
			
		}
		
		if(s!=null) currentPerson.setSex(s);
		
		if(bd != null) currentPerson.setBirthday(bd);
		
		if(height != null) currentPerson.setHeight(height);
		
		if(nat != null) currentPerson.setNationality(nat);
		
	}
	
	//------------------------------------------------------------------------------------
	
	//Serialize data
	
	public void write() throws FileNotFoundException, IOException {
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PEOPLE_DATA_PATH));
		
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
			
			int id = currentPerson.getId();
			
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
			persons.add(idsHashTable.search(Integer.parseInt(data)));
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
			
		} else {
			
			return false;
			
		}
		
	}

	//------------------------------------------------------------------------------------
	
	// Generate person method

	private Person generatePerson() {
		
		String name = randomFieldsGenerator.name();
		
		String surname = randomFieldsGenerator.surname();
		
		int id = randomFieldsGenerator.id();
		
		Sex sex = randomFieldsGenerator.sex();
		
		LocalDate birthday = randomFieldsGenerator.birthday();
		
		double height = randomFieldsGenerator.height();
		
		String nationality = randomFieldsGenerator.nationality();	
		
		return new Person(name, surname, id, sex, birthday, height, nationality);
		
	}
	
	//------------------------------------------------------------------------------------

}
