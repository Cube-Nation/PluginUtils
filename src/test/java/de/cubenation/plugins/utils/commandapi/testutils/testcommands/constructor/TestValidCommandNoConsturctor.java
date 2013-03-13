package de.cubenation.plugins.utils.commandapi.testutils.testcommands.constructor;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestValidCommandNoConsturctor {
    @Command(main = "test")
    public void testNoConstructorCommand(Player player, String[] args) {

    }
}
