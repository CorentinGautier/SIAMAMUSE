package corp.siam.siamamuse.Tutoriel;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import corp.siam.siamamuse.R;

public class SlideAdapterTuto extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Scroller scroll;

    // liste d'images
    public int[] lst_images = {
            R.drawable.ajout,
            R.drawable.deplacement,
            R.drawable.rotation,
            R.drawable.deplaceemntous,
            R.drawable.caserouge,
            R.drawable.timer,



    };

    // liste de titre
    public String[] lst_title = {
            "Ajout d'un pion",
            "Case verte",
            "Positionnement d'un pion",
            "Choix des déplacements possibles",
            "Zones rouges",
            "Timer",


    };

    // liste de descriptions
    public String[] lst_description = {
            "Pour pouvoir ajouter un pion, on doit tout d’abord cliquer sur le pion que l’on veut placer dans la liste de pion sur l’écran.",
            "Ensuite alors apparaît sur le plateau des cases vertes où l'on vous indique dans quel endroit placer votre pion",
            "Vous devez maiantenant choisir l’orientation de votre pion pour finaliser l’action",
            "Après que chacun des joueurs aient ajoutés un pion dans le plateau on obtient ce plateau par exemple. Lorsque l’on veut déplacer un pion du plateau, deux choix s’offre à nous soit on passe directement au changement de la rotation et donc on ne déplace pas le pion ; soit on déplace le pion et choisit sa nouvelle orientation.",
            "Vous ne pouvz pas déplacer votre pion sur les zones rouges",
            "Lorsque le timer est à zéro on passe son tour au joueur suivant. Il peut être désactivé dans les options de début de partie comme dit plus haut.",

    };

    // list des couleurs en background
    public int[] lst_backgroundcolor = {
            Color.rgb(55, 55, 55),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),
            Color.rgb(200, 42, 1),
            Color.rgb(4, 42, 167),
            Color.rgb(150, 4, 200),

    };


    public SlideAdapterTuto(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) { //affiche le tuto avec des slides
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);


        LinearLayout layoutslide = (LinearLayout) view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView txttitle = (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtdescription);

        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        description.setMovementMethod(new ScrollingMovementMethod());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//supprimer les objets du layout
        container.removeView((LinearLayout) object);
    }
}