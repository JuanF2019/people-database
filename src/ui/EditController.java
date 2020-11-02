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
    private ChoiceBox<String> editNationalityTextField;

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
		notImplemented();
    }

	//------------------------------------------------------------------------------------

	//RESET FIELDS METHOD
    @FXML
    void resetFields(ActionEvent event) {
    	notImplemented();
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
					
		String nat = editNationalityTextField.getValue();
					
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
		alert.setHeaderText("Person added succesfully");
		alert.setContentText("The person was added succesfully");
		alert.showAndWait();
	}
	
	public void notImplemented() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Function not yet implemented");
		alert.showAndWait();
	}

    //------------------------------------------------------------------------------------
    
  	//VALIDATE METHOD
    
    @FXML
    void validate(ActionEvent event) {
		if(nameCheckBox.isSelected()) {
			
			editNameTextField.setDisable(false);
			
			nameCheckBox.setDisable(true);
			
		}
		
		if(surnameCheckBox.isSelected()) {
			
			editSurnameTextField.setDisable(false);
			
			surnameCheckBox.setDisable(true);
			
		}

		if(sexCheckBox.isSelected()) {
			
			editMaleRButton.setDisable(false);
			
			editFemaleRButton.setDisable(false);
			
			sexCheckBox.setDisable(true);
			
		}
		
		if(birthdayCheckBox.isSelected()) {
			
			editBirthdayDatePicker.setDisable(false);
			
			birthdayCheckBox.setDisable(true);
			
		}
		
		if(heightCheckBox.isSelected()) {
			
			editHeightTextField.setDisable(false);
			
			heightCheckBox.setDisable(true);
			
		}

		if(nationalityCheckBox.isSelected()) {
			
			editNameTextField.setDisable(false);
			
			nationalityCheckBox.setDisable(true);
			
		}    	
    }
    
    //------------------------------------------------------------------------------------
	
  	// INITIALIZE METHOD
  	
  	public void initialize() {
  		
  		editNationalityTextField.getItems().addAll(dbm.getCountries());
  		
  	}
}
