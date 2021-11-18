package kim.pokemon.kimexpand.crazyauctions.api;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public enum Category {
    
    NONE("None", new ArrayList<>()),
    OTHER("Other", getOthers()),
    ARMOR("Armor", getArmor()),
    WEAPONS("Weapons", getWeapons()),
    TOOLS("Tools", getTools()),
    FOOD("Food", getFood()),
    POTIONS("Potions", getPotions()),
    BLOCKS("Blocks", getBlocks()),
    POKEMONS("Pokemons",getPokemons());
	
	
    private String Name;
    private ArrayList<Material> Items;
    
    private static ArrayList<Material> armor;
    private static ArrayList<Material> blocks;
    private static ArrayList<Material> food;
    private static ArrayList<Material> other;
    private static ArrayList<Material> pokemons;
    private static ArrayList<Material> potions;
    private static ArrayList<Material> tools;
    private static ArrayList<Material> weapons;
    
    
    /**
     * @param name Name of the Shop Type.
     */
    private Category(String name, ArrayList<Material> items) {
        this.Name = name;
        this.Items = items;
    }
    
    /**
     * @param name Name of the Type you want.
     * @return Returns the Type as a Enum.
     */
    public static Category getFromName(String name) {
        for (Category type : Category.values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        return null;
    }
    
    public static ArrayList<Material> getPokemons(){

    	if(pokemons != null) {
    		return pokemons;
    	}

    	ArrayList<Material> ma = new ArrayList<>();
    	File f = new File(Bukkit.getPluginManager().getPlugin("KimCore").getDataFolder(),"pokemons.yml");
    	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
    	List<String> materials = yaml.getStringList("materials");
    	for(String m:materials) {
    		Material target = Material.valueOf(m.toUpperCase());
    		ma.add(target);
    	}

    	pokemons = ma;

    	return ma;
    }
    
    private static ArrayList<Material> getArmor() {
    	
    	if(armor != null) {
    		return armor;
    	}
    	
    	
        ArrayList<Material> ma = new ArrayList<>();
        if (Version.getCurrentVersion().isNewer(Version.v1_12_R1)) {
            ma.add(Material.matchMaterial("GOLDEN_HELMET"));
            ma.add(Material.matchMaterial("GOLDEN_CHESTPLATE"));
            ma.add(Material.matchMaterial("GOLDEN_LEGGINGS"));
            ma.add(Material.matchMaterial("GOLDEN_BOOTS"));
        } else {
            ma.add(Material.matchMaterial("GOLD_HELMET"));
            ma.add(Material.matchMaterial("GOLD_CHESTPLATE"));
            ma.add(Material.matchMaterial("GOLD_LEGGINGS"));
            ma.add(Material.matchMaterial("GOLD_BOOTS"));
        }
        ma.add(Material.DIAMOND_HELMET);
        ma.add(Material.DIAMOND_CHESTPLATE);
        ma.add(Material.DIAMOND_LEGGINGS);
        ma.add(Material.DIAMOND_BOOTS);
        ma.add(Material.CHAINMAIL_HELMET);
        ma.add(Material.CHAINMAIL_CHESTPLATE);
        ma.add(Material.CHAINMAIL_LEGGINGS);
        ma.add(Material.CHAINMAIL_BOOTS);
        ma.add(Material.IRON_HELMET);
        ma.add(Material.IRON_CHESTPLATE);
        ma.add(Material.IRON_LEGGINGS);
        ma.add(Material.IRON_BOOTS);
        ma.add(Material.DIAMOND_HELMET);
        ma.add(Material.DIAMOND_CHESTPLATE);
        ma.add(Material.DIAMOND_LEGGINGS);
        ma.add(Material.DIAMOND_BOOTS);
        
        armor = ma;
        return ma;
    }
    
    private static ArrayList<Material> getTools() {
    	
    	if(tools != null) {
    		return tools;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        if (Version.getCurrentVersion().isNewer(Version.v1_12_R1)) {
            ma.add(Material.matchMaterial("WOODEN_PICKAXE"));
            ma.add(Material.matchMaterial("WOODEN_AXE"));
            ma.add(Material.matchMaterial("WOODEN_SHOVEL"));
            ma.add(Material.matchMaterial("WOODEN_HOE"));
            ma.add(Material.matchMaterial("GOLDEN_PICKAXE"));
            ma.add(Material.matchMaterial("GOLDEN_AXE"));
            ma.add(Material.matchMaterial("GOLDEN_SHOVEL"));
            ma.add(Material.matchMaterial("GOLDEN_HOE"));
            ma.add(Material.matchMaterial("STONE_SHOVEL"));
            ma.add(Material.matchMaterial("IRON_SHOVEL"));
            ma.add(Material.matchMaterial("DIAMOND_SHOVEL"));
        } else {
            ma.add(Material.matchMaterial("WOOD_PICKAXE"));
            ma.add(Material.matchMaterial("WOOD_AXE"));
            ma.add(Material.matchMaterial("WOOD_SPADE"));
            ma.add(Material.matchMaterial("WOOD_HOE"));
            ma.add(Material.matchMaterial("GOLD_PICKAXE"));
            ma.add(Material.matchMaterial("GOLD_AXE"));
            ma.add(Material.matchMaterial("GOLD_SPADE"));
            ma.add(Material.matchMaterial("GOLD_HOE"));
            ma.add(Material.matchMaterial("STONE_SPADE"));
            ma.add(Material.matchMaterial("IRON_SPADE"));
            ma.add(Material.matchMaterial("DIAMOND_SPADE"));
        }
        ma.add(Material.STONE_PICKAXE);
        ma.add(Material.IRON_PICKAXE);
        ma.add(Material.DIAMOND_PICKAXE);
        ma.add(Material.STONE_AXE);
        ma.add(Material.IRON_AXE);
        ma.add(Material.DIAMOND_AXE);
        ma.add(Material.STONE_HOE);
        ma.add(Material.IRON_HOE);
        ma.add(Material.DIAMOND_HOE);
        
        tools = ma;
        
        return ma;
    }
    
    private static ArrayList<Material> getWeapons() {
    	
    	if(weapons != null) {
    		return weapons;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        if (Version.getCurrentVersion().isNewer(Version.v1_12_R1)) {
            ma.add(Material.matchMaterial("WOODEN_SWORD"));
            ma.add(Material.matchMaterial("WOODEN_AXE"));
            ma.add(Material.matchMaterial("GOLDEN_SWORD"));
            ma.add(Material.matchMaterial("GOLDEN_AXE"));
        } else {
            ma.add(Material.matchMaterial("WOOD_SWORD"));
            ma.add(Material.matchMaterial("WOOD_AXE"));
            ma.add(Material.matchMaterial("GOLD_SWORD"));
            ma.add(Material.matchMaterial("GOLD_AXE"));
        }
        ma.add(Material.STONE_SWORD);
        ma.add(Material.IRON_SWORD);
        ma.add(Material.DIAMOND_SWORD);
        ma.add(Material.STONE_AXE);
        ma.add(Material.IRON_AXE);
        ma.add(Material.DIAMOND_AXE);
        ma.add(Material.BOW);
        
        weapons = ma;
        
        return ma;
    }
    
    private static ArrayList<Material> getFood() {
    	
    	if(food != null) {
    		return food;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        for (Material m : Material.values()) {
            if (m.isEdible()) {
                if (m != Material.POTION) ma.add(m);
            }
        }
        
        food = ma;
        
        return ma;
    }
    
    private static ArrayList<Material> getPotions() {
    	
    	if(potions != null) {
    		return potions;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        ma.add(Material.POTION);
        if (Material.matchMaterial("SPLASH_POTION") != null) {
            ma.add(Material.matchMaterial("SPLASH_POTION"));
        }
        if (Material.matchMaterial("LINGERING_POTION") != null) {
            ma.add(Material.matchMaterial("LINGERING_POTION"));
        }
        
        potions = ma;
        
        return ma;
    }
    
    private static ArrayList<Material> getBlocks() {
    	
    	if(blocks != null) {
    		return blocks;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        for (Material m : Material.values()) {
            if (m.isBlock()) {
                ma.add(m);
            }
        }
        
        blocks = ma;
        return ma;
    }
    
    private static ArrayList<Material> getOthers() {
    	
    	if(other != null) {
    		return other;
    	}
    	
        ArrayList<Material> ma = new ArrayList<>();
        for (Material m : Material.values()) {
            if (!(getArmor().contains(m) || getTools().contains(m) || getWeapons().contains(m) || getFood().contains(m) || getPotions().contains(m) || getBlocks().contains(m) || getPokemons().contains(m))) {
                ma.add(m);
            }
        }
        
        other = ma;
        return ma;
    }
    
    /**
     * @return Returns the type name as a string.
     */
    public String getName() {
        return Name;
    }
    
    public ArrayList<Material> getItems() {
        return Items;
    }
    
}