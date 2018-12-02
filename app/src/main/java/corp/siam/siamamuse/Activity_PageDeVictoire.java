package corp.siam.siamamuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Activity_PageDeVictoire extends AppCompatActivity {

    private boolean victoireElephant = true;
    private TextView affichageVictoire;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_de_victoire);
        affichageVictoire = findViewById(R.id.affichageVictoire);
        afficherTexteVictoire();
    }

    public void afficherTexteVictoire(){
        if(victoireElephant)
            affichageVictoire.setText(R.string.annonceVictoireElephant);
        else
            affichageVictoire.setText(R.string.annonceVictoireRhinoceros);
    }


    public void QuitterPageDeVictoire(View v){
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }
    public void Revanche(View v){
        Intent intent = new Intent(this, Activity_Partie.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
        this.finish();
    }
}
