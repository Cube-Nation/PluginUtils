package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.Console;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandConsole {
    private TestPlugin plugin;

    public TestValidCommandConsole(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @Console
    public void testConsoleCommand(ConsoleCommandSender console, String[] args) {
        plugin.doSomeThing("testConsoleCommand");
    }
}
