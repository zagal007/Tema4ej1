package h.virgos.gonzalo.tema4ej2almacenamientoenfichero;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText et1,et2;
    InputStreamReader rarchivo;
    OutputStreamWriter oarchivo;
    String todo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et1 = (EditText) findViewById(R.id.et1);
        et2 = (EditText) findViewById(R.id.et2);
        try {
            rarchivo = new InputStreamReader(
                    openFileInput("notas.txt"));
            BufferedReader br = new BufferedReader(rarchivo);
            String linea = br.readLine();
            todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();
            rarchivo.close();
            et2.setText(todo);
        } catch (IOException e) {
        }

    }

    public void añadir(View view) {

        try {


            String nuevo = et1.getText().toString();
            try {
                rarchivo = new InputStreamReader(
                        openFileInput("notas.txt"));
                BufferedReader br = new BufferedReader(rarchivo);
                String linea = br.readLine();
                todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
            } catch (IOException e) {
            }finally {
                rarchivo.close();
            }
            oarchivo = new OutputStreamWriter(openFileOutput(
                    "notas.txt", Activity.MODE_PRIVATE));
            todo = todo + nuevo;
            oarchivo.write(todo);

        } catch (IOException e) {
        }finally {
            try {
                oarchivo.flush();
                oarchivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Toast t = Toast.makeText(this, "Los datos fueron grabados",
                Toast.LENGTH_SHORT);
        t.show();

    }

    public void leer(View view){
        try {
            rarchivo = new InputStreamReader(
                    openFileInput("notas.txt"));
            BufferedReader br = new BufferedReader(rarchivo);
            String linea = br.readLine();
            todo = "";
            while (linea != null) {
                todo = todo + linea + "\n";
                linea = br.readLine();
            }
            br.close();

            et2.setText(todo);
        } catch (IOException e) {
        }finally {
            try {
                rarchivo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
//    InputStreamReader archivo = new InputStreamReader(
  //          openFileInput("notas.txt"));

 //   OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
 //           "notas.txt", Activity.MODE_PRIVATE));