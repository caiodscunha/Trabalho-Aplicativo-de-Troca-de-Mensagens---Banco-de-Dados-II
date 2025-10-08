package br.edu.puc;
import models.MenuRegister;
import models.MenuMessage;
import models.User;

public class Main {
    public static void main(String[] args) {
        MenuRegister menuRegister = new MenuRegister();

        // Executa o menu de registro/login
        menuRegister.chooseOption();

        // Após login, vamos assumir que o usuário logado é retornado
        // Para simplificar, você pode modificar MenuRegister para retornar o User logado
        // Aqui vamos pedir o email do usuário logado
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        System.out.print("Digite seu email para acessar o menu de mensagens: ");
        String email = scanner.nextLine();

        // Consulta o usuário logado no Mongo
        System.out.print("Digite sua senha: ");
        String password = scanner.nextLine();
        User loggedUser = User.login(email, password);

        if (loggedUser != null) {
            System.out.println("Bem-vindo " + loggedUser.getUsername() + "!");

            MenuMessage menuMessage = new MenuMessage(loggedUser.getUsername());
            try {
                menuMessage.chooseOption();
            } catch (Exception e) {
                System.out.println("Erro ao acessar o menu de mensagens: " + e.getMessage());
            }
        } else {
            System.out.println("Login falhou. Saindo do sistema.");
        }

        // Fecha conexão com MongoDB
        models.MongoHandler.close();
    }
}
