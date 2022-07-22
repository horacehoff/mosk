package com.mango.anosk.mosk.blocks.entity;


import com.mango.anosk.mosk.blocks.Blocks;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class BlockEntities {
    public static BlockEntityType<HupperBlockEntity> HUPPER_BLOCK_ENTITY;



    public static void InitEntities() {
        HUPPER_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, "mosk:hupper_block_entity", FabricBlockEntityTypeBuilder.create(HupperBlockEntity::new, Blocks.HUPPER_BLOCK).build(null));
    }
}
