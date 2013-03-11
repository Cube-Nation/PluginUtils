package de.cubenation.plugins.utils.commandapi;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlayer;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.TestValidCommandMainSub;

public class CommandNotFoundTest extends AbstractTest {
    @Test
    public void testCommandNotFound1() throws CommandException {
        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test1", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cBefehl nicht gefunden. Versuche /test1 help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound2() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test1", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cBefehl nicht gefunden. Versuche /test1 help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound3() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test1 bla", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cBefehl nicht gefunden. Versuche /test1 help oder /test1 bla help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound4() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test1 bla foo", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cBefehl nicht gefunden. Versuche /test1 help oder /test1 bla help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound5() throws CommandException {
        commandsManager.add(TestValidCommandMainSub.class);

        final ArrayList<String> chatList = new ArrayList<String>();
        executeComannd("/test bla1", new TestPlayer() {
            @Override
            public void sendMessage(String message) {
                chatList.add(message);
            }
        });

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals("§cBefehl nicht gefunden. Versuche /test help oder /test bla1 help", chatList.get(0));
    }
}
