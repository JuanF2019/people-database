/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class TestDataBaseManager {
	
	//------------------------------------------------------------------------------------
	
	// Relation's with another class

	private DataBaseManager dbm;
	
	
	//------------------------------------------------------------------------------------
	
	// Setup 1
	
	void setup1() {
		
		dbm = new DataBaseManager();
		
		dbm.readGenerationData();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Test 1
	
	@Test
	void testCreate1() {
		
		setup1();
		
		LocalDate date = LocalDate.now();
		
		assertTrue(dbm.create("Santiago", "Rodas", Sex.MALE, date, 180.0, "Colombia"));
		
	}
	
	//------------------------------------------------------------------------------------
	
	// NO EJECUTAR ESTA CLASE TEST HASTA QUE SE VERIFQUE LA EFICACIA DEL PROGRAMA
	
	@Test
	void testCreate2() {
		
		setup1();
		
		LocalDate date = LocalDate.now();
		
		dbm.generatePeople(100000000);
		
		/*
		for(int i = 0 ; i < Integer.MAX_VALUE ; i ++) {
			
			Person p = new Person("Alejandrra", "Diaz",i, Sex.FEMALE, date, 178.0, "Colombia");
			
			dbm.addPerson(p);
			
			System.out.println(i + 1);
			
		}*/
		
		assertFalse(dbm.create("Juan", "Martinez", Sex.MALE, date, 177.0, "Colombia"));
		
	}
	
	//------------------------------------------------------------------------------------
	
}
