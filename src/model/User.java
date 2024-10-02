package model;

import static services.Services.*;

public class User {
    String name;
    String senha;
    String email;
    Permissions permission;

    public User() {

        System.out.print("Nome: ");
        scanner.nextLine();
        this.name = scanner.nextLine();

        boolean naoEhUmNomeValido= isNameInUse(this.name);

        while(naoEhUmNomeValido){
            System.out.println("Este nome ja esta em uso! Escolha outro:");
            this.name = scanner.nextLine();
            naoEhUmNomeValido = isNameInUse(this.name);
        }

        System.out.print("Senha: ");
        this.senha = scanner.nextLine();
        System.out.print("Email: ");
        this.email = scanner.nextLine();


        System.out.print("Permissao(ADMIN,FUNCIONARIO,GERENTE): ");
        String input =  scanner.nextLine().toUpperCase();

        boolean naoEhPermissaoValida= isPermissionNotValid(input);
            if(naoEhPermissaoValida){

            while(naoEhPermissaoValida) {
                System.out.println("Escreva um cargo válido (ADMIN,FUNCIONARIO,GERENTE)!");
                input = scanner.nextLine().toUpperCase();
                if(!isPermissionNotValid(input)) {
                     this.permission = Permissions.valueOf(input);
                    System.out.println("Cadastro realizado!");
                    naoEhPermissaoValida =false;
                }
            }
            }else{
                this.permission = Permissions.valueOf(input);
                System.out.println("Cadastro realizado!");
            }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Permissions getPermission() {
        return permission;
    }

    public void setPermission(Permissions permission) {
        this.permission = permission;
    }
}
