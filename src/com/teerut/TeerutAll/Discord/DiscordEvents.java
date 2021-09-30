package com.teerut.TeerutAll.Discord;

import com.teerut.TeerutAll.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.io.IOException;

public class DiscordEvents implements Listener {
    private String url;
    private String avatarUrl;
    private String username;
    public Main plugin;

    public DiscordEvents(Main plugin) {
        this.plugin = plugin;
        this.url = this.plugin.getConfig().getString("Webhook Url");
        this.avatarUrl = this.plugin.getConfig().getString("Webhook AvatarUrl");
        this.username = this.plugin.getConfig().getString("Webhook Username");

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(this.url);
        webhook.setAvatarUrl(this.avatarUrl);
        webhook.setContent(event.getPlayer().getName() + " joined the game");
        webhook.setUsername(this.username);

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    @EventHandler
    public void PlayerLevelChangeEvent(PlayerQuitEvent event) throws IOException {
        DiscordWebhook webhook = new DiscordWebhook(this.url);
        webhook.setAvatarUrl(this.avatarUrl);
        webhook.setContent(event.getPlayer().getName() + " left the game");
        webhook.setUsername(this.username);

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @EventHandler
    public void playerAdvancementDoneEvent(PlayerAdvancementDoneEvent event) {
        DiscordWebhook webhook = new DiscordWebhook(this.url);
        webhook.setAvatarUrl(this.avatarUrl);
        webhook.addEmbed(new DiscordWebhook.EmbedObject()
                .setTitle(event.getPlayer().getName())
                .setColor(Color.GRAY)
        );
        webhook.setUsername(this.username);

        try {
            webhook.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
