package corp.siam.siamamuse.MoteurDeJeu;

import android.graphics.Color;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

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
    private Button btnRotation;
    ArrayList<BtnDeplacement> lesBtnDeplac = new ArrayList<>();
    private int etat;
//tourJoueur permet de verifier le joueur a qui c'est le tour
    //etat :0 désactivé, 1 activé deplacement , 2 activé rotation, 3 activé rotation avec possibilité de retour
    public PionInterface(Pion unPion, int x, int y, final Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface,Joueur tourJoueur,int etat){
        this.context=context;
        this.unPion=unPion;
        flecheAficher=false;
        this.mj=mj;
        this.x=x;
        this.y=y;
        this.plateauInterface = plateauInterface;
        this.etat=etat;
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(unPion.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()*0.8),(int)(PlateauInterface.calc.getTailleCase()*0.8));
        imagePion.setLayoutParams(paramsPion);
        imagePion.setX(PlateauInterface.calc.calculePionPlateauX(x));
        imagePion.setY(PlateauInterface.calc.calculePionPlateauY(y));

        setRegard();
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imagePion);
            }
        });
        btnRotation = new Button(context);
        btnRotation.setBackgroundResource(R.drawable.coutonbouton);
        btnRotation.setTextColor(Color.WHITE);
        final ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getLargeurEcrant()*0.2),(int)(PlateauInterface.calc.getLargeurEcrant()*0.12));
        btnRotation.setLayoutParams(paramsBtn);
        btnRotation.setX((int)(PlateauInterface.calc.getLargeurEcrant()*0.75));
        if(tourJoueur.getNom()=="elephant"){
            btnRotation.setY((int)(PlateauInterface.calc.getHauteurEcrant()*0.1));
            btnRotation.setRotation(180);
        }else{
            btnRotation.setY((int)(PlateauInterface.calc.getHauteurEcrant()*0.78));
        }
        //Gestion de l'etat du pion
        //etat ou le pion est grisé
        if(etat==0){
            imagePion.setBackgroundResource(unPion.getImageGris());
            //etat ou on peur faire des déplacement et le pion et de couleurs
        }else if(etat==1) {
            creationBtnAjout();
            imagePion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    afficherBtnAjout();
                }
            });
            creationBtnRotation(true);
        //etat ou le pion peut que faire une rotation
        }else if(etat==2){
            creationFleche(PlateauInterface.calc.calculeBtnAjoutX(x),PlateauInterface.calc.calculeBtnAjoutY(y));
            context.setPionSelectionner(this);

            //etat ou le pion peut que faire une rotation et possibilte de retour
        }else if(etat==3){
            creationFleche(PlateauInterface.calc.calculeBtnAjoutX(x),PlateauInterface.calc.calculeBtnAjoutY(y));
            creationBtnRotation(false);
            context.fondPartie.addView(btnRotation);
            //on fait ça pour le double tap
            context.setPionSelectionner(this);
        }
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
    //Quand on appuie sur le btn du pion on affiche les btnAjout et un btn pour passer directement au deplacement
    public void afficherBtnAjout(){
        //change le pionSelectionner dans le main pour gerer les swips
        context.setPionSelectionner(this);
        plateauInterface.suppresionBtnDeplacement(this);
        if(flecheAficher){
            flecheAficher=false;
            supprimerBtn();
            //suppresion btn Rotation
            context.fondPartie.removeView(btnRotation);
        }else{
            flecheAficher=true;
            for(BtnDeplacement unBtn:lesBtnDeplac){
                unBtn.afficheBtn(unPion);
            }
            //affichage du btn Rotation
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    context.fondPartie.addView(btnRotation);
                }
            });
        }
    }


    public void supprimerBtn(){
        for(BtnDeplacement unBtn:lesBtnDeplac){
            unBtn.effacer();
        }
    }


    public void disparitionFleche(){
        for(Fleche unefleche:lesFleches){
            unefleche.effacerFleche();
        }
        context.fondPartie.removeView(btnRotation);
    }

    public void affichageFleche(){
        for(Fleche unefleche:lesFleches){
            unefleche.afficherFleche();
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
    //creationd des 4 fleches de changement d'orientation
    public void creationFleche(float x, float y){
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*1.1)), (int)(y+(PlateauInterface.calc.getTailleCase()*0.25)),unPion,Orientation.EST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x-(PlateauInterface.calc.getTailleCase()*0.6)), (int)(y+(PlateauInterface.calc.getTailleCase()*0.25)),unPion,Orientation.OUEST, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*0.25)), (int)(y+(PlateauInterface.calc.getTailleCase()*1.1)),unPion,Orientation.SUD, context,mj,plateauInterface,this));
        lesFleches.add(new Fleche((int)(x+(PlateauInterface.calc.getTailleCase()*0.25)), (int)(y-(PlateauInterface.calc.getTailleCase()*0.6)),unPion,Orientation.NORD, context,mj,plateauInterface,this));
    }


    //creation d'un btn qui permet de dire qu'on ne veut pas faire de déplacement mais qu'on veut passer direct a la rotation
    public void creationBtnRotation(boolean rotation){
        context.fondPartie.removeView(btnRotation);
        if(rotation) {
            btnRotation.setText("Rotation");
            btnRotation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    passerALaRotation();
                }
            });
        }else{
            btnRotation.setText("Déplacement");
            btnRotation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    passerAuDeplacement();
                }
            });

        }
    }


    public void passerALaRotation(){
        mj.setPionRotation(unPion);
        plateauInterface.setRotationDeplacement(false);
        plateauInterface.convertionMatriceAffichage();
    }

    public void passerAuDeplacement(){;
        mj.setPionRotation(null);
        plateauInterface.setBloqueTourSuivant(true);
        plateauInterface.convertionMatriceAffichage();
    }

    public ImageButton getImagePion() {
        return imagePion;
    }

    public Pion getUnPion() {
        return unPion;
    }



    //swipe pour sortir du plateau

    //OUEST
    public void gauche(){
        if(etat==1) {
            //deplacement
            lesBtnDeplac.get(2).actionbtn();
        }else if(etat==2){
            //rotation
            lesFleches.get(1).rotation();
        }
    }
    //EST
    public void droit(){
        if(etat==1) {
            //deplacement
            lesBtnDeplac.get(3).actionbtn();
        }else if(etat==2){
            //rotation
            lesFleches.get(0).rotation();
        }
    }
    //NORD
    public void haut(){
        if(etat==1) {
            //deplacement
            lesBtnDeplac.get(0).actionbtn();
        }else if(etat==2){
            //rotation
            lesFleches.get(3).rotation();
        }
    }
    //SUD
    public void bas(){
        if(etat==1) {
            //deplacement
            lesBtnDeplac.get(1).actionbtn();
        }else if(etat==2){
            //rotation
            lesFleches.get(2).rotation();
        }
    }

}
