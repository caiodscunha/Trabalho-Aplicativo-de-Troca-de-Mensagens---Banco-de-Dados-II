package models;

import org.bson.Document;

public class User {
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getAt() {
        return this.username;
    }

    /* Transforma o usuário no bson para inserir no Mongo
    * */
    public Document toDocument() {
        return new Document("username", this.username)
                .append("email", email)
                .append("password", password);
    }

    /*Salva usuário no Mongo
    * */
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*Cria um novo usuário e usa o método save para salvar o novo user no Mongo
    e checa se username ou email já estão sendo utilizados
    * */
    public static User register(String username, String email, String plainPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /* Usa o mongoHandler para ver se existe documento com usuário e senha
    * */
    public static User login(String email, String plainPassword) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*Checa se o username já está registrado no mongo
    * */
    public static boolean existsByUsername(String at) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /*Checa se o email já está registrado no mongo
     * */
    public static boolean existsByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String toString() {
        return "@" + this.username + " - " + " <" + this.email + ">";
    }

}
