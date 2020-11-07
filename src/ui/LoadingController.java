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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.DataBaseManager;
import thread.LoadingThread;

public class LoadingController {
	
	//------------------------------------------------------------------------------------

	DataBaseManager dbm;

	PrincipalController controller;
	
	//------------------------------------------------------------------------------------

	@FXML
	private Label text;

	@FXML
	private Button startButton;
	
	//------------------------------------------------------------------------------------

	@FXML
	void start(ActionEvent event) throws IOException {

		Node source = (Node) event.getSource();

		Stage stage = (Stage) source.getScene().getWindow();

		stage.close();

		// ******************************************

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

	public LoadingController() {

		dbm = new DataBaseManager();

		new LoadingThread(this,dbm).start();
	}
	
	//------------------------------------------------------------------------------------
	
	public boolean isLoading() {
		
		if(startButton == null || text == null) {
			
			return true;
		
		}
		else {
		
			return false;
		
		}
	}

	//------------------------------------------------------------------------------------
	
	public void readyLoading() {		
				
		startButton.setDisable(false);
		
		text.setVisible(false);

		controller = new PrincipalController(dbm);
		
	}
	
	//------------------------------------------------------------------------------------
	
	public void error() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText("Error loading data");
		alert.showAndWait();
	}
}
