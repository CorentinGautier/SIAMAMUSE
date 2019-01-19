package corp.siam.siamamuse.JeuInterface;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.MoteurDeJeu.Jeton;
import corp.siam.siamamuse.MoteurDeJeu.Joueur;
import corp.siam.siamamuse.MoteurDeJeu.Pion;
import corp.siam.siamamuse.MoteurDeJeu.Rocher;

public class PlateauInterface {

    public static Calculateur calc;

    public static Activity_Partie context;
    public  MoteurJeu mj;

    public ArrayList<PionInterface> lesPions = new ArrayList<>();
    public ArrayList<RocherInterface> lesRochers = new ArrayList<>();

    public ArrayList<BtnAjout> lesBtnAjout = new ArrayList<>();
    public ArrayList<PionMain> lesPionsMain = new ArrayList<>();

    //boolean est egale true si la partie commence
    private boolean bloqueTourSuivant;
    private boolean rotationDeplacement;


    public PlateauInterface(Activity_Partie context,MoteurJeu moteurJeu) throws IOException, SAXException, ParserConfigurationException {
        calc = new Calculateur(context.largeurEcrant,context.hauteurEcrant,moteurJeu);
        this.context=context;
        mj = moteurJeu;
        bloqueTourSuivant =true;
    }

     public void convertionMatriceAffichage(){
        creationbtnAjout();
        boolean faireRotation = false;
        if(bloqueTourSuivant){
            bloqueTourSuivant =false;
        }else{
            faireRotation = mj.tourSuivant();
            //relancer le chrono
            if(!faireRotation) {
                mj.relancementChrono();
            }
        }
        Jeton[][] plateau = mj.getLePlateau().getPlateau();
        supprimerBtnAjout();
        suppressionJetons();
        affichagePion(mj.getJoueur1(),faireRotation);
        affichagePion(mj.getJoueur2(),faireRotation);
        for(int i=1;i<mj.getLePlateau().getTaillePlateau()+1;i++){
            for(int j=1;j<mj.getLePlateau().getTaillePlateau()+1;j++){
                if(plateau[i][j]instanceof Pion){
                    Pion pion= (Pion) plateau[i][j];
                    if(faireRotation){
                        if(plateau[i][j].equals(mj.getPionRotation())){
                            if(rotationDeplacement) {
                                lesPions.add(new PionInterface(pion, i - 1, j - 1, context, mj, this, mj.getTour(), 2));
                            }else{
                                lesPions.add(new PionInterface(pion, i - 1, j - 1, context, mj, this, mj.getTour(), 3));
                            }
                        }else{
                            lesPions.add(new PionInterface(pion,i-1,j-1,context,mj,this,mj.getTour(),0));
                        }
                    }else {
                        if(pion.getNom()==mj.getTour().getNom()){
                            lesPions.add(new PionInterface(pion, i - 1, j - 1, context, mj, this, mj.getTour(),1));
                        }else{
                            lesPions.add(new PionInterface(pion, i - 1, j - 1, context, mj, this, mj.getTour(),0));
                        }
                    }
                }else if(plateau[i][j]instanceof Rocher){
                    Rocher rocher = (Rocher) plateau[i][j];
                    lesRochers.add(new RocherInterface(rocher,i-1,j-1,context));
                }
            }
        }
        for(PionInterface unPion:lesPions){
            if(unPion.getUnPion()==mj.getPionRotation()){
                unPion.affichageFleche();
            }
        }

    }

    //fonction qui a chaque deplacement ou ajout supprime tout les imageBtn de l'interface et vide les listes
    public void suppressionJetons(){
        for(PionInterface unPion : lesPions){
            unPion.supprimerBtn();
            unPion.disparitionFleche();
            context.fondPartie.removeView(unPion.getImagePion());
        }
        for(RocherInterface unRocher : lesRochers){
            context.fondPartie.removeView(unRocher.getImageRocher());
        }
        for(PionMain unPionMain:lesPionsMain){
            context.fondPartie.removeView(unPionMain.getImagePion());
        }
        lesPionsMain.removeAll(lesPionsMain);
        lesRochers.removeAll(lesRochers);
        lesPions.removeAll(lesPions);
    }

    //Affiche les pions qu'un joueur a en main
    public void affichagePion(Joueur pJoueur, boolean bloquer){
        ArrayList<Pion> lesPionsJoueur = pJoueur.getLesPionsEnMain();
        int i=1;
        for (Pion unPion: lesPionsJoueur){
            lesPionsMain.add( new PionMain(unPion,context,i,pJoueur.getNom(),this,pJoueur,mj.getTour(),bloquer));
            i++;
        }
    }

    //les btnajouts sont les carre vert qui permet de choisir ou ont veut placer notre pion sur le plateau
    public void creationbtnAjout(){
        supprimerBtnAjout();
        lesBtnAjout.removeAll(lesBtnAjout);
        Integer[][] btnAjout = mj.getLePlateau().getCaseajout();
        for(int i=0;i<mj.getLePlateau().getTaillePlateau();i++){
            for(int j=0;j<mj.getLePlateau().getTaillePlateau();j++){
                if(btnAjout[i][j]==1) {
                    lesBtnAjout.add(new BtnAjout(i, j, context, mj,this));
                }
            }
        }
    }

    public void afficherBtnAjout(Pion unPion,Joueur unJoueur){
        for(BtnAjout unBtnAjout:lesBtnAjout){
            unBtnAjout.affiche(unPion,unJoueur);
        }
    }

    public void supprimerBtnAjout(){
        for(BtnAjout unBtnAjout:lesBtnAjout){
            unBtnAjout.effacer();
        }
    }

    public void suppresionBtnDeplacement(PionInterface pionNoSuppBtn){
        for(PionInterface unPion:lesPions){
            if(!unPion.equals(pionNoSuppBtn)){
                unPion.supprimerBtn();
            }
        }
    }

    public void setBloqueTourSuivant(boolean bloqueTourSuivant) {
        this.bloqueTourSuivant = bloqueTourSuivant;
    }

    public void setRotationDeplacement(boolean rotationDeplacement) {
        this.rotationDeplacement = rotationDeplacement;
    }
}