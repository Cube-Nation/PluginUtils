package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Block;
import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.Console;

public class TestInvalidCommandMultiAnnotation {
    public TestInvalidCommandMultiAnnotation(JavaPlugin plugin) throws Exception {
    }

    @Command(main = "test")
    @Console
    @Block
    public void emptyCommand(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}