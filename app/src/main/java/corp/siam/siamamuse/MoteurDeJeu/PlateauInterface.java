package corp.siam.siamamuse.MoteurDeJeu;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;

public class PlateauInterface {

    private int largeurEcrant, hauteurEcrant;
    private int tailleCase;
    private Activity_Partie context;

    public PlateauInterface(Activity_Partie context){
        largeurEcrant = context.largeurEcrant;
        hauteurEcrant = context.hauteurEcrant;
        tailleCase=(int)(largeurEcrant*0.2);
        this.context=context;
        Log.e("TEST","LOGIQUEMENT ");
    }

    public void convertionMatriceAffichage(Plateau lePlateau){
        Log.e("TEST","je suis passer par la ");
        Jeton[][] plateau = lePlateau.getPlateau();
        for(int i=1;i<lePlateau.taillePlateau;i++){
            for(int j=1;j<lePlateau.taillePlateau;j++){
                Log.e("TEST","mkkklsdmkdfskldslkm");
                if(plateau[i][j]instanceof Jeton){
                    afficheJeton(plateau[i][j],i-1,j-1);
                }
            }
        }
    }

    public void afficheJeton(Jeton unJeton,int x,int y){
        final ImageButton imagePion = unJeton.getImage();
        Log.e("TEST","non Rocher "+unJeton.toString());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        imagePion.setX((int)((tailleCase*x)+(tailleCase*0.1)));
        imagePion.setY((int)((hauteurEcrant*0.18)+(tailleCase*x)+(tailleCase*0.1)));
        context.runOnUiThread(new Runnable() {

            @Override
            public void run() {


                context.fondPartie.addView(imagePion);

            }
        });
    }
}
