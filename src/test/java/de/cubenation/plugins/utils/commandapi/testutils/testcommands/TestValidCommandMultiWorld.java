package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiWorld {
    private TestPlugin plugin;

    public TestValidCommandMultiWorld(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @World({ "world1", "world2", "" })
    public void testMultiWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiWorldCommand");
    }
}
