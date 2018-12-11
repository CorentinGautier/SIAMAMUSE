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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.MoteurDeJeu.Fleche;
import corp.siam.siamamuse.MoteurDeJeu.Jeton;
import corp.siam.siamamuse.MoteurDeJeu.MoteurJeu;
import corp.siam.siamamuse.MoteurDeJeu.Pion;
import corp.siam.siamamuse.MoteurDeJeu.PionInterface;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;
import corp.siam.siamamuse.MoteurDeJeu.Rocher;
import corp.siam.siamamuse.MoteurDeJeu.RocherInterface;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity {

    ImageButton imagePion;
    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;
    Button affichePlateau;
    public MoteurJeu mj;

    private int nbColone;
    private int nbLigne;

    private ArrayList<Pion> lesPion = new ArrayList<>();
    private ImageButton imageRocher;
    RelativeLayout context;

    ImageView imgVide; // création d'un élément : ligne
    TableRow row; // création d'un élément : colone
    int id;//id de la case créee
    public static int largeurEcrant, hauteurEcrant;
    public static int tailleCase;
    public static int posHautGauchY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);

        tailleCase = (int) (largeurEcrant * 0.2);

        final TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout

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
                    //CreaPlateau(nbColone, nbLigne, table);
                }
            }
        });

    }


    public void convertionMatriceAffichage(int Nbcolonne, int Nbligne){
        Jeton[][] plateau = mj.getLePlateau().getPlateau();
        for(int i=1;i<nbColone;i++){
            for(int j=1;j<Nbligne;j++){
                imagePion = new ImageButton(this);
                imagePion.setBackgroundResource(R.drawable.rocher);
                ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(tailleCase*0.8),(int)(tailleCase*0.8));
                imagePion.setLayoutParams(paramsPion);
                imagePion.setX((int)((PlateauInterface.tailleCase*x)+(PlateauInterface.tailleCase*0.1)));
                imagePion.setY((int)(PlateauInterface.posHautGauchY+(PlateauInterface.tailleCase*y)+(PlateauInterface.tailleCase*0.1)));
                context.addView(imagePion);

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
}