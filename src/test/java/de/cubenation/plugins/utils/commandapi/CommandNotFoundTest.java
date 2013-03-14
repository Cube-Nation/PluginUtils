package de.cubenation.plugins.utils.commandapi;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMainSub;

public class CommandNotFoundTest extends AbstractTest {
    @Test
    public void testCommandNotFound1() throws CommandException {
        executeComannd("/test1");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test1 help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound2() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test1");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test1 help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound3() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test1 bla");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test1 help oder /test1 bla help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound4() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test1 bla foo");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test1 help oder /test1 bla help", chatList.get(0));
    }

    @Test
    public void testCommandNotFound5() throws CommandException {
        commandsManager.add(TestValidCommandMainSub.class);

        executeComannd("/test bla1");

        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test help oder /test bla1 help", chatList.get(0));
    }
}
