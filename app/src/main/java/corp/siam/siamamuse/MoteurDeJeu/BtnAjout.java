package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public class BtnAjout {

    private ImageButton btnVerre;
    private final Activity_Partie context;
    private MoteurJeu mj;
    private Pion unPion;
    private int xPla,yPla;

    public BtnAjout(int xPla, int yPla, final Activity_Partie context,MoteurJeu mj){
        this.context=context;
        this.mj=mj;
        this.xPla=xPla;
        this.yPla=yPla;
        btnVerre = new ImageButton(context);
        btnVerre.setBackgroundResource(R.drawable.carrevert);
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.tailleCase),(int)(PlateauInterface.tailleCase));
        btnVerre.setLayoutParams(paramsPion);
        btnVerre.setX((int)((PlateauInterface.tailleCase*xPla)+(PlateauInterface.tailleCase*0.1)));
        btnVerre.setY((int)(PlateauInterface.posHautGauchY+(PlateauInterface.tailleCase*yPla)+(PlateauInterface.tailleCase*0.1)));
        btnVerre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionbtn();
            }
        });
    }

    public void affiche(Pion unPion){
        this.unPion = unPion;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                context.fondPartie.addView(btnVerre,xPla,yPla);
            }
        });

    }

    public void actionbtn(){
        mj.getLePlateau().ajouterPion(unPion,xPla,yPla);
    }

    public void effacer(){
        context.fondPartie.removeView(btnVerre);
    }
}
