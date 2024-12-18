package fr.parisNanterre.iqplaylib.unit.core;

import fr.parisNanterre.iqplaylib.Level;
import fr.parisNanterre.iqplaylib.Score;
import fr.parisnanterre.iqplaylib.api.*;
import fr.parisNanterre.iqplaylib.core.AbstractGame;
import fr.parisNanterre.iqplaylib.core.AbstractGameSession;
import fr.parisNanterre.iqplaylib.core.QuestionStorageSession;
import fr.parisNanterre.iqplaylib.core.QuestionGenerator;
import fr.parisNanterre.iqplaylib.core.DifficultyStrategy;
import fr.parisNanterre.iqplaylib.exceptions.SessionAlreadyExistsException;
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
