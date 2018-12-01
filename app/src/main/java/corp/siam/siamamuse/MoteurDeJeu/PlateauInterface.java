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

    public static int largeurEcrant, hauteurEcrant;
    public static int tailleCase;
    public static int posHautGauchY;

    public static Activity_Partie context;
    public  MoteurJeu mj;
    public  Plateau lePlateau;

    public  ArrayList<PionInterface> lesPions = new ArrayList<>();

    public PlateauInterface(Activity_Partie context,MoteurJeu moteurJeu) throws IOException, SAXException, ParserConfigurationException {
        largeurEcrant = context.largeurEcrant;
        hauteurEcrant = context.hauteurEcrant;
        tailleCase=(int)(largeurEcrant*0.2);
        posHautGauchY=(int)((hauteurEcrant-(tailleCase*5))/2);
        this.context=context;
        lePlateau = new Plateau(5);
        mj = moteurJeu;
    }

    public void convertionMatriceAffichage(){
        Jeton[][] plateau = mj.getLePlateau().getPlateau();
        for(int i=1;i<lePlateau.taillePlateau+1;i++){
            for(int j=1;j<lePlateau.taillePlateau+1;j++){
                if(plateau[i][j]instanceof Pion){
                    Log.e("TEST","ajoue pion");
                    Pion pion= (Pion) plateau[i][j];
                    lesPions.add(new PionInterface(pion,i-1,j-1,context,mj,this));
                }else{
                    Log.e("TEST",i+ "  "+j+ " hjklm");
                }
            }
        }
    }



}
