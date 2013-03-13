package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongMethodParameterWithoutSecond {
    public TestInvalidCommandWrongMethodParameterWithoutSecond(JavaPlugin plugin) {
    }

    @Command(main = "test")
    public void wrongCommad(Player player) {
        Assert.fail("command should not be called");
    }
}