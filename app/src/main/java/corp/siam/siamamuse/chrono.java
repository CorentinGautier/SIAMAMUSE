package corp.siam.siamamuse;

import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import java.util.concurrent.atomic.AtomicBoolean;

import corp.siam.siamamuse.R;



public class chrono extends AppCompatActivity {

    //timer
    private final String PROGRESS="textViewTestId";
    private Button countDownButton;
    private CountDownTimer countDownTimer;
    private Button CountDownTexte;
    private long tempsRestant = 10; // 10 sec

    Thread Compteur ;

    //L'AtomicBoolean qui gère la destruction de la Thread de background
    AtomicBoolean isRunning = new AtomicBoolean(false);
    //     L'AtomicBoolean qui gère la mise en pause de la Thread de background
    AtomicBoolean isPausing = new AtomicBoolean(false);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono);

        CountDownTexte = findViewById(R.id.CountDown_Texte);

    }

    public void onStart() {//démarage du thread
        super.onStart();
        //Comteur a 0
        int compteur = 0;
        Compteur = new Thread( new Runnable() {
            Message myMessage;
            Bundle messageBundle = new Bundle();
            public void run() {
                try {
                    int s = 10;
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

    //Méthode appelée quand l'activité s'arrête
    public void onStop() {
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
    protected void onResume(){
        super.onResume();
        // Mise-à-jour du booléen pour relancer la Thread de background
        isPausing.set(false);
    }
    //Permet de faire foctionner le timer en meme temps que le reste de l'application
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int nbS=msg.getData().getInt(PROGRESS);
            // Incrémenter la ProgressBar, on est bien dans la Thread de l'IHM
            CountDownTexte.setText(nbS+"s");
            // On peut faire toute action qui met à jour l'IHM
            if ( nbS == 0)
            {
                changementJoueur();
            }
        }
    };

    protected void changementJoueur(){
        CountDownTexte.setText("changement Joueur");
        onStart();
    }
}