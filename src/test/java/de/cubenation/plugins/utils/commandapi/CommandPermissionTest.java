package de.cubenation.plugins.utils.commandapi;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandMultiPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandNotMultiPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandNotPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandOneNotMultiPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandOneNotPermission;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.permission.TestValidCommandOnePermission;

public class CommandPermissionTest extends AbstractTest {
    @Test
    public void testValidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);

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

        Assert.assertEquals(0, chatList.size());
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

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiWorldCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMultiWorldCommand"));
    }

    @Test
    public void testInvalidOnePermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandOnePermission.class);

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
    public void testInvalidOneMultiPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandMultiPermission.class);

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
    public void testInvalidNotPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandNotPermission.class);

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
    public void testValidNotPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandNotPermission.class);

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

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testNotPermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testNotPermissionCommand"));
    }

    @Test
    public void testValidMultiNotPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandNotMultiPermission.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name) || "perm2".equals(name);
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
    public void testInvalidOneMultiNotPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandNotMultiPermission.class);

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
    public void testInvalidMultiNotPermissionCommand() throws CommandException {
        commandsManager.add(TestValidCommandNotMultiPermission.class);

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

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testNotPermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testNotPermissionCommand"));
    }

    @Test
    public void testValidOneNotPermissionCommand1() throws CommandException {
        commandsManager.add(TestValidCommandOneNotPermission.class);

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

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testNotPermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testNotPermissionCommand"));
    }

    @Test
    public void testValidOneNotPermissionCommand2() throws CommandException {
        commandsManager.add(TestValidCommandOneNotPermission.class);

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

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOnePermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOnePermissionCommand"));
    }

    @Test
    public void testValidOneNotMultiPermissionCommand1() throws CommandException {
        commandsManager.add(TestValidCommandOneNotMultiPermission.class);

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

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testNotPermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testNotPermissionCommand"));
    }

    @Test
    public void testValidOneNotMultiPermissionCommand2() throws CommandException {
        commandsManager.add(TestValidCommandOneNotMultiPermission.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm1".equals(name) || "perm2".equals(name);
            }

            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOnePermissionCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOnePermissionCommand"));
    }

    @Test
    public void testInvalidOneNotMultiPermissionCommand1() throws CommandException {
        commandsManager.add(TestValidCommandOneNotMultiPermission.class);

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
    public void testInvalidOneNotMultiPermissionCommand2() throws CommandException {
        commandsManager.add(TestValidCommandOneNotMultiPermission.class);

        executeComannd("/test", new TestPlayer() {
            @Override
            public boolean hasPermission(String name) {
                return "perm2".equals(name);
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
