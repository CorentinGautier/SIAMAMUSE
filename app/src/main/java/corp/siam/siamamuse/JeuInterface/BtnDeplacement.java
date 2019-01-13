package corp.siam.siamamuse.JeuInterface;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.MoteurDeJeu.Joueur;
import corp.siam.siamamuse.MoteurDeJeu.Orientation;
import corp.siam.siamamuse.MoteurDeJeu.Pion;

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
                context.fondPartie.addView(btnImage);
            }
        });
    }

    @Override
    public void actionbtn() {
        if(deplacementPossible) {
            plateauInterface.setRotationDeplacement(true);
            this.mj.deplacerPionInterf(this.unPion, orient);
            plateauInterface.suppressionJetons();
            plateauInterface.convertionMatriceAffichage();
        }
    }
}
