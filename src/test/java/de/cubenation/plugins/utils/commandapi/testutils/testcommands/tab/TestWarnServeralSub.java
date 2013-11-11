package de.cubenation.plugins.utils.commandapi.testutils.testcommands.tab;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestWarnServeralSub {
    @Command(main = "warn", sub = { "search", "check" }, max = 1, usage = "[Spieler]", help = "Nach einem Spieler suchen.")
    public void checkWarning(Player player, String[] args) {
    }

    @Command(main = "warn", sub = "accept", max = 0, help = "Damit aktzeptierst du eine Verwarnung.")
    public void acceptWarning(Player player) {
    }

    @Command(main = "warn", sub = "confirm", max = 0, help = "Bestätigt die Verwarnung eines offline Spielers")
    public void confirmWarning(Player player) {
    }

    @Command(main = "warn", sub = "del", min = 1, max = 1, usage = "[Id]", help = "Löscht eine einzelne Verwarnung")
    public void deleteWarning(Player player, String amount) {
    }

    @Command(main = "warn", sub = "delall", min = 1, max = 1, usage = "[Spieler]", help = "Löscht alle Verwarnungen des Spielers")
    public void deleteAllWarning(Player player, String playerName) {
    }

    @Command(main = "warn", sub = { "list", "info" }, min = 0, max = 0, help = "Zeigt dir deine Verwarnungen an.")
    public void listWarningPlayer(Player player) {
    }

    @Command(main = "warn", sub = { "list", "info" }, min = 1, max = 1, usage = "[Spieler]", help = "Zeigt alle Verwarnungen des Spielers")
    public void listWarningAdmin(Player player, String playerName) {
    }
}
