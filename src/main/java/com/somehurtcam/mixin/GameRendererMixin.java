package com.somehurtcam.mixin;

import com.somehurtcam.SomeHurtCamMod;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Inject(method = "bobViewWhenHurt", at = @At("HEAD"), cancellable = true)
    private void onBobViewWhenHurt(MatrixStack matrices, float tickDelta, CallbackInfo ci) {
        if (SomeHurtCamMod.isHurtCamDisabled()) {
            ci.cancel();
        }
    }
}
