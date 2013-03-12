package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;

public class TestInvalidCommandWrongMethodParameterFirstConsole {
    public TestInvalidCommandWrongMethodParameterFirstConsole(JavaPlugin plugin) {
    }

    @Command(main = "test")
    @SenderConsole
    public void wrongCommad(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}