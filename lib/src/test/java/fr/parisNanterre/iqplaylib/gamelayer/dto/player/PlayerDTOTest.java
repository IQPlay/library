package fr.parisnanterre.iqplaylib.gamelayer.dto.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerDTOTest {

    @Test
    void testConstructorWithAllFields() {
        PlayerDTO dto = new PlayerDTO("John Doe", "TeamA", 150, 100, "lvl1", "Level One");

        assertEquals("John Doe", dto.getName());
        assertEquals("TeamA", dto.getTeam());
        assertEquals(150, dto.getPoints());
        assertEquals(100, dto.getCredits());
        assertEquals("lvl1", dto.getLevelId());
        assertEquals("Level One", dto.getLevelName());
    }

    @Test
    void testConstructorWithPlayerAccount() {
        PlayerDTO dto = new PlayerDTO("player123", "accountABC");

        assertEquals("player123", dto.getPlayer());
        assertEquals("accountABC", dto.getAccount());
    }

    @Test
    void testSettersAndGetters() {
        PlayerDTO dto = new PlayerDTO();

        dto.setName("Jane Smith");
        dto.setTeam("TeamB");
        dto.setPoints(200);
        dto.setCredits(150);
        dto.setLevelId("lvl2");
        dto.setLevelName("Level Two");
        dto.setPlayer("player456");
        dto.setAccount("accountXYZ");

        assertEquals("Jane Smith", dto.getName());
        assertEquals("TeamB", dto.getTeam());
        assertEquals(200, dto.getPoints());
        assertEquals(150, dto.getCredits());
        assertEquals("lvl2", dto.getLevelId());
        assertEquals("Level Two", dto.getLevelName());
        assertEquals("player456", dto.getPlayer());
        assertEquals("accountXYZ", dto.getAccount());
    }
}
