package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderBlock;

public class TestInvalidCommandWrongMethodParameterFirstBlock {
    public TestInvalidCommandWrongMethodParameterFirstBlock(PluginBase plugin) {
    }

    @Command(main = "test")
    @SenderBlock
    public void wrongCommad(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}