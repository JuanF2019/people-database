/*
 * UNIVERSIDAD ICESI
 * TAREA INTEGRADORA 2 - ESTRUCTURAS DE DATOS
 * RODAS / DIAZ / MARTINEZ
 */

package collections;

import java.util.ArrayList;

public interface TrieInterface {

	//------------------------------------------------------------------------------------
	
	// METHODS TO USE 

	public boolean add(String s);
	
	public boolean remove(String s);
	
	public boolean search(String s);
	
	public boolean isEmpty();

	public ArrayList<String> getPredictions(String prefix);
	
	//------------------------------------------------------------------------------------

}
