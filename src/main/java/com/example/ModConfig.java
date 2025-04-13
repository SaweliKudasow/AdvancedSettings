package com.example;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import java.io.File;

public class ModConfig {
    private static Configuration config;
    private static final String CATEGORY_GENERAL = "general";
    
    public static void init(File configFile) {
        config = new Configuration(configFile);
        loadConfig();
    }
    
    public static void loadConfig() {
        try {
            config.load();
            
            // Get or create the auto sprint property
            Property autoSprintProp = config.get(CATEGORY_GENERAL, "autoSprint", false);
            CustomGuiScreen.autoSprintEnabled = autoSprintProp.getBoolean();
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }
    
    public static void saveConfig() {
        if (config != null) {
            config.get(CATEGORY_GENERAL, "autoSprint", false).set(CustomGuiScreen.autoSprintEnabled);
            config.save();
        }
    }
} 