package de.cubenation.plugins.utils.commandapi.testutils.testcommands;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;

public class TestValidCommandEmptyConsturctor {
    public TestValidCommandEmptyConsturctor() {
    }

    @Command(main = "test")
    public void testEmptyConstructorCommand(Player player, String[] args) {

    }
}
