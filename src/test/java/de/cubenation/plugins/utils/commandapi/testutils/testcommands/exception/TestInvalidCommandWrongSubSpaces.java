package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongSubSpaces {
    public TestInvalidCommandWrongSubSpaces(PluginBase plugin) throws Exception {
    }

    @Command(main = "test", sub = "test 1")
    public void emptyCommand(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}