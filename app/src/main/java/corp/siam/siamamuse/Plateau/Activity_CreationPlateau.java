package corp.siam.siamamuse.Plateau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity implements View.OnClickListener {


    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;
    Button affichePlateau;

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

       final TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout

       affichePlateau = (Button)findViewById(R.id.affichePlateau);
       affichePlateau.setOnClickListener(new View.OnClickListener(){ // Notre classe anonyme
           public void onClick(View view){ // et sa méthode !
               if (view.getId()==R.id.affichePlateau){
                   CreaPlateau(nbColone, nbLigne, table);
               }
           }
       });

    }
    public void CreaPlateau(int nbLigne, int nbColone,TableLayout table){

        TableRow row; // création d'un élément : colone
        TextView tv1; // création d'un élément : ligne
        final int[][] plateauView = new int[nbLigne][nbColone];
        Log.e("CreaPLateau","nb colone = "+nbColone+"nb ligne = "+nbLigne);
        Log.e("CreaPLateau","initialisation"+plateauView);
        // on remplis la matrice
        for(int i=0;i<nbLigne;i++) {
            for(int j = 0; j<nbColone;j++){
               plateauView[i][j] = Integer.parseInt("rien");
                Log.e("CreaPLateau","rempli les case");
            }
        }
        // on affiche cette matrice sur l'activity
        for(int i=0;i<nbLigne;i++) {
            Log.e("CreaPLateau","pour toute les lignes debut");
            row = new TableRow(this); // création d'une nouvelle ligne
            final int id=i;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) { //lorsque click alors

                    //this.plateauView[x][j]="rocher";
                    Log.d("mydebug","i="+id);
                }
            });
            for(int j = 0; j<nbColone;j++) {
                Log.e("CreaPLateau","pour toute les colones debut");
                tv1 = new TextView(this); // création cellule
                String text = String.valueOf(plateauView[i][j]) ;
                tv1.setText(text); // ajout du texte
                Log.e("CreaPLateau","pour toute les colones millieu");
                tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
                // adaptation de la largeur de colonne à l'écran :
                tv1.setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));


                // ajout des cellules à la ligne
                row.addView(tv1);
                Log.e("CreaPLateau","pour toute les colones fin");
            }
            // ajout de la ligne au tableau
            Log.e("CreaPLateau","pour toute les lignes fin");
            table.addView(row);
            Log.e("CreaPLateau","affichage du plateau");
        }
    }
    public void AjoutImage(){ // ajoute un rocher lors du click sur une des cases
        ImageButton imageRocher = new ImageButton(this);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams((int) 20, (int) 20);
        imageRocher.setLayoutParams(params);
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