package de.cubenation.plugins.utils.commandapi.testutils.testcommands.tab;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestWarnWithoutEmpty {
    @Command(main = "warn", sub = { "list", "info" }, min = 0, max = 0, help = "Zeigt dir deine Verwarnungen an.")
    public void listWarningPlayer(Player player) {
    }
}
