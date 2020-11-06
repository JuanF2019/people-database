/*
 * ALGORITMOS Y ESTRUCTURAS DE DATOS
 * TAREA INTEGRADORA 2
 * MARTINEZ - DIAZ - RODAS
 */

package thread;

import ui.PrincipalController;

/*
 * this is a skeleton of a thread that updates the value of
 * the progress bar, making it look like it is charging.
 * For this to work, this needs a method in the controller
 * that receives the values made here and updates the value 
 * of the progress bar. This also depends on the model sending 
 * the value of the time or whatever measure is going to be used
 * to calculate the thing on the thread
 */

public class ProgressBarThread extends Thread {

	//------------------------------------------------------------------------------------

	//Attributes of the ProgressBarThread class

	private PrincipalController controller;

	//------------------------------------------------------------------------------------

	// Constructor method

	public ProgressBarThread(PrincipalController controller) {

		this.controller = controller;

		setDaemon(true);

	}

	//------------------------------------------------------------------------------------

	//Run method


	@Override
	public void run() {

		long time = 0;

		while(time<=1) {

			time+= 0.005;

			controller.updateProgressBar(time);

			try {

				sleep(10);

			} catch (InterruptedException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

	}

	//------------------------------------------------------------------------------------

}
