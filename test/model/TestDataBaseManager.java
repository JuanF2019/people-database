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
		
		assertTrue(dbm.create("Alejandra", "Diaz", Sex.FEMALE, date, 178.0, "Colombia"));
		
		assertTrue(dbm.create("Juan", "Martinez", Sex.MALE, date, 177.0, "Colombia"));
		
		assertTrue(dbm.create("Susana", "Yugueros", Sex.FEMALE, date, 165.0, "Colombia"));
		
		assertTrue(dbm.create("David", "Rodriguez", Sex.MALE, date, 170.0, "Colombia"));
		
		assertTrue(dbm.create("Erika", "Osorio", Sex.FEMALE, date, 172.0, "Colombia"));
		
	}
	
	//------------------------------------------------------------------------------------
	
	@Test
	void testRead() {
		
		setup1();
		
		testCreate2();
		
		assertTrue(dbm.read());
		
	}
	
	//------------------------------------------------------------------------------------
	
}
