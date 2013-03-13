package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongMinMax {
    public TestInvalidCommandWrongMinMax(JavaPlugin plugin) throws Exception {
    }

    @Command(main = "test", min = 2, max = 1)
    public void emptyCommand(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}