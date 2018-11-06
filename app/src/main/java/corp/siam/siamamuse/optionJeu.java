package corp.siam.siamamuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class optionJeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_jeu);
    }

    public void RetourMenu(View view) { //quitter l'activity actuel.
        this.finish();
    }

    public void LancerLeJeu(View v){
        Intent intent = new Intent(this, partie.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
    }
}
