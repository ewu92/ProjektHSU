package Model;

import java.util.ArrayList;

public class Ausleihen {

	private ArrayList<Ausleihe> ausleihen;
	
	public Ausleihen(){
		ausleihen = new ArrayList<Ausleihe>();
	}
	
	public int size(){
		return ausleihen.size();
	}
	
	public void add(Ausleihe ausleihe){
		ausleihen.add(ausleihe);
	}
	
	public void addAtIndex(Ausleihe ausleihe, int index){
		ausleihen.add(index, ausleihe);
	}
	
	public Ausleihe get(int index){
		return ausleihen.get(index);
	}
	
	public void removeAtIndex(int index){
		ausleihen.remove(index);
	}
	
	public void removeBuch(Ausleihe ausleihe){
		ausleihen.remove(ausleihe);
	}
}
