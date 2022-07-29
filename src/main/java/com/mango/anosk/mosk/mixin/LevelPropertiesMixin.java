package com.mango.anosk.mosk.mixin;

import com.mango.anosk.mosk.config.MoskConfig;
import com.mojang.serialization.Lifecycle;
import net.minecraft.world.level.LevelProperties;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;


@Mixin(LevelProperties.class)
public class LevelPropertiesMixin {

    @Final
    @Shadow
    private
    Lifecycle lifecycle;

    /**
     * @author Just_a_Mango
     * @reason Remove the "here be dragons!" warning screen
     */
    @Overwrite
    public Lifecycle getLifecycle() {
        if (MoskConfig.skip_experimental_warning_screen) {
            return Lifecycle.stable();
        } else {
            return this.lifecycle;
        }
    }
}
