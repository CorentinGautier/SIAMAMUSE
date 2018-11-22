package corp.siam.siamamuse.MoteurDeJeu;

import android.widget.ImageButton;
import android.widget.ImageView;

public class Rocher extends Jeton {
	String nom;
	ImageButton image;

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

	@Override
	public ImageButton getImage() {
		return image;
	}
}
