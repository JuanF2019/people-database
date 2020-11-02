/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable{
	
	//------------------------------------------------------------------------------------

	// Attributes of the Person class

	//Auto generated
	private static final long serialVersionUID = 8315833007962346858L;

	private String name;

	private String surname;

	private int id;

	private Sex sex;

	private LocalDate birthday;

	private double height;

	private String nationality;

	//------------------------------------------------------------------------------------

	// Constructor method of the Person class

	public Person(String name, String surname, int id, 
			Sex sex, LocalDate birthday, double height, String nationality) {
		
		this.name = name;
		
		this.surname = surname;
		
		this.id = id;
		
		this.sex = sex;
		
		this.birthday = birthday;
		
		this.height = height;
		
		this.nationality = nationality;
		
	}

	//------------------------------------------------------------------------------------

	// Get's methods of the Person class

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public int getId() {
		return id;
	}

	public Sex getSex() {
		return sex;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public double getHeight() {
		return height;
	}

	public String getNationality() {
		return nationality;
	}

	//------------------------------------------------------------------------------------

	// Set's methods of the Person class

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	//------------------------------------------------------------------------------------

	//Operations of class Person

	//Clones the person

	public Person clone() {
		return new Person(name,surname,id,sex,birthday,height,nationality);
	}

	//------------------------------------------------------------------------------------
	
	public boolean equals(Person anotherPerson) {
			
		return name.equals(anotherPerson.name)&&
				surname.equals(anotherPerson.name) && 
				id == anotherPerson.id &&
				sex == anotherPerson.sex &&
				birthday.equals(anotherPerson.birthday) &&
				height == anotherPerson.height &&
				nationality.equals(anotherPerson.nationality);
	}

	//------------------------------------------------------------------------------------
}
