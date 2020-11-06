package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TestDataBaseManager {

	private DataBaseManager dbm;
	
	private RandomFieldsGenerator rfg;
	
	void setUp1() {
		dbm = new DataBaseManager();
		dbm.readGenerationData();
		rfg = dbm.getRandomFieldsGenerator();
	}
	
	
	@Test
	void test1() {
		setUp1();
		System.out.println(rfg.name());
		System.out.println(rfg.surname());
		System.out.println(rfg.sex());
		System.out.println(rfg.nationality());
		System.out.println(rfg.birthday());
		System.out.println(rfg.height());
		System.out.println(rfg.id());
	}
	
}
