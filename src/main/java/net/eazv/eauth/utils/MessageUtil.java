package net.eazv.eauth.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageUtil {

    public String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public List<String> translate(List<String> messages) {
        List<String> buffered = new ArrayList<>();
        messages.forEach(message -> buffered.add(translate(message)));
        return buffered;
    }
}
