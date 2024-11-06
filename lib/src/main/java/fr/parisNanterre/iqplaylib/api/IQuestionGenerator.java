package fr.parisNanterre.iqplaylib.api;

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
}