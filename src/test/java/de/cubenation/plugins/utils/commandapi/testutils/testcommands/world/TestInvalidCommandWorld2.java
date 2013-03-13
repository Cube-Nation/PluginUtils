package de.cubenation.plugins.utils.commandapi.testutils.testcommands.world;

import org.bukkit.entity.Player;

import de.cubenation.plugins.utils.commandapi.annotation.Command;
import de.cubenation.plugins.utils.commandapi.testutils.TestPlugin;

public class TestInvalidCommandWorld2 {
	private TestPlugin plugin;

	public TestInvalidCommandWorld2(TestPlugin plugin) {
		this.plugin = plugin;
	}

	@Command(main = "test")
	public void testFiveWorldCommand(Player player, String[] args) {
		plugin.doSomeThing("testFiveWorldCommand");
	}
}
