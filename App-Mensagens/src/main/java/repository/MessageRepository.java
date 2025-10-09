package repository;

import models.Message;
import java.util.List;

public class MessageRepository {
    private static List<Message> messages;

    /**
     * Usa fetchByReceiver do Message para salvar a lista de mensagens do usuário
     */
    public static void loadMessagesForReceiver(String receiver) {
        messages = Message.fetchByReceiver(receiver);
    }

    public static boolean isEmpty() {
        return messages == null || messages.isEmpty();
    }

    public static int size() {
        return messages == null ? 0 : messages.size();
    }

    public static Message getByIndex(int index) {
        if (messages == null || index < 0 || index >= messages.size()) {
            throw new IndexOutOfBoundsException("Índice inválido.");
        }
        return messages.get(index);
    }

    public static void printSummary() {
        if (isEmpty()) {
            System.out.println("\nNenhuma mensagem encontrada.");
            return;
        }

        System.out.println("\n=== MENSAGENS RECEBIDAS ===");
        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i);
            System.out.println((i + 1) + " - " + msg); //começa em 1 e adiciona espaço
        }
    }

    public static List<Message> getMessages() {
        return messages;
    }
}
