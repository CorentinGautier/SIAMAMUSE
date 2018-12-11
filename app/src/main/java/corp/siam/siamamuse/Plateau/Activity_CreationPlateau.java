package corp.siam.siamamuse.Plateau;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity {


    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;
    Button affichePlateau;

    private int nbColone;
    private int nbLigne;

    private ImageButton imageRocher;
    TableLayout context;

    ImageView imgVide; // création d'un élément : ligne
    TableRow row; // création d'un élément : colone
    int id;//id de la case créee

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);


        final TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout

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
                }
            }
        });
    }

   /* public void CreaPlateau(int nbLigne, int nbColone,TableLayout table) {

        if (nbColone != 0 && nbColone != 0 && nbColone< 6&& nbLigne< 6) {
            int[][] plateauView = new int[nbLigne][nbColone];
            Log.e("CreaPLateau", "nb colone = " + nbColone + "nb ligne = " + nbLigne);
            Log.e("CreaPLateau", "initialisation" + plateauView);
            // on remplis la matrice
            for (int i = 0; i < nbLigne; i++) {
                for (int j = 0; j < nbColone; j++) {
                   // plateauView[i][j] = 10;
                    Log.e("CreaPLateau", "rempli les case");
                }
            }
            // on affiche cette matrice sur l'activity
            for (int i = 0; i < nbLigne; i++) {
                Log.e("CreaPLateau", "pour toute les lignes debut");
                row = new TableRow(this); // création d'une nouvelle ligne
                id = i;
                row.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //lorsque click alors
                        AjoutImage();
                        Log.d("mydebug", "i=" + id);
                    }
                });
                for (int j = 0; j < nbColone; j++) {
                    Log.e("CreaPLateau", "pour toute les colones debut");
                    imgVide = new ImageView(this); // création cellule
                    String text = String.valueOf(plateauView[i][j]);

                    ViewGroup.LayoutParams paramas = new ViewGroup.LayoutParams(100, 100);
                    imgVide.setLayoutParams(paramas);
                    imgVide.setBackgroundResource(R.drawable.rhinoceros);
                    // .setText(text); // ajout du texte
                    Log.e("CreaPLateau", "pour toute les colones millieu");
                    //.setGravity(Gravity.CENTER); // centrage dans la cellule pour un texte
                    // adaptation de la largeur de colonne à l'écran :
                    imgVide.setLayoutParams(new TableRow.LayoutParams(200, 50, 50));

                    // ajout des cellules à la ligne
                    row.addView(imgVide);
                    Log.e("CreaPLateau", "pour toute les colones fin");
                }
                // ajout de la ligne au tableau
                Log.e("CreaPLateau", "pour toute les lignes fin");
                table.addView(row);
                Log.e("CreaPLateau", "affichage du plateau");
            }
        }
        else{
            Log.e("error","impossible de faire un tableau de 0 lignes et/ ou 0 colones");
        }
    }*/
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