package corp.siam.siamamuse.Plateau;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.R;

public class PionImage {

    ImageButton imagePion;

    public static final int ETATCAILLOUX = 1 ;
    public static final int ETATROCHERS = 2;
    public static final int ETATOUT = 3 ;
    public static final int ETATENTREE =4;

    int mode = 0 ;

    public PionImage (Activity_CreationPlateau context,int i,int j,Plateau unplateau){

        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float largeurEcrant = metrics.widthPixels;
        float hauteurEcrant = metrics.heightPixels;

        float tailleCase = (int) (largeurEcrant * 0.2);

        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(R.drawable.rocher);
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        Log.e("taillecase",""+tailleCase);
        imagePion.setX((int)((tailleCase*i)+(tailleCase*0.1)));
        imagePion.setY((int)(10+(tailleCase*j)+(tailleCase*0.1)));
        context.layout.addView(imagePion);
        imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
            public void onClick(View view) {
            }
        });
        setEtat(unplateau,i,j);
    }
    public void setEtat(final Plateau unplateau, final int i2, final int j2){
        if(mode == ETATOUT){
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeOut(unplateau,i2,j2);
                }
            });
        }else if (mode == ETA)
    }

    private void modeOut(Plateau unplateau,int i,int j) {
        unplateau.ajoutCaseOut(i,j);
    }
    private void modeEntree(Plateau unplateau,int i,int j){
        unplateau.ajoutCaseDepart(i,j);
    }
}
