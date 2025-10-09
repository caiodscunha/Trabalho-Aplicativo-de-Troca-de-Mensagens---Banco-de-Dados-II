package models;

import repository.MessageRepository;
import java.util.List;
import java.util.Scanner;

public class MenuMessage {
    private final Scanner scanner = new Scanner(System.in);
    private final String senderName;
    private final MessageRepository messageRepository = new MessageRepository();

    public MenuMessage(String senderName) {
        this.senderName = senderName;
    }

    public void chooseOption() throws Exception {
        int option = 0;

        while (option != 3) {
            this.showOptions();
            option = scanner.nextInt();
            scanner.nextLine();

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
                    break;
            }
        }
    }

    private void showOptions() {
        System.out.println("\n=== MENU DE MENSAGENS ===");
        System.out.println("1 - Ver mensagens");
        System.out.println("2 - Enviar mensagem");
        System.out.println("3 - Sair");
        System.out.print("Opção: ");
    }

    public void seeMessages() {
        MessageRepository.loadMessagesForReceiver(senderName);
        List<Message> messages = messageRepository.getMessages();

        if (messages == null || messages.isEmpty()) {
            System.out.println("\nNenhuma mensagem recebida.");
            System.out.println("Aperte ENTER para voltar.");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < messages.size(); i++) {
            System.out.println((i + 1) + " - " + messages.get(i)); // usa o toString()
        }

        System.out.print("\nDigite o número da mensagem que deseja abrir (ou 0 para voltar): ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index <= 0 || index > messages.size()) return;

        Message selected = messages.get(index - 1);
        System.out.print("Digite a chave para descriptografar: ");
        String key = scanner.nextLine();

        try {
            String decrypted = selected.getDecryptedMessage(key);
            System.out.println("\nMensagem de @" + selected.getSenderName() + ":");
            System.out.println(decrypted);
        } catch (Exception e) {
            System.out.println("\nChave incorreta ou erro ao descriptografar.");
        }

        System.out.println("\nAperte ENTER para voltar ao menu.");
        scanner.nextLine();
    }

    public void sendMessage() throws Exception {
        System.out.print("\nPara: ");
        String receiverName = scanner.nextLine();

        System.out.print("Mensagem: ");
        String message = scanner.nextLine();

        System.out.print("Chave: ");
        String chave = scanner.nextLine();

        try {
            Message msg = new Message(this.senderName, receiverName, message, chave);
            msg.saveNewMessage();
            System.out.println("\nMensagem enviada com sucesso!");
        } catch (Exception e) {
            System.out.println("\nErro ao enviar mensagem.");
        }

        System.out.println("\nAperte ENTER para voltar ao menu.");
        scanner.nextLine();
    }
}