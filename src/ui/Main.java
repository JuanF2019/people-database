/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	//------------------------------------------------------------------------------------
	
	// RELATION'S WITH ANOTHER CLASS
	
	private LoadingController controller;
	
	//------------------------------------------------------------------------------------
	
	// CONTRUCTOR METHOD OF THE MAIN CLASS
	
	public Main() throws Exception {
		
		controller = new LoadingController();
		
	}
	
	//------------------------------------------------------------------------------------
	
	// METHOD START OF THE CLASS MAIN
	
	@Override
	public void start(Stage primaryStage) {

		try {
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("loadingPanel.fxml"));

			fxmlLoader.setController(controller);
			
			Parent root = fxmlLoader.load();
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			
			primaryStage.setTitle("People database");
			
			primaryStage.show();
			
		} catch(Exception e) {
			
			e.printStackTrace();
			
		}
			
	}
	
	//------------------------------------------------------------------------------------
	
	// MAIN METHOD (PRINCIPAL METHOD)
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	//------------------------------------------------------------------------------------

}
