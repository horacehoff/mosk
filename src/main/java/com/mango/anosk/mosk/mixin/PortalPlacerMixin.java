package com.mango.anosk.mosk.mixin;

import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalPlacer;
import net.kyrptonaught.customportalapi.portal.frame.PortalFrameTester;
import net.kyrptonaught.customportalapi.util.PortalLink;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockLocating;
import net.minecraft.world.World;
import net.minecraft.world.border.WorldBorder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(PortalPlacer.class)
public class PortalPlacerMixin {

    @Shadow
    private static boolean canHoldPortal(BlockState state) {
        return false;
    }


    /**
     * @author Just_a_Mango
     * @reason Prevent the destination portal from being created and only teleport the player.
     */
    @Overwrite
    public static Optional<BlockLocating.Rectangle> createDestinationPortal(World world, BlockPos blockPos, BlockState frameBlock, Direction.Axis axis) {
        WorldBorder worldBorder = world.getWorldBorder();
        PortalLink link = CustomPortalApiRegistry.getPortalLinkFromBase(frameBlock.getBlock());
        PortalFrameTester portalFrameTester = link.getFrameTester().createInstanceOfPortalFrameTester();
        for (BlockPos.Mutable mutable : BlockPos.iterateInSquare(blockPos, 16, Direction.WEST, Direction.SOUTH)) {
            BlockPos testingPos = mutable.toImmutable();
            if (!worldBorder.contains(testingPos)) continue;

            int solidY = Math.min(world.getTopY(), world.getBottomY() + world.getDimension().logicalHeight()) - 5;
            BlockPos pos = null;
            while (solidY >= 3) {
                if (canHoldPortal(world.getBlockState(testingPos.withY(solidY)))) {
                    BlockPos testRect = portalFrameTester.doesPortalFitAt(world, testingPos.withY(solidY + 1), axis);
                    if (testRect != null) {
                        pos = testRect;
                        break;
                    }
                }
                solidY--;
            }

            if (pos != null) {
                portalFrameTester.createPortal(world, pos, Blocks.AIR.getDefaultState(), axis);
                return Optional.of(null);
            }
        }
        portalFrameTester.createPortal(world, blockPos, Blocks.AIR.getDefaultState(), axis);
        return Optional.of(null);
    }
}
