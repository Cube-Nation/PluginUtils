package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.plugin.PluginBase;
import org.junit.Assert;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandWrongMethodParameterWithoutFirst {
    public TestInvalidCommandWrongMethodParameterWithoutFirst(PluginBase plugin) {
    }

    @Command(main = "test")
    public void wrongCommad() {
        Assert.fail("command should not be called");
    }
}