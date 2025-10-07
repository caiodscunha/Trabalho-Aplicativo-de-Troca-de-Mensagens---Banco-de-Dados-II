package models;
import repository.MessageRepository;

import java.util.Scanner;

public class MenuMessage {
    private final Scanner scanner = new Scanner(System.in);
    private final String receiverName;
    private final MessageRepository messageRepository = new MessageRepository();

    public MenuMessage(String receiverName) {
        this.receiverName = receiverName;
    }


    public void chooseOption()
    {
        int option = 0;

        while (option != 3)
        {
            this.showOptions();
            option = scanner.nextInt();

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
        MessageRepository.loadMessagesForReceiver(receiverName);
        messageRepository.printSummary();
        System.out.println("Aperte ENTER para voltar para o menu!");
        scanner.nextLine();
    }

    public void sendMessage()
    {

    }


}
