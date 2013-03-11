package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMultiPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandOnePermission;

public class CommandPermissionTest extends AbstractTest {
    @Test
    public void testValidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name);
            }
        });

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOnePermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOnePermissionCommand"));
    }

    @Test
    public void testValidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name) || "perm2".equals(name);
            }
        });

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiWorldCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMultiWorldCommand"));
    }

    @Test
    public void testInvalidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return false;
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Nicht ausreichende Berechtigungen", chatList.get(0));
    }

    @Test
    public void testOneInvalidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name);
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Nicht ausreichende Berechtigungen", chatList.get(0));
    }

    @Test
    public void testInvalidMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return false;
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Nicht ausreichende Berechtigungen", chatList.get(0));
    }
}
