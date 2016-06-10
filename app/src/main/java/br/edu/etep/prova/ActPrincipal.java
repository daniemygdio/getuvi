package br.edu.etep.prova;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.facebook.stetho.Stetho;

/**
 * Created by alunos on 02/06/2016.
 */
public class ActPrincipal extends Activity {
    private static ListView lvDados;
    private Button btVisualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);

        Stetho.initializeWithDefaults(this);

        final Intent intent = new Intent(getBaseContext(), Servico.class);
        startService(intent);

        btVisualizar = (Button) findViewById(R.id.btVisualizar);
        btVisualizar.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                startActivity(new Intent(getBaseContext(), ActNova.class));
                return true;
            }
        });

   }
}
