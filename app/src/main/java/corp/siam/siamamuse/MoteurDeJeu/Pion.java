package corp.siam.siamamuse.MoteurDeJeu;

import android.media.ImageReader;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Pion extends Jeton {
	String nom;
	Orientation regard;
	Integer image;
	//a supprimer
	int id;

	public Pion(String nom,Orientation regard,int id) {
		this.nom = nom;
		this.regard = regard;
		this.id = id;
	}

	@Override
	public String toString() {
		return "Je suis " + nom+" "+id+" et je regard vers "+regard;
	}
	

	@Override
	public int veriforientation(Orientation testRegard) {
		if(regard.oppose()==testRegard) {
			return -1;
		}else if(regard==testRegard) {
			return 1;
		}else {
			return 0;
		}
	}

	@Override
	public Integer getImagePion() {
		return image;
	}

	public String getNom() {
		return nom;
	}

	public void setRegard(Orientation regard) {
		this.regard = regard;
	}



}
