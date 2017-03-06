package mx.itesm.m6_srb_labo_listaspersonalizadas2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class JugadorActivity extends AppCompatActivity  implements View.OnClickListener {

    EditText edNombreJugador ;
    Button btnTomarFoto;
    Button btnGuardar;
    ImageView ivFoto;
    final int REQUEST_IMAGE_CAPTURE = 1;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
        edNombreJugador = (EditText) findViewById(R.id.jugador_nombre);
        btnTomarFoto    = (Button) findViewById(R.id.button_tomar_foto);
        btnGuardar      = (Button) findViewById(R.id.button_guadar_jugador);
        ivFoto          = (ImageView) findViewById(R.id.i_v_foto);
        btnGuardar.setOnClickListener(this);
        btnTomarFoto.setOnClickListener(this);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            edNombreJugador.setText(bundle.getString("nombre"));
            imageBitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("foto"),0,bundle.getByteArray("foto").length);
        }
        ivFoto.setImageBitmap(imageBitmap);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
             imageBitmap = (Bitmap) data.getExtras().get("data");
            ivFoto.setImageBitmap(imageBitmap);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_tomar_foto:
                Intent fotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (fotoIntent.resolveActivity(getPackageManager()) != null){
                    startActivityForResult(fotoIntent,REQUEST_IMAGE_CAPTURE);
                }
                break;
            case R.id.button_guadar_jugador:
                if (edNombreJugador.getText() != null){
                    Toast.makeText(this.getApplicationContext(), "El jugador se ha registrado exitosamente", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent();
                    intent.putExtra("nombre",edNombreJugador.getText().toString());

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);

                    byte[] byteArray = stream.toByteArray();
                    intent.putExtra("foto",byteArray);
                    setResult(RESULT_OK, intent);
                    finish();
                }else {
                    Toast.makeText(this.getApplicationContext(),"Datos incorrectos",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
