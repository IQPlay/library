package fr.parisNanterre.iqplaylib.core;

import fr.parisNanterre.iqplaylib.api.*;

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
