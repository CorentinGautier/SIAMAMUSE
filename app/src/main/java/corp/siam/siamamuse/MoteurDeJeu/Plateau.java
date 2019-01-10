package corp.siam.siamamuse.MoteurDeJeu;

import android.content.Context;
import android.util.Log;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.DOMBuilder;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class Plateau {

	Jeton[][] plateau;
	int taillePlateau;
	int largeurPlateau,hauteurPlateau;

	Pion pionARecupere;
	boolean finJeu;
	Integer[][] caseAjout;

	public Plateau(int taillePlateau) {
		super();
		finJeu = false;
		plateau = new Jeton[taillePlateau + 2][taillePlateau + 2];
		this.largeurPlateau = taillePlateau;
		this.hauteurPlateau =taillePlateau;
		this.taillePlateau = taillePlateau;
		remplissageOut();
		simulationPartie();
		creationCaseAjout();
	}
	public Plateau (Context context) throws ParserConfigurationException, SAXException, IOException {
		//load(context);
	}

	public Plateau(int largeurPlateau,int hauteurPlateau){
		super();
		plateau = new Jeton[largeurPlateau + 2][hauteurPlateau + 2];
		remplissageOut();
		caseAjout = new Integer[taillePlateau][taillePlateau];
		this.largeurPlateau=largeurPlateau;
		this.hauteurPlateau=hauteurPlateau;
	}

	public void simulationPartie(){
		plateau[2][3] = new Rocher("roche");
		plateau[3][3] = new Rocher("roche");
		plateau[4][3] = new Rocher("roche");
	}

	public int getHauteurPlateau() {
		return hauteurPlateau;
	}

	public void ajoutRocher(int x, int y){
		plateau[x][y]=new Rocher("rocher");
	}

	public void ajoutCaseOut(int x, int y){
		plateau[x][y]=new Out();
	}

	public void ajoutCaseDepart(int x,int y){
		caseAjout[x][y]=1;
	}

	public void remplissageOut() {
		for (int i = 0; i < largeurPlateau + 2; i++) {
			plateau[i][0] = new Out();
			plateau[i][hauteurPlateau + 1] = new Out();
		}
		for(int j= 0;j<hauteurPlateau + 2;j++){
			plateau[0][j] = new Out();
			plateau[largeurPlateau + 1][j] = new Out();
		}
	}

	public Integer[][] getCaseajout(){
		return caseAjout;
	}

	public void creationCaseAjout(){
		caseAjout = new Integer[taillePlateau][taillePlateau];

		for (int a = 0; a < taillePlateau ; a++) {
			for (int b = 0; b < taillePlateau ; b++) {
				caseAjout[a][b]=0;
			}
		}
		for(int i=0;i<taillePlateau-1;i++){
			caseAjout[0][i]=1;
			caseAjout[i+1][0]=1;
			caseAjout[taillePlateau-1][i+1]=1;
			caseAjout[i][taillePlateau-1]=1;
		}
	}

	public void ajouterRocher(Rocher unRocher,int x,int y) {
		plateau[x][y] = unRocher;
	}


	
	public boolean ajouterPion(Pion unPion, int x, int y) {
	    boolean res;
		if(x<taillePlateau || y<taillePlateau) {
			if(x==0) {
				plateau[x][y+1]=unPion;
				res=deplacement(unPion, Orientation.EST);
				unPion.setRegard(Orientation.EST);
			}else if(x==taillePlateau-1) {
				plateau[x+2][y+1]=unPion;
				res=deplacement(unPion, Orientation.OUEST);
                unPion.setRegard(Orientation.OUEST);
			}else if(y==0) {
				plateau[x+1][y]=unPion;
				res=deplacement(unPion, Orientation.NORD);
                unPion.setRegard(Orientation.NORD);
			}else if(y==taillePlateau-1) {
				plateau[x+1][y+2]=unPion;
				res=deplacement(unPion, Orientation.SUD);
                unPion.setRegard(Orientation.SUD);
			}else {
			    res=false;
				System.err.println("Vous devez placer votre pion sur le rebord du plateau");
			}
			
		}else {
		    res=false;
			System.err.println("Vous devez placer votre pion dans le plateau");
		}
		return res;
	}
	
	//Fonction qui sert pour le d�placement d'un pion en r�cup�rant le pion sur le plateau si il existe
	public Pion recuperePion(int x,int y) {
		if(plateau[x+1][y+1] instanceof Pion) {
			return (Pion) plateau[x+1][y+1];
		}		
		return null;
	}

	public void suppresionPionDehors() {
		for (int i = 0; i < taillePlateau + 2; i++) {
			if (plateau[0][i] != null && !(plateau[0][i] instanceof Out)) {
				sortiePlateau(plateau[0][i]);
			}
			if (plateau[i][0] != null && !(plateau[i][0] instanceof Out) ) {
				sortiePlateau(plateau[i][0]);
			}
			if (plateau[taillePlateau + 1][i] != null && !(plateau[taillePlateau + 1][i] instanceof Out) ) {
				sortiePlateau(plateau[taillePlateau + 1][i]);
			}
			if (plateau[i][taillePlateau + 1] != null && !(plateau[i][taillePlateau + 1] instanceof Out) ) {
				sortiePlateau(plateau[i][taillePlateau + 1]);
			}
		}
		remplissageOut();
	}

	// res[0] int sert fonction pousser, res[1]=deplacemnt est possible, res[2] boolean qui dit si on vas devoir pousser

	public boolean testDeplacement(Pion unPion, Orientation directionDeplacement){
		pionARecupere = null;
		// On recupere les coordonne du jeton
		int[] coordonne = recherchePosition(unPion);
		int x = coordonne[0], y = coordonne[1];
		// Variable qui vas servir a v�rifier si le d�placment est possible
		int contreAttaque = 15;
		// Si le pion a deplacer des pion la cariable passe a true
		int aPousser = 0;

		// deplacemnt vers le nord
		if (directionDeplacement == Orientation.EST) {
			int i = x;
			i++;
			// tant qu'il y a un jeton et qu'on ne d�pace pas la taille du plateau
			while (i < taillePlateau + 1) {
				// additionne la valeur de retour du jeton
				// 0 si il n'a pas d'impacte 1 si il est dans notre sens et -1 si il est contre
				// nous
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
					i++;
				} else {
					break;
				}
				aPousser++;
			}
			// v�rification si la contre attaque permet le d�placement
			// si elle est inf�rieur � 0 c'est impossible

		}
		// deplacemnt vers le sud
		if (directionDeplacement == Orientation.OUEST) {
			int i = x;
			i--;
			while (i >= 1) {
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
					i--;
				} else {
					break;
				}
				aPousser++;
			}
		}
		// deplacement vers le ouest
		if (directionDeplacement == Orientation.SUD) {
			int i = y;
			i--;
			while (i >= 1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
					i--;
				} else {
					break;
				}
				aPousser++;
			}
		}
		// deplacement vers le nord
		if (directionDeplacement == Orientation.NORD) {
			int i = y;
			i++;
			while (i < taillePlateau+1) {

				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
					i++;
				} else {
					break;
				}
				aPousser++;
			}
		}
		if(unPion.getRegard()==directionDeplacement){
            return deplacementPossible(contreAttaque);

        }else{
		    if(aPousser>0){
		        return false;
            }
            return true;
        }
	}


	
	// cette fonction est un boolean qui retourn true si il y a eu un deplacement et false sinon
	public boolean deplacement(Jeton unJeton, Orientation directionDeplacement) {
		pionARecupere = null;
		// On recupere les coordonne du jeton
		int[] coordonne = recherchePosition(unJeton);
		int x = coordonne[0], y = coordonne[1];
		// Variable qui vas servir a v�rifier si le d�placment est possible
		int contreAttaque = 15;
		// Si le pion a deplacer des pion la cariable passe a true
		int aPousser = 0;

		// deplacemnt vers le nord
		if (directionDeplacement == Orientation.EST) {
			int i = x;
			i++;
			// tant qu'il y a un jeton et qu'on ne d�pace pas la taille du plateau
			while (i < taillePlateau + 1) {
				// additionne la valeur de retour du jeton
				// 0 si il n'a pas d'impacte 1 si il est dans notre sens et -1 si il est contre
				// nous
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
					i++;
				} else {
					break;
				}
				aPousser++;
			}
			// v�rification si la contre attaque permet le d�placement
			// si elle est inf�rieur � 0 c'est impossible
			if (deplacementPossible(contreAttaque)) {
				pousserEst(unJeton, i - 1, y);
			}
		}
		// deplacemnt vers le sud
		if (directionDeplacement == Orientation.OUEST) {
			int i = x;
			i--;
			while (i >= 1) {
				if (plateau[i][y] != null) {
					contreAttaque += plateau[i][y].veriforientation(directionDeplacement);
					i--;
				} else {
					break;
				}
				aPousser++;
			}
			if (deplacementPossible(contreAttaque)) {
				pousserOuest(unJeton, i + 1, y);
			}
		}
		// deplacement vers le ouest
		if (directionDeplacement == Orientation.SUD) {
			int i = y;
			i--;
			while (i >= 1) {
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
					i--;
				} else {
					break;
				}
				aPousser++;
			}
			if (deplacementPossible(contreAttaque)) {
				pousserSud(unJeton, x, i + 1);
			}
		}
		// deplacement vers le nord
		if (directionDeplacement == Orientation.NORD) {
			int i = y;
			i++;
			while (i < taillePlateau+1) {
				
				if (plateau[x][i] != null) {
					contreAttaque += plateau[x][i].veriforientation(directionDeplacement);
					i++;
				} else {
					break;
				}
				aPousser++;
			}
			if (deplacementPossible(contreAttaque)) {
				pousserNord(unJeton, x, i - 1);
			}
		}
		suppresionPionDehors();
		if(aPousser==0) {
			return false;
		}else {
			return true;
		}
	}

// fonction pousser
	public void pousserEst(Jeton unJeton, int xMax, int y) {
		while (plateau[xMax][y] != unJeton) {
			plateau[xMax + 1][y] = plateau[xMax][y];
			xMax--;
		}
		plateau[xMax + 1][y] = plateau[xMax][y];
		plateau[xMax][y] = null;

	}

	public Jeton[][] getPlateau() {
		return plateau;
	}

	public void pousserOuest(Jeton unJeton, int xMin, int y) {
		while (plateau[xMin][y] != unJeton) {
			plateau[xMin - 1][y] = plateau[xMin][y];
			xMin++;
		}
		plateau[xMin - 1][y] = plateau[xMin][y];
		plateau[xMin][y] = null;

	}

	public void pousserNord(Jeton unJeton, int x, int yMax) {
		while (plateau[x][yMax] != unJeton) {
			plateau[x][yMax + 1] = plateau[x][yMax];
			yMax--;
		}
		plateau[x][yMax + 1] = plateau[x][yMax];
		plateau[x][yMax] = null;
	}

	public void pousserSud(Jeton unJeton, int x, int yMin) {
		while (plateau[x][yMin] != unJeton) {
			plateau[x][yMin - 1] = plateau[x][yMin];
			yMin++;
		}
		plateau[x][yMin - 1] = plateau[x][yMin];
		plateau[x][yMin] = null;
	}

	public boolean deplacementPossible(int contre) {
		if (contre > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void sortiePlateau(Jeton unJeton) {
		if (unJeton instanceof Rocher) {
			finJeu = true;
		} else {
			pionARecupere=(Pion)unJeton;
		}
	}
	
	public boolean isFinJeu() {
		return finJeu;
	}
	
	public Pion recuperePionDehorsPlateau() {
		if(pionARecupere!=null) {
			return pionARecupere;
		}else {
			return null;
		}
	}

	public int[] recherchePosition(Jeton unJeton) {
		int[] coordonne = new int[2];
		for (int x = 0; x < taillePlateau+2; x++) {
			for (int y = 0; y < taillePlateau+2; y++) {
				if (plateau[x][y] == unJeton) {
					coordonne[0] = x;
					coordonne[1] = y;
					return coordonne;
				}
			}
		}
		return coordonne;
	}
}
