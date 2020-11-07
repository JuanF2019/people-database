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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import model.DataBaseManager;

public class PrincipalController {

	//------------------------------------------------------------------------------------

	// RELATION'S WITH THE ANOTHER CLASS

	private AddController addController;

	private SearchController searchController;

	private EditController editController;

	private GenerateController generateController;

	private DataBaseManager dbm;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD

	public PrincipalController(DataBaseManager dbm) {
		
		this.dbm = dbm;

		addController = new AddController(this, dbm);

		searchController = new SearchController(this,dbm);

		editController = new EditController(this,dbm);

		generateController = new GenerateController(dbm, this);

	}

	//------------------------------------------------------------------------------------

	@FXML
	private Tab addTab;

	@FXML
	private AnchorPane addAnchorPane;

	@FXML
	private Tab searchTab;

	@FXML
	private AnchorPane searchAnchorPane;

	@FXML
	private Tab editTab;

	@FXML
	private AnchorPane editAnchorPane;

	@FXML
	private Tab generateTab;

	@FXML
	private AnchorPane generateAnchorPane;	

	@FXML
	private Button saveInformationButton;
	
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private Label timeLabel;

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO LOAD THE EDIT TAB


	public void loadEditTab() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditPanel.fxml"));

		fxmlLoader.setController(editController);

		editAnchorPane.getChildren().add(fxmlLoader.load());

	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO LOAD THE ADD TAB

	public void loadAddTab() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddPanel.fxml"));

		fxmlLoader.setController(addController);

		addAnchorPane.getChildren().add(fxmlLoader.load());

	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO LOAD THE GENERATE TAB

	public void loadGenerateTab() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("GeneratePanel.fxml"));

		fxmlLoader.setController(generateController);

		generateAnchorPane.getChildren().add(fxmlLoader.load());

	}

	//------------------------------------------------------------------------------------

	// PUBLIC METHOD TO LOAD THE GENERATE TAB

	public void loadSearchTab() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchPanel.fxml"));

		fxmlLoader.setController(searchController);

		searchAnchorPane.getChildren().add(fxmlLoader.load());

	}

	//------------------------------------------------------------------------------------

	// METHOD TO UPDATE EDIT TAB VISIBILITY

	public void updateEdit(boolean visible) {

		editTab.setDisable(!visible);

		try {
			editController.setVisible(visible);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	//------------------------------------------------------------------------------------

	// LOAD TABS METHOD

	@FXML
	void initialize() throws IOException {

		loadAddTab();

		loadSearchTab();

		loadEditTab();

		loadGenerateTab();
		
		progressBar.setProgress(0);

	}


	//------------------------------------------------------------------------------------
	
	// SAVE INFORMATION BUTTON

	@FXML
	void saveInformation(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Do you want to save the information to disk?");
		alert.setContentText("Note: This process may take a while");
		alert.showAndWait();
		
		ButtonType result = alert.getResult();
		
		if(result == ButtonType.OK) {
			try {
				dbm.write();
			}
			catch (IOException e) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("There was an error saving data");
				alert.setContentText("Please contact support");
				alert.showAndWait();
			}
			
		}
		
	}

	//------------------------------------------------------------------------------------
	
	//UPDATES THE PROGRESS BAR
	
	public void updateProgressBar(double time) {
		progressBar.setProgress(time);
	}

	//------------------------------------------------------------------------------------
	
	//UPDATES THE TIME LABEL
	
	public void updateTime(long time) {
		
		timeLabel.setText(Long.toString(time) + " ms");
	}
	
	//------------------------------------------------------------------------------------

}
