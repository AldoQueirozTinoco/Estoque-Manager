package services;

import controller.ApplicationController;
import model.*;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;


public class Services {

        public static Scanner scanner = new Scanner(System.in);

        //Cadastro de produtos no estoque
    /*
    * Coloca o id do produto
    * Escreve o produto no cadastro
    * */
    public static void cadastrar(Estoque estoque){

        Product produto = new Product();

        //Set id do produto
        if(!estoque.produtosEmEstoque.isEmpty()){
        produto.setId(estoque.produtosEmEstoque.getLast().getId() + 1);
        }else{
            produto.setId(1);
        }


        try{
            //Escreve o produto na file
            FileWriter myWriter = new FileWriter("estoque.txt",true);
            myWriter.write(produto.toString());
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Successfully wrote in file!");

            //Cadastra o produto na lista de produtos do Estoque
            estoque.produtosEmEstoque.add(produto);
        } catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }

    public static void registrarOperacao(Estoque estoque, Product produto,int quantia, Boolean compra){
        Instant inst = Instant.now();
        String tipo = null;
        if(compra){

            tipo = "Compra";
            Registry registro = new Registry(Date.from(inst), produto.getName(), produto.getPrecoCompra() * quantia,quantia,tipo);

                //Cadastra o Regostro na lista de registros do Estoque
                estoque.registroDeComprasEVendas.add(registro);

        }else{
            tipo = "Venda";
            Registry registro = new Registry(Date.from(inst), produto.getName(), produto.getPrecoVenda() * quantia,quantia,tipo);

                //Cadastra o Registro na lista de registros do Estoque
                estoque.registroDeComprasEVendas.add(registro);

        }
    }

    public static void compra(Product produto, Estoque estoque){
        //Para fins didáticos o gerente têm dinheiro infinito
        /*
        * 1 Colocamos o tipo como compra
        * 2 Mudamos a quantia em estoque
        * 3 Registramos a operacao no registro
        *
        */
        System.out.print("Quanto deseja comprar do produto?: ");
        scanner.nextLine();
        int quantia = scanner.nextInt();
        Boolean compra = true;

        produto.setQuantiaEmEstoque(produto.getQuantiaEmEstoque() + quantia);
        registrarOperacao(estoque,produto,quantia,compra);
    }
    public static void venda(Product produto,Estoque estoque){
        System.out.print("Por quanto deseja vender do produto?: ");
        scanner.nextLine();
        int quantia = scanner.nextInt();
        if(quantia > produto.getQuantiaEmEstoque()){
            System.out.println("Não podes vender mais do que tem em estoque!");
            return;
        //Tentou vender mais que tem... F
        }
        Boolean compra = false;

        produto.setQuantiaEmEstoque(produto.getQuantiaEmEstoque() - quantia);
        registrarOperacao(estoque,produto,quantia,compra);
    }
    public static void consulta(Estoque estoque){
        int quantos =0;
        Boolean rodando = true;
        while(rodando){
        System.out.println("Como deseja consultar o estoque?");
        System.out.println("1:id\n2:nome\n3:faixa de quantidade em estoque\n4:produtos em baixo estoque\n5:Listar tudo!\n6:Consultar registro\n7:Voltar");
        System.out.print("Digite a opção: ");
        int opcao = scanner.nextInt();
        switch(opcao){
            case 1:
                System.out.print("Digite o id: ");
                int id = scanner.nextInt();
                quantos =0;
                for(Product produto: estoque.produtosEmEstoque){
                    if(produto.getId() == id){
                        System.out.println(produto);
                        quantos++;
                    }
                }
                    if(quantos ==0){
                        System.out.println("Sem produtos com esse id!");
                    }
                break;
            case 2:
                scanner.nextLine();
                System.out.print("Digite o nome: ");
                String name = scanner.nextLine();
                quantos =0;
                for(Product produto: estoque.produtosEmEstoque){
                    if(produto.getName().contains(name)){
                        System.out.println(produto);
                        quantos++;
                    }
                }
                if(quantos ==0){
                    System.out.println("Sem produtos com esse nome!");
                }
                break;
            case 3:
                System.out.print("Digite 2 inputs, a faixa inicial e a final: ");
                int faixa1 = scanner.nextInt();
                int faixa2= scanner.nextInt();
                quantos =0;
                for(Product produto: estoque.produtosEmEstoque){
                    if(produto.getQuantiaEmEstoque() >=faixa1 && produto.getQuantiaEmEstoque() <=faixa2){
                        System.out.println(produto);
                        quantos++;
                    }
                }
                if(quantos ==0){
                    System.out.println("Sem produtos nessa faixa de quantiade!");
                }
                break;
            case 4:
                quantos=0;
                System.out.println("Produtos com quantidade em estoque abaixo de 10: ");
                for(Product produto: estoque.produtosEmEstoque){
                    if(produto.getQuantiaEmEstoque() < 10){
                        System.out.println(produto);
                        quantos++;
                    }
                }
                if(quantos ==0){
                    System.out.println("Sem produtos com em baixo estoque!");
                }
                break;
            case 5:
               listarProdutos(estoque);
                break;
            case 6:
                if(ApplicationController.usuarioAtual.getPermission() == Permissions.ADMIN || ApplicationController.usuarioAtual.getPermission() == Permissions.GERENTE){

                int counter = 1;
                for(Registry registro: estoque.registroDeComprasEVendas){
                    System.out.println(counter + ". Nome: " + registro.getNomeDoProduto() + " Tipo: " + registro.getTipo() + " ValorDeTransacao: " + registro.getValorDeTransacao());
                    counter++;
                }
                }else{
                    System.out.println("Permissao negada!");
                }
                break;
            case 7:
                 rodando = false;
                break;
        }
    }
    }

    public static void listarProdutos(Estoque estoque){
        int counter =1;

        for(Product produto: estoque.produtosEmEstoque){
            System.out.println(counter + ". Nome: " +produto.getName() + " Valor: " + produto.getPrecoCompra() + " Quantia em estoque: " + produto.getQuantiaEmEstoque());
            counter++;
        }

    }
    public static void listarLojas(ArrayList<Loja> lojas){
        int counter = 1;
        for(Loja loja: lojas){
            System.out.println(counter + ". Nome: " +loja.getName() + " Localizacao: " + loja.getLocalizacao() );
            counter++;
        }
    }

    public static Boolean login(ArrayList<User> usuarios){
        scanner.nextLine();
        System.out.print("\n");
        System.out.print("Digite seu nome:");
        String nome = scanner.nextLine();
        System.out.print("Digite sua senha:");
        String senha = scanner.nextLine();

        for(User usuario: usuarios){
           if(usuario.getName().compareTo(nome) == 0 && usuario.getSenha().compareTo(senha)==0){
               ApplicationController.usuarioAtual = usuario;
               System.out.println("Logado com sucesso!");
               return false;
           }
        }
        System.out.println("Nome ou senha incorretos!");
        return true;
    }
    public static boolean isNameInUse(String name){
        for(User usuario: ApplicationController.usuarios){
            if(Objects.equals(name, usuario.getName())){
                return true;
            }
        }
        return false;
    }

    public static boolean isPermissionNotValid(String permission){
        //Se a permissao for igual a uma das permissoes existentes ela eh valida
        //logo, permissionIsNotValid eh falso
        if(permission.equals("ADMIN") || permission.equals("FUNCIONARIO") || permission.equals("GERENTE")){
            return false;
        }
        //Se a permissao digitada for invalida, retorna true
        return true;
    }

    public static void userManipulation(){
        int escolha;
                int counter=1;
                boolean rodando = true;
        while (rodando) {
        System.out.println("O que deseja fazer?\n1.Criar usuario\n2.Atualizar usuario\n3.Remover usuario\n4.Voltar");
        escolha = scanner.nextInt();
        switch(escolha){
            case 1:
                User novoUsuario = new User();
                ApplicationController.usuarios.add(novoUsuario);
                break;
            case 2:
                System.out.println("Qual usuario deseja atualizar?");
                for(User usuario: ApplicationController.usuarios){
                    System.out.println(counter+". "+usuario.getName());
                    counter++;
                }
                escolha = scanner.nextInt();
                if(ApplicationController.usuarios.get(escolha-1) != ApplicationController.usuarioAtual){
                ApplicationController.usuarios.add(escolha-1,new User());
                }else{
                    System.out.println("Nao podes mudar a si mesmo!");
                }
                break;
            case 3:
                System.out.println("Qual usuario deseja remover?");
                for(User usuario: ApplicationController.usuarios){
                    System.out.println(counter+". "+usuario.toString());
                    counter++;
                }
                escolha = scanner.nextInt();
                if(ApplicationController.usuarios.get(escolha-1) != ApplicationController.usuarioAtual){
                    ApplicationController.usuarios.remove(escolha-1);
                }else{
                    System.out.println("Nao podes remover a si mesmo!");
                }
                break;
            case 4:
                rodando = false;
                break;
        }
        }
    }
}
