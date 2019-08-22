
package com.trianacodes.script.sd;

import android.app.Activity;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.function.ToIntBiFunction;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombreArch, et_contenido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_nombreArch = findViewById(R.id.et_NombreArch);
        et_contenido = findViewById(R.id.et_Contenido);

    }

    public void Guardar(View view){

        String nombre = et_nombreArch.getText().toString();
        String contenido = et_contenido.getText().toString();

        try{

            File tarjetaSd = Environment.getExternalStorageDirectory();
            Toast.makeText(this,tarjetaSd.getPath(),Toast.LENGTH_LONG).show();
            File rutaArchivo = new File(tarjetaSd.getPath(), nombre);
            OutputStreamWriter abrir_archivo = new OutputStreamWriter(openFileOutput(nombre, Activity.MODE_PRIVATE));
            abrir_archivo.write(contenido);
            abrir_archivo.flush();
            abrir_archivo.close();
            Toast.makeText(this, "Guardado correctamente.", Toast.LENGTH_LONG).show();
            et_nombreArch.setText("");
            et_contenido.setText("");

        } catch (IOException e){

            Toast.makeText(this, "No se puede guardar el archivo", Toast.LENGTH_LONG).show();

        }

    }

    public void Abrir(View view){

        String nombre = et_nombreArch.getText().toString();

        try{

            File tarjetaSd = Environment.getExternalStorageDirectory();
            File rutaArchivo = new File(tarjetaSd.getPath(), nombre);
            InputStreamReader abrirArchivo = new InputStreamReader(openFileInput(nombre));
            BufferedReader leerArchivo = new BufferedReader(abrirArchivo);
            String linea = leerArchivo.readLine();
            String contenidoCompleto = "";

            while( linea != null){
                contenidoCompleto = contenidoCompleto + linea + "\n";
                linea = leerArchivo.readLine();
            }

            leerArchivo.close();
            abrirArchivo.close();
            et_contenido.setText(contenidoCompleto);

        } catch (IOException e){

            Toast.makeText(this, "No se ha podido abrir el archivo", Toast.LENGTH_LONG).show();

        }

    }

}
