package corp.siam.siamamuse.Plateau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import corp.siam.siamamuse.MoteurDeJeu.Jeton;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity implements View.OnClickListener {


    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;

    private int nbColone;
    private int nbLigne;

    private ImageButton imageRocher;
    TableLayout context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);
        btnEnvoi = (Button) findViewById(R.id.btnEnvoi);
        btnEnvoi.setOnClickListener(this);


        TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout
        TableRow row; // création d'un élément : colone
        TextView tv1; // création dd'un élément : ligne
        nbColone = 8;
        nbLigne = 12;
        int[][] plateauView = new int[nbLigne][nbColone];

        // on remplis la matrice
        for(int i=0;i<nbLigne;i++) {
            for(int j = 0; j<nbColone;j++){
                plateauView[i][j] = 165;
            }
        }
    // on affiche cette matrice sur l'activity
        for(int i=0;i<nbLigne;i++) {
            row = new TableRow(this); // création d'une nouvelle ligne
            for(int j = 0; j<nbColone;j++) {




                tv1 = new TextView(this); // création cellule
                String text = String.valueOf(plateauView[i][j]) ;
                tv1.setText(text); // ajout du texte
                tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
                // adaptation de la largeur de colonne à l'écran :
                tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));


                // ajout des cellules à la ligne
                row.addView(tv1);


            }
            // ajout de la ligne au tableau
            table.addView(row);
            }
        }

    public void AjoutImage(){
        ImageButton imageRocher = new ImageButton(this);
        imageRocher.setImageResource(R.drawable.rocher);
        context.addView(imageRocher);
    }


    public void onClick(View v) {
        String nbColoneS = editTextNbColone.getText().toString();
        Log.e("nbColone",nbColoneS);
        nbColone =  Integer.decode(nbColoneS);
        String nbLigneS = editTextNbLigne.getText().toString();
        Log.e("nbLigne", nbLigneS);
        nbLigne =  Integer.decode(nbLigneS);
    }


}
