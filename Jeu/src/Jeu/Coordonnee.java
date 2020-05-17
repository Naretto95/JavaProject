
public class Coordonnee {
	private int abscisse;
	private int ordonnee;
	public void setAbscisse(int abscisse) {
		this.abscisse = abscisse;
	}
	public void setOrdonnee(int ordonnee) {
		this.ordonnee = ordonnee;
	}
	public int getAbscisse() {
		return abscisse;
	}
	public int getOrdonnee() {
		return ordonnee;
	}
	public Coordonnee(int abscisse, int ordonnee) {
		this.abscisse = abscisse;
		this.ordonnee = ordonnee;
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj instanceof Coordonnee) {
			Coordonnee coordonnees = (Coordonnee) obj;
			return ((coordonnees.getAbscisse()==this.abscisse)&&(coordonnees.getOrdonnee()==this.ordonnee));
		}
		return false;
	}
}