package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.CommandSender;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMulti {
    private TestPlugin plugin;

    public TestValidCommandMulti(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @SenderConsole
    @SenderPlayer
    public void testMultiCommand(CommandSender console, String[] args) {
        plugin.doSomeThing("testMultiCommand");
    }
}
