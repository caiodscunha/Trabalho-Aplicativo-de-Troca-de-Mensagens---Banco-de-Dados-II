package br.edu.puc;
import models.MenuRegister;
import models.MenuMessage;
import models.User;

public class Main {
    public static void main(String[] args) {
        MenuRegister menuRegister = new MenuRegister();

        // Executa o menu de registro/login
        menuRegister.chooseOption();

        // Fecha conexão com MongoDB
        models.MongoHandler.close();
    }
}
