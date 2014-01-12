package de.cubenation.plugins.utils.chatapi;

import static org.junit.Assert.assertEquals;

import org.bukkit.ChatColor;
import org.junit.Test;

import de.cubenation.plugins.utils.chatapi.ColorParser;

public class ColorParserTest {
    @Test
    public void noColor() {
        assertEquals("bla bla", ColorParser.replaceColor("bla bla"));
    }

    @Test
    public void simpleColorBlack() {
        assertEquals("bla " + ChatColor.BLACK + "bla", ColorParser.replaceColor("bla {BLACK}bla"));
    }

    @Test
    public void simpleColorDarkBlue() {
        assertEquals("bla " + ChatColor.DARK_BLUE + "bla", ColorParser.replaceColor("bla {DARK_BLUE}bla"));
    }

    @Test
    public void simpleColorDarkGreen() {
        assertEquals("bla " + ChatColor.DARK_GREEN + "bla", ColorParser.replaceColor("bla {DARK_GREEN}bla"));
    }

    @Test
    public void simpleColorDarkAqua() {
        assertEquals("bla " + ChatColor.DARK_AQUA + "bla", ColorParser.replaceColor("bla {DARK_AQUA}bla"));
        assertEquals("bla " + ChatColor.DARK_AQUA + "bla", ColorParser.replaceColor("bla {DARK_CYAN}bla"));
    }

    @Test
    public void simpleColorDarkRed() {
        assertEquals("bla " + ChatColor.DARK_RED + "bla", ColorParser.replaceColor("bla {DARK_RED}bla"));
    }

    @Test
    public void simpleColorDarkPurple() {
        assertEquals("bla " + ChatColor.DARK_PURPLE + "bla", ColorParser.replaceColor("bla {DARK_PURPLE}bla"));
        assertEquals("bla " + ChatColor.DARK_PURPLE + "bla", ColorParser.replaceColor("bla {PURPLE}bla"));
    }

    @Test
    public void simpleColorGold() {
        assertEquals("bla " + ChatColor.GOLD + "bla", ColorParser.replaceColor("bla {GOLD}bla"));
    }

    @Test
    public void simpleColorGray() {
        assertEquals("bla " + ChatColor.GRAY + "bla", ColorParser.replaceColor("bla {GRAY}bla"));
    }

    @Test
    public void simpleColorDarkGray() {
        assertEquals("bla " + ChatColor.DARK_GRAY + "bla", ColorParser.replaceColor("bla {DARK_GRAY}bla"));
    }

    @Test
    public void simpleColorBlue() {
        assertEquals("bla " + ChatColor.BLUE + "bla", ColorParser.replaceColor("bla {BLUE}bla"));
    }

    @Test
    public void simpleColorGreen() {
        assertEquals("bla " + ChatColor.GREEN + "bla", ColorParser.replaceColor("bla {GREEN}bla"));
        assertEquals("bla " + ChatColor.GREEN + "bla", ColorParser.replaceColor("bla {BRIGHT_GREEN}bla"));
    }

    @Test
    public void simpleColorAqua() {
        assertEquals("bla " + ChatColor.AQUA + "bla", ColorParser.replaceColor("bla {AQUA}bla"));
        assertEquals("bla " + ChatColor.AQUA + "bla", ColorParser.replaceColor("bla {CYAN}bla"));
    }

    @Test
    public void simpleColorRed() {
        assertEquals("bla " + ChatColor.RED + "bla", ColorParser.replaceColor("bla {RED}bla"));
    }

    @Test
    public void simpleColorLightPurple() {
        assertEquals("bla " + ChatColor.LIGHT_PURPLE + "bla", ColorParser.replaceColor("bla {LIGHT_PURPLE}bla"));
        assertEquals("bla " + ChatColor.LIGHT_PURPLE + "bla", ColorParser.replaceColor("bla {PINK}bla"));
    }

    @Test
    public void simpleColorYellow() {
        assertEquals("bla " + ChatColor.YELLOW + "bla", ColorParser.replaceColor("bla {YELLOW}bla"));
    }

    @Test
    public void simpleColorWhite() {
        assertEquals("bla " + ChatColor.WHITE + "bla", ColorParser.replaceColor("bla {WHITE}bla"));
    }

    @Test
    public void simpleColorMagic() {
        assertEquals("bla " + ChatColor.MAGIC + "bla", ColorParser.replaceColor("bla {MAGIC}bla"));
        assertEquals("bla " + ChatColor.MAGIC + "bla", ColorParser.replaceColor("bla {RANDOM}bla"));
    }

    @Test
    public void simpleColorBold() {
        assertEquals("bla " + ChatColor.BOLD + "bla", ColorParser.replaceColor("bla {BOLD}bla"));
    }

    @Test
    public void simpleColorStrikeThrough() {
        assertEquals("bla " + ChatColor.STRIKETHROUGH + "bla", ColorParser.replaceColor("bla {STRIKETHROUGH}bla"));
    }

    @Test
    public void simpleColorUnderline() {
        assertEquals("bla " + ChatColor.UNDERLINE + "bla", ColorParser.replaceColor("bla {UNDERLINE}bla"));
        assertEquals("bla " + ChatColor.UNDERLINE + "bla", ColorParser.replaceColor("bla {UNDERLINED}bla"));
    }

    @Test
    public void simpleColorItalic() {
        assertEquals("bla " + ChatColor.ITALIC + "bla", ColorParser.replaceColor("bla {ITALIC}bla"));
    }

    @Test
    public void simpleColorReset() {
        assertEquals("bla " + ChatColor.RESET + "bla", ColorParser.replaceColor("bla {RESET}bla"));
        assertEquals("bla " + ChatColor.RESET + "bla", ColorParser.replaceColor("bla {PLAIN_WHITE}bla"));
    }

    @Test
    public void multiColor() {
        assertEquals(ChatColor.GREEN + "bla " + ChatColor.BLACK + "bla", ColorParser.replaceColor("{GREEN}bla {BLACK}bla"));
    }

    @Test
    public void preLastColorLast() {
        assertEquals(ChatColor.GREEN + "bla " + ChatColor.BLACK + "bla " + ChatColor.GREEN + "bla",
                ColorParser.replaceColor("{GREEN}bla {BLACK}bla {PRE_LAST}bla"));
    }

    @Test
    public void preLastColorFirst() {
        assertEquals(ChatColor.RESET + "bla " + ChatColor.BLACK + "bla " + ChatColor.WHITE + "bla",
                ColorParser.replaceColor("{PRE_LAST}bla {BLACK}bla {WHITE}bla"));
    }

    @Test
    public void preLastColorMiddle() {
        assertEquals(ChatColor.GREEN + "bla " + ChatColor.RESET + "bla " + ChatColor.GREEN + "bla",
                ColorParser.replaceColor("{GREEN}bla {PRE_LAST}bla {GREEN}bla"));
    }

    @Test
    public void preLastColorMulti() {
        assertEquals(ChatColor.GREEN + "bla " + ChatColor.RESET + "bla " + ChatColor.GREEN + "bla",
                ColorParser.replaceColor("{GREEN}bla {PRE_LAST}bla {PRE_LAST}bla"));
    }
}
