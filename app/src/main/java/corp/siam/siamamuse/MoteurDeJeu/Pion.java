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
	Integer imageGris;
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
				imageGris = R.drawable.elephantgris;
				break;
			case "rhinoceros":
				image = R.drawable.rhinoceros;
				imageGris = R.drawable.rhinocerosgris;
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

	public Integer getImageGris() {return imageGris;}

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
