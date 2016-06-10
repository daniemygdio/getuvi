package br.edu.etep.prova;

/* Falta incluir os métodos get, set e toString */
public class Cidade {
    private int codigo;
    private String nome, uf;
    private String data, hora;
    private int iuv;

    public Cidade() {
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "codigo=" + codigo +
                ", nome='" + nome + '\'' +
                ", uf='" + uf + '\'' +
                ", data='" + data + '\'' +
                ", hora='" + hora + '\'' +
                ", iuv=" + iuv +
                '}';
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getIuv() {
        return iuv;
    }

    public void setIuv(int iuv) {
        this.iuv = iuv;
    }

    public Cidade(int codigo, String nome, String uf, String data, String hora, int iuv) {
        this.codigo = codigo;
        this.nome = nome;
        this.uf = uf;
        this.data = data;
        this.hora = hora;
        this.iuv = iuv;
    }
}