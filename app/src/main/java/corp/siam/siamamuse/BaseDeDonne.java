package corp.siam.siamamuse;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import corp.siam.siamamuse.MoteurDeJeu.Out;
import corp.siam.siamamuse.MoteurDeJeu.Pion;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.MoteurDeJeu.Rocher;

public class BaseDeDonne extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Siam.db";
    //private int idMax;
    public static final String IDMAX_CASE = "SELECT MAX(ID_CAS) FROM CASEE";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_PLATEAU =
            "CREATE TABLE  PLATEAU ("
                    + " ID_PLA  INTEGER PRIMARY KEY , "
                    + " NB_LIGNE  INTEGER, "
                    + " NB_COLONNE INTEGER);";
    public static final String DROP_PLATEAU = "DROP TABLE PLATEAU";
    public static final String DEL_PLATEAU = "DELETE FROM PLATEAU";

    public static final String TABLE_CASE =
            "CREATE TABLE  CASEE  ("
                    + "ID_CAS  INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " ID_PLA_CAS  INTEGER, "
                    + " COOR_X_CAS  INTEGER, "
                    + " COOR_Y_CAS  INTEGER,"
                    + " TYPE_CAS TEXT);";//"out" "rocher" "spawn""vide"
    public static final String DROP_CASE = "DROP TABLE CASEE";
    public static final String DEL_CASE = "DELETE FROM CASEE";


    Context context;

    public BaseDeDonne(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.getReadableDatabase().delete("CASEE",null,null);
        this.getReadableDatabase().delete("PLATEAU",null,null);
        returnIdMax();

    }
    //méthode récup idMax


    @SuppressLint("LongLogTag")
    public int returnIdMax() {
        int idMax;
        Cursor cursor = this.getReadableDatabase().rawQuery(IDMAX_CASE, null);
        cursor.moveToFirst();
        if (cursor.getInt(0) != 0) {
            idMax = cursor.getInt(0);
            Log.i("Idmaxxxxxxxxxxxxxxxxxxxxx",""+idMax);

        } else {
            idMax = 0;
            Log.i("Idmaxxxxxxxxxxxxxxxxxxxxx",""+idMax);
        }
        cursor.close();
        return idMax;
    }

    //Creation de la base de donner quand on change de version
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CASE);
        db.execSQL(TABLE_PLATEAU);
        Log.i("DATABASE", "onCreate invoked");

    }

    //Supprime la base de données et la recrée quand on change de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CASE);
        db.execSQL(DROP_PLATEAU);
        onCreate(db);
        Log.i("DATABASE", "onUpgrade invoked");
    }

    public void AfficheBdd() {
        String reqSQLCase = "SELECT ID_CAS,ID_PLA_CAS,COOR_X_CAS,COOR_Y_CAS,TYPE_CAS FROM CASEE ";
        Cursor cursorCase = this.getReadableDatabase().rawQuery(reqSQLCase, null);
        cursorCase.moveToFirst();
        while (!cursorCase.isAfterLast()) {
            Log.i("les casesssssss",""+cursorCase.getInt(0)+","+cursorCase.getInt(1)+","+cursorCase.getInt(2)+","+cursorCase.getInt(3)+","+cursorCase.getString(4));
            cursorCase.moveToNext();
            }
        cursorCase.close();
        }




    //Creation d'une case
    public void creationCase(int idPlateau, int cordX, int cordY,String type){
        String reqSQLCase = "SELECT ID_CAS,COOR_X_CAS,COOR_Y_CAS,TYPE_CAS FROM CASEE WHERE ID_PLA_CAS=" +idPlateau+ " AND COOR_X_CAS ="+cordX+" AND COOR_Y_CAS="+cordY+"";
        Cursor cursorCase = this.getReadableDatabase().rawQuery(reqSQLCase, null);
        cursorCase.moveToFirst();
        if (cursorCase.getCount() != 0) {
          updateCaseEtat(cursorCase.getInt(0),type,idPlateau);
        } else {
            int idd = returnIdMax();
            idd++;
            Log.i("idddddd", "" + idd);
            String creacase = "insert into CASEE (ID_CAS,ID_PLA_CAS,COOR_X_CAS,COOR_Y_CAS,TYPE_CAS) values (" + idd + "," + idPlateau + "," + cordX + "," + cordY + ",'/" + type + "/')";
            this.getWritableDatabase().execSQL(creacase);
        }
        cursorCase.close();
    }
    //création d'un plateau
    public void creationPlateau(int idPlateau, int Nbligne, int Nbcolonne){
        String reqSQLPlateau = "SELECT NB_LIGNE,NB_COLONNE FROM CASEE WHERE ID_PLA=" + idPlateau + "";
        Cursor cursorPlateau = this.getReadableDatabase().rawQuery(reqSQLPlateau, null);
        cursorPlateau.moveToFirst();
        if (cursorPlateau.getCount() != 0) {
            updateTaillePlateau(idPlateau,Nbligne,Nbcolonne);
        } else {
            String creaPlateau = "insert into PLATEAU (ID_PLA,NB_LIGNE,NB_COLONNE) values (" + idPlateau + ",'" + Nbligne + "'," + Nbcolonne + ")";
            this.getWritableDatabase().execSQL(creaPlateau);
        }
        cursorPlateau.close();
    }
    //changement d'un état de la case
    public void updateCaseEtat(int idCase, String typeCase,int idPlateau){
        String updateCaseEtat="UPDATE CASEE SET TYPE_CAS='/" +typeCase+ "/' WHERE ID_CAS ="+idCase+" AND ID_PLA_CAS="+idPlateau+" " ;
        this.getWritableDatabase().execSQL(updateCaseEtat);
    }
    //changement de la taille du plateau
    public void updateTaillePlateau(int idPlateau,int newNbLigne,int newNbColonne){
        String updateTaillePlateau="UPDATE PlATEAU SET NB_LIGNE="+newNbLigne+",NB_COLONNE="+newNbColonne+" WHERE ID_PLA="+idPlateau+" " ;
        this.getWritableDatabase().execSQL(updateTaillePlateau);
    }
    //Lit le plateau deja présent dans la basee de donnée
    public Plateau readPlateau(int idplateau) {
        int nbligne, nbColonne;
        Plateau unPlateau = null;
        String reqSQLCase = "SELECT ID_CAS,COOR_X_CAS,COOR_Y_CAS,TYPE_CAS FROM CASEE WHERE ID_PLA_CAS=" + idplateau + "";
        String reqSQLPlateau = "SELECT NB_LIGNE,NB_COLONNE FROM CASEE WHERE ID_PLA=" + idplateau + "";

        Cursor cursorCase = this.getReadableDatabase().rawQuery(reqSQLCase, null);
        Cursor cursorPlateau = this.getReadableDatabase().rawQuery(reqSQLPlateau, null);

        cursorPlateau.moveToFirst();
        nbligne = cursorPlateau.getInt(1);
        nbColonne = cursorPlateau.getInt(2);

        cursorCase.moveToFirst();
        while (!cursorCase.isAfterLast()) {
            unPlateau = new Plateau((nbligne + 2) * (nbColonne + 2));
            switch (cursorCase.getString(4)) {
                case "Out":
                    unPlateau.ajoutCaseOut(cursorCase.getInt(2), cursorCase.getInt(3));
                    break;
                case "In":
                    unPlateau.ajoutCaseDepart(cursorCase.getInt(2), cursorCase.getInt(3));
                    break;
                case "Rocher":
                    unPlateau.ajoutRocher(cursorCase.getInt(2), cursorCase.getInt(3));
                    break;
            }
            cursorCase.moveToNext();
        }
        cursorCase.close();
        cursorPlateau.close();

        return unPlateau;
    }
    //insère un  plateau déja créer en objet dans la bdd
    public void InserPlateauEtCase (Plateau unPlateau,int idPlateau){
        creationPlateau(idPlateau,unPlateau.getHauteurPlateau(),unPlateau.getHauteurPlateau());
        for(int x=0;x<unPlateau.getHauteurPlateau();x++){
            for(int y=0;y<unPlateau.getHauteurPlateau();y++){
                if (unPlateau.getPlateau()[x][y] instanceof Out){
                    creationCase(idPlateau,x,y,"Out");
                }else if (unPlateau.getPlateau()[x][y]instanceof Pion){
                    creationCase(idPlateau,x,y,"Vide");
                }else if (unPlateau.getPlateau()[x][y]instanceof Rocher){
                    creationCase(idPlateau,x,y,"Rocher");
                }else {
                    creationCase(idPlateau,x,y,"In");
                }
            }
        }
    }



    /*//fonction qui lit le niveau
    public void readNiveau(Niveau niv,int id){
        String reqSQl="SELECT NOM_NIV, IMA_NIV FROM NIVEAU WHERE ID_NIV="+id;
        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQl,null);
       cursor.moveToFirst();
        niv.setImageFond(cursor.getString(1));
        niv.setNom(cursor.getString(0));
        }*/

 /*   //fonction qui vérifie si un niveau bloqué
    public boolean estBloquer(int id){
        String reqSQL="SELECT BLO_NIV FROM NIVEAU WHERE ID_NIV="+id;
        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQL,null);
        cursor.moveToFirst();
        if(cursor.getInt(0)==1){
            return true;
        }else{
            return false;
        }
    }*/

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
