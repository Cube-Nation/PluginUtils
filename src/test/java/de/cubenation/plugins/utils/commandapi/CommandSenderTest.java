package de.cubenation.plugins.utils.commandapi;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestBlockCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.TestConsoleCommandSender;
import de.cubenation.plugins.utils.commandapi.testutils.TestRemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation.TestValidCommandBlock;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation.TestValidCommandConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation.TestValidCommandMulti;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation.TestValidCommandRemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;

public class CommandSenderTest extends AbstractTest {
    @Test
    public void testConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandConsole.class);

        executeComannd("/test", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testConsoleCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testConsoleCommand"));
    }

    @Test
    public void testPlayerCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test");

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testBlockCommand() throws CommandException {
        commandsManager.add(TestValidCommandBlock.class);

        executeComannd("/test", new TestBlockCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testBlockCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testBlockCommand"));
    }

    @Test
    public void testRemoteConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandRemoteConsole.class);

        executeComannd("/test", new TestRemoteConsole() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testRemoteConsoleCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testRemoteConsoleCommand"));
    }

    @Test
    public void testNotConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }

    @Test
    public void testNotPlayerCommand() throws CommandException {
        commandsManager.add(TestValidCommandConsole.class);

        executeComannd("/test");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }

    @Test
    public void testNotBlockCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test", new TestBlockCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }

    @Test
    public void testNotRemoteConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test", new TestRemoteConsole() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }

    @Test
    public void testMultiCommand() throws CommandException {
        commandsManager.add(TestValidCommandMulti.class);

        executeComannd("/test", new TestConsoleCommandSender() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, chatList.size());
        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMultiCommand"));
    }
}
