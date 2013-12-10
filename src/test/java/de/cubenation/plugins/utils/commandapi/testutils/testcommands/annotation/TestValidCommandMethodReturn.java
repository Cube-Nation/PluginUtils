package de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMethodReturn {
    private TestPlugin plugin;

    public TestValidCommandMethodReturn(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test1")
    public void testVoid(Player player, String[] args) {
        plugin.doSomeThing("void");
    }

    @Command(main = "test2")
    public int testSimple(Player player, String[] args) {
        plugin.doSomeThing("simple");

        return 0;
    }

    @Command(main = "test3")
    public String testObject(Player player, String[] args) {
        plugin.doSomeThing("object");

        return "";
    }
}
