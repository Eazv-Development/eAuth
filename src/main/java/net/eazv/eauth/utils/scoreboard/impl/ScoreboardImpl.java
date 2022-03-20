package net.eazv.eauth.utils.scoreboard.impl;

import java.util.ArrayList;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import net.eazv.eauth.AuthPlugin;
import net.eazv.eauth.utils.MessageUtil;
import net.eazv.eauth.utils.scoreboard.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ScoreboardImpl implements AssembleAdapter {

    private final FileConfiguration config = AuthPlugin.getInstance().getConfig();

    @Override
    public String getTitle(Player player) {
        return MessageUtil.translate(config.getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> list = new ArrayList<>();

        MessageUtil.translate(config.getStringList("SCOREBOARD.LINES"))
                .forEach(list::add);
        
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, list);
        }

        return list;
    }

}
