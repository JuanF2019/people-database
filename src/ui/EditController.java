/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class EditController {
	
	//------------------------------------------------------------------------------------
	
	// RELATION'S WITH ANOTHER CLASS
	
	private PrincipalController controller;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD OF THE EDIT CONTROLLER

	public EditController() {

		controller = new PrincipalController("People database");

	}

	//------------------------------------------------------------------------------------

	// UP PART TO THE WINDOW

	@FXML
	private CheckBox nameCB;

	@FXML
	private CheckBox lastNameCB;

	@FXML
	private CheckBox genderCB;

	@FXML
	private CheckBox dateCB;

	@FXML
	private CheckBox heightCB;

	@FXML
	private CheckBox nacionalityCB;

	//------------------------------------------------------------------------------------

	// DOWN PART TO THE WINDOW
	
    @FXML
    private ToggleGroup gender;

	@FXML
	private TextField nameEditText;

	@FXML
	private TextField lastNameEditText;

	@FXML
	private RadioButton manRBEdit;

	@FXML
	private RadioButton womanRBEdit;

	@FXML
	private DatePicker dateEditText;

	@FXML
	private TextField heighEditText;

	@FXML
	private ChoiceBox<String> nacionalityEditText;

	//------------------------------------------------------------------------------------

	// BUTTON'S OF THE WINDOW

	@FXML
	private Button comeBackButton;

	@FXML
	private Button updateButton;
	
	@FXML
	private Button validateButton;

	//------------------------------------------------------------------------------------

	// COME BACK METHOD TO THE WINDOW

	@FXML
	public void comeBack(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();

		Stage stage = (Stage) source.getScene().getWindow();

		stage.close();

		Stage primaryStage = new Stage();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PrincipalPanel.fxml"));

		fxmlLoader.setController(controller);

		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);

		primaryStage.setTitle("People database");

		primaryStage.show();

	}

	//------------------------------------------------------------------------------------

	// UPDATE METHOD TO THE WINDOW

	@FXML
	public void update(ActionEvent event) {



	}

	//------------------------------------------------------------------------------------
	
	@FXML
	public void validate(ActionEvent event) {
		
		if(nameCB.isSelected()) {
			
			nameEditText.setDisable(false);
			
			nameCB.setDisable(true);
			
		}
		
		if(lastNameCB.isSelected()) {
			
			lastNameEditText.setDisable(false);
			
			lastNameCB.setDisable(true);
			
		}

		if(genderCB.isSelected()) {
			
			manRBEdit.setDisable(false);
			
			womanRBEdit.setDisable(false);
			
			genderCB.setDisable(true);
			
		}
		
		if(dateCB.isSelected()) {
			
			dateEditText.setDisable(false);
			
			dateCB.setDisable(true);
			
		}
		
		if(heightCB.isSelected()) {
			
			heighEditText.setDisable(false);
			
			heightCB.setDisable(true);
			
		}

		if(nacionalityCB.isSelected()) {
			
			nacionalityEditText.setDisable(false);
			
			nacionalityCB.setDisable(true);
			
		}
		

	}
	
	//------------------------------------------------------------------------------------
	
	// INITIALIZE METHOD
	
	public void initialize() {
		
		// A
		
		nacionalityEditText.getItems().add("Anguila");
		nacionalityEditText.getItems().add("Antigua y Barbuda");
		nacionalityEditText.getItems().add("Argentina");
		nacionalityEditText.getItems().add("Aruba");
		
		// B
		
		nacionalityEditText.getItems().add("Bahamas");
		nacionalityEditText.getItems().add("Barbados");
		nacionalityEditText.getItems().add("Belice");
		nacionalityEditText.getItems().add("Bermudas");
		nacionalityEditText.getItems().add("Bolivia");
		nacionalityEditText.getItems().add("Brasil");
		
		// C
		
		nacionalityEditText.getItems().add("Canada");
		nacionalityEditText.getItems().add("Chile");
		nacionalityEditText.getItems().add("Colombia");
		nacionalityEditText.getItems().add("Costa Rica");
		nacionalityEditText.getItems().add("Cuba");

		// D
		
		nacionalityEditText.getItems().add("Dominica");
		
		// E
		
		nacionalityEditText.getItems().add("Ecuador");
		nacionalityEditText.getItems().add("El Salvador");
		nacionalityEditText.getItems().add("Estados Unidos");
		
		// G

		nacionalityEditText.getItems().add("Granada");
		nacionalityEditText.getItems().add("Groelandia");
		nacionalityEditText.getItems().add("Guam");
		nacionalityEditText.getItems().add("Guatemala");
		nacionalityEditText.getItems().add("Guyana");
		
		// H
		
		nacionalityEditText.getItems().add("Haiti");
		nacionalityEditText.getItems().add("Honduras");
		
		// I
		
		nacionalityEditText.getItems().add("Islas Caiman");
		nacionalityEditText.getItems().add("Islas Turcas y Caicos");
		nacionalityEditText.getItems().add("Islas Virgenes Estadounidenses");
		nacionalityEditText.getItems().add("Islas Virgenes Britanicas");
		
		// J
		
		nacionalityEditText.getItems().add("Jamaica");
		
		// M
		
		nacionalityEditText.getItems().add("Malvinas");
		nacionalityEditText.getItems().add("Mexico");
		nacionalityEditText.getItems().add("Montserrat");
		
		// N
		
		nacionalityEditText.getItems().add("Nicaragua");
		
		// P
		
		nacionalityEditText.getItems().add("Panama");
		nacionalityEditText.getItems().add("Paraguay");
		nacionalityEditText.getItems().add("Peru");
		nacionalityEditText.getItems().add("Puerto rico");
		
		// R
		
		nacionalityEditText.getItems().add("Republica Dominicana");
		
		// S
		
		nacionalityEditText.getItems().add("San Cristobal y Nieves");
		nacionalityEditText.getItems().add("San Vicente y Granadines");
		nacionalityEditText.getItems().add("Santa Lucia");
		nacionalityEditText.getItems().add("Surinam");
		
		// T
		
		nacionalityEditText.getItems().add("Trinidad y Tobago");
		
		// U
		
		nacionalityEditText.getItems().add("Uruguay");
		
		// V
		
		nacionalityEditText.getItems().add("Venezuela");
		
	}
	
	//------------------------------------------------------------------------------------

}
