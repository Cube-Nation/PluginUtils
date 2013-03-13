package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongConstructor {
    public TestInvalidCommandWrongConstructor(Integer plugin) {
        Assert.fail("constructor should not be called");
    }

    @Command(main = "")
    public void emptyCommand(Player player, String[] args) {
        Assert.fail("command should not be called");
    }
}