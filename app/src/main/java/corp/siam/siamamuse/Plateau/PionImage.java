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
    private int etatActuel;
    //mode = mode choisi par l'utilsateur
    int mode = 0 ;// pour le mode out
    //mode 2 pour vide
    //mode 1 pour poser un rocher
    //mode 3 pour In

    public PionImage (Activity_CreationPlateau context, final int nbLigne, final int nbColone, final Plateau unPlateau, int etatActuel){

        //taille de l'écran pour bien dimentionner le tout
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float largeurEcrant = metrics.widthPixels;
        float hauteurEcrant = metrics.heightPixels;
        float tailleCase = (int) (largeurEcrant * 0.2);

        //donne une image au pion et permet de cliker desssus
        imagePion = new ImageButton(context);
        if(context.checkBoxOUT. isChecked ()){
            etatActuel = 0; // Out
        }else if(context.checkBoxPoserRocher. isChecked ()){
            etatActuel = 1; // rocher
        }else if(context.checkBoxIN. isChecked ()){
            etatActuel=3; //IN
        }else if(context.checkBoxVIDE. isChecked ()){
            etatActuel=2; //vide
        }else{
            try {
                context.fenetrePopUp();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(etatActuel == 1){
            imagePion.setBackgroundResource(R.drawable.rocher);
        }else if (etatActuel == 3){
            imagePion.setBackgroundResource(R.drawable.in);
        }else if (etatActuel == 2){
            imagePion.setBackgroundResource(R.drawable.vide);
        }else {
            imagePion.setBackgroundResource(R.drawable.out);
        }

        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        Log.e("taillecase",""+tailleCase);
        imagePion.setX((int)((tailleCase*nbLigne)+(tailleCase*0.1)));
        imagePion.setY((int)(10+(tailleCase*nbColone)+(tailleCase*0.1)));
        context.layout.addView(imagePion);
        imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
            public void onClick(View view) {

                            }
        });
    }
}
