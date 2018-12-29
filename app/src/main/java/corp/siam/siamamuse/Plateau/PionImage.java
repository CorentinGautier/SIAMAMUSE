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

    public static final int ETATCAILLOUX = 1 ; //etat de base ou on affiche tous les pions
    public static final int ETATROCHERS = 2; //ajoute ou tu veux les rochers
    public static final int ETATOUT = 3 ; //les cases que l'on enlve
    public static final int ETATDEPART =4; //ou on peut entré le pion au depart
    private int etat;
    //mode = mode choisi par l'utilsateur
    int mode = 0 ;//1 pour le mode out
    //mode 1 pour posser un rocher
    //mode 2 pour supprmer les cases
    //mode 3 pour possition les cases de départs

    public PionImage (Activity_CreationPlateau context, final int i, final int j, final Plateau unPlateau, int etat){
        PionImage unPionImage = new PionImage(context,i ,j, unPlateau, ETATOUT);
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
                setEtat(unPlateau,i, j);
            }
        });
        setEtat(unPlateau,i,j);
    }

    public void setEtat(final Plateau unplateau, final int i2, final int j2){
        if(mode == 0){
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeOut(unplateau,i2,j2);
                   // this.setEtatOut();
                }
            });
        }else if (mode == 1){ // met les case en rochers
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeCaseRocher(unplateau,i2,j2);
                    //this.setEtatRochers();
                }
            });
        }else if (mode == 2){ //surprimer la case
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    //modeOut(unplateau,i2,j2);
                    this.setEtatOut();
                    this.setEtatSuppr();
                }
            });
        }else if (mode == 3){ // met les case en mode départ
            imagePion.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
                public void onClick(View view) {
                    modeCaseDepart(unplateau,i2,j2);
                    this.setEtatOut();
                    this.setEtatDepart();

                }
            });
        }
    }


    public void setEtatCailloux(){
        this.imagePion.etat = ETATCAILLOUX;
    }
    public void setEtatRochers(){
        imagePion.etat = ETATROCHERS;
    }
    public void setEtatOut(){
        imagePion.etat = ETATOUT;
    }
    public void setEtatDepart(){
        imagePion.etat = ETATDEPART;
    }

    private void modeOut(Plateau unplateau,int i,int j) {
        unplateau.ajoutCaseOut(i,j);
    }
    private void modeCaseDepart(Plateau unplateau,int i,int j) {
        unplateau.ajoutCaseDepart(i,j);
    }
    private void modeCaseRocher(Plateau unplateau,int i,int j) {
        unplateau.ajoutRocher(i,j);
    }

}
