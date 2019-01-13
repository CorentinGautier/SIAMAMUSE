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
            R.drawable.swipe,
            R.drawable.ajout,
            R.drawable.deplacement,
            R.drawable.rotation,
            R.drawable.deplaceemntous,
            R.drawable.caserouge,
            R.drawable.timer,
    };

    // liste de titre
    public String[] lst_title = {
            "Bienvenue sur le tutoriel",
            "Ajout d'un pion",
            "Cases vertes",
            "Flèches rouges",
            "Choix des déplacements possibles",
            "Indication au long de la partie",
            "Timer",
    };

    // liste de descriptions
    public String[] lst_description = {
            "Swiper vers la droite ou la gauche pour voir le tutoriel",
            "Pour pouvoir ajouter un pion, on doit tout d’abord cliquer sur le pion dans la liste de votre côter de l'écran que l’on souhaite placer sur l’écran.",
            "Lors de la selection d'un pion, des cases vertes apparaissent et nous propose des cases où l'on peut endroit placer notre pion",
            "Les flèches rouges indique l’orientation de votre pion pour finaliser l’action",
            "Vous pouvez faire un double tap ou alors choisir le direction en cliquant sur les flèches rouges lorsque vous avez cliquer sur le bouton rotation ou choisit sur les cases vertes la position du pion choisit",
            "Après que chacun des joueurs aient ajoutés des pion sur le plateau, on obtient ce plateau par exemple. Lorsque l’on veut déplacer un pion du plateau, il est indiqué vos choix possibles de jeu",
            "Lorsque le timer est à zéro on passe son tour au joueur suivant. Il peut être désactivé ou modifié dans les options de début de partie",
    };

    // list des couleurs en background
    public int[] lst_backgroundcolor = {
            Color.rgb(74, 255, 51),
            Color.rgb(51, 255, 251),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),
            Color.rgb(255, 215, 51),
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