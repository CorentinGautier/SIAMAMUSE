package corp.siam.siamamuse.MoteurDeJeu;

public class Rocher extends Jeton {
	String nom;

	public Rocher(String nom) {
		this.nom = nom;
	}
	

	@Override
	public String toString() {
		return "Je suis "+nom;
	}

	@Override
	public int veriforientation(Orientation regard) {
		return -1;
	}

}
