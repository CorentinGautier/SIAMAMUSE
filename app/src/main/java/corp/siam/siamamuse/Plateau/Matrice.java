package corp.siam.siamamuse.Plateau;

import android.util.Log;

public class Matrice {

    int[][] matrice = new int[3][5];
    public Matrice() {

        // Création de la matrice :
       for (int i = 0; i < matrice.length; i++) {
            matrice[i] = new int[2];
        }

        // L'objet scanner va permettre de récupérer les entrées clavier.
        //  Scanner sc = new Scanner(System.in);
        // Remplissage :
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                // On appelle la méthode nextInt() de l'objet scanner, qui retourne l'entier que l'on frappe au clavier.
                //    matrice[i][j] = sc.nextInt();
            }
        }
          }

    public void afficherMatrice() {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                Log.e("matrice", " " + matrice[i][j]);
            }
            ;
        }
    }
}
