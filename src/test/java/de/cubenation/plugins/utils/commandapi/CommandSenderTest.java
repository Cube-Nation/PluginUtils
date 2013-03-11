package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestBlock;
import de.cubenation.plugins.utils.commandapi.testutils.TestConsole;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.TestRemoteConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandBlock;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandConsole;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMulti;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandRemoteConsole;

public class CommandSenderTest extends AbstractTest {
    @Test
    public void testConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandConsole.class);

        executeComannd("/test", new TestConsole());

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testConsoleCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testConsoleCommand"));
    }

    @Test
    public void testPlayerCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test", new TestPlayer());

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));
    }

    @Test
    public void testBlockCommand() throws CommandException {
        commandsManager.add(TestValidCommandBlock.class);

        executeComannd("/test", new TestBlock());

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testBlockCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testBlockCommand"));
    }

    @Test
    public void testRemoteConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandRemoteConsole.class);

        executeComannd("/test", new TestRemoteConsole());

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testRemoteConsoleCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testRemoteConsoleCommand"));
    }

    @Test
    public void testNotConsoleCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestConsole() {
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

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }

    @Test
    public void testNotBlockCommand() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test", new TestBlock() {
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

        final ArrayList<String> chatList = new ArrayList<String>();
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

        executeComannd("/test", new TestConsole());

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testMultiCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testMultiCommand"));
    }
}
