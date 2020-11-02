package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DataBaseManager;

public class GenerateController {
	
    //------------------------------------------------------------------------------------
    
  	//RELATIONS
	
	private DataBaseManager dbm;
	
    //------------------------------------------------------------------------------------
    
  	//WINDOW ELEMENTS
	
	@FXML
	private TextField numberGenerateText;

	@FXML
	private Button generateButton;

    //------------------------------------------------------------------------------------
    
  	//CONSTRUCTOR METHOD
	
	public GenerateController(DataBaseManager dbm) {
		this.dbm = dbm;
	}

    //------------------------------------------------------------------------------------
    
  	//GENERATE METHOD
	
	@FXML
	void generate(ActionEvent event) {
		notImplemented();
	}
	
    //------------------------------------------------------------------------------------
    
  	//ALERTS METHODS
	
	public void notImplemented() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Function not yet implemented");
		alert.showAndWait();
	}
}
