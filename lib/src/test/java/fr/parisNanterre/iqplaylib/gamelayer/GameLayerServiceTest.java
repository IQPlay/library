package fr.parisnanterre.iqplaylib.gamelayer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class GameLayerServiceTest {

    private static class DummyGameLayerService extends GameLayerService {
        public DummyGameLayerService() {
            super();
        }
    }

    private DummyGameLayerService service;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        service = new DummyGameLayerService();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testLoadApiCredential() throws Exception {
        setStaticField("API_KEY", null);
        setStaticField("ACCOUNT_ID", null);

        // Vérifier si le fichier application.properties existe dans le classpath
        InputStream resourceStream = GameLayerService.class.getClassLoader().getResourceAsStream("application.properties");

        GameLayerService.loadApiCredential();

        // Selon la présence du fichier, on vérifie le comportement attendu
        if (resourceStream == null) {
            // Si le fichier n'est pas présent, on s'attend à ce que API_KEY et ACCOUNT_ID restent null
            assertNull(getStaticField("API_KEY"));
            assertNull(getStaticField("ACCOUNT_ID"));
        } else {
            // Sinon, ils doivent être initialisés avec des valeurs non nulles et non vides
            String apiKey = (String) getStaticField("API_KEY");
            String accountId = (String) getStaticField("ACCOUNT_ID");
            assertNotNull(apiKey);
            assertFalse(apiKey.isEmpty());
            assertNotNull(accountId);
            assertFalse(accountId.isEmpty());
        }
    }

    @Test
    void testLogInformation() {
        // Vérification basique que logInformation s'exécute sans lever d'exception
        service.logInformation("Message de test");
    }

    /**
     * Méthode utilitaire pour récupérer la valeur d'un champ statique de la classe GameLayerService
     */
    private Object getStaticField(String fieldName) throws Exception {
        Field field = GameLayerService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(null);
    }

    /**
     * Méthode utilitaire pour définir la valeur d'un champ statique de la classe GameLayerService
     */
    private void setStaticField(String fieldName, Object value) throws Exception {
        Field field = GameLayerService.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(null, value);
    }
}
