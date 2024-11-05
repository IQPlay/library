package fr.parisNanterre.iqplaylib.Difficulty.strategy;

import fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces.ILevel;

public class Level implements ILevel {

    private final int currentLevel;

    private Level(int count) {
        this.currentLevel = count;
    }

    public ILevel init() {
        return new Level(1);
    }

    @Override
    public ILevel level() {
        return this;
    }

    @Override
    public ILevel levelUp() {
        return new Level(this.currentLevel + 1);
    }

    @Override
    public ILevel levelDown() {
        return new Level(this.currentLevel - 1);
    }

}
