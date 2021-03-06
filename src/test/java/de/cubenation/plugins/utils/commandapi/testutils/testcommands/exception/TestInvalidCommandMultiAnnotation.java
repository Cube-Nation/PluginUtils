package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;

public class TestInvalidCommandMultiAnnotation {
    public TestInvalidCommandMultiAnnotation(PluginBase plugin) throws Exception {
    }

    @Command(main = "test")
    @SenderConsole
    @SenderBlock
    public void emptyCommand(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}