package corp.siam.siamamuse.JeuInterface;

public class Calculateur {

    private int largeurEcrant,hauteurEcrant;
    private int posHautGauchY,tailleCase;
    private MoteurJeu mj;

    public Calculateur(int largeur, int hauteur,MoteurJeu mj){
        this.largeurEcrant=largeur;
        this.hauteurEcrant=hauteur;
        this.mj=mj;
        tailleCase=(int)(largeurEcrant*0.2);
        posHautGauchY=(int)((hauteurEcrant-(tailleCase*5))/2);
    }


    public int calculePionPlateauY(int y){
        return (int)((posHautGauchY+(tailleCase*(mj.getLePlateau().getHauteurPlateau() - y-1)))+(tailleCase*0.1));
    }

    public int calculePionPlateauX(int x){
        return (int)((tailleCase*x)+(tailleCase*0.1));
    }

    public int calculePionExterieurX(int numero){
        return (int) ((tailleCase-(tailleCase*0.5))*(numero-1) + largeurEcrant*0.1);
    }

    public int calculeBtnAjoutX(int x){
        return (int)((tailleCase*x));
    }

    public int calculeBtnAjoutY(int y){
        return (int)(posHautGauchY+(tailleCase*(mj.getLePlateau().getHauteurPlateau() - y-1)));
    }

    public int getTailleCase() {
        return tailleCase;
    }

    public int getPosHautGauchY() {
        return posHautGauchY;
    }

    public int getHauteurEcrant() {
        return hauteurEcrant;
    }

    public int getLargeurEcrant() {
        return largeurEcrant;
    }
}
