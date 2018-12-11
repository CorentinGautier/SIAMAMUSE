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
import android.widget.TableLayout;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.MoteurDeJeu.Pion;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;
import corp.siam.siamamuse.R;

import static corp.siam.siamamuse.MoteurDeJeu.PlateauInterface.tailleCase;

public class Activity_CreationPlateau extends AppCompatActivity {


    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;


    Button affichePlateau;

    private int nbColone;
    private int nbLigne;

    private ImageButton imageRocher;
    TableLayout context;

    ImageButton imgRocher; // création d'un élément : ligne
    int id;//id de la case créee
    public int largeurEcrant;
    private int hauteurEcrant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);

          calculTailleEcrant();
       // final TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout

       affichePlateau = (Button)findViewById(R.id.affichePlateau);
        affichePlateau.setOnClickListener(new View.OnClickListener(){ // Notre classe anonyme
            public void onClick(View view){
                if (view.getId()==R.id.affichePlateau){
                    String nbColoneS = editTextNbColone.getText().toString();
                    Log.e("nbColone",nbColoneS);
                    nbColone =  Integer.decode(nbColoneS);
                    String nbLigneS = editTextNbLigne.getText().toString();
                    Log.e("nbLigne", nbLigneS);
                    nbLigne =  Integer.decode(nbLigneS);
                    //CreaPlateau(nbColone, nbLigne, table);
                    Plateau unPlateau = new Plateau(nbColone, nbLigne);
                    try {
                        PlateauInterface unPlateauInterface = new PlateauInterface(Activity_CreationPlateau context);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    }
                    for(int i = 0; i<nbLigne; i++){
                        for(int j= 0; j<nbColone; j++){
                            unPlateau.ajoutRocher(nbColone, nbLigne);

                            Log.e("position", "vous etes au coordonnées de i = "+i+"et de j = "+j);
                            //AjoutImage();
                        }
                    }
                }
            }
        });

    }

public void AffichagePlateau(){
    largeurEcrant = this.getLargeurEcrant();
    hauteurEcrant = this.getHauteurEcrant();
    tailleCase=(int)(largeurEcrant*0.2);
    this.context=context;
    conversionMatriceAffichage();
}


    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }


    public void AjoutImage(){ // ajoute un rocher lors du click sur une des cases

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) 20, (int) 20);
        imgRocher.setLayoutParams(params);
        imgRocher.setBackgroundResource(R.drawable.rocher);
        context.addView(imgRocher);
    }

    public void RetourMenu(View v){
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLargeurEcrant() {
        return largeurEcrant;
    }

    public void setLargeurEcrant(int largeurEcrant) {
        this.largeurEcrant = largeurEcrant;
    }

    public int getHauteurEcrant() {
        return hauteurEcrant;
    }

    public void setHauteurEcrant(int hauteurEcrant) {
        this.hauteurEcrant = hauteurEcrant;
    }
}