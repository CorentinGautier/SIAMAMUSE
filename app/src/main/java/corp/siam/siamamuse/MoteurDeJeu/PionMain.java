package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

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

    public PionMain(Pion unPion, final Activity_Partie context, int numero, String nom, PlateauInterface interPlateau,Joueur unJoueur){
        this.unPion = unPion;
        this.context = context;
        this.interPlateau=interPlateau;
        this.unJoueur=unJoueur;
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase*0.8),(int)(PlateauInterface.tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        placementInterface(numero,nom);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imagePion);
            }
        });
        imagePion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajouterPlateau();
            }
        });
    }

    public void placementInterface(int numero,String nom){
        int x =(int) ((PlateauInterface.tailleCase-(PlateauInterface.tailleCase*0.5))*(numero-1) + PlateauInterface.largeurEcrant*0.1);
        imagePion.setX(x);
        //verifier l'orthographe
        int y;
        if(nom=="elephant"){
            y = (int) (PlateauInterface.hauteurEcrant*0.05);
        }else{
            y = (int) ((PlateauInterface.hauteurEcrant*0.95)-PlateauInterface.tailleCase);
        }
        imagePion.setY(y);

    }

    public void ajouterPlateau(){
        interPlateau.supprimerBtnAjout();
        interPlateau.afficherBtnAjout(unPion,unJoueur);
    }

    public ImageButton getImagePion() {
        return imagePion;
    }
}
