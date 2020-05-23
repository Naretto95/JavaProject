package controle;

import java.util.Observable;
import java.util.Observer;

import Jeu.Entité;

public class ControleInventaireItemsEntite extends Observable implements Observer{

	private Entité entite;
	
	public ControleInventaireItemsEntite(Entité entite) {
		this.entite = entite;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
