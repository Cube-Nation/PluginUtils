package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMin {
    private TestPlugin plugin;

    public TestValidCommandMin(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", min = 1)
    public void testMinCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinCommand");
    }
}
