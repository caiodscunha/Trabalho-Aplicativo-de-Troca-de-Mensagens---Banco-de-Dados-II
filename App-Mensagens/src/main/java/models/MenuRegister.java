package models;

import java.util.Scanner;

public class MenuRegister {
    private final Scanner scanner = new Scanner(System.in);

    public void chooseOption()
    {
        int option = 0;

        while (option != 3)
        {
            this.showOptions();
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    loginUser();
                    break;
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void showOptions()
    {
        System.out.println("Select an option:");
        System.out.println("1 - Registrar");
        System.out.println("2 - Login");
        System.out.println("3 - Sair");
    }

    public void registerUser()
    {
        System.out.println("Username: ");
        String username = scanner.next();

        System.out.println("Email: ");
        String email = scanner.next();

        System.out.println("Password: ");
        String password = scanner.next();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            System.out.println("Envie todos os campos!");
            return;
        }

        User user = User.register(username, email, password);

        if (user == null) {
            System.out.println("Usuário ou email já existem!");
        } else {
            System.out.println("Usuário registrado com sucesso: " + user);
        }
    }

    public void loginUser()
    {
        System.out.println("Email: ");
        String email = scanner.next();

        System.out.println("Password: ");
        String password = scanner.next();

        if (email.isEmpty() || password.isEmpty())
        {
            System.out.println("Envie todos os campos!");
            return;
        }
        User user = User.login(email, password);

        if (user == null) {
            System.out.println("Email ou senha incorretos!");
        } else {
            System.out.println("Login realizado com sucesso!");
            System.out.println("Bem-vindo, " + user.getUsername() + "!");
        }
    }




}
