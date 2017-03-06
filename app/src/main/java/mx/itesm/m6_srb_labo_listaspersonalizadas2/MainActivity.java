package mx.itesm.m6_srb_labo_listaspersonalizadas2;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class MainActivity extends ListActivity implements AdapterView.OnItemClickListener {

    int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;
    ArrayList<Jugador> jugadores;
    JugadorAdapter jugadorAdapter;
    int indexJugador;
    int REQUEST_CODE_AGREGAR = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jugadores = getDataForListView();
        jugadorAdapter = new JugadorAdapter(getApplicationContext(),R.layout.row,jugadores);
        setListAdapter(jugadorAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if ( resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {
                Bundle extras = data.getExtras();
                imageBitmap = (Bitmap) extras.get("data");
            }
            if (requestCode == REQUEST_CODE_AGREGAR){
                Bundle datos = data.getExtras();
                Jugador jugador = new Jugador(datos.getByteArray("foto"),0,null,datos.getString("nombre"),0);
                jugadores.set(indexJugador,jugador);
                jugadorAdapter.notifyDataSetChanged();
            }
        }

    }

    public ArrayList<Jugador> getDataForListView(){

        Jugador jugador;
        ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.person);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();

        jugador = new Jugador(byteArray,0,null,"Messi",0);
        listaJugadores.add(jugador);

        jugador = new Jugador(byteArray,0,null,"Ronaldo",0);
        listaJugadores.add(jugador);

        jugador = new Jugador(byteArray,0,null,"Neymar",0);
        listaJugadores.add(jugador);

        return listaJugadores;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent;
        intent = new Intent(this,JugadorActivity.class);
        Jugador jugador = jugadores.get(position);
        intent.putExtra("nombre",jugador.getNombre());
        intent.putExtra("foto",jugador.getByteArrayFoto());
        startActivityForResult(intent,REQUEST_CODE_AGREGAR);
        indexJugador = position;
    }
}
