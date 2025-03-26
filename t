package fr.parisnanterre.iqplaylib.api;

/**
 * Represents a general answer within the system.
 * This interface can be implemented to represent various types of answers,
 * such as correct answers, player answers, or any other specific answer types.
 *
 * @see ICorrectAnswer
 * @see IPlayerAnswer
 */
public interface IAnswer {
    String answer();
}
package fr.parisnanterre.iqplaylib.api;

/**
 * Interface representing a correct answer to a question.
 * This interface extends the IAnswer interface and provides a specific type
 * for correct answers within the system.
 *
 * @see IAnswer
 * @see IQuestion
 * @see IQuestionStorageSession
 * @see IDifficultyStrategy
 */
public interface ICorrectAnswer extends IAnswer {
}
package fr.parisnanterre.iqplaylib.api;

import java.util.Optional;

/**
 * Interface générique pour le stockage des données.
 * L'utilisateur peut fournir sa propre implémentation.
 */
public interface IDataStore<K, V> {
    void save(K key, V value);
    Optional<V> load(K key);
    void delete(K key);
}
package fr.parisnanterre.iqplaylib.api;

/**
 * The IDifficultyStrategy interface defines the contract for implementing
 * different difficulty strategies in a question-generation system.
 *
 */
public interface IDifficultyStrategy {
    void init(ILevel level);
    void update(ILevel level);
    IQuestion generateQuestion();
}
package fr.parisnanterre.iqplaylib.api;

import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;

/**
 * Interface representing a game.
 */
public interface IGame {
    String name();
    IGameSession createSession() throws SessionAlreadyExistsException;
}package fr.parisnanterre.iqplaylib.api;

 public interface IGameMessage {
     String message();
 }
package fr.parisnanterre.iqplaylib.api;

/**
 * Represents a game session, providing control over the session's lifecycle and interactions within it.
 */
public interface IGameSession {
    void start(ILevel level, IScore score);
    void resume();
    void pause();
    void end();
    ILevel level();
    IScore score();
    StateGameSessionEnum state();
    IQuestion nextQuestion();
    boolean submitAnswer(IPlayerAnswer answer);
    IQuestionStorageSession questionStorage();
}package fr.parisnanterre.iqplaylib.api;

 public interface IGameSessionService {
     IGameSession createSession(IPlayer player, IGame game);
     IGameSession findSession(Long sessionId);
     Long getSessionId(IGameSession session);
 }
package fr.parisnanterre.iqplaylib.api;

/**
 * The ILevel interface defines the methods required to manage and manipulate levels
 * within a given implementation. This typically involves initializing the level,
 * retrieving the current level, and providing capabilities to adjust the level up or down.
 * This can be particularly useful in scenarios like games or difficulty management systems.
 */
public interface ILevel {
    int level();
    void levelUp();
    void levelDown();
}package fr.parisnanterre.iqplaylib.api;

 public interface IPlayer {
     Long id();
 }
package fr.parisnanterre.iqplaylib.api;

/**
 * Represents an answer provided by a player within a game session.
 * This interface extends the IAnswer interface to specify that the answer
 * is from a player, differentiating it from other types of answers such as
 * correct answers.
 *
 * The IPlayerAnswer interface can be implemented to store and manage
 * the details of a player's response to a question during gameplay. Game session
 * mechanisms can then handle these responses to determine the outcome
 * and progress in the game.
 *
 * @see IAnswer
 * @see IGameSession
 * @see IQuestionStorageSession
 */
public interface IPlayerAnswer extends IAnswer {
}
package fr.parisnanterre.iqplaylib.api;

/**
 * IQuestion is an interface representing a question in a question-answer system.
 * Implementations of this interface should provide mechanisms to retrieve the
 * text of the question and the correct answer associated with it.
 *
 * The question() method returns the text of the question.
 * The correctAnswer() method returns the correct answer for the question,
 * which is represented by an ICorrectAnswer object.
 *
 * This interface can be implemented in various contexts such as quiz games,
 * educational applications, or any other system requiring a question-answer feature.
 */
public interface IQuestion {
    String question();
    ICorrectAnswer correctAnswer();
}
package fr.parisnanterre.iqplaylib.api;

/**
 * The IQuestionGenerator interface defines the contract for generating questions
 * within a question-answer system. Implementations of this interface should provide
 * the logic to produce instances of IQuestion.
 *
 * The generateQuestion method is responsible for creating and returning an
 * IQuestion object. This can be based on various factors such as difficulty level,
 * topic, or any other criteria defined by the implementing class.
 *
 * This interface can be particularly useful in educational applications, quiz games,
 * and any other system that requires the generation of questions for users to answer.
 */
public interface IQuestionGenerator {
    IQuestion generateQuestion();
}package fr.parisnanterre.iqplaylib.api;

 import java.util.List;

 /**
  * IQuestionStorageSession is an interface that defines the methods required for
  * managing the storage and retrieval of questions, answers, and scores within a session.
  */
 public interface IQuestionStorageSession {
     IQuestion lastQuestion();
     List<IQuestion> questions();
     List<ICorrectAnswer> correctAnswers();
     List<IPlayerAnswer> playerAnswers();
     void addCorrectAnswer(ICorrectAnswer answer);
     void addQuestion(IQuestion question);
     void addPlayerAnswer(IPlayerAnswer answer);
     IPlayerAnswer lastPlayerAnswer();
 }
package fr.parisnanterre.iqplaylib.api;

/**
 * The IScore interface defines the contract for managing a score within a system.
 * This interface provides methods to retrieve the current score and to increment the score by a specified value.
 */
public interface IScore {
    int score();
    void incrementScore(int value);
}package fr.parisnanterre.iqplaylib.api;

 /**
  * Enum representing the various states that a game session can be in.
  *
  * @see IGameSession
  */
 public enum StateGameSessionEnum {
     CREATED,
     STARTED,
     PAUSED,
     ENDED,
     INTERRUPTED,
     IN_PROGRESS,
 }
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;
import fr.parisnanterre.iqplaylib.exceptions.SessionAlreadyExistsException;

/**
 * AbstractGame is an abstract class that implements the IGame interface.
 * It provides common functionality for game implementations, including game creation
 * and session management. Concrete subclasses are expected to provide specific
 * implementations for creating game sessions.
 */
public abstract class AbstractGame implements IGame {
    private String name;
    private IGameSession currentSession;

    protected AbstractGame(String name) {
        this.name = name;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public IGameSession createSession() throws SessionAlreadyExistsException {
        if (currentSession != null && currentSession.state() != StateGameSessionEnum.ENDED) {
            throw new SessionAlreadyExistsException("Une session est déjà en cours.");
        }
        currentSession = createGameSession();
        return currentSession;
    }

    protected abstract IGameSession createGameSession();
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * Abstract class that defines the blueprint for a game session.
 * Implements IGameSession.
 */
public abstract class AbstractGameSession implements IGameSession {
    protected IGame game;
    protected ILevel level;
    protected IScore score;
    protected IQuestionGenerator questionGenerator;
    protected IQuestionStorageSession questionStorage;
    protected StateGameSessionEnum state;

    protected AbstractGameSession(IGame game) {
        this.game = game;
        this.questionStorage = createQuestionStorageSession();
        this.questionGenerator = createQuestionGenerator();
        this.state = StateGameSessionEnum.CREATED;
    }

    protected abstract IQuestionStorageSession createQuestionStorageSession();
    protected abstract IQuestionGenerator createQuestionGenerator();

    @Override
    public void start(ILevel level, IScore score) {
        this.state = StateGameSessionEnum.STARTED;
        this.level = level;
        this.score = score;
    }

    @Override
    public void resume() {
        this.state = StateGameSessionEnum.IN_PROGRESS;
    }

    @Override
    public void pause() {
        this.state = StateGameSessionEnum.PAUSED;
    }

    @Override
    public void end() {
        this.state = StateGameSessionEnum.ENDED;
    }

    @Override
    public ILevel level() {
        return level;
    }

    @Override
    public IScore score() {
        return score;
    }

    @Override
    public StateGameSessionEnum state() {
        return state;
    }

    @Override
    public IQuestion nextQuestion() {
        IQuestion question = questionGenerator.generateQuestion();
        questionStorage.addQuestion(question);
        return question;
    }

    @Override
    public boolean submitAnswer(IPlayerAnswer answer) {
        questionStorage.addPlayerAnswer(answer);
        IQuestion lastQuestion = questionStorage.lastQuestion();
        ICorrectAnswer correctAnswer = lastQuestion.correctAnswer();

        if (correctAnswer.answer().equals(answer.answer())) {
            score.incrementScore(1);
            level.levelUp();
            onCorrectAnswer();
            return true;
        } else {
            onIncorrectAnswer();
            return false;
        }
    }

    protected void onCorrectAnswer() {
       questionStorage.addCorrectAnswer(questionStorage.lastQuestion().correctAnswer());

    }

    protected void onIncorrectAnswer() {
        end();
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * This class represents a level management system implementing the ILevel interface.
 * It allows initialization and adjustment of the current level up or down, with a minimum level of 1.
 */
public abstract class AbstractLevel implements ILevel {
    protected int currentLevel;

    public AbstractLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    @Override
    public int level() {
        return currentLevel;
    }

    @Override
    public void levelUp() {
        currentLevel++;
    }

    @Override
    public void levelDown() {
        if (currentLevel > 1) {
            currentLevel--;
        }
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The Score class implements the IScore interface and provides functionality
 * for managing and manipulating a score value within the system.
 */
public abstract class AbstractScore implements IScore {
    private int scoreValue;

    public AbstractScore(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    @Override
    public int score() {
        return scoreValue;
    }

    @Override
    public void incrementScore(int value) {
        scoreValue += value;
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The CorrectAnswer class implements the ICorrectAnswer interface and represents
 * a correct answer within a question-answer system.
 *
 * This class holds the text of the correct answer and provides a method to retrieve it.
 */
public class CorrectAnswer implements ICorrectAnswer {
    private String answerText;

    public CorrectAnswer(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String answer() {
        return answerText;
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * DifficultyStrategy is an implementation of the IDifficultyStrategy interface,
 * responsible for generating questions based on the current level of difficulty.
 *
 * This class encapsulates a reference to an ILevel object, which represents the
 * current difficulty level. It provides methods to initialize, update, and generate
 * questions appropriate to the current level.
 *
 * The init method initializes the strategy with the provided level.
 * The update method updates the strategy based on the new level.
 * The generateQuestion method creates and returns a new question tailored to the current level.
 */
public class DifficultyStrategy implements IDifficultyStrategy {
    private ILevel level;

    public DifficultyStrategy(ILevel level) {
        this.level = level;
        init(level);
    }

    @Override
    public void init(ILevel level) {
        // Initialiser la stratégie avec le niveau
    }

    @Override
    public void update(ILevel level) {
        this.level = level;
        // Mettre à jour la stratégie en fonction du nouveau niveau
    }

    @Override
    public IQuestion generateQuestion() {
        // Générer une question en fonction du niveau actuel
        String questionText = "Question par défaut au niveau " + level.level();
        ICorrectAnswer correctAnswer = new CorrectAnswer("Réponse correcte par défaut pour le niveau " + level.level());
        return new Question(questionText, correctAnswer);
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * Represents a specific implementation of a game.
 * Extends the AbstractGame class to provide functionality for creating game sessions.
 */
public class Game extends AbstractGame {

    public Game(String name) {
        super(name);
    }

    @Override
    protected IGameSession createGameSession() {
        return new GameSession(this);
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * GameSession is a concrete implementation of AbstractGameSession, designed to manage the lifecycle,
 * levels, scoring, and question generation within a game session. This class provides specific logic
 * for handling correct and incorrect answers.
 */
public class GameSession extends AbstractGameSession {

    public GameSession(IGame game) {
        super(game);
    }

    @Override
    protected IQuestionStorageSession createQuestionStorageSession() {
        return new QuestionStorageSession();
    }

    @Override
    protected IQuestionGenerator createQuestionGenerator() {
        if (level == null) {
            level = new Level(1); // implémentation concrète avec un niveau par défaut (à changer plus tard)
        }
        return new QuestionGenerator(new DifficultyStrategy(level));
    }


    @Override
    protected void onCorrectAnswer() {
        // Logique supplémentaire en cas de bonne réponse
    }

    @Override
    protected void onIncorrectAnswer() {
        // Logique supplémentaire en cas de mauvaise réponse
        super.onIncorrectAnswer(); // Appelle end() par défaut
    }

    @Override
    public IQuestionStorageSession questionStorage() {
        return null;
    }
}
package fr.parisnanterre.iqplaylib.core;

/**
 * A concrete implementation of the AbstractLevel class for testing and production use.
 */
public class Level extends AbstractLevel {

    public Level(int initialLevel) {
        super(initialLevel); // Passe le niveau initial
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The `PlayerAnswer` class represents an answer provided by a player within a game session.
 * This class implements the `IPlayerAnswer` interface, which extends the `IAnswer` interface,
 * indicating that the answer originates from a player participating in the game.
 *
 * Instances of `PlayerAnswer` store the text of the player's response, which can be retrieved
 * through the `answer()` method.
 */
public class PlayerAnswer implements IPlayerAnswer {
    private String answerText;

    public PlayerAnswer(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String answer() {
        return answerText;
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The Question class is an implementation of the IQuestion interface representing
 * a specific question in a question-answer system.
 *
 * This class holds the text of the question and its associated correct answer.
 */
public class Question implements IQuestion {
    private String questionText;
    private ICorrectAnswer correctAnswer;

    public Question(String questionText, ICorrectAnswer correctAnswer) {
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    @Override
    public String question() {
        return questionText;
    }

    @Override
    public ICorrectAnswer correctAnswer() {
        return correctAnswer;
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

/**
 * The QuestionGenerator class is responsible for generating questions
 * based on a specified difficulty strategy. It implements the
 * IQuestionGenerator interface, which defines the contract for
 * question generation in the system.
 *
 * The constructor of this class takes an IDifficultyStrategy
 * object, which it uses to generate questions according to the
 * provided strategy.
 *
 * The generateQuestion method utilizes the difficulty strategy's
 * generateQuestion method to create and return an IQuestion object.
 */
public class QuestionGenerator implements IQuestionGenerator {
    private IDifficultyStrategy difficultyStrategy;

    public QuestionGenerator(IDifficultyStrategy difficultyStrategy) {
        this.difficultyStrategy = difficultyStrategy;
    }

    @Override
    public IQuestion generateQuestion() {
        return difficultyStrategy.generateQuestion();
    }
}
package fr.parisnanterre.iqplaylib.core;

import fr.parisnanterre.iqplaylib.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * QuestionStorageSession is a concrete implementation of the IQuestionStorageSession interface that
 * manages the storage and retrieval of questions, correct answers, player answers, and scoring within a session.
 */
public class QuestionStorageSession implements IQuestionStorageSession {
    private List<IQuestion> questions;
    private List<ICorrectAnswer> correctAnswers;
    private List<IPlayerAnswer> playerAnswers;

    public QuestionStorageSession() {
        this.questions = new ArrayList<>();
        this.correctAnswers = new ArrayList<>();
        this.playerAnswers = new ArrayList<>();
    }

    @Override
    public IQuestion lastQuestion() {
        if (questions.isEmpty()) {
            return null;
        }
        return questions.getLast();
    }

    @Override
    public List<IQuestion> questions() {
        return new ArrayList<>(questions);
    }

    @Override
    public List<ICorrectAnswer> correctAnswers() {
        return new ArrayList<>(correctAnswers);
    }

    @Override
    public List<IPlayerAnswer> playerAnswers() {
        return new ArrayList<>(playerAnswers);
    }

    public void addQuestion(IQuestion question) {
        questions.add(question);
        correctAnswers.add(question.correctAnswer());
    }

    public void addPlayerAnswer(IPlayerAnswer answer) {
        playerAnswers.add(answer);
    }

    public void addCorrectAnswer(ICorrectAnswer answer) {
        correctAnswers.add(answer);
    }

    @Override
    public IPlayerAnswer lastPlayerAnswer() {
        if (playerAnswers.isEmpty()) {
            return null;
        }
        return playerAnswers.getLast();
    }

}
package fr.parisnanterre.iqplaylib.exceptions;

/**
 * Exception thrown to indicate that a session already exists and cannot be created again.
 * This exception typically occurs when trying to create a new game session while an existing session
 * is still active or has not been properly terminated.
 *
 * @see fr.parisnanterre.iqplaylib.api.IGameSession
 */
public class SessionAlreadyExistsException extends RuntimeException {
    public SessionAlreadyExistsException(String message) {
        super(message);
    }
}
