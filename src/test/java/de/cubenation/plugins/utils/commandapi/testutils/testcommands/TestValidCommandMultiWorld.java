package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiWorld {
    private TestPlugin plugin;

    public TestValidCommandMultiWorld(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @World({ "world1", "world2" })
    public void testMultiWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiWorldCommand");
    }
}
