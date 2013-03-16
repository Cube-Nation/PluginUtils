package de.cubenation.plugins.utils.commandapi.testutils.testcommands.annotation;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Asynchron;
import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestCommandErrorHandler {
    @Command(main = "test1", min = 0, max = 0)
    @Asynchron
    public void oneCommand(Player player) throws Exception {
    }

    @Command(main = "test1", min = 1, max = 1)
    @Asynchron
    public void oneCommand(Player player, String str) throws Exception {
    }
}
