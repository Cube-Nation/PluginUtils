package de.cubenation.plugins.utils.commandapi.testutils.testcommands.exception;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Asynchron;
import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestInvalidCommandErrorHandler {
    @Command(main = "test", max = 0)
    @Asynchron
    public void emptyCommand(Player player) throws Exception {
        throw new Exception("test exception");
    }

    @Command(main = "test", min = 1, max = 1)
    @Asynchron
    public void emptyCommand(Player player, String str) throws Exception {
        throw new Exception("test exception");
    }
}
