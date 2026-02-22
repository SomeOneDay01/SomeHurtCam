package com.somehurtcam;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;

import java.util.Arrays;
import com.somehurtcam.mixin.GameOptionsAccessor;

public class SomeHurtCamMod implements ClientModInitializer {
    public static final String MOD_ID = "somehurtcam";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static boolean hurtCamDisabled = true;
    private static boolean keyRegistered = false;

    private static final KeyBinding TOGGLE_KEY = new KeyBinding(
        "key.somehurtcam.toggle",
        InputUtil.Type.KEYSYM,
        GLFW.GLFW_KEY_F8,
        "key.categories.misc"
    );

    @Override
    public void onInitializeClient() {
        LOGGER.info("SomeHurtCam initialized!");
    }

    public static boolean isHurtCamDisabled() {
        return hurtCamDisabled;
    }

    public static void onClientTick(MinecraftClient client) {
        if (!keyRegistered) {
            registerKeyBinding(client.options);
        }

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

    private static void registerKeyBinding(GameOptions options) {
        if (options == null) {
            return;
        }

        GameOptionsAccessor accessor = (GameOptionsAccessor) options;
        KeyBinding[] current = accessor.somehurtcam$getKeysAll();
        boolean alreadyRegistered = Arrays.stream(current).anyMatch(key -> key == TOGGLE_KEY);
        if (!alreadyRegistered) {
            KeyBinding[] extended = Arrays.copyOf(current, current.length + 1);
            extended[current.length] = TOGGLE_KEY;
            accessor.somehurtcam$setKeysAll(extended);
            KeyBinding.updateKeysByCode();
        }
        keyRegistered = true;
    }
}
