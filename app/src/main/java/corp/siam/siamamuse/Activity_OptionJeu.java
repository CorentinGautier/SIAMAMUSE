package corp.siam.siamamuse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class Activity_OptionJeu extends AppCompatActivity {

    Spinner spinnerNbManche;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_jeu);

        //Liste déroulante de la taille de charactère.
        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinnerNbManche = (Spinner) findViewById(R.id.spinnerNbManche);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        ArrayList nbManche = new ArrayList();
        nbManche.add("2");
        nbManche.add("3");
        nbManche.add("4");
        nbManche.add("5");
		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, nbManche);
        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinnerNbManche.setAdapter(adapter);
    }

    public void RetourMenu(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void LancerLeJeu(View v){
        Intent intent = new Intent(this, Activity_Partie.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }
}
