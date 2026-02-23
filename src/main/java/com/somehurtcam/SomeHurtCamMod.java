package com.somehurtcam;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

public class SomeHurtCamMod implements ClientModInitializer {
    public static final String MOD_ID = "somehurtcam";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static boolean hurtCamDisabled = true;

    private static final KeyBinding TOGGLE_KEY = new KeyBinding(
        "key.somehurtcam.toggle",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_F8,
        "category.somehurtcam"
    );

    @Override
    public void onInitializeClient() {
        KeyBindingHelper.registerKeyBinding(TOGGLE_KEY);
        LOGGER.info("SomeHurtCam initialized!");
    }

    public static boolean isHurtCamDisabled() {
        return hurtCamDisabled;
    }

    public static void onClientTick(MinecraftClient client) {
        while (TOGGLE_KEY.wasPressed()) {
            hurtCamDisabled = !hurtCamDisabled;
            String status = hurtCamDisabled
                ? "message.somehurtcam.hurtcam_disabled"
                : "message.somehurtcam.hurtcam_enabled";
            if (client.player != null) {
                client.player.sendMessage(new net.minecraft.text.TranslatableText(status), true);
            }
            LOGGER.info("HurtCam disabled: {}", hurtCamDisabled);
        }
    }
}
