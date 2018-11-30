package corp.siam.siamamuse.Plateau;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import corp.siam.siamamuse.R;

public class Activity_CreationPlateau extends AppCompatActivity {


    EditText editTextNbColone;
    EditText editTextNbLigne;

    private static int nbColone;
    private static int nbLigne;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__creation_plateau);

        editTextNbColone = findViewById(R.id.editTextNbColone);
        editTextNbLigne = findViewById(R.id.editTextNbLigne);

    }


    public void onClick(View v) {
String nbColoneS = editTextNbColone.getText().toString();
Log.e("nbColone",nbColoneS);
nbColone =  Integer.decode(nbColoneS);
String nbLigneS = editTextNbLigne.getText().toString();
Log.e("nbLigne", nbLigneS);
        nbLigne =  Integer.decode(nbLigneS);

    }

    public static int getNbColone() {
        return nbColone;
    }

    public void setNbColone(int nbColone) {
        this.nbColone = nbColone;
    }

    public static int getNbLigne() {
        return nbLigne;
    }

    public void setNbLigne(int nbLigne) {
        this.nbLigne = nbLigne;
    }
}
