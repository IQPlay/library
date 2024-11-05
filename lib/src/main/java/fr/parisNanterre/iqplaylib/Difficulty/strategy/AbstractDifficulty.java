package fr.parisNanterre.iqplaylib.Difficulty.strategy;

import fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces.ILevel;
import fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces.IQuestionGenerator;
import fr.parisNanterre.iqplaylib.interfaces.IGame;
import fr.parisNanterre.iqplaylib.interfaces.IQuestion;
import fr.parisNanterre.iqplaylib.quizz.Question;

public abstract class AbstractDifficulty implements IQuestionGenerator {

    private void init() {

    }

    private void update(ILevel lvl, IGame game) {

    }

    public IQuestion generate() {
        return new Question("Tu t'appelles comment ?");
    }

}
