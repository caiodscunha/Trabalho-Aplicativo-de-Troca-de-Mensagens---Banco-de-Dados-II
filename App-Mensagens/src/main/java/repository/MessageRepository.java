package repository;

import jdk.jshell.spi.ExecutionControl;
import models.Message;

import java.util.List;

public class MessageRepository {
    private static List<Message> messages;

    /*Usa fetchByReceiver do message para salvar a lista de mensagens do usuário

     */
    public static void loadMessagesForReceiver(String receiver) {
        messages = Message.fetchByReceiver(receiver);
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

    public int size() {
        return messages.size();
    }

    public Message getByIndex(int index) {
        if (index < 0 || index >= messages.size()) {
            throw new IndexOutOfBoundsException("Índice inválido.");
        }
        return messages.get(index);
    }

    public void printSummary() {
        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i);
            System.out.println(i + " - De @" + msg.getSenderName() + " [" + msg.getSendedAt() + "]");
        }
    }

}
