package controle;

import java.util.Observable;
import java.util.Observer;

import Jeu.Entit�;

public class ControleInventaireItemsEntite extends Observable implements Observer{

	private Entit� entite;
	
	public ControleInventaireItemsEntite(Entit� entite) {
		this.entite = entite;
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
