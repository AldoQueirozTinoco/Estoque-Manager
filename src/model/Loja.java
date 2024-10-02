package model;

import static services.Services.scanner;

public class Loja {
    private static int contadorId =0;
    String name;
    int id;
    String localizacao;

    Estoque estoque = new Estoque();

    public Loja(){
        this.id = contadorId++;

        System.out.print("Nome da Loja: ");
        scanner.nextLine();
        this.name=scanner.nextLine();

        System.out.print("Localizacao: ");
        this.localizacao = scanner.nextLine();
    }

    public Loja(String nome, int id, String localizacao) {
        this.name = nome;
        this.id = id;
        this.localizacao = localizacao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }
}
