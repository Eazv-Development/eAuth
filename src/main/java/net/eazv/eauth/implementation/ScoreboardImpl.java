package net.eazv.eauth.implementation;

import java.util.ArrayList;
import java.util.List;
import me.clip.placeholderapi.PlaceholderAPI;
import net.eazv.eauth.EAuthPlugin;
import net.eazv.eauth.utils.MessageUtil;
import net.eazv.eauth.utils.scoreboard.AssembleAdapter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ScoreboardImpl implements AssembleAdapter {

    private final FileConfiguration config;

    public ScoreboardImpl(EAuthPlugin plugin) {
        this.config = plugin.getConfig();
    }

    @Override
    public String getTitle(Player player) {
        String title = MessageUtil.translate(config.getString("SCOREBOARD.TITLE"));

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, title);
        }

        return title;
    }

    @Override
    public List<String> getLines(Player player) {
        List<String> list = new ArrayList<>
            (MessageUtil.translate(config.getStringList("SCOREBOARD.LINES")));
        
        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            return PlaceholderAPI.setPlaceholders(player, list);
        }

        return list;
    }

}