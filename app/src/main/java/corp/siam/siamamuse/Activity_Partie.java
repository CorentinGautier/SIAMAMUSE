package corp.siam.siamamuse;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import org.xml.sax.SAXException;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.xml.parsers.ParserConfigurationException;
import corp.siam.siamamuse.MoteurDeJeu.MoteurJeu;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;

public class Activity_Partie extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    public RelativeLayout fondPartie;
    Thread Compteur ;
    private Button CountDownTexte;
    private final String PROGRESS="textViewTestId";
    //L'AtomicBoolean qui gère la destruction de la Thread de background
    AtomicBoolean isRunning = new AtomicBoolean(false);
    //L'AtomicBoolean qui gère la mise en pause de la Thread de background
    AtomicBoolean isPausing = new AtomicBoolean(false);
    private int tempsParJoueur =20; // temps pour chaque tour des joueurs

    private MoteurJeu mj;
    private PlateauInterface plateauInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);
        calculTailleEcrant();
        CountDownTexte = findViewById(R.id.CountDown_Texte);
        fondPartie = (RelativeLayout) findViewById(R.id.pageJeu);
        try {
            creationPlateau();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }
    public void FinDeLaPartie(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void pageVictoire(){
        Intent intent = new Intent(this, Activity_PageDeVictoire.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void creationPlateau() throws IOException, SAXException, ParserConfigurationException {
        mj = new MoteurJeu(5,this);
        plateauInterface= new PlateauInterface(this,mj);
        plateauInterface.convertionMatriceAffichage();
    }

    public void timerFin(){
        Log.e("TEST","joueur suivant");
        mj.setPionRotation(null);
        mj.tourSuivant();
        plateauInterface.convertionMatriceAffichage();
        //appel onResume pour relancer le thread avec le timer
    }


    //Méthode appelée quand l'activité s'arrête
    public void onStop() {
        Log.e("TEST","je relance le chrono");
        super.onStop();
        //Mise-à-jour du booléen pour détruire la Thread de background
        isRunning.set(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Mise-à-jour du booléen pour mettre en pause la Thread de background
        isPausing.set(true);
    }

    @Override
    public void onResume(){
        super.onResume();
        // Mise-à-jour du booléen pour relancer la Thread de background
        isPausing.set(false);
    }

    public void onStart() {//démarage du thread
        super.onStart();
       Compteur = new Thread( new Runnable() {
            Message myMessage;
            Bundle messageBundle = new Bundle();
            public void run() {
                try {
                    int s = tempsParJoueur;
                    while (s > 0 && isRunning.get()) {
                        while (isPausing.get() && (isRunning.get())) {
                            // Faire une pause pour soulagé le CPU (dépend du traitement)
                            Thread.sleep(2000);
                        }
                        s--;
                        Thread.sleep(1000);
                        myMessage = handler.obtainMessage(); // otenir un message vierge
                        //Ajouter des données à transmettre au Handler via le Bundle
                        messageBundle.putInt(PROGRESS, s);
                        //Ajouter le Bundle au message
                        myMessage.setData(messageBundle);
                        //Envoyer le message
                        handler.sendMessage(myMessage);
                    }
                }
                catch(Throwable t) {
                }
            }
        });
        isRunning.set(true);
        isPausing.set(false);
        Compteur.start();
    }
    //Permet de faire foctionner le timer en meme temps que le reste de l'application
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // Incrémenter la ProgressBar, on est bien dans la Thread de l'IHM
            int nbS=msg.getData().getInt(PROGRESS);
            Log.e("TEST","temps restant = "+nbS+"s");
            CountDownTexte.setText(nbS+"s");
            // On peut faire toute action qui met à jour l'IHM
            if ( nbS == 0)
            {
                timerFin();
            }
        }
    };
}
