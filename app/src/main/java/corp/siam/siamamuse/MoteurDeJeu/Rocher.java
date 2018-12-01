package corp.siam.siamamuse.MoteurDeJeu;

import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public class Rocher extends Jeton {
	String nom;
	Integer image;

	public Rocher(String nom) {
		this.nom = nom;
		image = R.drawable.rocher;
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
	public Integer getImagePion() {
		return image;
	}

}
