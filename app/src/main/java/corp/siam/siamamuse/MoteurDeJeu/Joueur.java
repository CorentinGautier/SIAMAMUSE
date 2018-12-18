package corp.siam.siamamuse.MoteurDeJeu;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
	private List<Pion> lesPionsEnMain;
	private List<Pion> lesPionsSurPlateau;
	private String nom;
	
	public Joueur(String nom) {
		super();
		this.nom = nom;
		lesPionsEnMain = new ArrayList<Pion>();
		lesPionsSurPlateau= new ArrayList<Pion>();
		creationPion();
	}
	
	public void creationPion() {
		for(int i=0;i<5;i++) {
			Pion unPion = new Pion(nom,Orientation.NORD);//___________________________________ il faut modifier l'orientation
			lesPionsEnMain.add(unPion);
		}
	}

	public ArrayList<Pion> getLesPionsEnMain() {
		return (ArrayList) lesPionsEnMain;
	}

	public void recuperPionMain(Pion unPion) {
		lesPionsEnMain.add(unPion);
	}
	
	public Pion poserPionPlateau() {
		if(lesPionsEnMain.size()==0) {
			System.err.println("Vous n'avez plus de pion");
			return null;
		}else {
			Pion unPion=lesPionsEnMain.get(0);
			lesPionsSurPlateau.add(unPion);
			lesPionsEnMain.remove(0);
			return unPion;
		}
	}

	public Pion poserPionPlateau(Pion unPion){
		for(Pion lePion:lesPionsEnMain){
			if(lePion.equals(unPion)){
				lesPionsSurPlateau.add(unPion);
				lesPionsEnMain.remove(unPion);
				return unPion;
			}
		}
		return null;
	}

	public void setRegardProchainPion(Orientation orient) {
		lesPionsEnMain.get(0).setRegard(orient);;
		
	}

	public Pion pionDeplacer() {
		return lesPionsSurPlateau.get(0);
	}
	public String getNom() {
		return nom;
	}
	
	public void affichePionPlateau() {
		for(Pion unPion:lesPionsSurPlateau) {
			System.out.println("- "+unPion );
		}
	}
	
	
}
