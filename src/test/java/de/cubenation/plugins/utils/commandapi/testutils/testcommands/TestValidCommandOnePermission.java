package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandOnePermission {
    private TestPlugin plugin;

    public TestValidCommandOnePermission(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @CommandPermissions("perm1")
    public void testOnePermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testOnePermissionCommand");
    }
}
