package corp.siam.siamamuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Credit extends AppCompatActivity {

    public int largeurEcrant,hauteurEcrant;
    private TextView txtMembreGroupe;
    private TextView txtTitreActivity;
    private TextView txtProfesseur;
    private Button btnRetour;
    private ImageView imgLogoIUT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        txtMembreGroupe = findViewById(R.id.textMembre);
        txtTitreActivity = findViewById(R.id.titreCredit);
        imgLogoIUT = findViewById(R.id.imageLogoIUT);
        btnRetour = findViewById(R.id.buttonRetour);
        txtProfesseur = findViewById(R.id.textProfesseur);

        calculTailleEcrant();
        dispositionInterface();
    }

    public void dispositionInterface(){
        //coordX
        txtTitreActivity.setX((int)(largeurEcrant*0.1));
        imgLogoIUT.setX((int)(largeurEcrant*0.08));
        txtMembreGroupe.setX((int)(largeurEcrant*0.1));
        btnRetour.setX((int)(largeurEcrant*0.05));
        txtProfesseur.setX((int)(largeurEcrant*0.1));
        //CoordY
        txtTitreActivity.setY((int)(hauteurEcrant*0.1));
        imgLogoIUT.setY((int)(hauteurEcrant*0.2));
        txtMembreGroupe.setY((int)(hauteurEcrant*0.5));
        txtProfesseur.setY((int)(hauteurEcrant*0.75));
        btnRetour.setY((int)(hauteurEcrant*0.88));

        //Redimmensionnement
        ViewGroup.LayoutParams paramsTextTitre = txtTitreActivity.getLayoutParams();
        paramsTextTitre.width = (int)(largeurEcrant*0.8);
        paramsTextTitre.height = (int)(hauteurEcrant*0.30);
        ViewGroup.LayoutParams paramsImageLogo = imgLogoIUT.getLayoutParams();
        paramsImageLogo.width = (int)(largeurEcrant*0.8);
        paramsImageLogo.height = (int)(hauteurEcrant*0.30);
        ViewGroup.LayoutParams paramsTextMembreGroupe = txtMembreGroupe.getLayoutParams();
        paramsTextMembreGroupe.width = (int)(largeurEcrant*0.9);
        paramsTextMembreGroupe.height = (int)(hauteurEcrant*0.25);
        ViewGroup.LayoutParams paramsButtonRetour = btnRetour.getLayoutParams();
        paramsButtonRetour.width = (int)(largeurEcrant*0.2);
        paramsButtonRetour.height = (int)(hauteurEcrant*0.07);
        ViewGroup.LayoutParams paramsTextProfesseur = txtProfesseur.getLayoutParams();
        paramsTextProfesseur.width = (int)(largeurEcrant*0.9);
        paramsTextProfesseur.height = (int)(hauteurEcrant*0.25);

        txtMembreGroupe.setText("Membres du groupe: \nCOUPRIE Aymeric\nGAUTIER Corentin\nLOUARN Glenn\nMILCENT Corentin\nPEROUMAL-TEVANIN Christophe");
        txtProfesseur.setText("Professeur référent :\nGEORGE Sebastien");
    }

    public void calculTailleEcrant(){
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        largeurEcrant = metrics.widthPixels;
        hauteurEcrant = metrics.heightPixels;
    }

    public void RetourMenu(View view) { //quitter l'activity actuel.
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }
}
