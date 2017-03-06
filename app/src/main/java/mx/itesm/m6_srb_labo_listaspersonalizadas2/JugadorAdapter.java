package mx.itesm.m6_srb_labo_listaspersonalizadas2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saul on 18/2/2017.
 */

public class JugadorAdapter extends ArrayAdapter<Jugador> {

    public JugadorAdapter(Context context, int resource, List<Jugador> objects) {
        super(context,resource,objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row,parent,false);
        }
        Jugador jugador = getItem(position);

        if(jugador != null) {
            TextView tvNombre = (TextView) convertView.findViewById(R.id.text_nombre_r);
            ImageView ivFoto  = (ImageView) convertView.findViewById(R.id.i_v_foto_r);

            tvNombre.setText(jugador.getNombre());
            byte[] byteArray = jugador.getByteArrayFoto();
            Bitmap imageBitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            ivFoto.setImageBitmap(imageBitmap);
        }
        return  convertView;
    }

}
