package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginBase;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandMethodExceptionString {
    public TestInvalidCommandMethodExceptionString(PluginBase plugin) throws Exception {
    }

    @Command(main = "test", min = 1, max = 1)
    public void emptyCommand(Player player, String args) throws Exception {
        throw new Exception("test exception");
    }
}
