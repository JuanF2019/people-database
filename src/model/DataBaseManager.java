/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import collections.*;
import java.time.LocalDate;

public class DataBaseManager {

	//------------------------------------------------------------------------------------

	//Constants of the DataBaseManager class

	public static int MAX_PEOPLE_NUMBER = Integer.MAX_VALUE;

	//------------------------------------------------------------------------------------

	//Attributes of the DataBaseManager class

	private int savedPeopleNumber;

	//------------------------------------------------------------------------------------

	//Relations of the DataBaseManager class

	private BinarySearchTreeInterface<?,?> surnamesTree;

	private TrieInterface namesTrie;

	private TrieInterface surnamesTrie;

	private TrieInterface fullNamesTrie;

	private BinarySearchTreeInterface<?,?> fullNamesTree;

	private HashTableInterface<?,?> idsHashTable;

	private Person currentPerson;

	//------------------------------------------------------------------------------------

	// Constructor method of the DataBaseManager class

	public DataBaseManager() {
		surnamesTree = new AVLTree<>();
		namesTrie = new Trie();
		surnamesTrie = new Trie();
		fullNamesTrie = new Trie();
		idsHashTable = new HashTable<>();
		fullNamesTree = new RedBlackTree<>();
	}

	//------------------------------------------------------------------------------------

	// Get's methods of the DataBaseManager class

	public int getSavedPeopleNumber() {
		return savedPeopleNumber;
	}

	//------------------------------------------------------------------------------------

	// Set's methods of the DataBaseManager class

	public void setSavedPeopleNumber(int savedPeopleNumber) {
		this.savedPeopleNumber = savedPeopleNumber;
	}

	//------------------------------------------------------------------------------------

	//Operations of class DataBaseManager

	public boolean create() {}

	// *****************************************************

	public boolean read() {}

	// *****************************************************

	public boolean update() {}

	// *****************************************************

	public boolean delete() {}

	// *****************************************************

	public Person search(SearchCriteria criteria, String data) {
		Person aux = null;

		switch(criteria) {

		case NAME:
			aux = searchByName(data);
			break;		

		case SURNAME:
			aux = searchByLastName(data);
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

	private Person searchByName(String name) {}

	// *****************************************************

	private Person searchByLastName(String ln) {}

	// *****************************************************

	private Person searchByFullName(String fn) {}

	// *****************************************************

	private Person searchById(int id) {}

	// *****************************************************

	public String[] getNextPredictions(char newCharacter, SearchCriteria criteria) {}

	// *****************************************************

	public String[] getPrevPredictions(SearchCriteria criteria) {}

	// *****************************************************

	public void clearCurrentPerson() {}

	// *****************************************************

	public boolean generatePeople(int n) {}

	// *****************************************************

	private boolean generatePerson() {}

	//------------------------------------------------------------------------------------

}
