package models;

import org.bson.Document;

import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    /* Transforma o usuário no bson para inserir no Mongo
    * */
    public Document toDocument() {
        return new Document("username", this.username)
                .append("email", email)
                .append("password", password);
    }

    public static User fromDocument(Document d) {
        if (d == null) return null;
        return new User(
                d.getString("username"),
                d.getString("email"),
                d.getString("password")
        );
    }

    /*Salva usuário no Mongo - true se foi criado, false se já existe usuário com
    as informações fornecidas
    * */
    private boolean save() {
        if (existsByUsername(this.username) || existsByEmail(this.email)) return false;
        MongoHandler.insertDocument("users", this.toDocument());
        return true;
    }

    /*Cria um novo usuário e usa o método save para salvar o novo user no Mongo
    e checa se username ou email já estão sendo utilizados
    * */
    public static User register(String username, String email, String password) {
        try{
            User user = new User(username, email, password);
            boolean created = user.save();
            return created ? user : null;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar usuário: " + e.getMessage(), e);
        }
    }

    /* Usa o mongoHandler para ver se existe documento com usuário e senha
    * */
    public static User login(String email, String password) {
        try {
            List<Document> docs = MongoHandler.findDocuments("users", new Document("email", email).append("password", password));
            if (docs.isEmpty()) return null;
            return fromDocument(docs.getFirst());
        } catch (Exception e) {
            throw new RuntimeException("Erro ao tentar login: " + e.getMessage(), e);
        }
    }

    /*Checa se o username já está registrado no mongo
    * */
    public static boolean existsByUsername(String username) {
        return !MongoHandler.findDocuments("users", new Document("username", username)).isEmpty();
    }

    /*Checa se o email já está registrado no mongo
     * */
    public static boolean existsByEmail(String email) {
        return !MongoHandler.findDocuments("users", new Document("email", email)).isEmpty();
    }

    @Override
    public String toString() {
        return "@" + this.username + " - " + " <" + this.email + ">";
    }

}
