package me.ClownStick.RandomDrops;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin implements Listener{
	
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
		shuffleMap();
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("rd")) {
			
			sender.sendMessage(ChatColor.BLUE + "Random Drops" + ChatColor.GREEN + " away!");
			
			
//			Player player = (Player) sender;
//			
//			Location loc = player.getLocation();
//			World wd = player.getWorld();
			return true;
			
		}
		
		return false;
	}
	
	Map<Material, Material> shuffledMaterialsMap;

	public void shuffleMap() {
	  shuffledMaterialsMap = new HashMap<>();
	  Material[] materials = Material.values();
	  List<Material> shuffledMaterials = (Arrays.asList(materials));
	  Collections.shuffle(shuffledMaterials);
	  for (int i = 0; i < Material.values().length; i++) {
	    shuffledMaterialsMap.put(materials[i], shuffledMaterials.get(i));
	    
	  
	  }
	}
	
	@EventHandler
	public void onBreaking(BlockBreakEvent event) {
		
		
		
		Player player = event.getPlayer();
		
		if (player.getGameMode() == GameMode.SURVIVAL) {
		
//			event.setCancelled(true);
			
	//		Player player = event.getPlayer();
			Block b = event.getBlock();
			World wd = b.getWorld();
			
			Collection<ItemStack> ogDrops = b.getDrops();
			
			Location loc = b.getLocation();
			
			 for (ItemStack item : ogDrops) {
			   	  item.setType(shuffledMaterialsMap.get(item.getType()));
			   	  wd.dropItemNaturally(loc, item);
			   	}
			
	//		Iterator<ItemStack> k = ogDrops.iterator();
			
	//		if (k.hasNext())
	//			wd.dropItemNaturally(loc, k.next());
		}
	}
}
