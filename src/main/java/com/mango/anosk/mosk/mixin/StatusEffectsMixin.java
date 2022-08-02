package com.mango.anosk.mosk.mixin;


import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Potions.class)
public interface StatusEffectsMixin {

     @Invoker("register")
     static Potion invokeRegister(String name, Potion potion) {
         throw new AssertionError();
     }
}
