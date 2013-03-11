package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandMultiPermission {
    private TestPlugin plugin;

    public TestValidCommandMultiPermission(JavaPlugin plugin) {
        this.plugin = (TestPlugin) plugin;
    }

    @Command(main = "test")
    @CommandPermissions({ "perm1", "perm2" })
    public void testMultiWorldCommand(Player player, String[] args) {
        plugin.doSomeThing("testMultiWorldCommand");
    }
}
