package corp.siam.siamamuse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_PageDeVictoire extends AppCompatActivity {

    private boolean victoireElephant = true;
    private TextView affichageVictoire;
    private TextView nbVictoireElephant;
    private TextView nbVictoireRhinoceros;

    private int nbVictoireE;
    private int nbVictoireR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_victoire);
        affichageVictoire = findViewById(R.id.affichageVictoire);
        nbVictoireElephant = findViewById(R.id.nbVictoireElep);
        nbVictoireRhinoceros = findViewById(R.id.nbVictoireRhino);

        recupererNbWin();

        afficherTexteVictoire();
        actualiserNbVictoire();
    }

    public void afficherTexteVictoire() {
        if (victoireElephant)
            affichageVictoire.setText(R.string.annonceVictoireElephant);
        else
            affichageVictoire.setText(R.string.annonceVictoireRhinoceros);
    }

    public void actualiserNbVictoire(){
        if(victoireElephant) {
            nbVictoireE++;
            nbVictoireElephant.setText("Nombre de Victoire des Elephants : " + nbVictoireE);
        }else{
            nbVictoireR++;
            nbVictoireRhinoceros.setText("Nombre de Victoire des Rhinoceros : " + nbVictoireR);
        }
        sauvegarderNbWin();
    }
    public void sauvegarderNbWin(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nbWinE", nbVictoireE);
        editor.putInt("nbWinR", nbVictoireR);
        editor.apply();
    }

    public void recupererNbWin(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        nbVictoireE = preferences.getInt("nbWinE", 0);
        nbVictoireR = preferences.getInt("nbWinR", 0);
    }

    public void QuitterPageDeVictoire(View v) {
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void Revanche(View v) {
        Intent intent = new Intent(this, Activity_Partie.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    @Override
    protected void onStop() {
        sauvegarderNbWin();
        super.onStop();
    }

}
