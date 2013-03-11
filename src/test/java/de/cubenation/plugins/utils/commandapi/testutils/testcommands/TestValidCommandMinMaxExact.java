package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMinMaxExact {
    private TestPlugin plugin;

    public TestValidCommandMinMaxExact(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test", min = 1, max = 1)
    public void testMinMaxExactCommand(Player player, String[] args) {
        plugin.doSomeThing("testMinMaxExactCommand");
    }
}
