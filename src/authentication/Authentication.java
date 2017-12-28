package authentication;

import dao.UserDao;

/**
 * Clase que manipula la autenticacion de los usuarios.
 */
public class Authentication {

    private final UserDao user;

    public Authentication() {
        this.user = new UserDao();
    }
    
    /**
     * Registra un nuevo usuario en la base de datos.
     * @param username
     * @param password
     * @return mensaje indicando si el usuario pudo registrarse.
     */
    public String register(String username, String password) {
        String found = user.getUsername(username);

        if (found != null) {
            return "El usuario ya esta registrado";
        }
        
        String passwordHash = Hash.getInstance().toHash(password, username);
        
        user.create(username, passwordHash);

        return "Registrado correctamente";
    }
    
    /**
     * Autentica un usuario si este se encuentra ya registrado.
     * @param username
     * @param password
     * @return  Mensaje indicando el estado del login.
     */
    public String login(String username, String password) {
        String found = user.getUsername(username);
        String pass = user.getPassword(username);
        String passwordHash = Hash.getInstance().toHash(password, username);

        if (found == null) return "Usuario no encontrado";
        
        System.out.println(pass);
        System.out.println(passwordHash);
        
        if (pass.equals(passwordHash)) {
            return "Logeado con exito";
        }
        
        return "Usuario/contraseña invalida";
    }

}
