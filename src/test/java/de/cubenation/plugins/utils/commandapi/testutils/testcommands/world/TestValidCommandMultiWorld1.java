package de.cubenation.plugins.utils.commandapi.testutils.testcommands.world;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiWorld1 {
    private TestPlugin plugin;

    public TestValidCommandMultiWorld1(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @World({ "world1", "world2", "" })
    public void testMultiWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiWorldCommand");
    }
}
