package corp.siam.siamamuse.MoteurDeJeu;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.Scanner;


import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import corp.siam.siamamuse.Activity_Partie;

public class MoteurJeu {

	private Plateau lePlateau;
	private Joueur joueur1, joueur2;
	private boolean fin;
	private Activity_Partie context;
	private PlateauInterface plateauInterface;
	private boolean tour;
	private Pion pionRotation;

	public MoteurJeu(int taillePlateau, Activity_Partie context) throws ParserConfigurationException, SAXException, IOException {
		lePlateau = new Plateau(5);
		//lePlateau = new Plateau();
		this.context=context;
		joueur1 = new Joueur("elephant");
		joueur2 = new Joueur("rhinoceros");
		fin = false;
		// ajouter al�atoire
		tour = true;
		pionRotation=null;
	}

	public MoteurJeu(Plateau plateau, Activity_Partie context) throws ParserConfigurationException, SAXException, IOException {
		lePlateau = plateau;
		//lePlateau = new Plateau();
		this.context=context;
		joueur1 = new Joueur("elephant");
		joueur2 = new Joueur("rhinoceros");
		fin = false;
		// ajouter al�atoire
		tour = true;
		pionRotation=null;
	}

	public Plateau getLePlateau() {
		return lePlateau;
	}

	public Joueur getJoueur1(){return joueur1;};

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setPlateauInterface(PlateauInterface plateauInterface) {
		this.plateauInterface = plateauInterface;

	}

	public void jouer() {

		while (!lePlateau.isFinJeu()) {
			if (!tour) {
				unTour(joueur1);
			} else {
				unTour(joueur2);
			}
		//	lePlateau.afficherPlateauVisionJoueur();
		}
	}
	//la fonction return true si il faut faire une rotation
	public boolean tourSuivant(){
		//verifie si le joeur peut faire une rotation
		context.setPionSelectionner(null);
		if(pionRotation==null) {
			//verifie si un rocher est sortie de la map
			if (!lePlateau.isFinJeu()) {

				if (!tour) {
					unTourInter(joueur2);
					tour = true;
				} else {
					unTourInter(joueur1);
					tour = false;
				}
			} else {
				//La partie est finie
				gagner();
			}
			return false;
		}else{
			return true;
		}
	}

	public void relancementChrono(){
	//	Log.e("TEST","Le chrono est relancé");
//		context.onStop();
//		context.onStart();
	}
	public Joueur getTour() {
		if (!tour) {
			return joueur1;
		} else {
			return joueur2;
		}
	}

	public void unTourInter(Joueur joueur){
		Toast.makeText(context.getApplicationContext(),"tour de "+joueur.getNom(),Toast.LENGTH_LONG).show();
	}

	public Pion getPionRotation() {
		return pionRotation;
	}

	public void setPionRotation(Pion pionRotation) {
		this.pionRotation = pionRotation;
	}

	public void unTour(Joueur joueur) {
		//System.out.println("\n" + "" + joueur.getNom() + ": 1 pour ajouter un Pion 2 pour d�placer un Pion");
		Scanner sc = new Scanner(System.in);
		int reponse = sc.nextInt();
		if (reponse == 1) {
			ajouterPionPlateau(joueur);
		} else if (reponse == 2) {
			deplacerPion(joueur);
		} else {
			System.err.println("error entre texte");
		}

		if (lePlateau.recuperePionDehorsPlateau() != null) {
			if (joueur1.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur1.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			} else if (joueur2.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur2.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			}
		}

	}
//factoriser la fonction avec celle des deplacement
    public void ajouterPionPlateauInter(Joueur unJoueur,Pion unPion, int x,int y) {
        boolean res = lePlateau.ajouterPion(unJoueur.poserPionPlateau(unPion), x, y);
		if(!res){
			pionRotation=unPion;
		}else{
			pionRotation=null;
		}
		if (lePlateau.recuperePionDehorsPlateau() != null) {
			if (joueur1.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur1.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			} else if (joueur2.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur2.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			}
		}
    }

	public void ajouterPionPlateau(Joueur unJoueur) {
		Scanner sca = new Scanner(System.in);
		int x, y;
		System.out.println("Quelle coordonne pour le pion ?");
		System.out.println("x = ");
		x = sca.nextInt();
		System.out.println("y = ");
		y = sca.nextInt();
		unJoueur.setRegardProchainPion(choisirRegard());
		if (lePlateau.verifCoin(x, y)) {
			lePlateau.ajouterPionCoin(unJoueur.poserPionPlateau(), x, y, choisirPousser());
		} else {
			lePlateau.ajouterPion(unJoueur.poserPionPlateau(), x, y);
		}
	}

	public Orientation choisirDirection() {
		System.out.println("Vers ou voulez vous vous d�placer votre pion 1=Nord, 2=Sud, 3=Est, 4=Ouest");
		return choisirOrientation();
	}

	public Orientation choisirRegard() {
		System.out.println("Vers ou voulez vous que regard votre pion 1=Nord, 2=Sud, 3=Est, 4=Ouest");
		return choisirOrientation();
	}

	public Orientation choisirPousser() {
		System.out.println("Vers ou voulez vous poussez en entrain 1=Nord, 2=Sud, 3=Est, 4=Ouest");
		return choisirOrientation();
	}

	public Orientation choisirOrientation() {
		Scanner sca = new Scanner(System.in);
		int resultat = sca.nextInt();
		switch (resultat) {
		case 1:
			return Orientation.NORD;
		case 2:
			return Orientation.SUD;
		case 3:
			return Orientation.EST;
		case 4:
			return Orientation.OUEST;
		default:
			return null;

		}
	}

//	public void deplacerPion(Joueur unJoueur) {
//		lePlateau.deplacement(unJoueur.pionDeplacer(), choisirOrientation());
//
//	}

	public void deplacerPion(Joueur joueur) {
		Scanner sca = new Scanner(System.in);
		System.out.println("Quelle coordonne pour le pion ?");
		System.out.println("x = ");
		int x = sca.nextInt();
		System.out.println("y = ");
		int y = sca.nextInt();
		Pion pion = lePlateau.recuperePion(x, y);
		if (pion == null) {
			System.out.println("il n'y pas de pion a cette position ");
			deplacerPion(joueur);
		} else {
			boolean peutPasFaireRotation = lePlateau.deplacement(pion, choisirDirection());
			if (!peutPasFaireRotation) {
				pion.setRegard(choisirRegard());
			}
		}
	}

	public void deplacerPionInterf(Pion pion,Orientation orient){
		boolean res = lePlateau.deplacement(pion,orient);
		if(!res){
			pionRotation=pion;
		}else{
			pionRotation=null;
		}
		if (lePlateau.recuperePionDehorsPlateau() != null) {
			if (joueur1.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur1.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			} else if (joueur2.getNom() == lePlateau.recuperePionDehorsPlateau().getNom()) {
				joueur2.recuperPionMain(lePlateau.recuperePionDehorsPlateau());
			}
		}
	}

	public void gagner() {
		fin = true;
		Toast.makeText(context.getApplicationContext(),"FIN DE PARTIE",Toast.LENGTH_LONG).show();
		context.pageVictoire();
	}

}
