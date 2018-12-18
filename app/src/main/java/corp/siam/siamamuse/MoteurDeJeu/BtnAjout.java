package corp.siam.siamamuse.MoteurDeJeu;


import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public class BtnAjout extends Btn {

   // private ImageButton btnVerre;
    //private final Activity_Partie context;
  //  private MoteurJeu mj;
   // private Pion unPion;
   // private int xPla,yPla;
   // private PlateauInterface plateauInterface;
    private Joueur unJoueur;


    public BtnAjout(int xPla, int yPla, final Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface){
        super(xPla,yPla,context,mj,plateauInterface,true);
    }
//        this.context=context;
//        this.mj=mj;
//        this.xPla=xPla;
//        this.yPla=yPla;
//        this.plateauInterface=plateauInterface;
//        btnVerre = new ImageButton(context);
//        if(deplacementPossible){
//            btnVerre.setBackgroundResource(R.drawable.carrevert);
//            btnVerre.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    actionbtn();
//                }
//            });
//        }else{
//            btnVerre.setBackgroundResource(R.drawable.carrerouge);
//        }
//        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase),(int)(PlateauInterface.tailleCase));
//        btnVerre.setLayoutParams(paramsPion);
//        btnVerre.setX((int)((PlateauInterface.tailleCase*xPla)+(PlateauInterface.tailleCase*0.1)));
//        btnVerre.setY((int)(PlateauInterface.posHautGauchY+(PlateauInterface.tailleCase*yPla)+(PlateauInterface.tailleCase*0.1)));





//    public void affiche(Pion unPion,Joueur unJoueur){
//        this.unPion = unPion;
//        this.unJoueur = unJoueur;
//        context.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                context.fondPartie.addView(btnVerre);
//            }
//        });
//    }

    public void actionbtn(){
        mj.ajouterPionPlateauInter(unJoueur,unPion,xPla,yPla);
        plateauInterface.convertionMatriceAffichage();
        //ajouter un message quand le deplacement est impossible
    }

    public void affiche(Pion unPion,Joueur unJoueur){
        this.unPion = unPion;
        this.unJoueur = unJoueur;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(btnVerre);
            }
        });
    }

//    public void effacer(){
//        context.fondPartie.removeView(btnVerre);
//    }
}
