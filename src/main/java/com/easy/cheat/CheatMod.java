package com.easy.cheat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class CheatMod implements ModInitializer {
    
    private static boolean xray = false;
    
    @Override
    public void onInitialize() {
        // G - полёт
        KeyBinding flyKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Fly", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_G, "Cheats"));
        
        // H - скорость
        KeyBinding speedKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Speed", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_H, "Cheats"));
        
        // R - X-Ray
        KeyBinding xrayKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "XRay", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, "Cheats"));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            
            // Полёт
            if (flyKey.wasPressed()) {
                client.player.getAbilities().allowFlying = !client.player.getAbilities().allowFlying;
                client.player.getAbilities().flying = !client.player.getAbilities().flying;
            }
            
            // Скорость
            if (speedKey.wasPressed()) {
                float current = client.player.getAbilities().getWalkSpeed();
                client.player.getAbilities().setWalkSpeed(current > 0.2f ? 0.1f : 0.5f);
            }
            
            // X-Ray (упрощённо - прозрачные блоки)
            if (xrayKey.wasPressed()) {
                xray = !xray;
                client.worldRenderer.reload();
            }
        });
    }
}
