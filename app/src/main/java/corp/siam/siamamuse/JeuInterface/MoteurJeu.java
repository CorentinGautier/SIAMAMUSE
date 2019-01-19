package corp.siam.siamamuse.JeuInterface;

import android.widget.Toast;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.MoteurDeJeu.Joueur;
import corp.siam.siamamuse.MoteurDeJeu.Orientation;
import corp.siam.siamamuse.MoteurDeJeu.Pion;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;

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
		tour = false;
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

	//la fonction return true si il faut faire une rotation
	public boolean tourSuivant(){
		//verifie si le joeur peut faire une rotation
		context.setPionSelectionner(null);
		if(pionRotation==null) {
			//verifie si un rocher est sortie de la map
			if (!lePlateau.isFinJeu()) {
				//passe au joueurSuivant
				if (!tour) {
					tour = true;
				} else {
					tour = false;
				}
				lePlateau.increTour();
			} else {
				//La partie est finie
				if (!tour) {
					gagner(joueur1);
				} else {
					gagner(joueur2);
				}
			}
			return false;
		}else{
			return true;
		}
	}

	public void relancementChrono(){
		if(context.tempsParJoueur!=0) {
			context.onStop();
			context.onStart();
		}
	}
	public Joueur getTour() {
		if (!tour) {
			return joueur1;
		} else {
			return joueur2;
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

	public void deplacerPionInterf(Pion pion,Orientation orient){
		boolean res = lePlateau.deplacement(pion,orient);
		//Permet de verifier si on a pousser un pion si c'est le cas on ne fait pas de rotation
		if(!res){
			pionRotation=pion;
		}else{
			pionRotation=null;
		}
		Pion pionRecupere  = lePlateau.recuperePionDehorsPlateau();
		if (pionRecupere != null) {
			if (joueur1.getNom() == pionRecupere.getNom()) {
				joueur1.recuperPionMain(pionRecupere);
			} else if (joueur2.getNom() == pionRecupere.getNom()) {
				joueur2.recuperPionMain(pionRecupere);
			}
			//Permet de gérer le cas on on choisit nous meme de quitter le plateau
			if (!tour) {
				if(joueur1.getNom()==pionRecupere.getNom()){
					pionRotation=null;
				}
			} else {
				if(joueur2.getNom()==pionRecupere.getNom()){
					pionRotation=null;
				}
			}
		}
	}

	public void gagner(Joueur joueur) {
		fin = true;
		Toast.makeText(context.getApplicationContext(),"FIN DE PARTIE",Toast.LENGTH_LONG).show();
		context.pageVictoire(joueur);
	}

	public Plateau getLePlateau() {
		return lePlateau;
	}

	public Joueur getJoueur1(){return joueur1;};

	public Joueur getJoueur2() {
		return joueur2;
	}

	public Pion getPionRotation() {
		return pionRotation;
	}

	public void setPionRotation(Pion pionRotation) {
		this.pionRotation = pionRotation;
	}

}
