package com.easy.cheat;

import net.fabricmc.api.ModInitializer;

public class CheatMod implements ModInitializer {
    static boolean fly = false;
    
    @Override
    public void onInitialize() {
        System.out.println("[EasyCheat] Loaded");
    }
}
