package fr.evogames.evogamescore.player;

import java.util.HashMap;
import java.util.Map;

public class EvoGameStat implements fr.evogames.evogamesapi.player.EvoGameStat {

    private String gameName;
    private Map<String, String> attributes;

    public EvoGameStat(String gameName) {
        this.gameName = gameName;
        this.attributes = new HashMap<>();
    }

    @Override
    public String getGameName() {
        return this.gameName;
    }

    @Override
    public Map<String, String> getAttributes() {
        return this.attributes;
    }

    public void addAttribute(String name, String value) {
        this.attributes.put(name, value);
    }

    public String getAttribute(String name) {
        return this.attributes.get(name);
    }
}
