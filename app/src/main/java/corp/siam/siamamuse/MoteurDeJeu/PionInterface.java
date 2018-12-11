package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;

import static corp.siam.siamamuse.MoteurDeJeu.Orientation.NORD;

public class PionInterface {

    private int x,y;
    private Pion unPion;
    private Activity_Partie context;
    private ArrayList<Fleche> lesFleches = new ArrayList<>();
    private boolean flecheAficher;
    private MoteurJeu mj;
    private PlateauInterface plateauInterface;
    private ImageButton imagePion;

    public PionInterface(Pion unPion, int x, int y, final Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface){
        this.context=context;
        this.unPion=unPion;
        flecheAficher=false;
        this.mj=mj;
        this.plateauInterface = plateauInterface;
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase*0.8),(int)(PlateauInterface.tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        imagePion.setX((int)((PlateauInterface.tailleCase*x)+(PlateauInterface.tailleCase*0.1)));
        imagePion.setY((int)(PlateauInterface.posHautGauchY+(PlateauInterface.tailleCase*y)+(PlateauInterface.tailleCase*0.1)));
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imagePion);
            }
        });
        creationFleche((int)(imagePion.getX()-(PlateauInterface.tailleCase*0.1)),(int)(imagePion.getY()-(PlateauInterface.tailleCase*0.1)));
        imagePion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherBtn();
            }
        });
    }

    //fonction qui est relié au btnImage et qui affiche ou non les fleche
    public void afficherBtn(){
        if(flecheAficher){
            flecheAficher=false;
            disparitionFleche();
        }else{
            flecheAficher=true;
            for(Fleche unefleche:lesFleches){
                unefleche.afficherFleche();
            }
        }
    }

    public void disparitionFleche(){
        for(Fleche unefleche:lesFleches){
            unefleche.effacerFleche();
        }
    }
    //Il y a un probleme dans la gestion du nord et du sud
    public void creationFleche(float x, float y){
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*1.1)), (int)(y+(PlateauInterface.tailleCase*0.2)),unPion,Orientation.EST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x-(PlateauInterface.tailleCase*0.9)), (int)(y+(PlateauInterface.tailleCase*0.2)),unPion,Orientation.OUEST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*0.2)), (int)(y+(PlateauInterface.tailleCase*1.1)),unPion,Orientation.NORD, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*0.2)), (int)(y-(PlateauInterface.tailleCase*0.9)),unPion,Orientation.SUD, context,mj,plateauInterface,this));
    }

    public ImageButton getImagePion() {
        return imagePion;
    }
}
