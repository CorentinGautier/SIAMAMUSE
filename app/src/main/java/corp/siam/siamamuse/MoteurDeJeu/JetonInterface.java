package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;

import static corp.siam.siamamuse.MoteurDeJeu.Orientation.NORD;

public class JetonInterface {

    private int x,y;
    private Jeton unJeton;
    private Activity_Partie context;
    private ArrayList<Fleche> lesFleches = new ArrayList<>();
    private boolean flecheAficher;

    public JetonInterface(Jeton unJeton, int x, int y, final Activity_Partie context){
        this.context=context;
        this.unJeton=unJeton;
        flecheAficher=false;
        final ImageButton imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unJeton.getImagePion());
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
        creationFleche(imagePion.getX(),imagePion.getY());
        imagePion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afficherBtn();
            }
        });
    }
    public void afficherBtn(){
        if(flecheAficher){
            flecheAficher=false;
            for(Fleche unefleche:lesFleches){
                unefleche.effacerFleche();
            }
        }else{
            flecheAficher=true;
            for(Fleche unefleche:lesFleches){
                unefleche.afficherFleche();
            }
        }
    }



    public void creationFleche(float x, float y){
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*0.9)), (int)(y+(PlateauInterface.tailleCase*0.1)),0,  1,Orientation.NORD, context));
        lesFleches.add(new Fleche((int)(x-(PlateauInterface.tailleCase*0.9)), (int)(y+(PlateauInterface.tailleCase*0.1)),0,  -1,Orientation.SUD, context));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*0.1)), (int)(y+(PlateauInterface.tailleCase*0.8)),1,  0,Orientation.EST, context));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.tailleCase*0.1)), (int)(y-(PlateauInterface.tailleCase*0.9)),-1,  0,Orientation.OUEST, context));
    }


}
