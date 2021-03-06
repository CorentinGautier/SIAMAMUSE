package corp.siam.siamamuse;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import corp.siam.siamamuse.Plateau.Activity_CreationPlateau;

public class MainActivity extends AppCompatActivity { //page d'acceuil

    public int largeurEcrant,hauteurEcrant;
    private Button btnJouer;
    private Button btnQuitter;
    private Button btnEditerPlateau;
    private Button btnCredit;
    private ImageView imageCadenas;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verificationBDDRemplie();
        btnEditerPlateau = (Button) findViewById(R.id.buttonEditplateau);
        btnJouer = (Button) findViewById(R.id.buttonJouer);
        btnQuitter = (Button) findViewById(R.id.buttonQuitter);
        btnCredit = (Button) findViewById(R.id.buttonCredit);
        imageCadenas = (ImageView) findViewById(R.id.imageCadenas);
        calculTailleEcrant();
        dispositionInterface();
    }

    public void dispositionInterface(){
        //coordX
        btnJouer.setX((int)(largeurEcrant*0.35));
        btnEditerPlateau.setX((int)(largeurEcrant*0.35));
        btnQuitter.setX((int)(largeurEcrant*0.35));
        btnCredit.setX((int)(largeurEcrant*0.70));
        imageCadenas.setX((int)(largeurEcrant*0.45));
        //CoordY
        btnJouer.setY((int)(hauteurEcrant*0.40));
        btnEditerPlateau.setY((int)(hauteurEcrant*0.55));
        btnQuitter.setY((int)(hauteurEcrant*0.70));
        btnCredit.setY((int)(hauteurEcrant*0.85));
        imageCadenas.setY((int)(hauteurEcrant*0.56));
        //Redimmensionnement
        ViewGroup.LayoutParams paramsButtonJouer = btnJouer.getLayoutParams();
        paramsButtonJouer.width = (int)(largeurEcrant*0.3);
        paramsButtonJouer.height = (int)(hauteurEcrant*0.1);
        ViewGroup.LayoutParams paramsButtonQuitter = btnQuitter.getLayoutParams();
        paramsButtonQuitter.width = (int)(largeurEcrant*0.3);
        paramsButtonQuitter.height = (int)(hauteurEcrant*0.1);
        ViewGroup.LayoutParams paramsButtonEditer = btnEditerPlateau.getLayoutParams();
        paramsButtonEditer.width = (int)(largeurEcrant*0.3);
        paramsButtonEditer.height = (int)(hauteurEcrant*0.1);
        ViewGroup.LayoutParams paramsButtonCredit = btnCredit.getLayoutParams();
        paramsButtonCredit.width = (int)(largeurEcrant*0.3);
        paramsButtonCredit.height = (int)(hauteurEcrant*0.1);
        ViewGroup.LayoutParams paramsImageCadenas = imageCadenas.getLayoutParams();
        paramsImageCadenas.width = (int)(largeurEcrant*0.1);
        paramsImageCadenas.height = (int)(hauteurEcrant*0.08);
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }

//    public void allerEditPlateau(View v){
//        Intent intent = new Intent(this, Activity_CreationPlateau.class); // l'activité où on est vers la prochaine
//        startActivity(intent);
//        this.finish() ;
//    }

    public void ActivityOptionJeu(View viewview){
        Intent intent2 = new Intent(this, Activity_OptionJeu.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent2);
        this.finish();
    }

    public void ActivityCredit(View viewview){
        Intent intent3 = new Intent(this, Credit.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent3);
        this.finish();
    }

  /*  public void ActivityChrono(View viewview){
        Intent intent2 = new Intent(this, chrono.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent2);
        this.finish();
    }
    */
    public void remplissageBDD(BaseDeDonne bdd){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = pref.edit();
      // on met les tables qu'on veut pre enregistrer

        edit.putString("VersionBdd",bdd.getDatabaseVersion()+"");
        edit.apply();

    }

    public void verificationBDDRemplie(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String versionBDD = pref.getString("VersionBdd","");
        BaseDeDonne bdd = new BaseDeDonne(this);
        if(!versionBDD.equals(bdd.getDatabaseVersion()+"")){
            remplissageBDD(bdd);
        }
    }

    public void QuitterLeJeu(View view) { //quitter l'activity actuel.
        this.finish();
    }
}
