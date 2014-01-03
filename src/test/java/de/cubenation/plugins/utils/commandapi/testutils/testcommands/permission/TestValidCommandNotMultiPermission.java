package de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.annotation.CommandPermissionsNot;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestValidCommandNotMultiPermission {
    private TestPlugin plugin;

    public TestValidCommandNotMultiPermission(TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Command(main = "test")
    @CommandPermissionsNot({ "perm1", "perm2" })
    public void testNotPermissionCommand(Player player, String[] args) {
        plugin.doSomeThing("testNotPermissionCommand");
    }
}
