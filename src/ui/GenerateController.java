/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package ui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.DataBaseManager;
import thread.ProgressBarThread;

public class GenerateController {

	//------------------------------------------------------------------------------------

	// RELATIONS

	private DataBaseManager dbm;

	private ProgressBarThread pbt;

	private PrincipalController pc;

	//------------------------------------------------------------------------------------

	// WINDOW ELEMENTS

	@FXML
	private TextField numberGenerateText;

	@FXML
	private Button generateButton;

	//------------------------------------------------------------------------------------

	// CONSTRUCTOR METHOD

	public GenerateController(DataBaseManager dbm, PrincipalController pc) {

		this.dbm = dbm;

		this.pc = pc;

	}

	//------------------------------------------------------------------------------------

	// GENERATE METHOD

	@FXML
	void generate(ActionEvent event) throws InterruptedException {

		if(!numberGenerateText.getText().equals("")) {

			try {

				final int peopleNumber = Integer.parseInt(numberGenerateText.getText());

				boolean g = false;

				new Thread() {
					public void run(){

						dbm.generatePeople(peopleNumber);

					}
				}.start();

				pbt = new ProgressBarThread(pc);
				pbt.start();


				/*
				if(g) success();
				else notSuccess();
				 */
			}
			catch(NumberFormatException ex) {

				notSuccess();

			}


		}
		else {
			warning();
		}	

	}

	//------------------------------------------------------------------------------------

	// SUCCESS METHOD

	public void success() {

		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Alert");
		alert.setHeaderText("Generation done succesfully");
		alert.setContentText(numberGenerateText.getText() + " people generated succesfully.");
		alert.showAndWait();

	}

	//------------------------------------------------------------------------------------

	// NOT SUCCESS METHOD

	public void notSuccess() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Generation failed");
		alert.setContentText("The program could not generate the desired amount of people. Please contact technical support.");
		alert.showAndWait();

	}

	//------------------------------------------------------------------------------------

	// WARNING METHOD

	public void warning() {

		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Alert");
		alert.setHeaderText("Empty field");
		alert.setContentText("Please be sure all the fields are fullfilled correctly and a number is typed.");
		alert.showAndWait();

	}

	//------------------------------------------------------------------------------------

}
