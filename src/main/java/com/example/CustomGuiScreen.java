package com.example;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import java.io.IOException;

public class CustomGuiScreen extends GuiScreen {
    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int CLOSE_BUTTON_ID = 0;
    private static final int SPRINT_BUTTON_ID = 1;
    public static boolean autoSprintEnabled = false;
    private GuiButton sprintButton;
    private static final String COLOR_GREEN = "\u00A7a"; // Minecraft color code for green
    private static final String COLOR_RED = "\u00A7c";   // Minecraft color code for red

    @Override
    public void initGui() {
        int centerX = width / 2 - BUTTON_WIDTH / 2;
        int centerY = height / 2 - BUTTON_HEIGHT / 2;
        
        // Sprint button - initialize with current state
        sprintButton = new GuiButton(SPRINT_BUTTON_ID, centerX, centerY, BUTTON_WIDTH, BUTTON_HEIGHT, "Auto Sprint: " + (autoSprintEnabled ? COLOR_GREEN + "On" : COLOR_RED + "Off"));
        buttonList.add(sprintButton);
        
        // Close button - at the bottom of the screen
        buttonList.add(new GuiButton(CLOSE_BUTTON_ID, centerX, height - BUTTON_HEIGHT - 10, BUTTON_WIDTH, BUTTON_HEIGHT, COLOR_RED + "Close"));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        
        // Draw title
        String title = "My GUI";
        int titleWidth = fontRendererObj.getStringWidth(title);
        fontRendererObj.drawString(title, width / 2 - titleWidth / 2, height / 4, 0xFFFFFF);
        
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == CLOSE_BUTTON_ID) {
            mc.displayGuiScreen(null);
        } else if (button.id == SPRINT_BUTTON_ID) {
            autoSprintEnabled = !autoSprintEnabled;
            sprintButton.displayString = "Auto Sprint: " + (autoSprintEnabled ? COLOR_GREEN + "On" : COLOR_RED + "Off");
            // Save settings when changed
            ModConfig.saveConfig();
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
} 