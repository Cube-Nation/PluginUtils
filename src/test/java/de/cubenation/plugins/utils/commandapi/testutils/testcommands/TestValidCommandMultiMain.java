package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiMain {
    private TestPlugin plugin;

    public TestValidCommandMultiMain(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = { "test1", "test2" })
    public void testMultiMainCommand(CommandSender player, String[] args) {
        plugin.doSomeThing("testMultiMainCommand");
    }
}
