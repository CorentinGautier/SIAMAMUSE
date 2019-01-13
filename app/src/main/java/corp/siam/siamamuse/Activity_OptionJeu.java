package corp.siam.siamamuse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;

public class Activity_OptionJeu extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    private TextView textTitre;
    private TextView textNbrManche;
    private TextView textTimer;
    private Button btnRetour;
    private Button btnPlay;
    private ConstraintLayout fond;
    private int nbMancheGagnante,tempsTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_jeu);
        textTitre = (TextView) findViewById(R.id.titreOption);
        textNbrManche = (TextView) findViewById(R.id.textNbManche);
        textTimer = (TextView) findViewById(R.id.textTimer);
        btnRetour = (Button) findViewById(R.id.buttonRetourOption);
        btnPlay = (Button) findViewById(R.id.buttonPlay);
        fond = (ConstraintLayout) findViewById(R.id.constraintOptionPartie);
        nbMancheGagnante=1;
        tempsTimer=20;
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
        textTitre.setX((int)(largeurEcrant*0.3));
        btnRetour.setX((int)(largeurEcrant*0.05));
        btnPlay.setX((int)(largeurEcrant*0.75));
        textTimer.setX((int)(largeurEcrant*0.15));
        textNbrManche.setX((int)(largeurEcrant*0.15));

        //CoordY
        textTitre.setY((int)(hauteurEcrant*0.1));
        btnRetour.setY((int)(hauteurEcrant*0.88));
        btnPlay.setY((int)(hauteurEcrant*0.88));
        textNbrManche.setY((int)(hauteurEcrant*0.30));
        textTimer.setY((int)(hauteurEcrant*0.45));

        //Redimensionnement
        ViewGroup.LayoutParams paramsButtonPlay = btnPlay.getLayoutParams();
        paramsButtonPlay.width = (int)(largeurEcrant*0.2);
        paramsButtonPlay.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsButtonRetour = btnRetour.getLayoutParams();
        paramsButtonRetour.width = (int)(largeurEcrant*0.2);
        paramsButtonRetour.height = (int)(hauteurEcrant*0.07);

        ViewGroup.LayoutParams paramsTextNbManche = textNbrManche.getLayoutParams();
        paramsTextNbManche.width = (int)(largeurEcrant*0.70);
        ViewGroup.LayoutParams paramsTextTimer = textTimer.getLayoutParams();
        paramsTextTimer.width = (int)(largeurEcrant*0.70);
        ViewGroup.LayoutParams paramsTextTitre = textTitre.getLayoutParams();
        paramsTextTitre.width = (int)(largeurEcrant*0.4);
        paramsTextTitre.height = (int)(hauteurEcrant*0.30);

        textNbrManche.setText("Nombre de manche gagnant : "+nbMancheGagnante);
        textTimer.setText("Temps du timer : "+tempsTimer);
        btnNbManche();
        btnTempsTimer();
    }

    public void btnNbManche(){
        for(int i=1;i<5;i++){
            Button btnNbManche = new Button(this);
            btnNbManche.setY((int)(hauteurEcrant*0.34));
            btnNbManche.setX((int)(largeurEcrant*0.15+(i-1)*largeurEcrant*0.1));
            btnNbManche.setText(i+"");
            btnNbManche.setTextColor(Color.WHITE);
            btnNbManche.setBackgroundResource(R.drawable.coutonbouton);
            ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int)(largeurEcrant*0.07),(int)(largeurEcrant*0.07));
            btnNbManche.setLayoutParams(paramsBtn);
            final int finalI = i;
            btnNbManche.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionNbManche(finalI);
                }
            });
            this.fond.addView(btnNbManche);
        }
    }

    public void actionNbManche(int i){
        nbMancheGagnante=i;
        textNbrManche.setText("Nombre de manche gagnant : "+nbMancheGagnante);
        this.fond.removeView(textNbrManche);
        this.fond.addView(textNbrManche);
    }
    public void btnTempsTimer(){
        ImageButton btntimeroff = new ImageButton(this);
        for(int i=0;i<4;i++){
            if(i==0){
                btntimeroff.setBackgroundResource(R.drawable.btntimeroff);
                btntimeroff.setY((int)(hauteurEcrant*0.5));
                btntimeroff.setX((int)(largeurEcrant*0.15+(i)*largeurEcrant*0.15));
                ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int)(largeurEcrant*0.12),(int)(largeurEcrant*0.07));
                btntimeroff.setLayoutParams(paramsBtn);
                btntimeroff.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionTimer(0,"Désactivé");
                    }
                });
                this.fond.addView(btntimeroff);
            }else {
                Button btnTimer = new Button(this);
                btnTimer.setY((int) (hauteurEcrant * 0.5));
                btnTimer.setX((int) (largeurEcrant * 0.15 + (i) * largeurEcrant * 0.15));
                btnTimer.setText(i * 10 + "s");
                btnTimer.setTextColor(Color.WHITE);
                btnTimer.setBackgroundResource(R.drawable.coutonbouton);
                ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int) (largeurEcrant * 0.12), (int) (largeurEcrant * 0.07));
                btnTimer.setLayoutParams(paramsBtn);
                final int finalI = i * 10;
                btnTimer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        actionTimer(finalI, "s");
                    }
                });
                this.fond.addView(btnTimer);
            }
        }
    }

    public void actionTimer(int i,String text){
        tempsTimer=i;
        if(i==0){
            textTimer.setText("Temps du timer : "  + text);
        }else {
            textTimer.setText("Temps du timer : " + tempsTimer + text);
        }
        this.fond.removeView(textTimer);
        this.fond.addView(textTimer);
    }

    public void resetNbVictoire(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("nbWinE", 0);
        editor.putInt("nbWinR", 0);
        editor.putInt("nbWinTotal",nbMancheGagnante);
        editor.putInt("tempsTimer",tempsTimer);
        editor.apply();
    }

    public void RetourMenu(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void LancerLeJeu(View v){
        resetNbVictoire();
        Intent intent = new Intent(this, Activity_Partie.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }
}
