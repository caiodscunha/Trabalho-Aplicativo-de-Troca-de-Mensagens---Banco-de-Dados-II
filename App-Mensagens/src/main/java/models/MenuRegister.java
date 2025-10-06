package models;

import java.util.Scanner;

public class MenuRegister {
    private Scanner scanner = new Scanner(System.in);

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

    }

    public void loginUser()
    {

    }


}
