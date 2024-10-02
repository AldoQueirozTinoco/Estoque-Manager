package model;

import java.util.Date;

public class Registry {

    Date data;
    String nomeDoProduto;
    float valorDeTransacao;
    int quantia;
    String tipo;



    public Registry(Date data, String nomeDoProduto, float valorDeTransacao, int quantia, String tipo) {
        this.data = data;
        this.nomeDoProduto = nomeDoProduto;
        this.valorDeTransacao = valorDeTransacao;
        this.quantia = quantia;
        this.tipo = tipo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNomeDoProduto() {
        return nomeDoProduto;
    }

    public void setNomeDoProduto(String nomeDoProduto) {
        this.nomeDoProduto = nomeDoProduto;
    }

    public float getValorDeTransacao() {
        return valorDeTransacao;
    }

    public void setValorDeTransacao(float valorDeTransacao) {
        this.valorDeTransacao = valorDeTransacao;
    }

    public int getQuantia() {
        return quantia;
    }

    public void setQuantia(int quantia) {
        this.quantia = quantia;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }



    @Override
    public String toString() {
        return "Produto{" +
                "data=" + data +
                ", nomeDoProduto='" + nomeDoProduto + '\'' +
                ", valorDeTransacao=" + valorDeTransacao +
                ", quantia=" + quantia +
                ", tipo=" + tipo + '\'' +
                '}';
    }


}
