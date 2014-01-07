package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongMethodParameterSecond {
    public TestInvalidCommandWrongMethodParameterSecond(PluginBase plugin) {
    }

    @Command(main = "test")
    public void wrongCommad(Player player, Integer args) {
        Assert.fail("command should not be called");
    }
}