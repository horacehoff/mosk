package com.mango.anosk.mosk.mixin;


import net.minecraft.block.AbstractSignBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSignBlock.class)
public class EditSignMixin {
    /**
     * @author Just_a_Mango
     * @reason Allow signs to be edited
     */
    @Inject(at = @At("HEAD"), method = "onUse")
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {
        if (player.getStackInHand(hand).getItem() == Items.STONE_AXE && world.getBlockEntity(pos) instanceof SignBlockEntity signBlockEntity) {
            signBlockEntity.setEditable(true);
            player.openEditSignScreen(signBlockEntity);
        }
    }
}
