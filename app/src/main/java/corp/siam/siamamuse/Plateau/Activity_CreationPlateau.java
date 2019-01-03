package corp.siam.siamamuse.Plateau;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import corp.siam.siamamuse.BaseDeDonne;
import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity {

    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button affichePlateau;
    private int nbColone;
    private int nbLigne;
    RelativeLayout layout;
    public static int largeurEcrant, hauteurEcrant;
    public static int tailleCase;
    private int etatActuel;
    CheckBox checkBoxOUT;
    CheckBox checkBoxPoserRocher;
    CheckBox checkBoxIN;
    CheckBox checkBoxVIDE;
    BaseDeDonne bdd ;
    private String etatActuelString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);
        layout = findViewById(R.id.page);
        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);
        checkBoxOUT = findViewById(R.id.checkBoxOUT) ;
        checkBoxPoserRocher= findViewById(R.id.checkBoxPoserUnRocher);
        checkBoxIN= findViewById(R.id.checkBoxIN);
        checkBoxVIDE= findViewById(R.id.checkBoxVIDE);
        affichePlateau = (Button) findViewById(R.id.affichePlateau);
        affichePlateau.setOnClickListener(new View.OnClickListener() { // Notre classe anonyme
            public void onClick(View view) {
                String nbColoneS = editTextNbColone.getText().toString();
                Log.e("nbColone", nbColoneS);
                nbColone = Integer.decode(nbColoneS.trim());
                String nbLigneS = editTextNbLigne.getText().toString();
                Log.e("nbLigne", nbLigneS);
                nbLigne = Integer.decode(nbLigneS.trim());
                calculTailleEcrant();
                tailleCase = (int) (largeurEcrant * 0.2);
                convertionMatriceAffichage(nbColone, nbLigne);
            }
        });
        bdd = new BaseDeDonne(this);
    }

    public void convertionMatriceAffichage(int Nbcolonne, int Nbligne){
        final Plateau unPlateau = new Plateau(nbColone, nbLigne);

        //bdd.creationPlateau(1,nbLigne,nbColone);
        for (int i = 0; i < nbLigne; i++) {
            for (int j = 0; j < nbColone; j++) {
                try {
                    choix(this.etatActuel);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PionImage   unPionImage = new PionImage( this ,nbColone,nbLigne, unPlateau, etatActuel);
               // bdd.creationCase(1,i,j,this.etatActuelString);
               // Log.e("LA_BDDD",""+bdd.toString());
            }
        }
    }

    public void choix(int etatActuel) throws InterruptedException {
        if(checkBoxOUT. isChecked ()){
            etatActuel = 0; // Out
            etatActuelString = "Out";
        }else if(checkBoxPoserRocher. isChecked ()){
            etatActuel = 1; // rocher
            etatActuelString = "Rocher";
        }else if(checkBoxIN. isChecked ()){
            etatActuel=3; //IN
            etatActuelString = "In";
        }else if(checkBoxVIDE. isChecked ()){
            etatActuel=2; //VIDE
            etatActuelString = "Vide";
        }else{
            fenetrePopUp();
        }
    }

    public void fenetrePopUp()throws InterruptedException {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("action");
        alertDialog.setMessage("Veuillez cocher une une checkBox lorsque vous poser un pion !");

        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        alertDialog.show();
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
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

}