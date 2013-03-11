package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMultiPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandOnePermission;

public class CommandPermissionExternalTest extends AbstractTest {
    @Test
    public void testValidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);
        commandsManager.setPermissionInterface(new PermissionInterface() {
            @Override
            public boolean hasPermission(Player player, String permissionName) {
                return "perm1".equals(permissionName);
            }
        });

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOnePermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOnePermissionCommand"));
    }

    @Test
    public void testValidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);
        commandsManager.setPermissionInterface(new PermissionInterface() {
            @Override
            public boolean hasPermission(Player player, String permissionName) {
                return "perm1".equals(permissionName) || "perm2".equals(permissionName);
            }
        });

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiWorldCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMultiWorldCommand"));
    }

    @Test
    public void testInvalidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);
        commandsManager.setPermissionInterface(new PermissionInterface() {
            @Override
            public boolean hasPermission(Player player, String permissionName) {
                return false;
            }
        });

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cNicht ausreichende Berechtigungen", chatList.get(0));
    }

    @Test
    public void testOneInvalidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);
        commandsManager.setPermissionInterface(new PermissionInterface() {
            @Override
            public boolean hasPermission(Player player, String permissionName) {
                return "perm1".equals(permissionName);
            }
        });

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cNicht ausreichende Berechtigungen", chatList.get(0));
    }

    @Test
    public void testInvalidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);
        commandsManager.setPermissionInterface(new PermissionInterface() {
            @Override
            public boolean hasPermission(Player player, String permissionName) {
                return false;
            }
        });

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cNicht ausreichende Berechtigungen", chatList.get(0));
    }
}
