/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceDialog;
import model.DataBaseManager;
import model.Person;
import model.SearchCriteria;


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
    
    @FXML
    private Label predictionCount;
    
	
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
    		
    		predictionsListViewer.getSelectionModel().clearSelection();
    		
    		if(predictions != null) {
    			
    			for (int i = 0; i < predictions.size() && i < 100; i++) {
    				
					predictionsListViewer.getItems().add(predictions.get(i));
					
				}
    			
    			
    		}   
    		
    		predictionCount.setText(Integer.toString(predictions.size()));
    		
    	}  
    	
    }    
    
    //------------------------------------------------------------------------------------
    
  	//UPDATE SEARCH CRITERIA METHOD
    
    @FXML
    public void updateSearchCriteria(ActionEvent event) {
    	
    	if(nSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.NAME;
    		
    	} else if(sSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.SURNAME;
    		
    	} else if(fnSearch.isSelected()) {
    	
    		searchCriteria = SearchCriteria.FULL_NAME;
    		
    	} else {
    	
    		searchCriteria = SearchCriteria.ID;
    		predictionsListViewer.setVisible(false);
    	}
    	
    	searchTextField.clear();
    	
    	predictionsListViewer.getItems().clear();
    	
    	predictionsListViewer.getSelectionModel().clearSelection();
    	
    }
    
    //------------------------------------------------------------------------------------
    
  	//RETRIEVE PERSON METHOD
    
    @FXML
    void retrievePerson(ActionEvent event) {
    	
    	long t1 = System.currentTimeMillis();
    	    	
	    ArrayList<Person> people = dbm.search(searchCriteria, searchTextField.getText());
	    
	    if(people != null) {
	    	
	    	Person p = null;
	    	
	    	dbm.clearCurrentPerson();
	    	
	    	principalController.updateEdit(false);   	
	    	
	    	if(people.size() > 1) {
	    		
	    		ChoiceDialog<Person> dialog = new ChoiceDialog<Person>(people.get(0), people);
	    		
	    		dialog.setTitle("Multiple people found!");
	    		
	    		dialog.setContentText("Multiple people with the same criteria had been found!\n"
	    				+ "Please select one of the following:\n"
	    				+ "(By default the first one will be selected)\n");   
	    		
	    		Person result = dialog.showAndWait().get();
	    		
	    		if(result != null) {
	    			
	    			dbm.setCurrentPerson(result);
	    			
	    			principalController.updateEdit(true);
	    			
	    			success();
	    			
	    		} else {
	    			
	    			unexpectedError();
	    			
	    		}
	    	    	
	    	} else if(people.size() == 1) {
	    		
	    		p = people.get(0);
	    		
	    		dbm.setCurrentPerson(p);
	    		
	    		principalController.updateEdit(true);
	    		
	    		success();
	    		
	    	} else {
	    		
	    		notFound();
	    		
	    	}
	    	
	    } else {
	    	
	    	notFound();
	    	
	    }
	    
    	long t2 = System.currentTimeMillis();
		
		principalController.updateTime(t2-t1);
    	
    }
 
    //------------------------------------------------------------------------------------
    
  	//INITIALIZE METHOD
    
	@FXML
	void initialize() {		
		
		searchTextField.textProperty().addListener(new ChangeListener<String>() {

			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				if(newValue == null || newValue.isEmpty() || searchCriteria == SearchCriteria.ID) {
					
					predictionsListViewer.setVisible(false);
					
					predictionCount.setText("0");
					
				} else {
						
					predictionsListViewer.setVisible(true);
					
					updatePredictions();
					
				}		
				
			}
		
		});
		
		predictionsListViewer.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
			
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				
				searchTextField.setText(newValue);							
				
				predictionsListViewer.getSelectionModel().select(0);
				
			}		
		
		});
		
	}
	
	//------------------------------------------------------------------------------------
    
  	// NOT FOUND METHOD
	
	public void notFound() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Person not found");
		alert.setContentText("The person with the given data was not found");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// UNEXPECTED ERROR METHOD
			
	public void unexpectedError() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Unexpected Error");
		alert.setContentText("Please contact the software engineer!");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// SUCCESS METHOD
	
	public void success() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Person selected succesfully");
		alert.setContentText("The person can now be edited in the edit tab");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// NOT IMPLEMENTED METHOD
		
	public void notImplemented() {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Function not yet implemented");
		alert.showAndWait();
		
	}
	
	//------------------------------------------------------------------------------------
	
}
