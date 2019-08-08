package fr.evogames.evogamesproxy;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class EvoGamesProxy extends Plugin {

    private EvoGamesProxy instance;

    @Override
    public void onEnable() {
        instance = this;

        ProxyServer.getInstance().getLogger().info("[EvoGamesProxy] Lancer avec succès.");
    }

    public EvoGamesProxy getInstance() {
        return instance;
    }

}
