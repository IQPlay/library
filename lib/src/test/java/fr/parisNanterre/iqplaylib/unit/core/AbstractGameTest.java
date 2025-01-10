package fr.parisnanterre.iqplaylib.unit.core;

import fr.parisnanterre.iqplaylib.Level;
import fr.parisnanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.core.AbstractGame;
import fr.parisnanterre.iqplaylib.core.AbstractGameSession;
import fr.parisnanterre.iqplaylib.core.QuestionStorageSession;
import fr.parisnanterre.iqplaylib.core.QuestionGenerator;
import fr.parisnanterre.iqplaylib.core.DifficultyStrategy;
import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractGameTest {

    private static class TestGame extends AbstractGame {
        public TestGame(String name) {
            super(name);
        }

        @Override
        protected IGameSession createGameSession() {
            return new TestGameSession(this);
        }
    }

    private static class TestGameSession extends AbstractGameSession {
        public TestGameSession(IGame game) {
            super(game);
        }

        @Override
        protected IQuestionStorageSession createQuestionStorageSession() { return new QuestionStorageSession(); }

        @Override
        protected IQuestionGenerator createQuestionGenerator() {
            return new QuestionGenerator(new DifficultyStrategy(this.level));
        }

        @Override
        public IQuestionStorageSession questionStorage() {
            return null;
        }
    }

    @Test
    void testCreateSessionFromAbstractGame() throws SessionAlreadyExistsException {
        IGame game = new TestGame("Abstract Test Game");
        IGameSession session = game.createSession();
        assertNotNull(session);
        assertEquals(StateGameSessionEnum.CREATED, session.state());
    }

    @Test
    void testCreateMultipleSessionsFromAbstractGame() throws SessionAlreadyExistsException {
        IGame game = new TestGame("Abstract Test Game");
        IGameSession session1 = game.createSession();
        ILevel level = new Level();
        IScore score = new Score();
        session1.start(level, score);
        assertThrows(SessionAlreadyExistsException.class, () -> game.createSession());
    }
}
