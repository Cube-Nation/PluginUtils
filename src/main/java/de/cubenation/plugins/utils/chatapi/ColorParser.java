package de.cubenation.plugins.utils.chatapi;

import org.bukkit.ChatColor;

public class ColorParser {
    public static String replaceColor(String src) {
        String ret = src;
        ret = ret.replace("{BLACK}", ChatColor.BLACK.toString()); // §0
        ret = ret.replace("{DARK_BLUE}", ChatColor.DARK_BLUE.toString()); // §1
        ret = ret.replace("{DARK_GREEN}", ChatColor.DARK_GREEN.toString()); // §2
        ret = ret.replace("{DARK_AQUA}", ChatColor.DARK_AQUA.toString()); // §3
        ret = ret.replace("{DARK_CYAN}", ChatColor.DARK_AQUA.toString()); // §3
        ret = ret.replace("{DARK_RED}", ChatColor.DARK_RED.toString()); // §4
        ret = ret.replace("{DARK_PURPLE}", ChatColor.DARK_PURPLE.toString()); // §5
        ret = ret.replace("{PURPLE}", ChatColor.DARK_PURPLE.toString()); // §5
        ret = ret.replace("{GOLD}", ChatColor.GOLD.toString()); // §6
        ret = ret.replace("{GRAY}", ChatColor.GRAY.toString()); // §7
        ret = ret.replace("{DARK_GRAY}", ChatColor.DARK_GRAY.toString()); // §8
        ret = ret.replace("{BLUE}", ChatColor.BLUE.toString()); // §9
        ret = ret.replace("{GREEN}", ChatColor.GREEN.toString()); // §a
        ret = ret.replace("{BRIGHT_GREEN}", ChatColor.GREEN.toString()); // §a
        ret = ret.replace("{AQUA}", ChatColor.AQUA.toString()); // §b
        ret = ret.replace("{CYAN}", ChatColor.AQUA.toString()); // §b
        ret = ret.replace("{RED}", ChatColor.RED.toString()); // §c
        ret = ret.replace("{LIGHT_PURPLE}", ChatColor.LIGHT_PURPLE.toString()); // §d
        ret = ret.replace("{PINK}", ChatColor.LIGHT_PURPLE.toString()); // §d
        ret = ret.replace("{YELLOW}", ChatColor.YELLOW.toString()); // §e
        ret = ret.replace("{WHITE}", ChatColor.WHITE.toString()); // §f
        ret = ret.replace("{MAGIC}", ChatColor.MAGIC.toString()); // §k
        ret = ret.replace("{RANDOM}", ChatColor.MAGIC.toString()); // §k
        ret = ret.replace("{BOLD}", ChatColor.BOLD.toString()); // §l
        ret = ret.replace("{STRIKETHROUGH}", ChatColor.STRIKETHROUGH.toString()); // §m
        ret = ret.replace("{UNDERLINE}", ChatColor.UNDERLINE.toString()); // §n
        ret = ret.replace("{UNDERLINED}", ChatColor.UNDERLINE.toString()); // §n
        ret = ret.replace("{ITALIC}", ChatColor.ITALIC.toString()); // §o
        ret = ret.replace("{RESET}", ChatColor.RESET.toString()); // §r
        ret = ret.replace("{PLAIN_WHITE}", ChatColor.RESET.toString()); // §r

        while (ret.contains("{PRE_LAST}")) {
            int pos = ret.indexOf("{PRE_LAST}");

            String firstPart = ret.substring(0, pos);

            String foundPreLastColor = ChatColor.RESET.toString();
            int indexLast = firstPart.lastIndexOf(ChatColor.COLOR_CHAR);
            if (indexLast > -1) {
                int preIndexLast = ret.substring(0, indexLast).lastIndexOf(ChatColor.COLOR_CHAR);
                if (preIndexLast > -1) {
                    foundPreLastColor = ret.substring(preIndexLast, preIndexLast + 2);
                }
            }

            ret = ret.substring(0, pos) + foundPreLastColor + ret.substring(pos + 10);
        }

        return ret;
    }
}
