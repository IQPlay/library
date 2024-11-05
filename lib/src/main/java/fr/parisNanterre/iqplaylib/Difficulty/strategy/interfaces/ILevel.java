package fr.parisNanterre.iqplaylib.Difficulty.strategy.interfaces;

public interface ILevel {
    ILevel init();
    ILevel level();
    ILevel levelUp();
    ILevel levelDown();
}
