package br.edu.etep.prova;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alunos on 01/06/2016.
 */
public class Servico extends IntentService {

    private Cidade cidade;

    public Servico() { super(Servico.class.getSimpleName()); }

    @Override
    protected void onHandleIntent(Intent intent) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager)
                    getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo;
            Log.e("SERVICO", "passou aqui");
            if (connectivityManager != null) {
                networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
                    busca10Dados();
                }
            }
        }
        catch(Exception e){
            Log.e("SERVICO", e.getMessage());
        }
    }

    private void busca10Dados() {
        for(int i = 0; i < 10; i++) {
            buscarDadosNoSite(220+i);
        }
        Log.e("SERVICO", "Busca dados terminou!");
    }

    private void buscarDadosNoSite(int codigo) {
        InputStream stream = null;

        try {
            URL url = new URL("http://servicos.cptec.inpe.br/XML/cidade/"+codigo+"/condicoesAtuaisUV.xml");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(1000);
            conn.setConnectTimeout(1500);
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.connect();
            stream = conn.getInputStream();
            Log.e("SERVICO", "passou aqui");
            if(stream != null) {
                cidade = lerInputStream(stream, codigo);
                Controlador controlador = new Controlador(getBaseContext());
                controlador.inserir(cidade);
                Log.e("SERVICO", "passou aqui");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if(stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    Log.e("ERRO: ", e.getMessage());
                }

            }
        }
    }

    /* Ler o InputStream para retornar um objeto Cidade */
    private static Cidade lerInputStream(InputStream stream, int codigo) throws Exception {
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(stream, null); /* Percorre o documento XML */
        parser.nextTag();
        String nome = null, uf = null, data = null, hora = null, iuv = null;
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) continue; /* Dentro da tag <uv> serão lidas as tags <nome>, <uf>, <data>, <hora> e <iuv> */
            if (parser.getName().equals("nome")) nome = lerTexto(parser);
            else if (parser.getName().equals("uf")) uf = lerTexto(parser);
            else if (parser.getName().equals("data")) data = lerTexto(parser);
            else if (parser.getName().equals("hora")) hora = lerTexto(parser);
            else if (parser.getName().equals("iuv")) iuv = lerTexto(parser);
        }
        Cidade cidade = null;
        if (nome != null && uf != null && data != null && hora != null && iuv != null)
            cidade = new Cidade(codigo, nome, uf, data, hora, Integer.parseInt(iuv));

        return cidade;
    }

    /* Ler o conteúdo da tag, ou seja, o texto */
    private static String lerTexto(XmlPullParser parser) throws Exception {
        String res = "";
        if (parser.next() == XmlPullParser.TEXT) {
            res = parser.getText();
            parser.nextTag();
        }
        return res;
    }
}
