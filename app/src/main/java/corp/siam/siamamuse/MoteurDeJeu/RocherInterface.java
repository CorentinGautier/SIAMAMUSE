package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import corp.siam.siamamuse.Activity_Partie;

public class RocherInterface {
    private Activity_Partie context;
    private Rocher unRocher;
    private ImageView imageRocher;

    public RocherInterface(Rocher unRocher, int x, int y, final Activity_Partie context){
        this.context=context;
         imageRocher = new ImageView(context);
        imageRocher.setBackgroundResource(unRocher.getImagePion());
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()*0.8),(int)(PlateauInterface.calc.getTailleCase()*0.8));
        imageRocher.setLayoutParams(paramsPion);
        imageRocher.setX(PlateauInterface.calc.calculePionPlateauX(x));
        imageRocher.setY(PlateauInterface.calc.calculePionPlateauY(y));
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(imageRocher);
            }
        });
    }

    public ImageView getImageRocher() {
        return imageRocher;
    }
}
