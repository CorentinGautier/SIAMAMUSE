package corp.siam.siamamuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import corp.siam.siamamuse.MoteurDeJeu.MoteurJeu;
import corp.siam.siamamuse.MoteurDeJeu.Plateau;
import corp.siam.siamamuse.MoteurDeJeu.PlateauInterface;

public class Activity_Partie extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    public RelativeLayout fondPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partie);
        calculTailleEcrant();
        fondPartie = (RelativeLayout) findViewById(R.id.pageJeu);
        try {
            creationPlateau();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }
    public void FinDeLaPartie(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }

    public void creationPlateau() throws IOException, SAXException, ParserConfigurationException {
        Log.e("TEST","jksdjfjkfsdkjsfd");
        MoteurJeu mj = new MoteurJeu(5,this);
        PlateauInterface pi= new PlateauInterface(this);
        pi.convertionMatriceAffichage(mj.getLePlateau());
    }
}
