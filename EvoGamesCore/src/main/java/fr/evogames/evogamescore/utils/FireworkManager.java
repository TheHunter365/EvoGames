package fr.evogames.evogamescore.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Builder;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkManager {
	
	private boolean flicker;
	private boolean trail;
	private Type type;
	private Color color;
	private Color fade;
	private int power;
	
	public FireworkManager(boolean flicker, boolean trail, Type type, Color color, Color fade, int power) {
		this.flicker = flicker;
		this.trail = trail;
		this.type = type;
		this.color = color;
		this.fade = fade;
		this.power = power;
	}
	
	public void spawn(Location location) {
		Firework firework = (Firework) location.getWorld().spawnEntity(location, EntityType.FIREWORK);
		firework.detonate();
		
		firework.setFireworkMeta(buildFireworkMeta(firework));
	}
	
	public void killAll(World world) {
		for (Firework firework : find(world)) {
			firework.remove();
		}
	}
	
	public List<Firework> find(World world) {
		List<Firework> fireworkList = new ArrayList<Firework>();
		for (Entity entity : world.getEntities()) {
			if(entity instanceof Firework) {
				Firework firework = (Firework) entity;
				if(firework.getFireworkMeta().getEffects().contains(buildFireworkEffect())) {
					fireworkList.add(firework);
				}
			}
		}
		return fireworkList;
	}
	
	private FireworkMeta buildFireworkMeta(Firework firework) {
		FireworkMeta fireworkMeta = firework.getFireworkMeta();
		
		FireworkEffect effect = buildFireworkEffect();
		fireworkMeta.setPower(power);
		
		fireworkMeta.addEffect(effect);
		return fireworkMeta;
	}
	
	private FireworkEffect buildFireworkEffect() {
		Builder builder = FireworkEffect.builder();
		if(type != null) {
			builder.with(type);
		}
		if(color != null) {
			builder.withColor(color);
		}
		if(fade != null) {
			builder.withFade(fade);
		}
		builder.flicker(flicker);
		builder.trail(trail);
		FireworkEffect effect = builder.build();
		return effect;
	}
}