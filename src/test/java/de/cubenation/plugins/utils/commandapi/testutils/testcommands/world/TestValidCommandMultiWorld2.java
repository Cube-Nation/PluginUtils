package de.cubenation.plugins.utils.commandapi.testutils.testcommands.world;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.World;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiWorld2 {
    private TestPlugin plugin;

    public TestValidCommandMultiWorld2(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test", min = 0, max = 0)
    @World("world1")
    public void testMultiWorldCommand1(Player player) {
        plugin.doSomeThing("testMultiWorldCommand1");
    }

    @Command(main = "test", min = 1, max = 1)
    @World("world1")
    public void testMultiWorldCommand2(Player player, String arg) {
        plugin.doSomeThing("testMultiWorldCommand2");
    }
    
    @Command(main = "test", min = 1, max = 1)
    @World("world2")
    public void testMultiWorldCommand3(Player player, String arg) {
        plugin.doSomeThing("testMultiWorldCommand2");
    }
}
