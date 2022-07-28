package com.mango.anosk.mosk.mixin;


import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(JukeboxBlock.class)
public class JukeboxBlockMixin {

    @Final
    @Shadow
    public static BooleanProperty HAS_RECORD;


    @Inject(method = "setRecord", at = @At("HEAD"))
    public void setRecord(Entity user, WorldAccess world, BlockPos pos, BlockState state, ItemStack stack, CallbackInfo ci) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (!(blockEntity instanceof JukeboxBlockEntity)) {
            return;
        }
        ((JukeboxBlockEntity)blockEntity).setRecord(stack.copy());
        world.setBlockState(pos, (BlockState)state.with(HAS_RECORD, true), Block.NOTIFY_LISTENERS);
        world.emitGameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Emitter.of(user, state));
        for (PiglinEntity entity : world.getEntitiesByClass(PiglinEntity.class, new Box(0, 0, 0, 15, 15, 15), new Predicate<PiglinEntity>() {
            @Override
            public boolean test(PiglinEntity piglinEntity) {
                return false;
            }
        })) {
            entity.setDancing(true);
        }
    }

}
