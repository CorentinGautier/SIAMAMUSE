package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;

import static corp.siam.siamamuse.MoteurDeJeu.Orientation.EST;
import static corp.siam.siamamuse.MoteurDeJeu.Orientation.NORD;
import static corp.siam.siamamuse.MoteurDeJeu.Orientation.OUEST;
import static corp.siam.siamamuse.MoteurDeJeu.Orientation.SUD;

public class PionInterface {

    private int x,y;
    private Pion unPion;
    private Activity_Partie context;
    private ArrayList<Fleche> lesFleches = new ArrayList<>();
    private boolean flecheAficher;
    private MoteurJeu mj;
    private PlateauInterface plateauInterface;
    private ImageButton imagePion;
    ArrayList<BtnDeplacement> lesBtnDeplac = new ArrayList<>();
//tourJoueur permet de verifier le joueur a qui c'est le tour
    //etat :0 désactivé, 1 activé deplacement , 2 activé rotation
    public PionInterface(Pion unPion, int x, int y, final Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface,Joueur tourJoueur,int etat){
        this.context=context;
        this.unPion=unPion;
        flecheAficher=false;
        this.mj=mj;
        this.x=x;
        this.y=y;
        this.plateauInterface = plateauInterface;
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()*0.8),(int)(PlateauInterface.calc.getTailleCase()*0.8));
        imagePion.setLayoutParams(paramsPion);
        imagePion.setX(PlateauInterface.calc.calculePionPlateauX(x));
        imagePion.setY(PlateauInterface.calc.calculePionPlateauY(y));
        if(etat==1 && (unPion.getNom() == tourJoueur.getNom())) {
            creationBtnAjout();
            imagePion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afficherBtnAjout();
                }
            });

        }else if(etat==2){
            creationFleche(PlateauInterface.calc.calculeBtnAjoutX(x),PlateauInterface.calc.calculeBtnAjoutY(y));
           
        }
        setRegard();
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imagePion);
            }
        });
    }

    public void setRegard(){
        switch(unPion.getRegard()){
            case EST:
                imagePion.setRotation(90);
                break;
            case SUD:
                imagePion.setRotation(180);
                break;
            case OUEST:
                imagePion.setRotation(270);
                break;
            case NORD:
                break;
        }
    }

    //fonction qui est relié au btnImage et qui affiche ou non les fleche
    public void afficherBtnAjout(){
        if(flecheAficher){
            flecheAficher=false;
            supprimerBtn();
        }else{
            flecheAficher=true;
            for(BtnDeplacement unBtn:lesBtnDeplac){
                unBtn.afficheBtn(unPion);
            }
        }
    }
    public void afficherFleche(){
        if(!flecheAficher){
            flecheAficher=true;
            affichageFleche();
        }
    }




    public void supprimerBtn(){
        for(BtnDeplacement unBtn:lesBtnDeplac){
            unBtn.effacer();
        }
    }

    public void creationBtnAjout(){

        boolean res  = mj.getLePlateau().testDeplacement(unPion,NORD);
        lesBtnDeplac.add(new BtnDeplacement(x, y+1, context, mj, plateauInterface,res, NORD));

        res  = mj.getLePlateau().testDeplacement(unPion,SUD);
        lesBtnDeplac.add(new BtnDeplacement(x, y-1, context, mj, plateauInterface,res, SUD));

        res  = mj.getLePlateau().testDeplacement(unPion,OUEST);
        lesBtnDeplac.add(new BtnDeplacement(x-1, y, context, mj, plateauInterface,res, OUEST));

        res  = mj.getLePlateau().testDeplacement(unPion,EST);
        lesBtnDeplac.add(new BtnDeplacement(x+1, y, context, mj, plateauInterface,res, EST));
    }

    public void disparitionFleche(){
        for(Fleche unefleche:lesFleches){
            unefleche.effacerFleche();
        }
    }

    public void affichageFleche(){
        for(Fleche unefleche:lesFleches){
            unefleche.afficherFleche();
        }
    }
    //Il y a un probleme dans la gestion du nord et du sud
    public void creationFleche(float x, float y){
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*1.1)), (int)(y+(PlateauInterface.calc.getTailleCase()*0.25)),unPion,Orientation.EST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x-(PlateauInterface.calc.getTailleCase()*0.6)), (int)(y+(PlateauInterface.calc.getTailleCase()*0.25)),unPion,Orientation.OUEST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*0.25)), (int)(y+(PlateauInterface.calc.getTailleCase()*1.1)),unPion,Orientation.SUD, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*0.25)), (int)(y-(PlateauInterface.calc.getTailleCase()*0.6)),unPion,Orientation.NORD, context,mj,plateauInterface,this));
    }

    public ImageButton getImagePion() {
        return imagePion;
    }

    public Pion getUnPion() {
        return unPion;
    }
}
