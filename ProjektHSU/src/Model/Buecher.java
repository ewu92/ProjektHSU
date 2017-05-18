package Model;

import java.util.ArrayList;

public class Buecher {

	private ArrayList<Buch> buecher;
	
	public Buecher(){
		buecher = new ArrayList<Buch>();
	}
	
	public int size(){
		return buecher.size();
	}
	
	public void add(Buch buch){
		buecher.add(buch);
	}
	
	public void addAtIndex(Buch buch, int index){
		buecher.add(index, buch);
	}
	
	public Buch get(int index){
		return buecher.get(index);
	}
	
	public void removeAtIndex(int index){
		buecher.remove(index);
	}
	
	public void removeBuch(Buch buch){
		buecher.remove(buch);
	}
}
