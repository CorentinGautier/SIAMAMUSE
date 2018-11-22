package corp.siam.siamamuse.MoteurDeJeu;

import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public class Rocher extends Jeton {
	String nom;
	ImageButton image;

	public Rocher(String nom, Activity_Partie context) {
		this.nom = nom;
		image = new ImageButton(context);
		image.setBackgroundResource(R.drawable.rocher);
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
