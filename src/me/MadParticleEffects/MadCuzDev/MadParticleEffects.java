package me.MadParticleEffects.MadCuzDev;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MadParticleEffects extends JavaPlugin implements Listener {

	@Override
    public void onEnable() {
		getLogger().info("MadParticleEffects Enabled");
		this.saveDefaultConfig();
		getServer().getPluginManager().registerEvents(this, this);
    }
   
    @Override
    public void onDisable() {
        getLogger().info("MadParticleEffects Disabled");
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
    	Player p1 = (Player) event.getPlayer();
    	if (getConfig().getBoolean("particleEnable." + p1.getName())) {
			getConfig().addDefault("particleEnable." + p1.getName(), false);
    		getConfig().set("particleEnable." + p1.getName(), false);
    		saveConfig();
    } else {}
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	Player player = (Player) sender;
    	
    	if (cmd.getName().equalsIgnoreCase("particle") && sender instanceof Player) {
    		
    		if (player.hasPermission("mpe.use")) {
    		if (args.length == 0) {
    		if (player.hasPermission("mpe.toggle")) {
    			if (getConfig().getBoolean("particleEnable." + player.getName())) {
    			getConfig().addDefault("particleEnable." + player.getName(), false);
        		getConfig().set("particleEnable." + player.getName(), false);
        		saveConfig();
    			player.sendMessage(getConfig().getString("messages.prefix") + " §cParticles Disabled");
    		} else if (!getConfig().getBoolean("particleEnable." + player.getName())) {
    			getConfig().addDefault("particleEnable." + player.getName(), true);
        		getConfig().set("particleEnable." + player.getName(), true);
        		saveConfig();
    			player.sendMessage(getConfig().getString("messages.prefix") + " §aParticles Enabled");
    		}
    		} } else if (args.length == 1) {
    			if (player.hasPermission("mpe.particles")) {
    				if (args[0].equalsIgnoreCase("crit") || args[0].equalsIgnoreCase("flame") || args[0].equalsIgnoreCase("smoke") || args[0].equalsIgnoreCase("heart") || args[0].equalsIgnoreCase("pride") || args[0].equalsIgnoreCase("glyph") || args[0].equalsIgnoreCase("aqua")) {
    		getConfig().addDefault("particle." + player.getName(), args[0]);
    		getConfig().set("particle." + player.getName(), args[0]);
    		saveConfig();
    		player.sendMessage(getConfig().getString("messages.prefix") + " §7Particles set to §c" + args[0].toLowerCase());
    			} else {
       			 player.sendMessage(getConfig().getString("messages.prefix") + " §cValid particles:§7 Crit, Flame, Smoke, Heart, Pride, Glyph, Aqua");
       		 }
    		} else {
        		player.sendMessage(getConfig().getString("messages.prefix") + " §cUsage: /particle or /particle (particle)");
    		} }
    		
    		String currentParticle = getConfig().getString("particle." + player.getName()).toLowerCase();
    		
    		if (getConfig().getBoolean("particleEnable." + player.getName())) {
    			Bukkit.getScheduler().cancelAllTasks();
    			switch (currentParticle) {
    			case ("crit"):
        		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
              	  @SuppressWarnings("deprecation")
    				public void run() {
              		player.playEffect(player.getLocation(), Effect.CRIT, 0);
              	  }
    			}, 0, 1L);
    			break;
    			
    			case ("flame"):
            	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                  @SuppressWarnings("deprecation")
                  	public void run() {
                  	player.playEffect(player.getLocation(), Effect.FLAME, 0);
                  }
        		}, 0, 2L);
        		break;
        		
    			case ("smoke"):
                	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                      @SuppressWarnings("deprecation")
                      	public void run() {
                      	player.playEffect(player.getLocation(), Effect.CLOUD, 0);
                      }
            		}, 0, 2L);
            		break;
        		
    			case ("heart"):
            	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                  @SuppressWarnings("deprecation")
                  	public void run() {
                	  Location location = player.getLocation();
                      for(int i = 0; i < 360; i+=30) {
                          Location flamelocation = location;
                          flamelocation.setZ(player.getLocation().getZ() + Math.cos(i)*1);
                          flamelocation.setX(player.getLocation().getX() + Math.sin(i)*1);
                		  player.playEffect(flamelocation, Effect.HEART, 0);
                      }
                  }
        		}, 0, 5L);
        		break;
        		
    			case ("pride"):
                	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                      @SuppressWarnings("deprecation")
                      	public void run() {
                    	  Location location = player.getLocation();
                			Location location1 = player.getLocation();
                			Location location2 = player.getLocation();
                			Location location3 = player.getLocation();
                			Location location4 = player.getLocation();
                          for(int i = 0; i < 360; i+=5) {
                              Location flamelocation = location;
                              flamelocation.setZ(player.getLocation().getZ() + Math.cos(i)*3);
                              flamelocation.setX(player.getLocation().getX() + Math.sin(i)*3);
                              player.playEffect(flamelocation, Effect.COLOURED_DUST, 0);
                              
                              Location flamelocation1 = location1;
                              flamelocation1.setZ(player.getLocation().getZ() + Math.cos(i)*2.75);
                              flamelocation1.setX(player.getLocation().getX() + Math.sin(i)*2.75);
                              flamelocation1.setY(player.getLocation().getY() + .5);
                              player.playEffect(flamelocation1, Effect.COLOURED_DUST, 0);
                              
                              Location flamelocation2 = location2;
                              flamelocation2.setZ(player.getLocation().getZ() + Math.cos(i)*2.5);
                              flamelocation2.setX(player.getLocation().getX() + Math.sin(i)*2.5);
                              flamelocation2.setY(player.getLocation().getY() + 1);
                              player.playEffect(flamelocation2, Effect.COLOURED_DUST, 0);
                              
                              Location flamelocation3 = location3;
                              flamelocation3.setZ(player.getLocation().getZ() + Math.cos(i)*2.75);
                              flamelocation3.setX(player.getLocation().getX() + Math.sin(i)*2.75);
                              flamelocation3.setY(player.getLocation().getY() + 1.5);
                              player.playEffect(flamelocation3, Effect.COLOURED_DUST, 0);
                              
                              Location flamelocation4 = location4;
                              flamelocation4.setZ(player.getLocation().getZ() + Math.cos(i)*3);
                              flamelocation4.setX(player.getLocation().getX() + Math.sin(i)*3);
                              flamelocation4.setY(player.getLocation().getY() + 2);
                              player.playEffect(flamelocation4, Effect.COLOURED_DUST, 0);
                          }
                      }
            		}, 0, 4L);
            		break;
            		
    			case ("glyph"):
                	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                      @SuppressWarnings("deprecation")
                      	public void run() {
                    	  Location location = player.getLocation();
                			Location location1 = player.getLocation();
                			Location location2 = player.getLocation();
                			Location location3 = player.getLocation();
                			Location location4 = player.getLocation();
                          for(int i = 0; i < 360; i+=5) {
                              Location flamelocation = location;
                              flamelocation.setZ(player.getLocation().getZ() + Math.cos(i)*1);
                              flamelocation.setX(player.getLocation().getX() + Math.sin(i)*1);
                              player.playEffect(flamelocation, Effect.FLYING_GLYPH, 0);
                              
                              Location flamelocation1 = location1;
                              flamelocation1.setZ(player.getLocation().getZ() + Math.cos(i)*1.5);
                              flamelocation1.setX(player.getLocation().getX() + Math.sin(i)*1.5);
                              player.playEffect(flamelocation1, Effect.FLYING_GLYPH, 0);
                              
                              Location flamelocation2 = location2;
                              flamelocation2.setZ(player.getLocation().getZ() + Math.cos(i)*2);
                              flamelocation2.setX(player.getLocation().getX() + Math.sin(i)*2);
                              player.playEffect(flamelocation2, Effect.FLYING_GLYPH, 0);
                              
                              Location flamelocation3 = location3;
                              flamelocation3.setZ(player.getLocation().getZ() + Math.cos(i)*2.5);
                              flamelocation3.setX(player.getLocation().getX() + Math.sin(i)*2.5);
                              player.playEffect(flamelocation3, Effect.FLYING_GLYPH, 0);
                              
                              Location flamelocation4 = location4;
                              flamelocation4.setZ(player.getLocation().getZ() + Math.cos(i)*3);
                              flamelocation4.setX(player.getLocation().getX() + Math.sin(i)*3);
                              player.playEffect(flamelocation4, Effect.FLYING_GLYPH, 0);
                          }
                      }
            		}, 0, 4L);
            		break;
            		
    			case ("aqua"):
                	this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                      @SuppressWarnings("deprecation")
                      	public void run() {
                    	  Location location = player.getLocation();
                			Location location1 = player.getLocation();
                			Location location2 = player.getLocation();
                			Location location3 = player.getLocation();
                			Location location4 = player.getLocation();
                          for(int i = 0; i < 999; i+=90) {
                              Location flamelocation = location;
                              flamelocation.setZ(player.getLocation().getZ() + Math.cos(i)*1);
                              flamelocation.setX(player.getLocation().getX() + Math.sin(i)*1);
                              player.playEffect(flamelocation, Effect.WATERDRIP, 0);
                              
                              Location flamelocation1 = location1;
                              flamelocation1.setZ(player.getLocation().getZ() + Math.cos(i)*1.2);
                              flamelocation1.setX(player.getLocation().getX() + Math.sin(i)*1.2);
                              flamelocation1.setY(player.getLocation().getY() + .5);
                              player.playEffect(flamelocation1, Effect.WATERDRIP, 0);
                              
                              Location flamelocation2 = location2;
                              flamelocation2.setZ(player.getLocation().getZ() + Math.cos(i)*1.5);
                              flamelocation2.setX(player.getLocation().getX() + Math.sin(i)*1.5);
                              flamelocation2.setY(player.getLocation().getY() + 1);
                              player.playEffect(flamelocation2, Effect.WATERDRIP, 0);
                              
                              Location flamelocation3 = location3;
                              flamelocation3.setZ(player.getLocation().getZ() + Math.cos(i)*2);
                              flamelocation3.setX(player.getLocation().getX() + Math.sin(i)*2);
                              flamelocation3.setY(player.getLocation().getY() + 1.5);
                              player.playEffect(flamelocation3, Effect.WATERDRIP, 0);
                              
                              Location flamelocation4 = location4;
                              flamelocation4.setZ(player.getLocation().getZ() + Math.cos(i)*2.5);
                              flamelocation4.setX(player.getLocation().getX() + Math.sin(i)*2.5);
                              flamelocation4.setY(player.getLocation().getY() + 2);
                              player.playEffect(flamelocation4, Effect.WATERDRIP, 0);
                          }
                      }
            		}, 0, 5L);
            		break;
        		default:
    		} } else {}
    		
    		} else { player.sendMessage(getConfig().getString("messages.prefix") + " §cYou don't have access to this"); }
    		} return true;
    }  
}
