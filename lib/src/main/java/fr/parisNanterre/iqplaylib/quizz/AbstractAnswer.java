package fr.parisNanterre.iqplaylib.quizz;

import fr.parisNanterre.iqplaylib.interfaces.IAnswer;

public abstract class AbstractAnswer implements IAnswer, Comparable<IAnswer> {

    @Override
    public int compareTo(IAnswer o) {
        return 0;
    }

}
