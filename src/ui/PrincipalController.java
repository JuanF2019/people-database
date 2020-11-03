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

	public PrincipalController() {
		
		dbm = new DataBaseManager();
		
		dbm.read();
		
		addController = new AddController(dbm);
		
		searchController = new SearchController(this,dbm);

		editController = new EditController(this,dbm);
		
		generateController = new GenerateController(dbm);

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
		
		editController.setVisible(visible);	
		
	}

	//------------------------------------------------------------------------------------

	// LOAD TABS METHOD
	
	@FXML
    void initialize() throws IOException {
		
		loadAddTab();
		
		loadSearchTab();
		
		loadEditTab();
		
		loadGenerateTab();
		
    }
	
	
	//------------------------------------------------------------------------------------

}
