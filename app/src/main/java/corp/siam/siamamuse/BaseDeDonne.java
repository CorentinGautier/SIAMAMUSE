package corp.siam.siamamuse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BaseDeDonne extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Siam.db";
    private static final int DATABASE_VERSION=1;
    public static final String TABLE_PLATEAU =
            "CREATE TABLE  PLATEAU ("
                    + " ID_PLA  INTEGER PRIMARY KEY , "
                    + " TAILLE_X  INTEGER, "
                    + " TAILLE_Y  INTEGER);";
    public static final String DROP_PLATEAU ="DROP TABLE PLATEAU";
    public static final String TABLE_CASE =
            "CREATE TABLE  CASEE  ("
                    + "ID_CAS  INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + " ID_PLA_CAS  INTEGER, "
                    + " COOR_X_CAS  INTEGER, "
                    + " COOR_Y_CAS  INTEGER,"
                    + " TYPE_CAS TEXT);";//"out" "rocher" "spawn"
    public static final String DROP_CASE ="DROP TABLE CASEE";
    Context context;

    public BaseDeDonne(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }
    //Creation de la base de donner quand on change de version
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CASE);
        db.execSQL(TABLE_PLATEAU);
        Log.i("DATABASE","onCreate invoked");

    }
    //Supprime la base de donner et la recrée quand on change de version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_CASE);
        db.execSQL(DROP_PLATEAU);
        onCreate(db);
        Log.i("DATABASE","onUpgrade invoked");
    }
    //Niveau Toy storie
    public void creationNiveauToyStorie(){
        String nivstr="insert into NIVEAU (ID_NIV,NOM_NIV,IMA_NIV,BLO_NIV) values (1,'Toy Storie','imageToyStorie',0)";
        this.getWritableDatabase().execSQL(nivstr);
        Log.i("DATABASE","creation niveau toy storie");
        creationetape(0,"petitTS",1,100,0);
        creationetape(500,"petitTS",1,600,0);
        creationetape(700,"petitTS",1,400,0);
        creationetape(700,"petitTS",1,700,0);

        creationetape(4000,"petitTS",1,200,1);
        creationetape(1000,"moyenTS",1,500,0);
        creationetape(190,"petitTS",1,300,0);
        creationetape(350,"petitTS",1,100,0);
        creationetape(2000,"petitTS",1,700,0);

        creationetape(4000,"moyenTS",1,600,1);
        creationetape(700,"petitTS",1,200,0);
        creationetape(700,"petitTS",1,300,0);
        creationetape(1200,"petitTS",1,500,0);
        creationetape(1600,"moyenTS",1,400,0);
        creationetape(700,"petitTS",1,200,0);
        creationetape(900,"petitTS",1,700,0);

        creationetape(4000,"petitTS",1,400,1);
        creationetape(700,"moyenTS",1,300,0);
        creationetape(1200,"petitTS",1,200,0);
        creationetape(1000,"petitTS",1,100,0);
        creationetape(10,"petitTS",1,600,0);

        creationetape(4000,"petitTS",1,700,1);
        creationetape(500,"petitTS",1,300,0);
        creationetape(5000,"bossTS",1,200,0);
    }
    //Niveau monstre
    public void creationNiveaumostre(){
        int niveau = 2;
        String nivstr="insert into NIVEAU (ID_NIV,NOM_NIV,IMA_NIV,BLO_NIV) values ("+niveau+",'monstre','imageMonstre',1)";
        this.getWritableDatabase().execSQL(nivstr);
        Log.i("DATABASE","creation niveau toy storie");
        //Vague 1
        creationetape(0,"monstrePetit",niveau,100,0);
        creationetape(10,"monstreMoyen",niveau,400,0);
        creationetape(10,"monstrePetit",niveau,650,0);
        creationetape(2500,"monstreMoyen",niveau,500,0);
        creationetape(1500,"monstrePetit",niveau,100,0);
        creationetape(10,"monstreMoyen",niveau,500,0);
        //vague 2
        creationetape(3000,"monstreMoyen",niveau,100,1);
        creationetape(10,"monstreMoyen",niveau,600,0);
        creationetape(10,"monstrePetit",niveau,300,0);
        creationetape(2500,"monstreMoyen",niveau,200,0);
        creationetape(800,"monstreMoyen",niveau,400,0);
        creationetape(800,"monstrePetit",niveau,200,0);
        creationetape(10,"monstrePetit",niveau,550,0);
        //vague 3
        creationetape(3000,"monstreMoyen",niveau,100,1);
        creationetape(10,"monstrePetit",niveau,300,0);
        creationetape(10,"monstreMoyen",niveau,600,0);
        creationetape(2500,"monstreMoyen",niveau,150,0);
        creationetape(100,"monstreMoyen",niveau,450,0);
        creationetape(2500,"monstreMoyen",niveau,150,0);
        creationetape(100,"monstreMoyen",niveau,550,0);
        creationetape(2500,"monstrePetit",niveau,100,0);
        creationetape(500,"monstrePetit",niveau,500,0);
        creationetape(500,"monstrePetit",niveau,200,0);
        creationetape(500,"monstrePetit",niveau,600,0);
        creationetape(500,"monstrePetit",niveau,400,0);
        creationetape(500,"monstrePetit",niveau,700,0);
        // vague 4
        creationetape(4500,"monstreMoyen",niveau,700,1);
        creationetape(10,"monstrePetit",niveau,150,0);
        creationetape(10,"monstreMoyen",niveau,500,0);
        creationetape(800,"monstrePetit",niveau,700,0);
        creationetape(2500,"monstreMoyen",niveau,200,0);
        creationetape(100,"monstrePetit",niveau,500,0);
        creationetape(100,"monstrePetit",niveau,650,0);
        creationetape(1500,"monstreMoyen",niveau,200,0);
        creationetape(400,"monstrePetit",niveau,600,0);
        creationetape(600,"monstreMoyen",niveau,100,0);
        creationetape(1500,"monstrePetit",niveau,700,0);
        creationetape(800,"monstreMoyen",niveau,400,0);
        creationetape(800,"monstreMoyen",niveau,50,0);
        creationetape(10,"monstreMoyen",niveau,650,0);
        creationetape(1000,"monstreMoyen",niveau,350,0);
        creationetape(800,"monstrePetit",niveau,700,0);
        creationetape(10,"monstrePetit",niveau,100,0);
        creationetape(800,"monstrePetit",niveau,300,0);

        //vague 5 boss
        creationetape(5000,"monstreBoss",niveau,250,1);

    }
    //Creation des etapes de chaque niveau
    public void creationetape(int temps,String typesoucoupe,int idNiveau,int cordX,int vague){
        String etastr="insert into ETAPE (ID_NIV_ETA,TYPE_SOUC_ETA,TEM_ETA,CORDX_ETA,VAG_ETA) values ("+idNiveau+",'"+typesoucoupe+"',"+temps+","+cordX+","+vague+")";
        this.getWritableDatabase().execSQL(etastr);
    }

    //fonction qui lit la base de donnée et crée une liste d'étapes
//    public List<Etape> readEtape(int idNiv){
//        List<Etape> lesEtapes = new ArrayList<>();
//        String reqSQL = "SELECT TYPE_SOUC_ETA,TEM_ETA,CORDX_ETA,VAG_ETA FROM ETAPE WHERE ID_NIV_ETA = "+idNiv;
//        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQL,null);
//        cursor.moveToFirst();
//        while (! cursor.isAfterLast()){
//            Etape uneEtape = new Etape(cursor.getString(0),cursor.getInt(1),cursor.getInt(2),(MainActivity)context,cursor.getInt(3));
//            lesEtapes.add(uneEtape);
//            cursor.moveToNext();
//        }
//        cursor.close();
//        return lesEtapes;
//    }
//
//    //fonction qui lit le niveau
//    public void readNiveau(Niveau niv,int id){
//        String reqSQl="SELECT NOM_NIV, IMA_NIV FROM NIVEAU WHERE ID_NIV="+id;
//        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQl,null);
//        cursor.moveToFirst();
//        niv.setImageFond(cursor.getString(1));
//        niv.setNom(cursor.getString(0));
//    }

    //fonction qui retourne le nombre d'étape d'un niveaux
    public int tailleNiveau(int id){
        String reqSQL="SELECT COUNT(ID_ETA) FROM ETAPE WHERE ID_NIV_ETA ="+id;
        Cursor cursor = this.getReadableDatabase().rawQuery(reqSQL,null);
        cursor.moveToFirst();
        return cursor.getInt(0);
    }

    //fonction qui permet de debloquer un niveau quand on à réussit a faire celui d'avant
    public void debloquerNiveau(int id){
        String etastr="UPDATE NIVEAU SET BLO_NIV=0 WHERE ID_NIV="+id;
        this.getWritableDatabase().execSQL(etastr);
    }

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
