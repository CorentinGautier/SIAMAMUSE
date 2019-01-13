package corp.siam.siamamuse.MoteurDeJeu;


import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public class BtnAjout extends Btn {
    private Joueur unJoueur;


    public BtnAjout(int xPla, int yPla, final Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface){
        super(xPla,yPla,context,mj,plateauInterface,true);
    }

    public void actionbtn(){
        Log.e("TEST","Je m'ajoute ");
        mj.ajouterPionPlateauInter(unJoueur,unPion,xPla,yPla);
        plateauInterface.setRotationDeplacement(true);
        plateauInterface.convertionMatriceAffichage();
        //ajouter un message quand le deplacement est impossible
    }

    public void affiche(Pion unPion,Joueur unJoueur){
        this.unPion = unPion;
        this.unJoueur = unJoueur;
        Log.e("TEST","Pour le x = "+xPla+" et le y = "+yPla+" deplacement possible "+mj.getLePlateau().testAjouterPion(unPion,xPla,yPla));
        setBoutton(mj.getLePlateau().testAjouterPion(unPion,xPla,yPla));
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(btnImage);
            }
        });
    }

}
