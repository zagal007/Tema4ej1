package h.virgos.gonzalo.tema4ej1sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText et1;
    TextView tv2, tv3,tv4;
    Button bt1,bt2;
    int secreto, intent,record;
    SharedPreferences prefe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.et1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);
        tv4 = (TextView) findViewById(R.id.tv4);
        bt1 = (Button) findViewById(R.id.bt1);
        bt2 = (Button) findViewById(R.id.bt2);
        secreto = 1 + (int) (Math.random() * 10);
        intent = 0;
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        record =  prefe.getInt("Record", 10);
        tv3.setText("El mejor recor hasta ahora es: " + record + " intentos");
        tv4.setText("Ultima vez : " + prefe.getInt("puntos", 0) + " intentos");
    }
     public void verificar(View view){
        int aux = Integer.parseInt(et1.getText().toString());
         intent = intent + 1;
         if(secreto == aux){
             String msn = "Correcto el numero era el: " + secreto;
             Toast toast = Toast.makeText(getApplicationContext(), msn,Toast.LENGTH_SHORT);
             toast.show();
             tv2.setText("El numero correcto era= " + secreto + "    intentos: " + intent);
             tv2.setVisibility(View.VISIBLE);
             bt1.setVisibility(View.INVISIBLE);
             bt2.setVisibility(View.VISIBLE);
             SharedPreferences.Editor editor = prefe.edit();
             editor.putInt("puntos", intent);
             if(record > intent){
                 editor.putInt("Record", intent);
             }
             editor.commit();

         }else{
             String msn = "Vuelve a probar";
             Toast toast = Toast.makeText(getApplicationContext(), msn,Toast.LENGTH_SHORT);
             toast.show();
         }
     }

    public void volver(View view){
        secreto = 1 + (int) (Math.random() * 10);
        intent = 0;
        tv2.setVisibility(View.INVISIBLE);
        bt2.setVisibility(View.INVISIBLE);
        bt1.setVisibility(View.VISIBLE);
        prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
        record =  prefe.getInt("Record", 10);
        tv3.setText("El mejor recor hasta ahora es: " + record + " intentos");
        tv4.setText("Ultima vez : " + prefe.getInt("puntos", 0) + " intentos");
    }
}
