package de.cubenation.plugins.utils.chatapi;

public class ColorParser {
    public static String replaceColor(String src) {
        String ret = src;
        ret = ret.replace("{BLACK}", "§0");
        ret = ret.replace("{DARK_BLUE}", "§1");
        ret = ret.replace("{DARK_GREEN}", "§2");
        ret = ret.replace("{DARK_AQUA}", "§3");
        ret = ret.replace("{DARK_CYAN}", "§3");
        ret = ret.replace("{DARK_RED}", "§4");
        ret = ret.replace("{DARK_PURPLE}", "§5");
        ret = ret.replace("{PURPLE}", "§5");
        ret = ret.replace("{GOLD}", "§6");
        ret = ret.replace("{GRAY}", "§7");
        ret = ret.replace("{DARK_GRAY}", "§8");
        ret = ret.replace("{BLUE}", "§9");
        ret = ret.replace("{GREEN}", "§a");
        ret = ret.replace("{BRIGHT_GREEN}", "§a");
        ret = ret.replace("{AQUA}", "§b");
        ret = ret.replace("{CYAN}", "§b");
        ret = ret.replace("{RED}", "§c");
        ret = ret.replace("{LIGHT_PURPLE}", "§d");
        ret = ret.replace("{PINK}", "§d");
        ret = ret.replace("{YELLOW}", "§e");
        ret = ret.replace("{WHITE}", "§f");
        ret = ret.replace("{MAGIC}", "§k");
        ret = ret.replace("{RANDOM}", "§k");
        ret = ret.replace("{BOLD}", "§l");
        ret = ret.replace("{STRIKETHROUGH}", "§m");
        ret = ret.replace("{UNDERLINE}", "§n");
        ret = ret.replace("{UNDERLINED}", "§n");
        ret = ret.replace("{ITALIC}", "§o");
        ret = ret.replace("{RESET}", "§r");
        ret = ret.replace("{PLAIN_WHITE}", "§r");

        if (ret.contains("{PRE_LAST}")) {
            // TODO support pre last color
        }

        return ret;
    }
}
