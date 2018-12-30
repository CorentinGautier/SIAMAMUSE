package corp.siam.siamamuse.MoteurDeJeu;

import android.content.Context;
import android.util.Log;

import org.jdom2.Attribute;
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
		save(context);
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


	
	//pour ajouter un pion on doit le mettre au coordoner que le joueur voie
	
	//on est oblig� de mettre le pion sur un ext�rieur du plateau car sinon il y auras une erreur
	public void ajouterPionCoin(Jeton unJeton, int x, int y,Orientation orientation) {
		if(x<taillePlateau || y<taillePlateau) {
			if((x==0)&&(y==0)) {
				if(orientation == Orientation.NORD) {
					plateau[x+1][y]=unJeton;
					deplacement(unJeton, orientation);
				}else if(orientation == Orientation.EST){
					plateau[x][y+1]=unJeton;
					deplacement(unJeton, orientation);
				}else {
					System.err.println("Vous ne pouvez pas ajouter votre pion car vous n'etes pas dans le bon sens");
				}
			}else if((x==0)&&(y==taillePlateau-1)) {
				if(orientation == Orientation.SUD) {
					plateau[x+1][y+2]=unJeton;
					deplacement(unJeton, orientation);
				}else if(orientation == Orientation.EST){
					plateau[x][y+1]=unJeton;
					deplacement(unJeton, orientation);
				}else {
					System.err.println("Vous ne pouvez pas ajouter votre pion car vous n'etes pas dans le bon sens");
				}
			}else if((x==taillePlateau-1)&&(y==0)) {
				if(orientation == Orientation.NORD) {
					plateau[x+1][y]=unJeton;
					deplacement(unJeton, orientation);
				}else if(orientation == Orientation.OUEST){
					plateau[x+2][y+1]=unJeton;
					deplacement(unJeton, orientation);
				}else {
					System.err.println("Vous ne pouvez pas ajouter votre pion car vous n'etes pas dans le bon sens");
				}
			}else if((x==taillePlateau-1)&&(y==taillePlateau-1)) {
				if(orientation == Orientation.SUD) {
					plateau[x+1][y+2]=unJeton;
					deplacement(unJeton, orientation);
				}else if(orientation == Orientation.OUEST){
					plateau[x+2][y+1]=unJeton;
					deplacement(unJeton, orientation);
				}else {
					System.err.println("Vous ne pouvez pas ajouter votre pion car vous n'etes pas dans le bon sens");
				}
			}else {
				System.err.println("Erreur ajout pion dans un coin ");
			}
//			if(x==0) {
//				
//			}else if(x==taillePlateau-1) {
//				plateau[x+2][y+1]=unJeton;
//				deplacement(unJeton, orientation);
//			}else if(y==0) {
//				plateau[x+1][y]=unJeton;
//				deplacement(unJeton, orientation);
//			}else if(y==taillePlateau-1) {
//				plateau[x+1][y+2]=unJeton;
//				deplacement(unJeton, orientation);
//			}else {
//				System.err.println("Vous devez placer votre pion sur le rebord du plateau");
//			}
			
		}else {
			System.err.println("Vous devez placer votre pion dans le plateau");
		}
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
	
	public boolean verifCoin( int x, int y) {
		if((x==0)||(x==taillePlateau-1)){
			if((y==0)||(y==taillePlateau-1)) {
				if(plateau[x+1][y+1]!=null) {
					return true;					
				}
			}
		}
		return false;
	}
	

	public void afficherPlateauVisionDev() {
		Log.e("TEST","\n\n le plateau :");
		for (int i = 0; i < taillePlateau + 2; i++) {
			for (int j = 0; j < taillePlateau + 2; j++) {
				if (plateau[i][j] != null && !(plateau[i][j] instanceof Out)) {
					Log.e("TEST",(plateau[i][j] + " � la position " + i + " " + j));
				}
			}
		}
	}

	public void afficherPlateauVisionJoueur() {
		Log.e("TEST",("\n\n le plateau :"));
		for (int i = 1; i < taillePlateau + 1; i++) {
			for (int j = 1; j < taillePlateau + 1; j++) {
				if (plateau[i][j] != null && !(plateau[i][j] instanceof Out)) {
					Log.e("TEST",(plateau[i][j] + " � la position " + (i-1) + " " + (j-1)));
				}
			}
		}
	}

	public void afficherPlateauVide() {
		System.out.println("\n\nVoici le plateau :");
		for (int i = 0; i < taillePlateau + 2; i++) {
			for (int j = 0; j < taillePlateau + 2; j++) {
				if (plateau[i][j] == null) {
					System.out.println(plateau[i][j] + " � la position " + i + " " + j);
				}else if ((plateau[i][j] instanceof Out)) {
					System.out.println(plateau[i][j] + " � la position " + i + " " + j);
				}else if ((plateau[i][j] instanceof Rocher)) {
					System.out.println(plateau[i][j] + " � la position " + i + " " + j);
				}
			}
		}
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
		int contreAttaque = 1;
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
		int contreAttaque = 1;
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
		if (contre >= 0) {
			return true;
		} else {
			System.out.println("D�placement impossible car contre = " + contre);
			return false;
		}
	}

	public void sortiePlateau(Jeton unJeton) {
		if (unJeton instanceof Rocher) {
			System.out.println("Vous avez gg");
			finJeu = true;
		} else {
			pionARecupere=(Pion)unJeton;
			System.out.println("vous recupere un pion dans votre inventaire"+unJeton);
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
	
	// Sauvegarde des plateaux dans un fichier XML
		// filename : nom du fichier
		// comment : commentaire
        // context : context de l'appli pour retrouver le fichier xml
	public void save (Context context) throws ParserConfigurationException {
		// On cr�e un nouveau Document JDOM
		Document document = new Document();

		// on set la racine du document avec l'�l�ment meilleurScore
		Element racine = new Element("Plateau");
		document.setRootElement(racine);
		
		Attribute taille = new Attribute ("taille",""+taillePlateau);
		racine.setAttribute(taille);

		for (int x = 0; x < taillePlateau+2; x++) {
			for (int y = 0; y < taillePlateau+2; y++) {

			Element uneCase = new Element("Case");
			racine.addContent(uneCase);
			
			if (plateau[x][y] instanceof Out) {
				Attribute type1 = new Attribute("type","Out");
			    uneCase.setAttribute(type1);
			}else if(plateau[x][y] instanceof Rocher) {
				Attribute type2 = new Attribute("type","Rocher");
			    uneCase.setAttribute(type2);
			}else{
				Attribute type3 = new Attribute("type","In");
			    uneCase.setAttribute(type3);
			}
			
		      
			Element horizontale = new Element("X");// balise valeur du score (nb de tentative)
			uneCase.addContent(horizontale);
			horizontale.setText(""+x);// on remplis les elements(balises) avec le contenu de la
															// liste

			Element verticale = new Element("Y");// balise pour savoir qui la r�alis�
			uneCase.addContent(verticale);
			verticale.setText(""+y);
			

			}
		}

      /*  try {
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(document, new FileWriter(filename));
            Log.i("5665656",""+sortie.toString());
        } catch (IOException e) {
        }*/
	}
	// Chargement des plateaux depuis un fichier XML
		// filename : nom du fichier
        // context : le context de l'activity
		public void load(Context context) throws ParserConfigurationException, SAXException, IOException {
			/*// permet la cr�ation d'un fichier pour extraire les donn�es du fichier xml
            Log.e("TEST","je rentre dans le load");
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			Log.e("TEST","1");
			DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Log.e("TEST","2");
			org.w3c.dom.Document domDocument = documentBuilder.parse(filename);
            Log.e("TEST","3");
			DOMBuilder domBuilder = new DOMBuilder();
            Log.e("TEST","4");
			Document doc = domBuilder.build(domDocument);
            Log.e("TEST","5");
			// Element racine = doc.getRootElement();
			// on recup�re les fils de case a patir de la racine plateau
			List<Element> attribElments = doc.getRootElement().getChildren("Case");
			Log.e("TEST",attribElments.size()+"");
			this.taillePlateau = Integer.parseInt(doc.getRootElement().getAttributeValue("taille")) ;
			plateau = new Jeton[taillePlateau +2][taillePlateau+2];
			// pour chaque case du fichier xml on �crit la case avec un x , un y et en focntion de ses attribut in , out , rien 
			for (Element uneCase : attribElments) {
				int x = Integer.parseInt(uneCase.getChildText("X")); 
					int y = Integer.parseInt(uneCase.getChildText("Y")); 
				if (uneCase.getAttributeValue("type").equals("Out")) {
					plateau [x][y] = new Out();
				}else if (uneCase.getAttributeValue("type").equals("In")){
					plateau [x][y] = null;
				}else if (uneCase.getAttributeValue("type").equals("Rocher")) {
					int id = 1 ;
					Rocher unRocher = new Rocher("cailloux "+id);
					ajouterRocher(unRocher, x ,y);
					id++;
				
				}
			}*/

			try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();

                InputStream in = context.getAssets().open("plateau.xml");
                org.w3c.dom.Document doc2 = docBuilder.parse(in);
                DOMBuilder domBuilder = new DOMBuilder();
                Document doc = domBuilder.build(doc2);
				List<Element> attribElments = doc.getRootElement().getChildren("Case");

				this.taillePlateau = Integer.parseInt(doc.getRootElement().getAttributeValue("taille")) ;
				plateau = new Jeton[taillePlateau +2][taillePlateau+2];

				for (Element uneCase : attribElments) {
					int x = Integer.parseInt(uneCase.getChildText("X"));
					int y = Integer.parseInt(uneCase.getChildText("Y"));
					if (uneCase.getAttributeValue("type").equals("Out")) {
						plateau [x][y] = new Out();
					}else if (uneCase.getAttributeValue("type").equals("In")){
						plateau [x][y] = null;
					}else if (uneCase.getAttributeValue("type").equals("Rocher")) {
						int id = 1 ;
						Rocher unRocher = new Rocher("cailloux "+id);
						ajouterRocher(unRocher, x ,y);
						id++;

					}
				}

			} catch (ParserConfigurationException | IOException | SAXException e) {
				e.printStackTrace();
			}
		}


	
	
}
