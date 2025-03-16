package fr.parisnanterre.iqplaylib.gamelayer.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class NewPlayerDTO {

    @NotBlank(message = "Le nom du joueur est obligatoire")
    private String name;

    @NotBlank(message = "Le joueur doit avoir un nombre de points minimum")
    private int points;

    @NotBlank(message = "Le joueur doit avoir un nombre de crédit minimum")
    private int credits;

    @NotBlank(message = "Le joueur doit avoir un identifiant")
    private String player;

    @NotBlank(message = "Le joueur doit être attaché à un compte")
    private String account;

    public NewPlayerDTO(String name, int points, int credits, String player, String account) {
        this.name = name;
        this.points = points;
        this.credits = credits;
        this.player = player;
        this.account = account;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String playerId) {
        this.player = playerId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
