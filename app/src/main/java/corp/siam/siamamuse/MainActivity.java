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

import corp.siam.siamamuse.Plateau.Activity_CreationPlateau;
import corp.siam.siamamuse.Tutoriel.Activity_Tutoriel;

public class MainActivity extends AppCompatActivity { //page d'acceuil

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    public void allerEditPlateau(View v){
        Intent intent = new Intent(this, Activity_CreationPlateau.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish() ;
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

    public void QuitterLeJeu(View view) { //quitter l'activity actuel.
        this.finish();
    }
}
