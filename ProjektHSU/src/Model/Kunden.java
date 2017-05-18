package Model;

import java.util.ArrayList;

public class Kunden {

	private ArrayList<Kunde> kunden;
	
	public Kunden(){
		kunden = new ArrayList<Kunde>();
	}
	
	public int size(){
		return kunden.size();
	}
	
	public void add(Kunde kunde){
		kunden.add(kunde);
	}
	
	public void addAtIndex(Kunde Kunde, int index){
		kunden.add(index, Kunde);
	}
	
	public Kunde get(int index){
		return kunden.get(index);
	}
	
	public void removeAtIndex(int index){
		kunden.remove(index);
	}
	
	public void removeBuch(Kunde Kunde){
		kunden.remove(Kunde);
	}
}
