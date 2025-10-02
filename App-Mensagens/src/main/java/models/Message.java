package models;

import jdk.jshell.spi.ExecutionControl;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.Document;


public class Message {
    private LocalDateTime sendedAt;
    private String encryptedMessage;
    private String senderName;
    private String receiverName;

    public Message(String sender, String receiver, String encryptedText) {
        this.senderName = sender;
        this.receiverName = receiver;
        this.encryptedMessage = encryptedText;
        this.sendedAt = LocalDateTime.now();
    }

    private Message(String sender, String receiver, String encryptedText, LocalDateTime timestamp) {
        this.senderName = sender;
        this.receiverName = receiver;
        this.encryptedMessage = encryptedText;
        this.sendedAt = timestamp;
    }

    public Document toDocument() {
        return new Document("senderName", senderName)
                .append("receiverName", receiverName)
                .append("encryptedMessage", encryptedMessage)
                .append("sendedAt", sendedAt);
    }

    /*salva nova mensagem no Mongo, utilizando a classe static MongoUtil (a ser implementada)

     */
    public void saveNewMessage() throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }
    /*carrega as mensagem no Mongo, utilizando a classe static MongoUtil (a ser implementada)

     */
    public static List<Message> fetchByReceiver(String receiver) throws ExecutionControl.NotImplementedException{
        throw new ExecutionControl.NotImplementedException("");
    }

    public String getDecryptedMessage(String key) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("");
    }


    public LocalDateTime getSendedAt() {
        return sendedAt;
    }

    public String getSenderName() {
        return senderName;
    }

}
