package repository;

import jdk.jshell.spi.ExecutionControl;
import models.Message;

import java.util.List;

public class MessageRepository {
    private List<Message> messages;

    /*Usa fetchByReceiver do message para salvar a lista de mensagens do usuário

     */
    public static MessageRepository loadMessagesForReceiver(String receiver) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }

    public boolean isEmpty() {
        return this.messages.isEmpty();
    }

    public int size() {
        return this.messages.size();
    }

    public Message getByIndex(int index) {
        if (index < 0 || index >= this.messages.size()) {
            throw new IndexOutOfBoundsException("Índice inválido.");
        }
        return this.messages.get(index);
    }

    public void printSummary() {
        for (int i = 0; i < this.messages.size(); i++) {
            Message msg = this.messages.get(i);
            System.out.println(i + " - De @" + msg.getSenderName() + " [" + msg.getSendedAt() + "]");
        }
    }

}
