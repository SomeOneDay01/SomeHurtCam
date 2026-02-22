package com.somehurtcam.mixin;

import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GameOptions.class)
public interface GameOptionsAccessor {
    @Accessor("keysAll")
    KeyBinding[] somehurtcam$getKeysAll();

    @Mutable
    @Accessor("keysAll")
    void somehurtcam$setKeysAll(KeyBinding[] keysAll);
}
