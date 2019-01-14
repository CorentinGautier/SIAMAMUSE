package corp.siam.siamamuse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import corp.siam.siamamuse.Tutoriel.Activity_Tutoriel;

public class Activity_OptionJeu extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    private TextView textTitre;
    private TextView textNbrManche;
    private TextView textTimer;
    private Button btnRetour;
    private Button btnPlay;
    private Button btnRegle;
    private Button btnTutoriel;
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
        btnRegle = (Button) findViewById(R.id.buttonRegle);
        btnTutoriel = (Button) findViewById(R.id.buttonTuto);
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
        textTitre.setX((int)(largeurEcrant*0.1));
        btnRetour.setX((int)(largeurEcrant*0.05));
        btnPlay.setX((int)(largeurEcrant*0.75));
        textTimer.setX((int)(largeurEcrant*0.07));
        textNbrManche.setX((int)(largeurEcrant*0.07));
        btnTutoriel.setX((int)(largeurEcrant*0.07));
        btnRegle.setX((int)(largeurEcrant*0.39));

        //CoordY
        textTitre.setY((int)(hauteurEcrant*0.1));
        btnRetour.setY((int)(hauteurEcrant*0.88));
        btnPlay.setY((int)(hauteurEcrant*0.88));
        textNbrManche.setY((int)(hauteurEcrant*0.30));
        textTimer.setY((int)(hauteurEcrant*0.47));
        btnTutoriel.setY((int)(hauteurEcrant*0.68));
        btnRegle.setY((int)(hauteurEcrant*0.68));

        //Redimensionnement
        ViewGroup.LayoutParams paramsButtonPlay = btnPlay.getLayoutParams();
        paramsButtonPlay.width = (int)(largeurEcrant*0.2);
        paramsButtonPlay.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsButtonRetour = btnRetour.getLayoutParams();
        paramsButtonRetour.width = (int)(largeurEcrant*0.2);
        paramsButtonRetour.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsButtonRegle = btnRegle.getLayoutParams();
        paramsButtonRegle.width = (int)(largeurEcrant*0.25);
        paramsButtonRegle.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsButtonTuto = btnTutoriel.getLayoutParams();
        paramsButtonTuto.width = (int)(largeurEcrant*0.25);
        paramsButtonTuto.height = (int)(hauteurEcrant*0.07);

        ViewGroup.LayoutParams paramsTextNbManche = textNbrManche.getLayoutParams();
        paramsTextNbManche.width = (int)(largeurEcrant);
        ViewGroup.LayoutParams paramsTextTimer = textTimer.getLayoutParams();
        paramsTextTimer.width = (int)(largeurEcrant*0.70);
        ViewGroup.LayoutParams paramsTextTitre = textTitre.getLayoutParams();
        paramsTextTitre.width = (int)(largeurEcrant*0.8);
        paramsTextTitre.height = (int)(hauteurEcrant*0.30);

        textNbrManche.setText("Nombre de manches gagnantes : "+nbMancheGagnante);
        textTimer.setText("Temps du timer : "+tempsTimer);
        btnNbManche();
        btnTempsTimer();
    }

    public void btnNbManche(){
        for(int i=1;i<5;i++){
            Button btnNbManche = new Button(this);
            btnNbManche.setY((int)(hauteurEcrant*0.36));
            btnNbManche.setX((int)(largeurEcrant*0.07+(i-1)*largeurEcrant*0.12));
            btnNbManche.setText(i+"");
            btnNbManche.setTextColor(Color.WHITE);
            btnNbManche.setBackgroundResource(R.drawable.coutonbouton);
            ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int)(largeurEcrant*0.1),(int)(largeurEcrant*0.1));
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
        textNbrManche.setText("Nombre de manches gagnantes : "+nbMancheGagnante);
        this.fond.removeView(textNbrManche);
        this.fond.addView(textNbrManche);
    }
    public void btnTempsTimer(){
        ImageButton btntimeroff = new ImageButton(this);
        for(int i=0;i<4;i++){
            if(i==0){
                btntimeroff.setBackgroundResource(R.drawable.btntimeroff);
                btntimeroff.setY((int)(hauteurEcrant*0.53));
                btntimeroff.setX((int)(largeurEcrant*0.07+(i)*largeurEcrant*0.17));
                ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int)(largeurEcrant*0.15),(int)(largeurEcrant*0.1));
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
                btnTimer.setY((int) (hauteurEcrant * 0.53));
                btnTimer.setX((int) (largeurEcrant * 0.07 + (i) * largeurEcrant * 0.17));
                btnTimer.setText(i * 10 + "s");
                btnTimer.setTextColor(Color.WHITE);
                btnTimer.setBackgroundResource(R.drawable.coutonbouton);
                ViewGroup.LayoutParams paramsBtn = new ViewGroup.LayoutParams((int) (largeurEcrant * 0.15), (int) (largeurEcrant * 0.1));
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

    public void allerAuTuto(View v){
        Intent intent = new Intent(this, Activity_Tutoriel.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish();
    }
    public void allerAuRegle(View v){
        String url = "https://drive.google.com/open?id=1YCtlhaxEdgUYlvHKFrAvq70c7j-LzL5j";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
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
