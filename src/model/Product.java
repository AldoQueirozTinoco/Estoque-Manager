package model;

import java.util.Locale;
import java.util.Scanner;

public class Product {
    private static int contadorId = 0;
    int id;
    String name;
    String description;
    float precoCompra;
    float precoVenda;
    int quantiaEmEstoque;

    public Product() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.of("pt","BR"));

        this.id = contadorId++;
        System.out.print("Digite o nome do produto: ");
        this.name = scanner.nextLine();
        System.out.print("\n");
        System.out.print("Digite a descrição do produto: ");
        this.description = scanner.nextLine();
        System.out.print("\n");
        System.out.print("Digite o preço de compra do produto: ");
        this.precoCompra = scanner.nextFloat();
        System.out.print("\n");
        System.out.print("Digite o preço de venda do produto: ");
        this.precoVenda = scanner.nextFloat();
        System.out.print("\n");

        //O usuário deve primeiro cadastrar o produto para depois comprá-lo
        this.quantiaEmEstoque = 0;
    }

    public Product(int id, String name, String description, float precoCompra, float precoVenda, int quantiaEmEstoque) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.quantiaEmEstoque = quantiaEmEstoque;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantiaEmEstoque() {
        return quantiaEmEstoque;
    }

    public void setQuantiaEmEstoque(int quantiaEmEstoque) {
        this.quantiaEmEstoque = quantiaEmEstoque;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrecoCompra() {
        return precoCompra;
    }

    public void setPrecoCompra(float precoCompra) {
        this.precoCompra = precoCompra;
    }

    public float getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(float precoVenda) {
        this.precoVenda = precoVenda;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", precoCompra=" + precoCompra +
                ", precoVenda=" + precoVenda +
                ", estoque=" + quantiaEmEstoque +
                '}';
    }

}
