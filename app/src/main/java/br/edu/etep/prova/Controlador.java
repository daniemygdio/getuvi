package br.edu.etep.prova;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by alunos on 01/06/2016.
 */
public class Controlador {
    private SQLiteDatabase bd;
    private Banco banco;

    public Controlador(Context context) {
        banco = new Banco(context);
    }

    /***
     * Insere dados de cidade na tabela tbCidade.
     * @param cidade - Cidade a ser cadastrada.
     * @return id que deve ser -1 caso haja erro.
     */
    public long inserir(Cidade cidade) {
        ContentValues valores = new ContentValues();
        bd = banco.getWritableDatabase();
        valores.put("codigo", cidade.getCodigo());
        valores.put("nome", cidade.getNome());
        valores.put("uf", cidade.getUf());
        valores.put("data", cidade.getData());
        valores.put("hora", cidade.getHora());
        valores.put("iuv", cidade.getIuv());
        long id = bd.insert("tbCidade", null, valores);
        Log.e("DDD", "cadastrou"+id);
        return id;
    }

    public List<Cidade> selecionar() {
        List<Cidade> lista = new ArrayList<>();
        Cidade cidade;
        Cursor cursor;
        Log.d("CONTROLADORD", "Antes de Readable");
        bd = banco.getReadableDatabase();
        Log.d("CONTROLADORD", "Depois de Readable");
        cursor = bd.rawQuery("select * from tbCidade", null);
        if(cursor != null) {
            cursor.moveToFirst();
            Log.d("CONTROLADORD", "cursor NOT NULL");
            int i = 0;
            while (i < 10) {
                Log.d("CONTROLADORD", "dentro do while");
                cidade = new Cidade();
                cidade.setCodigo(Integer.parseInt(cursor.getString(0)));
                cidade.setNome(cursor.getString(1));
                cidade.setUf(cursor.getString(2));
                cidade.setData(cursor.getString(3));
                cidade.setHora(cursor.getString(4));
                cidade.setIuv(Integer.parseInt(cursor.getString(5)));
                lista.add(cidade);
                cursor.moveToNext();
                i++;
            }

            cursor.close();
        }

        bd.close();
        return lista;
    }
}
