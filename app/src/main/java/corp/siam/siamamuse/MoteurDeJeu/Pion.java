package corp.siam.siamamuse.MoteurDeJeu;

import android.media.ImageReader;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.R;

public class Pion extends Jeton {
	String nom;
	Orientation regard;
	Integer image;
	//a supprimer
	String typePion;

	public Pion(String nom,Orientation regard) {
		this.nom = nom;
		this.regard = regard;
		choixImage();
	}

	public void choixImage(){
		switch(nom){
			case "elephant":
				image = R.drawable.elephant;
				break;
			case "rhinoceros":
				image = R.drawable.rhinoceros;
				break;
			default:
				image = R.drawable.elephant;
				break;
		}
	}

	@Override
	public String toString() {
		return "Je suis " + nom+" et je regard vers "+regard;
	}


	@Override
	public int veriforientation(Orientation testRegard) {
		if(regard.oppose()==testRegard) {
			return -15;
		}else if(regard==testRegard) {
			return 15;
		}else {
			return 0;
		}
	}

	@Override
	public Integer getImagePion() {	return image;}

	public String getNom() {
		return nom;
	}

	public void setRegard(Orientation regard) {
		this.regard = regard;
	}

	public Orientation getRegard() {
		return regard;
	}
}
