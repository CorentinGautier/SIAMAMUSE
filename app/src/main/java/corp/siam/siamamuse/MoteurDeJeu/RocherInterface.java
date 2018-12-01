package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;

public class RocherInterface {
    private Activity_Partie context;
    private Rocher unRocher;
    private final ImageView imageRocher;

    public RocherInterface(Rocher unRocher, int x, int y, final Activity_Partie context){
        this.context=context;
         imageRocher = new ImageView(context);
        imageRocher.setBackgroundResource(unRocher.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase*0.8),(int)(PlateauInterface.tailleCase*0.8));
        imageRocher.setLayoutParams(paramsPion);
        imageRocher.setX((int)((PlateauInterface.tailleCase*x)+(PlateauInterface.tailleCase*0.1)));
        imageRocher.setY((int)(PlateauInterface.posHautGauchY+(PlateauInterface.tailleCase*y)+(PlateauInterface.tailleCase*0.1)));
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imageRocher);
            }
        });
    }
}
