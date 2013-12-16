package de.cubenation.plugins.utils.confirmapi;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class ConfirmService {
    private HashMap<Player, Confirm> confirms = new HashMap<Player, Confirm>();

    public Confirm get(Player player) {
        return confirms.get(player);
    }

    public Confirm remove(Player player) {
        return confirms.remove(player);
    }

    public Confirm put(Player player, Confirm data) {
        return confirms.put(player, data);
    }

    public void clear() {
        confirms.clear();
    }

    public boolean hasConfirm(Player player, Class<? extends Confirm> clazz) {
        if (player == null || clazz == null) {
            return false;
        }

        Confirm confirm = confirms.get(player);
        if (confirm == null) {
            return false;
        }

        if (confirm.getClass().isInstance(clazz)) {
            return true;
        }
        return false;
    }
}
