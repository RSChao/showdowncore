package com.rschao.plugins.showdowncore.showdownCore.api.enchantment.util;

public enum ColorCodes {
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    LIGHT_PURPLE("§d"),
    YELLOW("§e"),
    WHITE("§f"),
    BOLD("§l"),
    ITALIC("§o"),
    UNDERLINE("§n"),
    STRIKETHROUGH("§m"),
    RESET("§r");

    private final String code;
    private final String toString;
    ColorCodes(String code) {
        this.code = code;
        this.toString = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return toString;
    }
}
