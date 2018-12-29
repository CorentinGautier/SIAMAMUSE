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

    public static final int ETATIN = 1 ; //Etat ou on peut entré ses pion au depart
    public static final int ETATROCHERS = 2; //ajoute ou tu veux les rochers
    public static final int ETATOUT = 3 ; //les cases que l'on enlve

    private int etatActuel;
    //mode = mode choisi par l'utilsateur
    int mode = 0 ;// pour le mode out
    //mode 1 pour posser un rocher
    //mode 3 pour position les cases de départs

    public PionImage (Activity_CreationPlateau context, final int nbLigne, final int nbColone, final Plateau unPlateau, int etatActuel){

        //taille de l'écran pour bien dimentionner le tout
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        float largeurEcrant = metrics.widthPixels;
        float hauteurEcrant = metrics.heightPixels;
        float tailleCase = (int) (largeurEcrant * 0.2);

        //donne une image au pion et permet de cliker desssus
        imagePion = new ImageButton(context);
        imagePion.setBackgroundResource(R.drawable.rocher);
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
        imagePion.setLayoutParams(paramsPion);
        Log.e("taillecase",""+tailleCase);
        imagePion.setX((int)((tailleCase*nbLigne)+(tailleCase*0.1)));
        imagePion.setY((int)(10+(tailleCase*nbColone)+(tailleCase*0.1)));
        context.layout.addView(imagePion);
        imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
            public void onClick(View view) {
                setEtat(unPlateau,nbLigne, nbColone);
            }
        });
    }

    public void setEtat(final Plateau unplateau, final int i2, final int j2){
        if(mode == 0){
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeOut(unplateau,i2,j2);
                  setEtat("Out");
                }
            });
        }else if (mode == 1){ // met les case en rochers
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeCaseRocher(unplateau,i2,j2);
                   setEtat("Rocher");
                }
            });
       /* }else if (mode == 2){ //surprimer la case
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeSupprCase(unplateau,i2,j2);
                    this.setEtat("Out");
                }
            });*/
        }else if (mode == 3){ // met les case en mode départ
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeCaseDepart(unplateau,i2,j2);
                    setEtat("In");
                }
            });
        }
    }

    public void setEtat(String etat){
        if (etat == "Out") {
            etatActuel = ETATOUT;
        }else if (etat == "Rocher") {
            etatActuel = ETATROCHERS;
        }else if (etat== "In"){
            etatActuel = ETATIN;
        }
    }

    private void modeOut(Plateau unplateau,int nbLignes,int nbColones) {
        unplateau.ajoutCaseOut(nbLignes,nbColones);
    }
    private void modeCaseDepart(Plateau unplateau,int nbLignes,int nbColones) {
        unplateau.ajoutCaseDepart(nbLignes,nbColones);
    }
    private void modeCaseRocher(Plateau unplateau,int nbLignes,int nbColones) {
        unplateau.ajoutRocher(nbLignes,nbColones);
    }

}
