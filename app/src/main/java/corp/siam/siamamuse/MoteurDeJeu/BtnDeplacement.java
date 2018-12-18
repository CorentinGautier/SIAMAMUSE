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
        //convertirResVariable(res);
    }

    private void convertirResVariable(int[] res){
        if(res[1]==0){
            deplacementPossible=false;
        }else{
            deplacementPossible=true;
        }

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
        this.mj.deplacerPionInterf(this.unPion,orient);
        plateauInterface.suppressionJetons();
        plateauInterface.convertionMatriceAffichage();
    }
}
