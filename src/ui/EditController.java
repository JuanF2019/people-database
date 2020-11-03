/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import model.DataBaseManager;
import model.Person;
import model.Sex;

public class EditController {
	
	//------------------------------------------------------------------------------------

	// RELATIONS
	
	private PrincipalController principalController;
	
	private DataBaseManager dbm;	
	
	//------------------------------------------------------------------------------------

	// UP PART OF THE WINDOW
	
    @FXML
    private CheckBox nameCheckBox;

    @FXML
    private CheckBox surnameCheckBox;

    @FXML
    private CheckBox sexCheckBox;

    @FXML
    private CheckBox birthdayCheckBox;

    @FXML
    private CheckBox heightCheckBox;

    @FXML
    private CheckBox nationalityCheckBox;
    
    //------------------------------------------------------------------------------------

  	// BOTTOM LEFT PART OF THE WINDOW

    @FXML
    private TextField editNameTextField;

    @FXML
    private TextField editHeightTextField;

    @FXML
    private TextField editSurnameTextField;

    @FXML
    private RadioButton editMaleRButton;

    @FXML
    private ToggleGroup editSexToogleGroup;

    @FXML
    private RadioButton editFemaleRButton;

    @FXML
    private DatePicker editBirthdayDatePicker;

    @FXML
    private ChoiceBox<String> editNationalityChoiceBox;

    //------------------------------------------------------------------------------------

  	// BOTTOM RIGHT PART OF THE WINDOW
    
    @FXML
    private Button saveButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button resetButton;

    @FXML
    private ImageView image;
    
    //------------------------------------------------------------------------------------

  	// CONSTRUCTOR METHOD

    public EditController(PrincipalController principalController, DataBaseManager dbm) {
    	
    	this.principalController = principalController;
		
		this.dbm = dbm;
		
	}

    //------------------------------------------------------------------------------------

  	//DELETE CURRENT PERSON METHOD
	@FXML
    void deleteCurrentPerson(ActionEvent event) {
		if(dbm.delete()) {
			
			successDelete();
			
			principalController.updateEdit(false);
			
		}
		else {
			
			unexpectedError();
			
		}		
    }

	//------------------------------------------------------------------------------------

	//RESET FIELDS METHOD
    @FXML
    void resetFields(ActionEvent event) {
    	Person p = dbm.getCurrentPerson();
    	
    	if(p!=null) {
    		editNameTextField.setText(p.getName());
    		editSurnameTextField.setText(p.getSurname());
    		editHeightTextField.setText(Double.toString(p.getHeight()));
    		
    		if(p.getSex() == Sex.MALE) {
    			editMaleRButton.setSelected(true);
    		}
    		else{
    			editFemaleRButton.setSelected(true);
    		}
    		
    		editBirthdayDatePicker.setValue(p.getBirthday());
    		editNationalityChoiceBox.setValue(p.getNationality());
    	}
    	else {
    		unexpectedError();
    	}   	
    }

    //------------------------------------------------------------------------------------

  	//SAVE CHANGES METHOD
    
    @FXML
    void saveChanges(ActionEvent event) {
    	String name = editNameTextField.getText();		
		String trimName = editNameTextField.getText().trim();
			
		String surname = editSurnameTextField.getText();		
		String trimSurname = editSurnameTextField.getText().trim();
			
		Sex s = null;
		if(editMaleRButton.isSelected())
			s = Sex.FEMALE;
		
		else if(editFemaleRButton.isSelected())
			s = Sex.MALE;
			
		double height;
			
		try {
			height = Double.parseDouble(editHeightTextField.getText());
		}
		catch(NumberFormatException ex) {
			height = 0;
		}
					
		String nat = editNationalityChoiceBox.getValue();
					
		LocalDate birthday = editBirthdayDatePicker.getValue();
			
		if(trimName.isEmpty() || trimSurname.isEmpty() || s == null || height <= 0 || nat == null || nat.isEmpty() || birthday == null || birthday.isAfter(LocalDate.now())) {
			generalWarning();
		}
		else {
			boolean created = dbm.create(name, surname, s, birthday, height, nat);
				
			if(created) {
				success();
			}
		}
    }
    
    //------------------------------------------------------------------------------------
    
  	//ALERTS METHODS
    
    public void generalWarning() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Empty field");
		alert.setContentText("Please be sure all the fields are fullfilled correctly.");
		alert.showAndWait();
	}
		
	public void success() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Changes saved succesfully");
		alert.showAndWait();
	}
	
	public void successDelete() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Person removed succesfully");
		alert.showAndWait();
	}
	
	public void notImplemented() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Function not yet implemented");
		alert.showAndWait();
	}

	public void unexpectedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Unexpected Error");
		alert.setContentText("Please contact the software engineer!");
		alert.showAndWait();
	}
	
    //------------------------------------------------------------------------------------
    
  	//VALIDATE METHOD
    
    @FXML
    void validate(ActionEvent event) throws NullPointerException{
    	
    	Person p = dbm.getCurrentPerson();       	
    	
    	//NAME
    	
		if(nameCheckBox.isSelected()) {
			
			editNameTextField.setDisable(false);
			
		}
		else {
			
			editNameTextField.setDisable(true);
			
			editNameTextField.setText(p.getName());
			
		}
		
		//SURNAME
		
		if(surnameCheckBox.isSelected()) {
			
			editSurnameTextField.setDisable(false);
			
		}
		else {
			
			editSurnameTextField.setDisable(true);
			
			editSurnameTextField.setText(p.getSurname());
			
		}
		
		//SEX

		if(sexCheckBox.isSelected()) {
			
			editMaleRButton.setDisable(false);
			
			editFemaleRButton.setDisable(false);
			
		}
		else {
			editMaleRButton.setDisable(true);
			
			editFemaleRButton.setDisable(true);
			
			if(p.getSex() == Sex.MALE) {
				
				editMaleRButton.setSelected(true);
				
			}
			else{
				
				editFemaleRButton.setSelected(true);
			}
		}
		
		//BIRTHDAY
		
		if(birthdayCheckBox.isSelected()) {
			
			editBirthdayDatePicker.setDisable(false);
			
		}
		else {
			
			editBirthdayDatePicker.setDisable(true);
			
			editBirthdayDatePicker.setValue(p.getBirthday());
			
		}
		
		//HEIGHT
		
		if(heightCheckBox.isSelected()) {
			
			editHeightTextField.setDisable(false);
			
		}		
		else {
			
			editHeightTextField.setDisable(true);
			
			editHeightTextField.setText(Double.toString(p.getHeight()));
			
		}

		//NATIONALITY
		
		if(nationalityCheckBox.isSelected()) {
			
			editNationalityChoiceBox.setDisable(false);
			
		}    
		else {
			
			editNationalityChoiceBox.setDisable(true);
			
			editNationalityChoiceBox.setValue(p.getNationality());
			
		}
    }
    
    //------------------------------------------------------------------------------------
	
  	// INITIALIZE METHOD
  	
  	public void initialize() {
  		
  		editNationalityChoiceBox.getItems().addAll(dbm.getCountries());
  		
  	}

  	//------------------------------------------------------------------------------------
	
  	// SET VISIBLE METHOD, UPDATES VISIBILITY OF COMPONENTS, IT ONLY GETS VISIBLE WHEN THERE IS A CURRENT PERSON
  	
	public void setVisible(boolean visible) {
		
		if(visible) {
			
			resetFields(new ActionEvent());
			
		}
		else {
			
			clearAndDisableFields();
			
		}
		
		resetValidationCheckBoxes();
		
	}
    //------------------------------------------------------------------------------------
	
  	// RESET VALIDATION CHECK BOXES METHOD
	
	private void resetValidationCheckBoxes() {
		
		nameCheckBox.setSelected(false);
		
		surnameCheckBox.setSelected(false);
		
		sexCheckBox.setSelected(false);
		
		heightCheckBox.setSelected(false);
		
		birthdayCheckBox.setSelected(false);
		
		nationalityCheckBox.setSelected(false);
		
	}
    //------------------------------------------------------------------------------------
	
  	// CLEAR AND DISABLE FIELDS METHOD
	
	private void clearAndDisableFields() {
		
		editNameTextField.setText("");
		editNameTextField.setDisable(true);
		
		editSurnameTextField.setText("");
		editSurnameTextField.setDisable(true);
		
		editHeightTextField.setText("");
		editHeightTextField.setDisable(true);		
		
		editNationalityChoiceBox.setValue("");
		editNationalityChoiceBox.setDisable(true);
		
		editBirthdayDatePicker.setValue(null);
		editBirthdayDatePicker.setDisable(true);
		
		editMaleRButton.setSelected(false);
		editMaleRButton.setDisable(true);
		
		editFemaleRButton.setSelected(false);
		editFemaleRButton.setDisable(true);
		
	}
	
}
