package fr.parisNanterre.iqplaylib.api;

/**
 * The ILevel interface defines the methods required to manage and manipulate levels
 * within a given implementation. This typically involves initializing the level,
 * retrieving the current level, and providing capabilities to adjust the level up or down.
 * This can be particularly useful in scenarios like games or difficulty management systems.
 */
public interface ILevel {
    void init();
    int level();
    void levelUp();
    void levelDown();
}