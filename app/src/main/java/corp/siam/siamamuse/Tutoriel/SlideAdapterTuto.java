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
           R.drawable,
            R.drawable.elephant,
            R.drawable.plateausiam,
                };

    // liste de titre
    public String[] lst_title = {
            " Présentation",
            "Jeu n°2 : Déplacement de la voiture",
            "Version 4.0",


    };

    // liste de descriptions
    public String[] lst_description = {
            "Nous sommes au Royaume de SIAM, jadis véritable paradis terrestre, terre d’immensité ou éléphants et rhinocéros vivaient en paix depuis des siècles. Un jour la terre se mit à trembler et SIAM fut alors réduite à trois régions entourées de gigantesques montages montagnes. Depuis éléphants et rhinocéros n’ont plus assez d’espace pour vivre, ces deux espèces d’une force incroyable vont alors se livrer à une lutter sans merci pour régner.",
            "elephant = le plus intelligent",
            "vous jouerez sur ce plateau"
    };

    // list des couleurs en background
    public int[] lst_backgroundcolor = {
            Color.rgb(55, 55, 55),
            Color.rgb(239, 85, 85),
            Color.rgb(110, 49, 89),

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
