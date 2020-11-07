/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

import model.DataBaseManager;
import model.Sex;

public class AddController {
	
	//------------------------------------------------------------------------------------
	
	// RELATION WITH THE CLASS DATABASE MANAGER
	
	private DataBaseManager dbm;
	
	private PrincipalController pc;
	
	//------------------------------------------------------------------------------------
	
	// THINGS TO USE IN JAVA'FX
	
	@FXML
	private TextField nameTextAdd;

	@FXML
	private TextField lastNameTextAdd;

	@FXML
	private RadioButton manRBAdd;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton womanRBAdd;

	@FXML
	private DatePicker dateTextAdd;

	@FXML
	private TextField heightTextAdd;

	@FXML
	private Button addButton;
	 
	@FXML
	private ChoiceBox<String> nacionalityTextAdd;
	
	//------------------------------------------------------------------------------------
	
	// CONSTRUCTOR METHOD
	 
	public AddController(PrincipalController pc, DataBaseManager dbm) {
		
		this.dbm = dbm;
		
		this.pc = pc;
	}
	
	//------------------------------------------------------------------------------------
	
	// ADD METHOD
	 
	@FXML
	void add(ActionEvent event) {
		
		long t1 = System.currentTimeMillis();
		
		String name = nameTextAdd.getText();		
		
		String trimName = nameTextAdd.getText().trim();
			
		String surname = lastNameTextAdd.getText();		
		
		String trimSurname = lastNameTextAdd.getText().trim();
			
		Sex s = null;
		
		if(womanRBAdd.isSelected())
			s = Sex.FEMALE;
		
		else if(manRBAdd.isSelected())
			s = Sex.MALE;
			
		double height;
			
		try {
			
			height = Double.parseDouble(heightTextAdd.getText());
			
		} catch(NumberFormatException ex) {
			
			height = 0;
			
		}
					
		String nat = nacionalityTextAdd.getValue();
					
		LocalDate birthday = dateTextAdd.getValue();
			
		if(trimName.isEmpty() || trimSurname.isEmpty() || s == null || height <= 0 || nat == null || nat.isEmpty() || birthday == null || birthday.isAfter(LocalDate.now())) {
			
			generalWarning();
			
		} else {
			
			boolean created = dbm.create(name, surname, s, birthday, height, nat);
				
			if(created) {
				
				success();
				
			} else {
				
				notSuccess();
				
			}
			
		}
		
		long t2 = System.currentTimeMillis();
		
		pc.updateTime(t2-t1);
		
	}	
	
	//------------------------------------------------------------------------------------
	
	// GENERAL WARNING METHOD
	 
	public void generalWarning() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Empty field");
		alert.setContentText("Please be sure all the fields are fullfilled correctly.");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// SUCESS METHOD
		
	public void success() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Person added succesfully");
		alert.setContentText("The person was added succesfully");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// NOT SUCCESS METHOD
		
	public void notSuccess() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Could not add person");
		alert.setContentText("Reached maximum number of people");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// INITIALIZE METHOD
	
	public void initialize() {
		
		ArrayList<String> countries = dbm.getCountries();
		
		nacionalityTextAdd.getItems().addAll(countries);
		
	}
	
	//------------------------------------------------------------------------------------
	
}
