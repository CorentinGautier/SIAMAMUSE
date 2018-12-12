package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import corp.siam.siamamuse.Activity_Partie;

public class PlateauInterface {

    public static Calculateur calc;

    public static Activity_Partie context;
    public  MoteurJeu mj;

    public ArrayList<PionInterface> lesPions = new ArrayList<>();
    public ArrayList<RocherInterface> lesRochers = new ArrayList<>();

    public ArrayList<BtnAjout> lesBtnAjout = new ArrayList<>();
    public ArrayList<PionMain> lesPionsMain = new ArrayList<>();

    public PlateauInterface(Activity_Partie context,MoteurJeu moteurJeu) throws IOException, SAXException, ParserConfigurationException {
        calc = new Calculateur(context.largeurEcrant,context.hauteurEcrant,moteurJeu);
        this.context=context;
        mj = moteurJeu;
        creationbtnAjout();
    }

    public void convertionMatriceAffichage(){
        Jeton[][] plateau = mj.getLePlateau().getPlateau();
        supprimerBtnAjout();
        suppressionJetons();
        affichagePion(mj.getJoueur1());
        affichagePion(mj.getJoueur2());
        for(int i=1;i<mj.getLePlateau().taillePlateau+1;i++){
            for(int j=1;j<mj.getLePlateau().taillePlateau+1;j++){
                if(plateau[i][j]instanceof Pion){
                    Pion pion= (Pion) plateau[i][j];
                    lesPions.add(new PionInterface(pion,i-1,j-1,context,mj,this));
                }else if(plateau[i][j]instanceof Rocher){
                    Rocher rocher = (Rocher) plateau[i][j];
                    lesRochers.add(new RocherInterface(rocher,i-1,j-1,context));
                }
            }
        }
    }

    //fonction qui a chaque deplacement ou ajout supprime tout les imageBtn de l'interface et vide les listes
    public void suppressionJetons(){
        for(PionInterface unPion : lesPions){
            context.fondPartie.removeView(unPion.getImagePion());
            //unPion.disparitionFleche();
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
    public void affichagePion(Joueur joueur){
        ArrayList<Pion> lesPionsJoueur = joueur.getLesPionsEnMain();
        int i=1;
        for (Pion unPion: lesPionsJoueur){
            lesPionsMain.add( new PionMain(unPion,context,i,joueur.getNom(),this,joueur));
            i++;
        }
    }

    //les btnajouts sont les carre vert qui permet de choisir ou ont veut placer notre pion sur le plateau
    public void creationbtnAjout(){
        Integer[][] btnAjout = mj.getLePlateau().getCaseajout();
        for(int i=0;i<mj.getLePlateau().taillePlateau;i++){
            for(int j=0;j<mj.getLePlateau().taillePlateau;j++){
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
}

