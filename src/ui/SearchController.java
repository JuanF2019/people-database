package ui;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import model.DataBaseManager;

public class SearchController {
	
    //------------------------------------------------------------------------------------
    
  	//RELATIONS
	
	private PrincipalController principalController;
	
	private DataBaseManager dbm;
	
    //------------------------------------------------------------------------------------
    
  	//WINDOW ELEMENTS
	
	@FXML
    private TextField nameTextSearch;

    @FXML
    private RadioButton cnSearch;

    @FXML
    private ToggleGroup optionSearch;

    @FXML
    private RadioButton nSearch;

    @FXML
    private RadioButton lnSearch;

    @FXML
    private RadioButton csearch;
	
    //------------------------------------------------------------------------------------
    
  	//CONSTRUCTOR METHOD
    
	public SearchController(PrincipalController principalController, DataBaseManager dbm) {
		
		this.principalController = principalController;
		
		this.dbm = dbm;
		
	}

	
}
