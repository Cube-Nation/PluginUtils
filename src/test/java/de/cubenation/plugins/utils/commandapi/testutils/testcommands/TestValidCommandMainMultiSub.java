package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMainMultiSub {
    private TestPlugin plugin;

    public TestValidCommandMainMultiSub(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", sub = { "bla1", "bla2" })
    public void testOneMainMultiSubCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneMainMultiSubCommand");
    }
}
