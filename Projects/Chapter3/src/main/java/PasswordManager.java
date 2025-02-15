import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;

public class PasswordManager {
    private static final int SHIFT = 3; // Shift the value for caesar cipher.

    /**
     * Encrypts a password using a Caesar cipher.
     * 
     * @param password the plaintext password to encrypt
     * @return the encrypted password
     */
    public static String encryptPassword(String password) {
        StringBuilder encrypt = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypt.append((char) ((c - base + SHIFT) % 26 + base));
            } else {
                encrypt.append((char) (c + SHIFT)); // To shift non-alphabetic characters as well.
            }
        }
        return encrypt.toString();
    }

    /**
     * Decrypts a password encrypted using a Caesar cipher.
     * 
     * @param encrypted the encrypted password
     * @return the decrypted password
     */
    public static String decryptPassword(String encrypt) {
        StringBuilder decrypt = new StringBuilder();
        for (char c : encrypt.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                decrypt.append((char) ((c - base - SHIFT + 26) % 26 + base));
            } else {
                decrypt.append((char) (c - SHIFT)); // To reverse shift non-alphabetic characters as well.
            }
        }
        return decrypt.toString();
    }

    /**
     * Stores an encrypted password in a text file.
     * 
     * @param username The username associated with the password.
     * @param password The plaintext password.
     */
    public static void storePassword(String username, String password) {
        String encryptedPassword = encryptPassword(password);
        // Using try-with-resources ensures the resource is closed properly.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("passwords.dat", true))) {
            bw.write(username.trim() + ":" + encryptedPassword.trim());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Retrieves and decrypts a stored password.
     * 
     * @param username the username whose password is to be retrieved
     * @return the decrypted password, or a message if not found
     */
    public static String retrievePassword(String username) {
        try (BufferedReader br = new BufferedReader(new FileReader("passwords.dat"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                //Prevents ArrayIndexOutOfBounds incase format of "username : password" is missing.
                if (parts.length == 2 && parts[0].trim().equals(username.trim())) {
                    return decryptPassword(parts[1].trim());

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "User not found.";
    }
}
