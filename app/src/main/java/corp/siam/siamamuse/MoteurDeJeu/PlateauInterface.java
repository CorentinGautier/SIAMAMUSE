package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;

import corp.siam.siamamuse.Activity_Partie;

public class PlateauInterface {

    public static int largeurEcrant, hauteurEcrant;
    public static int tailleCase;
    public static int posHautGauchY;

    private Activity_Partie context;

    private ArrayList<JetonInterface> lesJetons = new ArrayList<>();
    public PlateauInterface(Activity_Partie context){
        largeurEcrant = context.largeurEcrant;
        hauteurEcrant = context.hauteurEcrant;
        tailleCase=(int)(largeurEcrant*0.2);
        posHautGauchY=(int)((hauteurEcrant-(tailleCase*5))/2);
        this.context=context;
    }

    public void convertionMatriceAffichage(Plateau lePlateau){
        Jeton[][] plateau = lePlateau.getPlateau();
        for(int i=1;i<lePlateau.taillePlateau+1;i++){
            for(int j=1;j<lePlateau.taillePlateau+1;j++){
                if(plateau[i][j]instanceof Jeton){
                    lesJetons.add(new JetonInterface(plateau[i][j],i-1,j-1,context));
                }
            }
        }
    }


}
