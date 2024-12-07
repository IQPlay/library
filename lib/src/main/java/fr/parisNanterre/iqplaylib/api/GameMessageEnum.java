package fr.parisnanterre.iqplaylib.api;

/**
 * Enum to centralize game messages.
 */
public enum GameMessageEnum implements IGameMessage {
    SESSION_STARTED("Session démarrée avec succès."),
    SESSION_NOT_FOUND("Session introuvable."),
    GAME_ENDED_NO_OPERATION("La partie est terminée, aucune nouvelle opération ne peut être générée."),
    GAME_ENDED_NO_RESPONSE("La partie est terminée, vous ne pouvez plus soumettre de réponse."),
    ANSWER_CORRECT("Réponse correcte !"),
    GAME_STOPPED("La session a été arrêtée avec succès."),
    OPERATION_PENDING("Vous devez répondre à l'opération précédente avant d'en générer une nouvelle."),
    GAME_ALREADY_ENDED("La partie est déjà terminée.");

    private final String message;

    GameMessageEnum(String message) {
        this.message = message;
    }

    @Override
    public String message() {
        return message;
    }
}
