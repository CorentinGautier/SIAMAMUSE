package corp.siam.siamamuse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity { //page d'acceuil

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ActivityOption(View v){
        Intent intent = new Intent(this, option.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent);
    }

    public void ActivityOptionJeu(View viewview){
        Intent intent2 = new Intent(this, optionJeu.class); // l'activité où on est en ce moment et la prochaine activity
        startActivity(intent2);
    }

    public void QuitterLeJeu(View view) { //quitter l'activity actuel.
        this.finish();
    }
}
