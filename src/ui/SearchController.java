package ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import model.DataBaseManager;
import model.Person;
import model.SearchCriteria;
import model.Sex;

public class SearchController {
	
    //------------------------------------------------------------------------------------
    
  	//RELATIONS
	
	private PrincipalController principalController;
	
	private DataBaseManager dbm;
	
	private SearchCriteria searchCriteria;
	
    //------------------------------------------------------------------------------------
    
  	//WINDOW ELEMENTS	

    @FXML
    private RadioButton fnSearch;

    @FXML
    private ToggleGroup optionSearch;

    @FXML
    private RadioButton nSearch;

    @FXML
    private RadioButton sSearch;

    @FXML
    private RadioButton cSearch;
    
    @FXML
    private TextField searchTextField;

    @FXML
    private ListView<String> predictionsListViewer;
    
    @FXML
    private ComboBox<String> searchComboBox;
    
	
    //------------------------------------------------------------------------------------
    
  	//CONSTRUCTOR METHOD
    
	public SearchController(PrincipalController principalController, DataBaseManager dbm) {
		
		this.principalController = principalController;
		
		this.dbm = dbm;
		
		searchCriteria = SearchCriteria.NAME;
		
	}
	
	//------------------------------------------------------------------------------------
    
  	//UPDATE PREDICTIONS METHOD
	
    public void updatePredictions() {	    
    	
    	if(searchCriteria != SearchCriteria.ID) {
    		
    		String prefix = searchTextField.getText();
    		
        	ArrayList<String> predictions = dbm.getPredictions(prefix, searchCriteria);
        	
    		predictionsListViewer.getItems().clear();
    		
    		if(predictions != null) {
    			
    			predictionsListViewer.getItems().addAll(dbm.getPredictions(prefix, searchCriteria));
    		}   
    		
    	}   	
    }    
    
    
    //------------------------------------------------------------------------------------
    
  	//UPDATE SEARCH CRITERIA METHOD
    
    @FXML
    public void updateSearchCriteria(ActionEvent event) {
    	
    	if(nSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.NAME;
    		
    	}
    	
    	else if(sSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.SURNAME;
    		
    	}
    	
    	else if(fnSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.FULL_NAME;
    		
    	}
    	
    	else {
    	
    		searchCriteria = SearchCriteria.ID;
    	}
    }
    
    //------------------------------------------------------------------------------------
    
  	//RETRIEVE PERSON METHOD
    
    @FXML
    void retrievePerson(ActionEvent event) {
    	   	
    	ArrayList<Person> people = dbm.search(searchCriteria, searchTextField.getText());
    	
    	Person p = null;
    	
    	dbm.clearCurrentPerson();
    	
    	principalController.updateEdit(false);   	
    	
    	if(people.size() > 1) {
    		
    		ChoiceDialog<Person> dialog = new ChoiceDialog<Person>(people.get(0), people);
    		
    		dialog.setTitle("Multiple people found!");
    		
    		dialog.setContentText("Multiple people with the same criteria had been found!\n"
    				+ "Please Type the number of one of the following\n:"
    				+ "(By default the first one will be selected)\n");   
    		
    		Person result = dialog.showAndWait().get();
    		
    		if(result != null) {
    			
    			dbm.setCurrentPerson(result);
    			
    			//principalController.updateEdit(true);
    			
    			success();
    			
    		}
    		else {
    			
    			unexpectedError();
    			
    		}
    	    	
    	}
    	else if(people.size() == 1) {
    		
    		p = people.get(0);
    		
    		dbm.setCurrentPerson(p);
    		
    		principalController.updateEdit(true);
    		
    		success();
    		
    	}
    	else {
    		
    		notFound();
    		
    	}
    	
    }
 
    //------------------------------------------------------------------------------------
    
  	//INITIALIZE METHOD
    
	@FXML
	void initialize() {		
		searchTextField.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				updatePredictions();
			}
		
		});
		
		predictionsListViewer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				searchTextField.setText(newValue);
			}
		
		});
	}
	
	//------------------------------------------------------------------------------------
    
  	//ALERTS METHODS
	
	public void notFound() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Person not found");
		alert.setContentText("The person with the given data was not found");
		alert.showAndWait();
	}
			
	public void unexpectedError() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Unexpected Error");
		alert.setContentText("Please contact the software engineer!");
		alert.showAndWait();
	}
	
	public void success() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Person selected succesfully");
		alert.setContentText("The person can now be edited in the edit tab");
		alert.showAndWait();
	}
		
	public void notImplemented() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Function not yet implemented");
		alert.showAndWait();
	}
}
