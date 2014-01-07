package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.command.RemoteConsoleCommandSender;
import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;

public class TestInvalidCommandWrongMethodParameterFirstPlayer {
    public TestInvalidCommandWrongMethodParameterFirstPlayer(PluginBase plugin) {
    }

    @Command(main = "test")
    @SenderPlayer
    public void wrongCommad(RemoteConsoleCommandSender sender, String[] args) {
        Assert.fail("command should not be called");
    }
}