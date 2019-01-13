package corp.siam.siamamuse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Activity_PageDeVictoire extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    private boolean victoireElephant = true;
    private ConstraintLayout fond;
    private TextView affichageVictoire;
    private TextView nbVictoireElephant;
    private TextView nbVictoireRhinoceros;

    private Button btnRevanche;
    private Button btnMenu;

    private int nbVictoireE;
    private int nbVictoireR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_victoire);
        affichageVictoire = findViewById(R.id.affichageVictoire);
        nbVictoireElephant = findViewById(R.id.nbVictoireElep);
        nbVictoireRhinoceros = findViewById(R.id.nbVictoireRhino);
        btnMenu = findViewById(R.id.buttonMenuFin);
        btnRevanche = findViewById(R.id.buttonRevanche);
        fond = findViewById(R.id.constraintFinPartie);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String winner = preferences.getString("Winner", "");
        if(winner.equals("elephant")){
            victoireElephant=true;
        }else{
            victoireElephant=false;
        }
        recupererNbWin();
        afficherTexteVictoire();
        actualiserNbVictoire();
        calculTailleEcrant();
        dispositionInterface();
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }


    public void dispositionInterface(){
        //CoordX
        affichageVictoire.setX((int)(largeurEcrant*0.1));
        nbVictoireElephant.setX((int)(largeurEcrant*0.15));
        nbVictoireRhinoceros.setX((int)(largeurEcrant*0.15));
        btnRevanche.setX((int)(largeurEcrant*0.05));
        btnMenu.setX((int)(largeurEcrant*0.75));

        //CoordY
        affichageVictoire.setY((int)(hauteurEcrant*0.1));
        nbVictoireElephant.setY((int)(hauteurEcrant*0.4));
        nbVictoireRhinoceros.setY((int)(hauteurEcrant*0.5));
        btnRevanche.setY((int)(hauteurEcrant*0.88));
        btnMenu.setY((int)(hauteurEcrant*0.88));

        //Redimensionnement
        ViewGroup.LayoutParams paramsTextTitre = affichageVictoire.getLayoutParams();
        paramsTextTitre.width = (int)(largeurEcrant*0.8);
        paramsTextTitre.height = (int)(hauteurEcrant*0.3);

        ViewGroup.LayoutParams paramsButtonRevanche = btnRevanche.getLayoutParams();
        paramsButtonRevanche.width = (int)(largeurEcrant*0.2);
        paramsButtonRevanche.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsButtonMenu = btnMenu.getLayoutParams();
        paramsButtonMenu.width = (int)(largeurEcrant*0.2);
        paramsButtonMenu.height = (int)(hauteurEcrant*0.07);
    }

    public void afficherTexteVictoire() {
        if (victoireElephant)
            affichageVictoire.setText(R.string.annonceVictoireElephant);
        else
            affichageVictoire.setText(R.string.annonceVictoireRhinoceros);
    }

    public void actualiserNbVictoire(){
        if(victoireElephant) {
            this.nbVictoireE++;
        }else{
            this.nbVictoireR++;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int nbVictoireGagnant = preferences.getInt("nbWinTotal", 0);

        nbVictoireElephant.setText("Victoire des Elephants : " + nbVictoireE);
        nbVictoireRhinoceros.setText("Victoire des Rhinoceros : " + nbVictoireR);
        sauvegarderNbWin();
        if(nbVictoireE==nbVictoireGagnant){
            gagnantFinal();
        }
        if(nbVictoireR==nbVictoireGagnant){
            gagnantFinal();
        }
    }

    public void gagnantFinal(){
        btnRevanche.setText("");
        btnRevanche.setBackgroundColor(Color.TRANSPARENT);
        nbVictoireElephant.setText("");
        nbVictoireRhinoceros.setText("");
        affichageVictoire.setY((int)(hauteurEcrant*0.55));
        affichageVictoire.setTextSize(50);
        fond.removeView(affichageVictoire);
        fond.addView(affichageVictoire);

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
