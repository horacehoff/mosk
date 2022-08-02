package com.mango.anosk.mosk.world.portals;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class CryingObsidianPortal {
    /**
     * Register a portal that teleports the player to the nether dimension when they walk through it, and has a purple
     * tint.
     */
    public static void registerPortal() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.CRYING_OBSIDIAN)
                .destDimID(new Identifier("the_nether"))
                .tintColor(131, 66, 184)
                .registerPortal();
    }
}
