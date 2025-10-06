package models;
import java.util.Scanner;

public class MenuMessage {
    private Scanner scanner = new Scanner(System.in);

    public void chooseOption()
    {
        this.showOptions();
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                seeMessages();
                break;
            case 2:
                sendMessage();
                break;
            case 3:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void showOptions()
    {
        System.out.println("Select an option:");
        System.out.println("1 - Ver mensagens");
        System.out.println("2 - Enviar mensagem");
        System.out.println("3 - Sair");
    }

    public void seeMessages()
    {

    }

    public void sendMessage()
    {

    }


}
