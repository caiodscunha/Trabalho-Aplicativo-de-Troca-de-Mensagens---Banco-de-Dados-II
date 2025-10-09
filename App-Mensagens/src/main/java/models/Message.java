package models;

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

    public Message(String sender, String receiver, String message, String key) throws Exception {
        this.senderName = sender;
        this.receiverName = receiver;
        this.encryptedMessage = CryptographyHandler.encrypt(message, key);
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
                .append("sendedAt", java.util.Date.from(
                        sendedAt.atZone(java.time.ZoneId.systemDefault()).toInstant()
                ));
    }

    public static Message documentToMessage(Document d) {
        if (d == null) return null;

        var date = d.getDate("sendedAt");
        LocalDateTime timestamp = date != null
                ? date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime()
                : null;

        return new Message(
                d.getString("senderName"),
                d.getString("receiverName"),
                d.getString("encryptedMessage"),
                timestamp
        );
    }

    public void saveNewMessage() {
        MongoHandler.insertDocument(COLLECTION_NAME, this.toDocument());
    }

    public static List<Message> fetchByReceiver(String receiver) {
        Document query = new Document("receiverName", receiver);

        var messagesDocuments = MongoHandler.findDocuments(COLLECTION_NAME, query);
        List<Message> messages = new ArrayList<>();

        for (Document doc : messagesDocuments) {
            Message message = documentToMessage(doc);
            if (message != null) messages.add(message);
        }
        return messages;
    }

    public String getDecryptedMessage(String key) throws Exception {
        return CryptographyHandler.decrypt(this.encryptedMessage, key);
    }

    public LocalDateTime getSendedAt() {
        return sendedAt;
    }

    public String getSenderName() {
        return senderName;
    }

    @Override
    public String toString() {
        return "De @" + senderName + " [" + sendedAt + "]";
    }

    public String showMessageWithKey(String key) {
        try {
            String decrypted = getDecryptedMessage(key);
            return "De @" + senderName + " [" + sendedAt + "]: " + decrypted;
        } catch (Exception e) {
            return "Chave incorreta para mensagem de @" + senderName;
        }
    }
}
