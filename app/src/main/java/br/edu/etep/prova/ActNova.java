package br.edu.etep.prova;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Created by alunos on 02/06/2016.
 */
public class ActNova extends Activity {
    private static ListView lvDados;
    private ArrayAdapter<Cidade> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_nova);
        stopService(new Intent(this, Servico.class));
        Log.e("ActNova", "criada");
        listarNoLv();
    }

    private void listarNoLv() {
        try {
            Controlador controlador = new Controlador(getBaseContext());

            lvDados = (ListView) findViewById(R.id.lvDados);
            Log.e("ActNova", "ANTES ADAPTER");

            List<Cidade> lista = controlador.selecionar();
            if(lista != null) {
                adapter = new ArrayAdapter<Cidade>(this, android.R.layout.simple_list_item_1, lista);
            } else {
                throw new Exception("Lista de cidades nula!");
            }


            Log.e("ActNova", "act nova CHEGOU NO ADAPTER");
            if(adapter != null) {
                lvDados.setAdapter(adapter);
            } else {
                Log.e("ActNova", "adapter null");
            }
        } catch (Exception e) {
            Log.e("ActNova", e.toString());
        }

    }
}
