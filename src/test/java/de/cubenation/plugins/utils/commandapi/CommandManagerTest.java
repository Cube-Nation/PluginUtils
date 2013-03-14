package de.cubenation.plugins.utils.commandapi;

import org.bukkit.ChatColor;
import org.junit.Assert;
import org.junit.Test;

import de.cubenation.plugins.utils.commandapi.exception.CommandException;
import de.cubenation.plugins.utils.commandapi.testutils.AbstractTest;
import de.cubenation.plugins.utils.commandapi.testutils.testcommands.parameter.TestValidCommandMain;

public class CommandManagerTest extends AbstractTest {
    @Test
    public void testCommandManager() throws CommandException {
        commandsManager.add(TestValidCommandMain.class);

        executeComannd("/test");

        Assert.assertEquals(1, testValid.size());
        Assert.assertTrue(testValid.containsKey("testOneMainCommand"));
        Assert.assertEquals(new Short((short) 1), testValid.get("testOneMainCommand"));

        testValid.clear();
        Assert.assertEquals(0, testValid.size());

        commandsManager.clear();
        executeComannd("/test");
        Assert.assertEquals(0, testValid.size());
        Assert.assertEquals(1, chatList.size());
        Assert.assertEquals(ChatColor.RED + "Befehl nicht gefunden. Versuche /test help", chatList.get(0));
    }
}
