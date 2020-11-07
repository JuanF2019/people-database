package thread;

import model.DataBaseManager;
import ui.LoadingController;

public class LoadingThread extends Thread {
	private LoadingController lc;
	private DataBaseManager dbm;
	
	public LoadingThread(LoadingController lc, DataBaseManager dbm) {
		this.lc = lc;
		this.dbm = dbm;
	}
	
	public void run() {
		
		if(dbm.read()) {
			while(lc.isLoading()) {
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.exit(10);							
				}
			}
			lc.readyLoading();
		}
		else {
			lc.error();
			System.exit(11);
		}		
	}
}
