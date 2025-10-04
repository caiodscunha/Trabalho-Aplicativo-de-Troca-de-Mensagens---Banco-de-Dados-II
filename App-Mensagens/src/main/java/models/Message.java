package models;

import jdk.jshell.spi.ExecutionControl;

import java.io.ObjectStreamException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;


public class Message {
    private static final String COLLECTION_NAME = "messages";

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

    public static Message documentToMessage(Document d) {
        if (d == null) return null;

        return new Message(d.getString("senderName"), d.getString("receiverName"), d.getString("encryptedMessage"), (LocalDateTime) d.get( "sendetAt"));
    }

    /*salva nova mensagem no Mongo, utilizando a classe static MongoUtil (a ser implementada)

     */
    public void saveNewMessage() {
        Document message = this.toDocument();

        MongoHandler.insertDocument(COLLECTION_NAME, message);
    }
    /*carrega as mensagem no Mongo, utilizando a classe static MongoUtil (a ser implementada)

     */
    public static List<Message> fetchByReceiver(String receiver) {
        Document query = new Document("receiverName", receiver);

        var messagesDocuments = MongoHandler.findDocuments(COLLECTION_NAME, query);
        List<Message> messages = new ArrayList<>();

        for (Document doc : messagesDocuments) {
            Message message = documentToMessage(doc);
            if (message != null) {
                messages.add(message);
            }
        }
        return messages;
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
