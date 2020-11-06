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
	
	private RandomFieldsGenerator rfg;
	
	//------------------------------------------------------------------------------------
	
	// Setup 1
	
	void setup1() {
		
		dbm = new DataBaseManager();
		
		dbm.readGenerationData();
		
		rfg = dbm.getRandomFieldsGenerator();
		
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
		
		for(int i = 0 ; i < Integer.MAX_VALUE ; i ++) {
			
			dbm.create("Alejandra", "Diaz", Sex.FEMALE, date, 175.0, "Colombia");
			
			System.out.println(i + 1);
			
		}
		
		assertFalse(dbm.create("Juan", "Martinez", Sex.MALE, date, 177.0, "Colombia"));
		
	}
	
	//------------------------------------------------------------------------------------
	
}
