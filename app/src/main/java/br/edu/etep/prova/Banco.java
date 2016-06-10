package br.edu.etep.prova;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by alunos on 01/06/2016.
 */
public class Banco extends SQLiteOpenHelper {
    private static final String BD_NOME = "bdCadastro";
    private static final int VERSAO = 1;

    public Banco(Context context) {
        super(context, BD_NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        String sql = "CREATE TABLE if not exists tbCidade (" +
                " codigo integer primary key," +
                " nome text not null," +
                " uf text not null," +
                " data text not null," +
                " hora text not null," +
                " iuv integer not null)";
        bd.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int oldVersion, int newVersion) {
        try {
            bd.execSQL("drop table if exists tbCidade");
        } catch (SQLException e) {
            Log.e("ERRO", e.getMessage());
        }
        onCreate(bd);
    }
}
