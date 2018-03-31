package me.s3ns3iw00.manager;

import me.s3ns3iw00.main.MainClass;

public class LanguageManager {

    private String prefix = "§6[§cSmart§9Server§3Say§6]";
    private MainClass plugin;

    public LanguageManager(MainClass plugin) {
        this.plugin = plugin;
    }

    public String pluginActiveMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §aThe plugin is active!";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §aA plugin aktív!";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String pluginDeactiveMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §cThe plugin is inactive!";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §cA plugin inaktív!";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String reloadMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §aThe plugin is reloaded!";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §aA plugin újratöltve!";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String aboutMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §aThe plugin's developer is §5S3nS3IW00§a. You can find it on Spigot.";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §aA plugin fejlesztője §5S3nS3IW00§a. Megtalálod Spigoton.";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String argumentErrorMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §cCommand argument not found. Available arguments: reload, about";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §cParancs argumentum nem található! Elérhető argumentumok: reload, about";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String permissionErrorMessage() {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §cYou don't have permission to do this!";
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §cNincs engedélyed ehhez!";
        } else {
            message = prefix + " §cLanguage error! Please check the config.";
        }
        return message;
    }

    public String needUpdateMessage(String link) {
        String message;
        if (plugin.getLanguage().equalsIgnoreCase("english")) {
            message = prefix + " §aUpdate available! Download here: "+link;
        } else if (plugin.getLanguage().equalsIgnoreCase("magyar")) {
            message = prefix + " §cFrissítés elérhető! Töltsd le innen: "+link;
        } else {
            message = prefix + " §aUpdate available! Download here: "+link;
        }
        return message;
    }

}
