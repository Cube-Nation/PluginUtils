package de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissions;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandOneNotMultiPermission {
    private TestPlugin plugin;

    public TestValidCommandOneNotMultiPermission(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @CommandPermissions({ "perm1", "perm2" })
    public void testOnePermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testOnePermissionCommand");
    }

    @Command(main = "test")
    @CommandPermissionsNot({ "perm1", "perm2" })
    public void testNotPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testNotPermissionCommand");
    }
}
