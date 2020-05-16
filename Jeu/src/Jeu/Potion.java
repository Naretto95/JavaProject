package Jeu;

public class Potion extends Item {
	
	private Effet Effet;
	
	public Potion(Effet _Effet, int _Niveau) {
		this.setEtat(true);
		this.setNiveau(_Niveau);
		this.setEffet(_Effet);
	}
	
	public Effet getEffet() {
		return Effet;
	}

	public void setEffet(Effet effet) {
		Effet = effet;
	}
	
}
