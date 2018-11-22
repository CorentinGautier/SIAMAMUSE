package corp.siam.siamamuse.MoteurDeJeu;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;

public class plateauInterface {

    private int largeurEcrant, hauteurEcrant;
    private int tailleCase;
    private Activity_Partie context;

    public plateauInterface(Activity_Partie context){
        largeurEcrant = context.largeurEcrant;
        hauteurEcrant = context.hauteurEcrant;
        tailleCase=(int)(largeurEcrant*0.2);
        this.context=context;
    }

    public void convertionMatriceAffichage(Plateau lePlateau){
        Jeton[][] plateau = lePlateau.getPlateau();
        for(int i=1;i<lePlateau.taillePlateau;i++){
            for(int j=1;j<lePlateau.taillePlateau;j++){
                if(plateau[i][j]instanceof Jeton){
                    afficheJeton(plateau[i][j],i,j);
                }
            }
        }
    }

    public void afficheJeton(Jeton unJeton,int x,int y){
        ImageButton imagePion = unJeton.getImage();
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        imagePion.setX((int)((tailleCase*x)+(tailleCase*0.1)));
        imagePion.setY((int)((hauteurEcrant*0.25)+(tailleCase*x)+(tailleCase*0.1)));
        context.fondPartie.addView(imagePion);
    }
}
