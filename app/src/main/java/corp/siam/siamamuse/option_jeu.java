package corp.siam.siamamuse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class option_jeu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_jeu);
    }

    public void RetourMenu(View view) { //quitter l'activity actuel.
        this.finish();
    }
}
