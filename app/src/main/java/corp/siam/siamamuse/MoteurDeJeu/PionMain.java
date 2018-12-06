package corp.siam.siamamuse.MoteurDeJeu;

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

    public PionMain(Pion unPion,Activity_Partie context,int numero,String nom){
        this.unPion = unPion;
        this.context = context;

        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase*0.8),(int)(PlateauInterface.tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        placementInterface(numero,nom);
    }

    public void placementInterface(int numero,String nom){
        int x =(int) ((PlateauInterface.tailleCase-(PlateauInterface.tailleCase*0.5))*(numero-1) + PlateauInterface.largeurEcrant*0.1);
        imagePion.setX(x);
        //verifier l'orthographe
        int y;
        if(nom=="elephant"){
            y = (int) (PlateauInterface.hauteurEcrant*0.05);
        }else{
            y = (int) ((PlateauInterface.hauteurEcrant*0.95)+PlateauInterface.tailleCase);
        }
        imagePion.setY(y);
    }

    public void ajouterPlateau(){

    }
}
