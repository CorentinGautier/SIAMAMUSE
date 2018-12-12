package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public abstract class Btn  {

    protected ImageButton btnVerre;
    protected final Activity_Partie context;
    protected MoteurJeu mj;
    protected Pion unPion;
    protected int xPla,yPla;
    protected PlateauInterface plateauInterface;

    public Btn(int xPla, int yPla, final Activity_Partie context, MoteurJeu mj, PlateauInterface plateauInterface){
        this.context=context;
        this.mj=mj;
        this.xPla=xPla;
        this.yPla=yPla;
        this.plateauInterface=plateauInterface;
        btnVerre = new ImageButton(context);
        setBoutton(true);

    }

    public void setBoutton(boolean deplacementPossible){
        btnVerre = new ImageButton(context);
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()),(int)(PlateauInterface.calc.getTailleCase()));
        btnVerre.setLayoutParams(paramsPion);
        btnVerre.setX(PlateauInterface.calc.calculeBtnAjoutX(xPla));
        btnVerre.setY(PlateauInterface.calc.calculeBtnAjoutY(yPla));

        if(deplacementPossible){
            btnVerre.setBackgroundResource(R.drawable.carrevert);
            btnVerre.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionbtn();
                }
            });
        }else{
            btnVerre.setBackgroundResource(R.drawable.carrerouge);
        }
    }



    public abstract void actionbtn();

    public void effacer(){
        context.fondPartie.removeView(btnVerre);
    }
}
