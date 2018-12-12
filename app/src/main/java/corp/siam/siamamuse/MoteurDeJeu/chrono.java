package corp.siam.siamamuse.MoteurDeJeu;

public class chrono {

    //timer
    private Button countDownButton;
    private CountDownTimer countDownTimer;
    private Button CountDownTexte;
    private long tempsRestant = 10; // 10 sec


    Thread Compteur ;
    private final String PROGRESS="textViewTestId";
    //L'AtomicBoolean qui gère la destruction de la Thread de background
    AtomicBoolean isRunning = new AtomicBoolean(false);
    //     L'AtomicBoolean qui gère la mise en pause de la Thread de background
    AtomicBoolean isPausing = new AtomicBoolean(false);

    // creation de l'activite
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activit1_main);
        
        //association
        CountDownTexte = findViewById(R.id.CountDown_Texte);

    }


    public void onStart() {//démarage du thread
        super.onStart();
        //Comteur a 0
        compteurBalle = 0;
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
                scoreFinal = getCompteurBalle();
                changeActivity();
            }

        }
    };
}
