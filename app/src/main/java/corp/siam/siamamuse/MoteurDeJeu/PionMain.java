package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


import corp.siam.siamamuse.Activity_Partie;

public class PionMain {

    private int x,y;
    private Pion unPion;
    private Activity_Partie context;
    private MoteurJeu mj;
    private PlateauInterface plateauInterface;
    private ImageButton imagePion;
    private PlateauInterface interPlateau;
    private Joueur unJoueur;
    private boolean btnAjoutAffiche;

    public PionMain(Pion unPion, final Activity_Partie context, int numero, String nom, PlateauInterface interPlateau,Joueur unJoueur,Joueur tourJoueur){
        this.unPion = unPion;
        this.context = context;
        this.interPlateau=interPlateau;
        this.unJoueur=unJoueur;
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()*0.8),(int)(PlateauInterface.calc.getTailleCase()*0.8));
        imagePion.setLayoutParams(paramsPion);
        placementInterface(numero,nom);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imagePion);
            }
        });
        if(unJoueur==tourJoueur) {
            imagePion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ajouterPlateau();
                }
            });
        }
        btnAjoutAffiche=false;
    }

    //si c'est un elephant il les places d'un cote et si c'est un rhinoceros il les places de l'autre cote
    public void placementInterface(int numero,String nom){
        int x =PlateauInterface.calc.calculePionExterieurX(numero);
        imagePion.setX(x);
        //verifier l'orthographe
        int y;
        if(nom=="elephant"){
            y = (int) (PlateauInterface.calc.getHauteurEcrant()*0.05);
            imagePion.setRotation(180);
        }else{
            y = (int) ((PlateauInterface.calc.getHauteurEcrant()*0.95)-PlateauInterface.calc.getTailleCase());
        }
        imagePion.setY(y);

    }

    public void ajouterPlateau(){
        if(btnAjoutAffiche){
            btnAjoutAffiche=false;
            interPlateau.supprimerBtnAjout();
        }else{
            btnAjoutAffiche=true;
            interPlateau.supprimerBtnAjout();
            interPlateau.afficherBtnAjout(unPion,unJoueur);
        }
    }

    public ImageButton getImagePion() {
        return imagePion;
    }
}
