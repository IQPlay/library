package fr.parisnanterre.iqplaylib.gamelayer.dto.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NewPlayerDTOTest {

    @Test
    void testGettersAndSetters() {
        NewPlayerDTO dto = new NewPlayerDTO("John Doe", 100, 50, "player123", "accountABC");

        assertEquals("John Doe", dto.getName());
        assertEquals(100, dto.getPoints());
        assertEquals(50, dto.getCredits());
        assertEquals("player123", dto.getPlayer());
        assertEquals("accountABC", dto.getAccount());

        dto.setName("Jane Smith");
        dto.setPoints(200);
        dto.setCredits(75);
        dto.setPlayer("player456");
        dto.setAccount("accountXYZ");

        assertEquals("Jane Smith", dto.getName());
        assertEquals(200, dto.getPoints());
        assertEquals(75, dto.getCredits());
        assertEquals("player456", dto.getPlayer());
        assertEquals("accountXYZ", dto.getAccount());
    }
}
