package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongMethodParameterFirstParameter {
    public TestInvalidCommandWrongMethodParameterFirstParameter(JavaPlugin plugin) {
    }

    @Command(main = "test")
    public void wrongCommad(Integer player, String[] args) {
        Assert.fail("command should not be called");
    }
}