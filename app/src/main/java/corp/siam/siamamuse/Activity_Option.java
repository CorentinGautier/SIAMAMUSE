package corp.siam.siamamuse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import corp.siam.siamamuse.Tutoriel.Activity_Tutoriel;

public class Activity_Option extends AppCompatActivity {

    Spinner spinnerTailleCarac;
        @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);

        //Liste déroulante de la taille de charactère.
        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinnerTailleCarac = (Spinner) findViewById(R.id.spinnerTailleCarac);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        ArrayList tailleCarac = new ArrayList();
        tailleCarac.add("12");
        tailleCarac.add("13");
        tailleCarac.add("14");
        tailleCarac.add("15");
		/*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tailleCarac);
        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinnerTailleCarac.setAdapter(adapter);
    }
    public void allerAuTuto(View v){
        Intent intent = new Intent(this, Activity_Tutoriel.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish();
         }
    public void RetourMenu(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish();
    }

}
