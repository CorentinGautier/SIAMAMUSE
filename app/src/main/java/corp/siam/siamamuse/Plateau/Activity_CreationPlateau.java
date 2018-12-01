package corp.siam.siamamuse.Plateau;

import android.database.MatrixCursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity implements View.OnClickListener {


    EditText editTextNbColone;
    EditText editTextNbLigne;
    Button btnEnvoi;
    Button btnAffichageMatrice;

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

        ///////////


        // données du tableau
        final String [] col1 = {"col1:ligne1","col1:ligne2","col1:ligne3","col1:ligne4","col1:ligne5"};
        final String [] col2 = {"col2:ligne1","col2:ligne2","col2:ligne3","col2:ligne4","col2:ligne5"};

        TableLayout table = (TableLayout) findViewById(R.id.idTable); // on prend le tableau défini dans le layout
        TableRow row; // création d'un élément : ligne
        TextView tv1,tv2; // création des cellules

// pour chaque ligne
        for(int i=0;i<col1.length;i++) {
            row = new TableRow(this); // création d'une nouvelle ligne
            final int id=i;
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // faites ici ce que vous voulez lors d'un clik
                    //AjoutImage();
                    //relier a la class rocher de glenn
                    Log.e("click sur case ","i="+id);
                }
            });

            tv1 = new TextView(this); // création cellule
            tv1.setText(col1[i]); // ajout du texte
            tv1.setGravity(Gravity.CENTER); // centrage dans la cellule
            // adaptation de la largeur de colonne à l'écran :
            tv1.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // idem 2ème cellule
            tv2 = new TextView(this);
            tv2.setText(col2[i]);
            tv2.setGravity(Gravity.CENTER);
            tv2.setLayoutParams( new TableRow.LayoutParams( 0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1 ) );

            // ajout des cellules à la ligne
            row.addView(tv1);
            row.addView(tv2);

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
