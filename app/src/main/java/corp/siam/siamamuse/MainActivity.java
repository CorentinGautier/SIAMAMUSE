package corp.siam.siamamuse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import corp.siam.siamamuse.Tutoriel.Activity_Tutoriel;

public class MainActivity extends AppCompatActivity { //page d'acceuil
    Spinner spinnerOption;
    Button Tuto;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //liste deroulante
        //Liste déroulante de la taille de charactère.
        //Récupération du Spinner déclaré dans le fichier main.xml de res/layout
        spinnerOption = (Spinner) findViewById(R.id.spinnerTailleCarac);
        //Création d'une liste d'élément à mettre dans le Spinner(pour l'exemple)
        ArrayList listBouton = new ArrayList();
        listBouton.add("tuto");
        listBouton.add("Option");
        /*Le Spinner a besoin d'un adapter pour sa presentation alors on lui passe le context(this) et
                un fichier de presentation par défaut( android.R.layout.simple_spinner_item)
		Avec la liste des elements (exemple) */
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listBouton);
        /* On definit une présentation du spinner quand il est déroulé         (android.R.layout.simple_spinner_dropdown_item) */
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Enfin on passe l'adapter au Spinner et c'est tout
        spinnerOption.setAdapter(adapter);
      //  spinnerOption.callOnClick(allerAuTuto());

    }

    public void ActivityOption(View v){
        Intent intent = new Intent(this, Activity_Option.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void ActivityOptionJeu(View viewview){
        Intent intent2 = new Intent(this, Activity_OptionJeu.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent2);
        this.finish();
    }
    public void allerAuTuto(){
        Intent intent = new Intent(this, Activity_Tutoriel.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish();
    }

    public void QuitterLeJeu(View view) { //quitter l'activity actuel.
        this.finish();
    }
}
