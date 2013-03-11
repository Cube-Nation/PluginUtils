package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.SenderConsole;
import de.cubenation.plugins.utils.commandapi.annotation.SenderPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMulti {
    private TestPlugin plugin;

    public TestValidCommandMulti(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @SenderConsole
    @SenderPlayer
    public void testMultiCommand(CommandSender console, String[] args) {
        plugin.doSomeThing("testMultiCommand");
    }
}
