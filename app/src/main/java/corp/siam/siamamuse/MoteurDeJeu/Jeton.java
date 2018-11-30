package corp.siam.siamamuse.MoteurDeJeu;

import android.widget.ImageButton;

public abstract class Jeton {

	public Jeton() {

	}

	public abstract int veriforientation(Orientation regard);

	public abstract Integer getImagePion();
}
