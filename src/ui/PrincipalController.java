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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.DataBaseManager;

public class PrincipalController {

	//------------------------------------------------------------------------------------

	// RELATION'S WITH THE ANOTHER CLASS

	private EditController controller;
	
	private DataBaseManager dbm;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD

	public PrincipalController() {

		dbm = new DataBaseManager();
		controller = new EditController(dbm);

	}

	// *****************************************

	public PrincipalController(String name) {


	}

	//------------------------------------------------------------------------------------

	// TAB'S IN THE JAVA'FX VIEW

	@FXML
	private Tab addTab;

	@FXML
	private Tab searchTab;

	@FXML
	private Tab editTab;

	@FXML
	private Tab generateTab;

	//------------------------------------------------------------------------------------

	// WINDOW 1 (ADD PERSON)

	@FXML
	private TextField nameTextAdd;

	@FXML
	private TextField lastNameTextAdd;

	@FXML
	private ToggleGroup gender;

	@FXML
	private RadioButton manRBAdd;

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

	// WINDOW 2

	@FXML
	private ToggleGroup optionSearch;

	@FXML
	private TextField nameTextSearch;

	@FXML
	private RadioButton cnSearch;

	@FXML
	private RadioButton nSearch;

	@FXML
	private RadioButton lnSearch;

	@FXML
	private RadioButton csearch;

	//------------------------------------------------------------------------------------

	// WINDOW 3

	@FXML
	private Label showLabelNameEdit;

	@FXML
	private Button editButton;

	@FXML
	private Button removeButton;

	//------------------------------------------------------------------------------------

	// WINDOW 4

	@FXML
	private TextField numberGenerateText;

	@FXML
	private Button generateButton;

	@FXML
	private Rectangle box;

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO ADD A NEW PERSON IN THE SYSTEM

	@FXML
	public void add(ActionEvent event) {



	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO EDIT A PERSON IN THE SYSTEM

	@FXML
	public void edit(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();

		Stage stage = (Stage) source.getScene().getWindow();

		stage.close();

		Stage primaryStage = new Stage();

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditPanel.fxml"));

		fxmlLoader.setController(controller);

		Parent root = fxmlLoader.load();

		Scene scene = new Scene(root);

		primaryStage.setScene(scene);

		primaryStage.setTitle("Edit a person");

		primaryStage.show();

	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO REMOVE A PERSONA FROM THE SYSTEM

	@FXML
	public void remove(ActionEvent event) {



	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO GENERATE N PEOPLE IN THE SYSTEM

	@FXML
	public void generate(ActionEvent event) {



	}

	//------------------------------------------------------------------------------------
	
	// INITIALIZE METHOD
	
	public void initialize() {
		
		// A
		
		nacionalityTextAdd.getItems().add("Anguila");
		nacionalityTextAdd.getItems().add("Antigua y Barbuda");
		nacionalityTextAdd.getItems().add("Argentina");
		nacionalityTextAdd.getItems().add("Aruba");
		
		// B
		
		nacionalityTextAdd.getItems().add("Bahamas");
		nacionalityTextAdd.getItems().add("Barbados");
		nacionalityTextAdd.getItems().add("Belice");
		nacionalityTextAdd.getItems().add("Bermudas");
		nacionalityTextAdd.getItems().add("Bolivia");
		nacionalityTextAdd.getItems().add("Brasil");
		
		// C
		
		nacionalityTextAdd.getItems().add("Canada");
		nacionalityTextAdd.getItems().add("Chile");
		nacionalityTextAdd.getItems().add("Colombia");
		nacionalityTextAdd.getItems().add("Costa Rica");
		nacionalityTextAdd.getItems().add("Cuba");

		// D
		
		nacionalityTextAdd.getItems().add("Dominica");
		
		// E
		
		nacionalityTextAdd.getItems().add("Ecuador");
		nacionalityTextAdd.getItems().add("El Salvador");
		nacionalityTextAdd.getItems().add("Estados Unidos");
		
		// G

		nacionalityTextAdd.getItems().add("Granada");
		nacionalityTextAdd.getItems().add("Groelandia");
		nacionalityTextAdd.getItems().add("Guam");
		nacionalityTextAdd.getItems().add("Guatemala");
		nacionalityTextAdd.getItems().add("Guyana");
		
		// H
		
		nacionalityTextAdd.getItems().add("Haiti");
		nacionalityTextAdd.getItems().add("Honduras");
		
		// I
		
		nacionalityTextAdd.getItems().add("Islas Caiman");
		nacionalityTextAdd.getItems().add("Islas Turcas y Caicos");
		nacionalityTextAdd.getItems().add("Islas Virgenes Estadounidenses");
		nacionalityTextAdd.getItems().add("Islas Virgenes Britanicas");
		
		// J
		
		nacionalityTextAdd.getItems().add("Jamaica");
		
		// M
		
		nacionalityTextAdd.getItems().add("Malvinas");
		nacionalityTextAdd.getItems().add("Mexico");
		nacionalityTextAdd.getItems().add("Montserrat");
		
		// N
		
		nacionalityTextAdd.getItems().add("Nicaragua");
		
		// P
		
		nacionalityTextAdd.getItems().add("Panama");
		nacionalityTextAdd.getItems().add("Paraguay");
		nacionalityTextAdd.getItems().add("Peru");
		nacionalityTextAdd.getItems().add("Puerto rico");
		
		// R
		
		nacionalityTextAdd.getItems().add("Republica Dominicana");
		
		// S
		
		nacionalityTextAdd.getItems().add("San Cristobal y Nieves");
		nacionalityTextAdd.getItems().add("San Vicente y Granadines");
		nacionalityTextAdd.getItems().add("Santa Lucia");
		nacionalityTextAdd.getItems().add("Surinam");
		
		// T
		
		nacionalityTextAdd.getItems().add("Trinidad y Tobago");
		
		// U
		
		nacionalityTextAdd.getItems().add("Uruguay");
		
		// V
		
		nacionalityTextAdd.getItems().add("Venezuela");
		
	}
	
	//------------------------------------------------------------------------------------

}
