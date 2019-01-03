package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.widget.ImageButton;

import corp.siam.siamamuse.Activity_Partie;

public class BtnDeplacement extends Btn {
   // private ImageButton btnVerre;
   // private Activity_Partie context;
   // private MoteurJeu mj;
   // private Pion unPion;
   // private int xPla,yPla;
   // private PlateauInterface plateauInterface;
    private Joueur unJoueur;
    private boolean deplacementPossible;
    private Orientation orient;


    public BtnDeplacement(int xPla, int yPla, final Activity_Partie context, MoteurJeu mj, PlateauInterface plateauInterface, boolean deplacementPossible,Orientation orient) {
        super(xPla, yPla, context, mj, plateauInterface,deplacementPossible);
        this.orient=orient;
        this.deplacementPossible=deplacementPossible;

    }


    public void afficheBtn(Pion unPion){
        this.unPion = unPion;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(btnVerre);
            }
        });
    }

    @Override
    public void actionbtn() {
        if(deplacementPossible) {
            this.mj.deplacerPionInterf(this.unPion, orient);
            plateauInterface.suppressionJetons();
            plateauInterface.convertionMatriceAffichage();
        }
    }
}
