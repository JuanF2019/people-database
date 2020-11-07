/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package thread;

import javafx.application.Platform;
import model.DataBaseManager;
import ui.GenerateController;
import ui.PrincipalController;

public class GenerateThread extends Thread {
	
	//------------------------------------------------------------------------------------
	
	// Relation with another class
	
	PrincipalController pc;
	
	GenerateController gc;
	
	DataBaseManager dbm;
	
	//------------------------------------------------------------------------------------
	
	// Some information to use
	
	int n;
	
	boolean generated;
	
	//------------------------------------------------------------------------------------
	
	// Constructor method
	
	public GenerateThread(PrincipalController pc, GenerateController gc, DataBaseManager dbm, int n) {
		
		this.pc = pc;
		
		this.gc = gc;
		
		this.dbm = dbm;
		
		this.n = n;		
		
		generated = false;
		
	}
	
	//------------------------------------------------------------------------------------
	
	// Run method
	
	public void run() {
		
		long t1 = System.currentTimeMillis();
		
		new Thread() {
			
			public void run() {
				
				dbm.generatePeople(n);
				
			}
			
		}.start();
		
		while(!dbm.isGenerating()) {
			
			try {
				
				Thread.sleep(20);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
			
		}
		
		while(dbm.isGenerating()) {
						
			pc.updateProgressBar(dbm.getCurrentGeneration()/(n-1.0));
			
			try {
				
				Thread.sleep(50);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
				
			}
			
		}		
		
		long t2 = System.currentTimeMillis();
		
		Platform.runLater(new Thread() {
			
			public void run() {
				
				if(dbm.isLastGenerationSuccesful()) {
					
					gc.success();
					
				} else {
					
					gc.notSuccess();
					
				}
				
				pc.updateTime(t2-t1);
				
			}
			
		});	
		
	}
	
	//------------------------------------------------------------------------------------
	
}
