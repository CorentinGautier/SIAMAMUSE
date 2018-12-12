package corp.siam.siamamuse.Plateau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity {

    ImageButton imagePion;
    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;
    Button affichePlateau;

    private int nbColone;
    private int nbLigne;


    RelativeLayout context;

    ImageView imgVide; // création d'un élément : ligne
    TableRow row; // création d'un élément : colone
    public static int largeurEcrant, hauteurEcrant;
    public static int tailleCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        context = findViewById(R.id.page);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);



        affichePlateau = (Button) findViewById(R.id.affichePlateau);
        affichePlateau.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
            public void onClick(View view) {
                if (view.getId() == R.id.affichePlateau) {
                    String nbColoneS = editTextNbColone.getText().toString();
                    Log.e("nbColone", nbColoneS);
                    nbColone = Integer.decode(nbColoneS);
                    String nbLigneS = editTextNbLigne.getText().toString();
                    Log.e("nbLigne", nbLigneS);
                    nbLigne = Integer.decode(nbLigneS);

                    calculTailleEcrant();
                    tailleCase = (int) (largeurEcrant * 0.2);


                    convertionMatriceAffichage(nbColone, nbLigne);
                }
            }
        });
    }


    public void convertionMatriceAffichage(int Nbcolonne, int Nbligne){

        Plateau unPlateau = new Plateau(nbColone, nbLigne);
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColone; j++) {
                imagePion = new ImageButton(this);
                imagePion.setBackgroundResource(R.drawable.rocher);
                ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
                imagePion.setLayoutParams(paramsPion);
                Log.e("taillecase",""+tailleCase);
                imagePion.setX((int)((tailleCase*i)+(tailleCase*0.1)));
                imagePion.setY((int)(10+(tailleCase*j)+(tailleCase*0.1)));
                context.addView(imagePion);
                Log.e("position", "vous etes au coordonnées de i = " + i + "et de j = " + j);
            }
        }
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }

    public void AjoutImage(){ // ajoute un rocher lors du click sur une des cases
        row.removeView(context);

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) 20, (int) 20);
        imgVide.setLayoutParams(params);

        imgVide.setBackgroundResource(R.drawable.rocher);
        row.addView(imgVide);
    }

    public void RetourMenu(View v){
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }


    public Button getAffichePlateau() {
        return affichePlateau;
    }

    public void setAffichePlateau(Button affichePlateau) {
        this.affichePlateau = affichePlateau;
    }

    public int getNbColone() {
        return nbColone;
    }

    public void setNbColone(int nbColone) {
        this.nbColone = nbColone;
    }

    public int getNbLigne() {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne) {
        this.nbLigne = nbLigne;
    }

    public Button getBtnEnvoi() {
        return btnEnvoi;
    }

    public void setBtnEnvoi(Button btnEnvoi) {
        this.btnEnvoi = btnEnvoi;
    }

    public static int getLargeurEcrant() {
        return largeurEcrant;
    }

    public static void setLargeurEcrant(int largeurEcrant) {
        Activity_CreationPlateau.largeurEcrant = largeurEcrant;
    }

    public static int getHauteurEcrant() {
        return hauteurEcrant;
    }

    public static void setHauteurEcrant(int hauteurEcrant) {
        Activity_CreationPlateau.hauteurEcrant = hauteurEcrant;
    }
}