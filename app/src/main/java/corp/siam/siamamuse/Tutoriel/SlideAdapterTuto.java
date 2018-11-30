package corp.siam.siamamuse.Tutoriel;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import corp.siam.siamamuse.R;

public class SlideAdapterTuto extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    // liste d'images
    public int[] lst_images = {
            R.drawable.elephant,
            R.drawable.tuto_un,
            R.drawable.tuto_deux,

            R.drawable.tuto_trois,
            R.drawable.tuto_quatre,
            R.drawable.tuto_cinq,

            R.drawable.tuto_six,
            R.drawable.tuto_sept,
            R.drawable.tuto_huit,

            R.drawable.rhinoceros
    };

    // liste de titre
    public String[] lst_title = {
            "Context",
            "Se déplacer sur une case libre et changer l’orientation de son animal sur une case.",
            "Orientation 1",

            "Orientation 2",
            "Pousser 1",
            "Pousser 2",

            "Pousser 3",
            "Pousser 4",
            "Le gagnant",

            "info complémentaire",


    };

    // liste de descriptions
    public String[] lst_description = {
            "Nous sommes au Royaume de SIAM, jadis véritable paradis terrestre, terre d’immensité ou éléphants et rhinocéros vivaient en paix depuis des siècles. Un jour la terre se mit à trembler et SIAM fut alors réduite à trois régions entourées de gigantesques montages montagnes. Depuis éléphants et rhinocéros n’ont plus assez d’espace pour vivre, ces deux espèces d’une force incroyable vont alors se livrer à une lutter sans merci pour régner.",
            "Vous pouvez changer l’orientation de votre animal sur sa case de ¼ ou de ½ tour. Ce coup compte comme tu tour de jeux, Vous ne pouvez pas déplacer qu’une seule case et de façon orthogonal. (Pas de déplacement en diagonal) l’orientation de votre animal n’est pas liée à la direction de son déplacement. Tout en déplaçant, vous pouvez à votre guise changer l’orientation de votre animal.",
            "Vous ne pouvez pousser que dans la direction indiquée par le chevron noir.",

            "Un animal orienté dans la bonne direction peut pousser un rocher. Deux animaux orienté dans la bonne direction peuvent pousser deux rochers. Trois animaux orientés dans la bonne direction peuvent pousser 3 rochers",
            "Un animal ne peut pousser un autre animal qui lui fait face (peu importe à qui appartient l’animal) : ils se neutralisent. En effet, rhinocéros et éléphants ont la même force de poussée : pour pouvoir pousser, il faut qu’il y ait donc une majorité d’animaux qui poussent dans la même direction. Précision : un de vos animaux peut empêcher votre poussés, un animal adverse peut aider votre poussée.",
            "Un animal peut pousser à lui un ou plusieurs s’ils ne lui font pas face",

            "Vous pouvez pousser en entrant une pièce sur le plateau.",
            "Pour résoudre des situations de poussée plus compliquée, il suffit de regarder les animaux qui ont le même sens de poussée et de les déduire de ceux qui font opposition. Ensuite il faut voir si ceux qui restent sont en nombre suffisant pour pousser des rochers.",
            "Lorsqu’un rocher du plateau, la partie est terminé. Mais attention : le gagnant est le joueur qui est le plus proche du Rocher et dans le même sens de poussée.",

            "Un animal qui sort du plateau suite à une poussé n’est pas éliminé, il est récupéré par son propriétaire et pourra être joué plus tard dans la partie.",

        };

    // list des couleurs en background
    public int[] lst_backgroundcolor = {
            Color.rgb(55, 55, 55),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),

            Color.rgb(5, 246, 100),
            Color.rgb(110, 22, 86),
            Color.rgb(123, 49, 70),

            Color.rgb(110, 49, 18),
            Color.rgb(210, 140, 50),
            Color.rgb(200, 200, 200),

            Color.rgb(100, 40, 50),
            Color.rgb(160, 60, 55),

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
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {//supprimer les objets du layout
        container.removeView((LinearLayout) object);
    }
}
