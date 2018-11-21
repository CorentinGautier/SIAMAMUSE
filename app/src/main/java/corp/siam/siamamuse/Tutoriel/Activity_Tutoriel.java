package corp.siam.siamamuse.Tutoriel;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import corp.siam.siamamuse.MainActivity;
import corp.siam.siamamuse.R;

public class Activity_Tutoriel extends AppCompatActivity {

    private ViewPager viewPager;
    private SlideAdapterTuto myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutoriel);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        myadapter = new SlideAdapterTuto(this);
        viewPager.setAdapter(myadapter);

    }
    public void Retour(View view) {//retour menu
        Intent intent = new Intent(this, MainActivity.class); // l'activité où on est vers la prochaine
        startActivity(intent);
        this.finish();
    }
}
