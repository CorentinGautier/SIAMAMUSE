//package corp.siam.siamamuse.MoteurDeJeu;
//
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//
//import corp.siam.siamamuse.Activity_Partie;
//import corp.siam.siamamuse.R;
//
//public class Fleche {
//    private int positionx,positiony;
//    private int coordX,coordY;
//    private ImageButton btnFleche;
//    private Activity_Partie context;
//    private MoteurJeu mj;
//    private Orientation orient;
//    private Pion pion;
//    private PlateauInterface plateauInterface;
//    private PionInterface pionInter;
//
//    //les coordonner de l'image sont dans le coin en haut gauche
//    public Fleche(int x, int y,Pion pion,Orientation orient,Activity_Partie context,MoteurJeu mj,PlateauInterface plateauInterface,PionInterface pionInterface){
//        btnFleche = new ImageButton(context);
//        this.context=context;
//        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase*0.5),(int)(PlateauInterface.tailleCase*0.5));
//        gestionDirection(orient);
//        btnFleche.setLayoutParams(paramsPion);
//        btnFleche.setX(x);
//        btnFleche.setY(y);
//        this.coordX=coordX;
//        this.coordY=coordY;
//        this.positionx=x;
//        this.positiony=y;
//        this.mj=mj;
//        this.plateauInterface=plateauInterface;
//        this.orient=orient;
//        this.pion = pion;
//        this.pionInter = pionInterface;
//        btnFleche.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deplacement();
//            }
//        });
//    }
//
//    public void deplacement(){
//        mj.deplacerPionInterf(pion,orient);
//        plateauInterface.suppressionJetons();
//        plateauInterface.convertionMatriceAffichage();
//      //  pionInter.disparitionFleche();
//
//    }
//
//    private void gestionDirection(Orientation orient){
//        switch (orient){
//            case EST:
//                btnFleche.setBackgroundResource(R.drawable.fleche_est);
//                break;
//            case OUEST:
//                btnFleche.setBackgroundResource(R.drawable.fleche_ouest);
//                break;
//            case NORD:
//                btnFleche.setBackgroundResource(R.drawable.fleche_sud);
//                break;
//            case SUD:
//                btnFleche.setBackgroundResource(R.drawable.fleche_nord);
//                break;
//        }
//    }
//
//    public void afficherFleche(){
//        context.runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                context.fondPartie.addView(btnFleche);
//            }
//        });
//    }
//
//    public void effacerFleche(){
//        context.fondPartie.removeView(btnFleche);
//    }
//
//    public void setBtnFleche(ImageButton btnFleche) {
//        this.btnFleche = btnFleche;
//    }
//
//    public void setCoordX(int coordX) {
//        this.coordX = coordX;
//    }
//
//    public void setCoordY(int coordY) {
//        this.coordY = coordY;
//    }
//}
