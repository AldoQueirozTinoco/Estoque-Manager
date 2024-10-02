package controller;

import model.Estoque;
import model.Loja;
import model.Permissions;
import model.User;
import services.Services;

import java.util.ArrayList;

import static services.Services.*;

public class ApplicationController {
   public static User  usuarioAtual;
   public static Estoque estoqueAtual;
    public static ArrayList<User> usuarios = new ArrayList<>();
    public static ArrayList<Loja> lojas = new ArrayList<>();

    static int opcao=0;
    static int escolha;
    static Boolean rodando = true;

     Estoque estoque = new Estoque();

     //Loop principal da aplicacao
    public ApplicationController(){
        while (rodando) {
            loginOptions(usuarios);
            if(!rodando){
                break;
            }

            Boolean voltarAoLogin = lojaOptions();
            if(voltarAoLogin){
                continue;
            }else if(!rodando){
                break;
            }
            rodando = estoqueOptions(estoque);
        }
    }

    public  void loginOptions(ArrayList<User> usuarios){
        //Rodando é setado como falso ao fazer login e permite passar para as opções de estoque
        while(rodando){
            System.out.println("1. Fazer login\n2. Fazer Cadastro\n3. Sair");
            opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    //Seta rodando false para sair do loop da funcao
                    rodando = Services.login(usuarios);
                    break;
                case 2:
                    User novoUsuario = new User();
                    usuarios.add(novoUsuario);
                    break;
                case 3:
                    rodando = false;
                    break;
            }
        }
        //Recoloca rodando como true para rodar o Application, se nao a aplicacao para
        rodando = true;
    }

    public static Boolean lojaOptions(){
            boolean logoff = false;
        while(rodando){
            System.out.println("1. Criar loja\n2. Escolher loja\n3. Listar lojas\n4. Manipular usuarios\n5. Deslogar\n6. Sair");
            opcao = scanner.nextInt();
            switch(opcao){
                case 1:
                    if(usuarioAtual.getPermission()== Permissions.ADMIN){
                        System.out.println("Permissão Concedida!");
                        Loja novaLoja = new Loja();
                        lojas.add(novaLoja);
                    }else{
                        System.out.println("Permissão negada!");
                    }
                    break;
                case 2:
                    if(!lojas.isEmpty()){
                    System.out.println("Qual loja deseja acessar?");
                    listarLojas(lojas);
                    opcao = scanner.nextInt();
                    estoqueAtual = lojas.get(opcao-1).getEstoque();
                    }else{
                        System.out.println("Nao existem lojas criadas!");
                    }
                    return false;

                case 3:
                    listarLojas(lojas);
                    break;

                case 4:
                    if(usuarioAtual.getPermission()== Permissions.ADMIN){
                        System.out.println("Permissão Concedida!");
                        userManipulation();
                    }else{
                        System.out.println("Permissão negada!");
                    }
                    break;
                case 5:
                //Retorna true, o que volta para o continue e permite o usuario logar
                return true;
                case 6:
                    rodando = false;
                    break;
            }
        }
        //Caso 5, se o usuario seleciona sair o valor de rodando sai do loop, logoff=false permite que o usuario nao volte ao login e rodando = false faz com que a aplicacao pare
        return logoff;
    }
    public  Boolean estoqueOptions(Estoque estoque){
        while (rodando) {
            System.out.println("O quê deseja fazer?");
            System.out.println("1.Cadastrar produto\n2.Comprar produto\n3.Vender produto\n4.Consultar estoque\n5.Deslogar\n6.Sair");
            opcao = scanner.nextInt();

            switch(opcao){
                case 1:
                    Services.cadastrar(estoque);
                    break;
                case 2:
                    System.out.println("Qual produto deseja comprar?");
                    Services.listarProdutos(estoque);
                    escolha = scanner.nextInt();
                    Services.compra(estoque.produtosEmEstoque.get(escolha - 1),estoque);
                    break;
                case 3:
                    System.out.println("Qual produto deseja vender?");
                    Services.listarProdutos(estoque);
                    escolha = scanner.nextInt();
                    Services.venda(estoque.produtosEmEstoque.get(escolha - 1 ),estoque);
                    break;
                case 4:
                    Services.consulta(estoque);
                    break;
                case 5:
                    return true;
                    case 6:
                        return false;
            }
        }
        return true;
    }
}
