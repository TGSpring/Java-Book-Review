import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PasswordManagerTest {

    private static final String USERNAME = "testUser";
    private static final String PASSWORD = "TestPassword123";
    private static final String ENCRYPTED_PASSWORD = PasswordManager.encryptPassword(PASSWORD);

    // Ensure the "passwords.dat" file is cleared before each test.
    @BeforeEach
public void setup() {
    File file = new File("passwords.dat");
    if (!file.exists()) {
        try {
            file.createNewFile();  // Ensure the file exists
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Optionally clear contents if needed for clean tests
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
        bw.write("");  // Clear file content
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @Test
    public void testEncryptedPassword() {
        String encrypted = PasswordManager.encryptPassword(PASSWORD);
        assertNotNull(encrypted, "Encrypted password should not be null");
        assertNotEquals(PASSWORD, encrypted, "Encrypted password should be different from the original password.");
    }

    @Test
    public void testDecryptPassword() {
        String decrypted = PasswordManager.decryptPassword(ENCRYPTED_PASSWORD);
        assertEquals(PASSWORD, decrypted, "Decrypted password should match the original password");
    }

    @Test
    public void testStorePassword() {
        // Store password and check if it is saved in the file
        PasswordManager.storePassword(USERNAME, PASSWORD);

        File file = new File("passwords.dat");
        assertTrue(file.exists(), "Password file should exist after storing a password");

        // Verify the password is stored correctly
        String storedPassword = PasswordManager.retrievePassword(USERNAME);
        assertNotEquals("User not found.", storedPassword, "Password should be found in the file");
        assertEquals(PASSWORD, storedPassword, "Retrieved password should match the original password");
    }

    @Test
    public void testRetrievePassword() {
        // Store the password first
        PasswordManager.storePassword(USERNAME, PASSWORD);

        // Retrieve and check the password
        String retrievedPassword = PasswordManager.retrievePassword(USERNAME);
        assertEquals(PASSWORD, retrievedPassword, "The retrieved password should match the original password");
    }

    @Test
    public void testRetrievePasswordUserNotFound() {
        // Try to retrieve a password that doesn't exist
        String result = PasswordManager.retrievePassword("nonExistentUser");
        assertEquals("User not found.", result, "The system should return 'User not found.' for nonexistent users");
    }
}
