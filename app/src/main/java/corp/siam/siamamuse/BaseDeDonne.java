package corp.siam.siamamuse;

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
    private int idMax ;
    public static final String IDMAX_CASE ="SELECT MAX(ID_CAS) FROM CASEE";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_PLATEAU =
            "CREATE TABLE  PLATEAU ("
                    + " ID_PLA  INTEGER PRIMARY KEY , "
                    + " NB_LIGNE  INTEGER, "
                    + " NB_COLONNE INTEGER);";
    public static final String DROP_PLATEAU ="DROP TABLE PLATEAU";
    public static final String TABLE_CASE =
            "CREATE TABLE  CASEE  ("
                    + "ID_CAS  INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " ID_PLA_CAS  INTEGER, "
                    + " COOR_X_CAS  INTEGER, "
                    + " COOR_Y_CAS  INTEGER,"
                    + " TYPE_CAS TEXT);";//"out" "rocher" "spawn""vide"
    public static final String DROP_CASE ="DROP TABLE CASEE";

    Context context;

    public BaseDeDonne(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
        Cursor cursor = this.getReadableDatabase().rawQuery(IDMAX_CASE,null);
        cursor.moveToFirst();
        if(cursor.getInt(0)!=0){
            this.idMax = cursor.getInt(0);
        }else{
            this.idMax =1;
        }

    }
    //Creation de la base de donner quand on change de version
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CASE);
        db.execSQL(TABLE_PLATEAU);
        Log.i("DATABASE","onCreate invoked");

    }
    //Supprime la base de données et la recrée quand on change de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CASE);
        db.execSQL(DROP_PLATEAU);
        onCreate(db);
        Log.i("DATABASE","onUpgrade invoked");
    }

    //Creation d'une case
    public void creationCase(int idPlateau, int cordX, int cordY,String type){
        String creacase="insert into CASEE (ID_CAS,ID_PLA_CAS,COOR_X_CAS,COOR_Y_CAS,TYPE_CAS) values ("+idMax+1+",'"+idPlateau+"',"+cordX+","+cordY+","+type+")";
        this.getWritableDatabase().execSQL(creacase);
    }
    //création d'un plateau
    public void creationPlateau(int idPlateau, int Nbligne, int Nbcolonne){
        String creaPlateau="insert into PLATEAU (ID_PLA,NB_LIGNE,NB_COLONNE) values ("+idPlateau+",'"+Nbligne+"',"+Nbcolonne+")";
        this.getWritableDatabase().execSQL(creaPlateau);
    }
    //changement d'un état de la case
    public void UpdateCaseEtat(int idCase, String typeCase,int idPlateau){
        String updateCaseEtat="UPDATE CASEE SET TYPE_CAS="+typeCase+" WHERE ID_CAS ="+idCase+" AND ID_PLA_CAS="+idPlateau+" " ;
        this.getWritableDatabase().execSQL(updateCaseEtat);
    }
    //changement de la taille du plateau
    public void UpdateTaillePlateau(int idPlateau,int newNbLigne,int newNbColonne){
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


//
//    //fonction qui lit le niveau
//    public void readNiveau(Niveau niv,int id){
//        String reqSQl="SELECT NOM_NIV, IMA_NIV FROM NIVEAU WHERE ID_NIV="+id;
//        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQl,null);
//        cursor.moveToFirst();
//        niv.setImageFond(cursor.getString(1));
//        niv.setNom(cursor.getString(0));
//    }

    //fonction qui vérifie si un niveau bloqué
    public boolean estBloquer(int id){
        String reqSQL="SELECT BLO_NIV FROM NIVEAU WHERE ID_NIV="+id;
        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQL,null);
        cursor.moveToFirst();
        if(cursor.getInt(0)==1){
            return true;
        }else{
            return false;
        }
    }

    public static int getDatabaseVersion() {
        return DATABASE_VERSION;
    }
}
