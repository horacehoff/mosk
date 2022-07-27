package com.mango.anosk.mosk.world.portals;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;

public class CryingObsidianPortal {
    public static void registerPortal() {
        CustomPortalBuilder.beginPortal()
                .frameBlock(Blocks.CRYING_OBSIDIAN)
                .destDimID(new Identifier("the_nether"))
                .tintColor(131, 66, 184)
                .registerPortal();
    }
}
