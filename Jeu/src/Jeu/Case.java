
package Jeu;

import java.util.ArrayList;
import java.util.Stack;

public class Case {
	public static Integer VIDE = new Integer(0);
	
	private Object contenu;
	private Stack<Item> items;
	private Stack<Ressource> ressources;
	
	public Case() {
		this.contenu = VIDE;
		this.items = new Stack<Item>();
		this.ressources = new Stack<Ressource>();
	}
	
	public void addItem(Item item) {
		this.items.push(item);
	}
	public void addRessource(Ressource ressource) {
		this.ressources.push(ressource);
	}
	public Item getItem() {
		return this.items.pop();		
	}
	public Ressource getRessource() {
		return this.ressources.pop();
	}
	public ArrayList<Ressource> getAllRessources() {
		ArrayList<Ressource> allRessources = new ArrayList<Ressource>();
		while (!this.ressources.isEmpty()) {
			allRessources.add(this.getRessource());
		}
		return allRessources;
	}
	public ArrayList<Item> getAllItems() {
		ArrayList<Item> allItems = new ArrayList<Item>();
		while (!this.items.isEmpty()) {
			allItems.add(this.getItem());
		}
		return allItems;
	}
	public void addSomeRessources(Stack<Ressource> ressources) {
		while(!ressources.isEmpty()) {
			this.addRessource(ressources.pop());
		}
	}
	public void addSomeItems(Stack<Item> items) {
		while(!items.isEmpty()) {
			this.addItem(items.pop());
		}
	}

	public Object getContenu() {
		return contenu;
	}

	public void setContenu(Object contenu) {
		this.contenu = contenu;
	}
}
