package corp.siam.siamamuse;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.xml.parsers.ParserConfigurationException;

import corp.siam.siamamuse.MoteurDeJeu.Joueur;
import corp.siam.siamamuse.MoteurDeJeu.MoteurJeu;
import corp.siam.siamamuse.MoteurDeJeu.PionInterface;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;

public class Activity_Partie extends AppCompatActivity  implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {

    public int largeurEcrant,hauteurEcrant;
    public RelativeLayout fondPartie;
    Thread Compteur ;
    private Button CountDownTexte;
    private final String PROGRESS="textViewTestId";
    //L'AtomicBoolean qui gère la destruction de la Thread de background
    AtomicBoolean isRunning = new AtomicBoolean(false);
    // L'AtomicBoolean qui gère la mise en pause de la Thread de background
    AtomicBoolean isPausing = new AtomicBoolean(false);
    private int tempsParJoueur =20; // temps pour chaque tour des joueurs

    private MoteurJeu mj;
    private PlateauInterface plateauInterface;
    private GestureDetectorCompat gestureDetector;
    private PionInterface pionSelectionner;

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
        gestureDetector = new GestureDetectorCompat(this,this);
        gestureDetector.setOnDoubleTapListener(this);
        pionSelectionner=null;
    }


    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }
    public void FinDeLaPartie(View view) { //quitter l'activity actuel.
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Etes vous sur de vouloir abandonner le jeux ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Nom", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog  alertDialog = builder.create();
        alertDialog.show();

    }

    public void pageVictoire(Joueur joueur){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Winner", joueur.getNom());
        editor.apply();
        Intent intent = new Intent(this, Activity_PageDeVictoire.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void creationPlateau() throws IOException, SAXException, ParserConfigurationException {
        mj = new MoteurJeu(5,this);
        plateauInterface= new PlateauInterface(this,mj);
        plateauInterface.convertionMatriceAffichage();
        affichageDesCases();
    }

    public PionInterface getPionSelectionner() {
        return pionSelectionner;
    }

    public void setPionSelectionner(PionInterface pionSelectionner){
        this.pionSelectionner=pionSelectionner;
    }

    public void affichageDesCases(){
        for(int i=0;i<5;i++)
            for (int j = 0; j < 5; j++) {
                ImageView imageCase = new ImageView(this);
                imageCase.setBackgroundResource(R.drawable.png_deuxcase);
                ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int) (plateauInterface.calc.getTailleCase()), (int) (plateauInterface.calc.getTailleCase()));
                imageCase.setLayoutParams(paramsPion);
                imageCase.setX(plateauInterface.calc.calculeBtnAjoutX(i));
                imageCase.setY(plateauInterface.calc.calculeBtnAjoutY(j));
                this.fondPartie.addView(imageCase);
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX,float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) > Math.abs(diffY)) { //droite ou gauche
            if (Math.abs(diffX) > 50 && Math.abs(velocityX) > 100) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
            //haut ou bas
        } else if (Math.abs(diffY) > 50 && Math.abs(velocityY) > 100) {
            if (diffY > 0) {
                onSwipeBottom();
            } else {
                onSwipeTop();
            }
            result = true;
        }
        return result;
    }

    public void onSwipeTop() {
        //NORD
        if(pionSelectionner!=null){
            pionSelectionner.haut();
        }
    }

    public void onSwipeBottom() {
        //SUD
        if(pionSelectionner!=null){
            pionSelectionner.bas();
        }
    }

    public void onSwipeLeft() {
        //OUEST
        if(pionSelectionner!=null){
            pionSelectionner.gauche();
        }
    }

    public void onSwipeRight() {
        //EST
        if(pionSelectionner!=null){
            pionSelectionner.droit();
        }
    }

    //passer à la rotation
    @Override
    public boolean onDoubleTap(MotionEvent e) {
        if(pionSelectionner!=null){
            pionSelectionner.passerALaRotation();
        }
        return false;
    }

    //fonction non utilisé
    @Override
    public boolean onDown(MotionEvent e) {return false;}
    @Override
    public void onShowPress(MotionEvent e) {}
    @Override
    public boolean onSingleTapUp(MotionEvent e) { return false;}
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {return false;}
    @Override
    public void onLongPress(MotionEvent e) {}
    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {return false;}
    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {return false;}

   public void timerFin(){
        mj.setPionRotation(null);
        plateauInterface.convertionMatriceAffichage();
       //appel onResume pour relancer le thread avec le timer
    }

    //Méthode appelée quand l'activité s'arrête
    public void onStop() {
        super.onStop();
        //Mise-à-jour du booléen pour détruire la Thread de background
        isRunning.set(false);
        Compteur.interrupt();
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
                            Thread.sleep(1000);
                        }
                        Thread.sleep(1000);
                        myMessage = handler.obtainMessage(); // otenir un message vierge
                        //Ajouter des données à transmettre au Handler via le Bundle
                        messageBundle.putInt(PROGRESS, s);
                        //Ajouter le Bundle au message
                        myMessage.setData(messageBundle);
                        //Envoyer le message
                        handler.sendMessage(myMessage);
                        s--;
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
            int nbS=msg.getData().getInt(PROGRESS)-1;
          // Log.e("TEST","temps restant = "+nbS+"s");
            CountDownTexte.setText(nbS+"s");
            // On peut faire toute action qui met à jour l'IHM
            if ( nbS == 0)
            {
               timerFin();
            }
        }
    };
}