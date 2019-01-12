package corp.siam.siamamuse.MoteurDeJeu;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import corp.siam.siamamuse.Activity_Partie;
import corp.siam.siamamuse.R;

public abstract class Btn  {

    protected ImageButton btnImage;
    protected Activity_Partie context;
    protected MoteurJeu mj;
    protected Pion unPion;
    protected int xPla,yPla;
    protected PlateauInterface plateauInterface;

    public Btn(int xPla, int yPla, Activity_Partie context, MoteurJeu mj, PlateauInterface plateauInterface,boolean deplacementPossible){
        this.context=context;
        this.mj=mj;
        this.xPla=xPla;
        this.yPla=yPla;
        this.plateauInterface=plateauInterface;
        btnImage = new ImageButton(context);
        setBoutton(deplacementPossible);
    }

    public void setBoutton(boolean deplacementPossible){
        btnImage = new ImageButton(context);
        ViewGroup.LayoutParams paramsPion = new ViewGroup.LayoutParams((int)(PlateauInterface.calc.getTailleCase()),(int)(PlateauInterface.calc.getTailleCase()));
        btnImage.setLayoutParams(paramsPion);
        btnImage.setX(PlateauInterface.calc.calculeBtnAjoutX(xPla));
        btnImage.setY(PlateauInterface.calc.calculeBtnAjoutY(yPla));

        if(deplacementPossible){
            btnImage.setBackgroundResource(R.drawable.carrevert);
            btnImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    actionbtn();
                }
            });
        }else{
            btnImage.setBackgroundResource(R.drawable.carrerouge);
        }
    }

    public abstract void actionbtn();

    public void effacer(){
        context.fondPartie.removeView(btnImage);
    }
}
