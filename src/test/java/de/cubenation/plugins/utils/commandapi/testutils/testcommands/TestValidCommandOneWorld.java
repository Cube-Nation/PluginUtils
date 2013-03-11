package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandOneWorld {
    private TestPlugin plugin;

    public TestValidCommandOneWorld(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @World("world")
    public void testOneWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testOneWorldCommand");
    }
}
