package fr.parisnanterre.iqplaylib.gamelayer.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


/**
 * DTO pour la création d'un joueur dans GameLayer.
 */
public class PlayerDTO {

    @NotBlank(message = "Le nom du joueur est obligatoire")
    private String name;

    private String team;

    @Min(value = 0, message = "Les points ne peuvent pas être négatifs")
    private int points;

    @Min(value = 0, message = "Les crédits ne peuvent pas être négatifs")
    private int credits;

    @NotNull(message = "L'ID du niveau est obligatoire")
    private String levelId;

    @NotBlank(message = "Le nom du niveau est obligatoire")
    private String levelName;

    @NotBlank(message = "Le joueur doit avoir un identifiant")
    private String player;

    @NotBlank(message = "Le joueur doit avoir un compte")
    private String account;

    //  Constructeurs
    public PlayerDTO() {}

    public PlayerDTO(String name, String team, int points, int credits, String levelId, String levelName) {
        this.name = name;
        this.team = team;
        this.points = points;
        this.credits = credits;
        this.levelId = levelId;
        this.levelName = levelName;
    }

    public PlayerDTO(String player, String account) {
        this.player = player;
        this.account = account;
    }

    //  Getters et Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public String getLevelId() { return levelId; }
    public void setLevelId(String levelId) { this.levelId = levelId; }

    public String getLevelName() { return levelName; }
    public void setLevelName(String levelName) { this.levelName = levelName; }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
