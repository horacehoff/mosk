package com.mango.anosk.mosk.mixin;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(OverworldBiomeCreator.class)
public class OverworldBiomeCreatorMixin {

    /**
     * @author
     * @reason
     */
     private static int getNewSkyColor(float temperature) {
         float f = temperature;
         f /= 3.0f;
         f = MathHelper.clamp(f, -1.0f, 1.0f);
         return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
     }




    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBiome(precipitation, temperature, downfall, 4159204, 329011, spawnSettings, generationSettings, music);
    }

    private static Biome createNewBirch(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBirchBiome(precipitation, temperature, downfall, 4159204, 329011, spawnSettings, generationSettings, music);
    }


    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall).effects(new BiomeEffects.Builder().waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(getNewSkyColor(temperature)).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    private static Biome createBirchBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall).effects(new BiomeEffects.Builder().waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(getNewSkyColor(temperature)).grassColor(9423669).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }


    /**
     * @author
     * @reason
     */
    @Overwrite
    public static Biome createNormalForest(boolean birch, boolean oldGrowth, boolean flower) {
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        addBasicFeatures(builder);
        if (flower) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FOREST_FLOWERS);
        } else {
            DefaultBiomeFeatures.addForestFlowers(builder);
        }
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addDefaultDisks(builder);
        if (flower) {
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.TREES_FLOWER_FOREST);
            builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.FLOWER_FLOWER_FOREST);
            DefaultBiomeFeatures.addDefaultGrass(builder);
        } else {
            if (birch) {
                if (oldGrowth) {
                    DefaultBiomeFeatures.addTallBirchTrees(builder);
                } else {
                    DefaultBiomeFeatures.addBirchTrees(builder);
                }
            } else {
                DefaultBiomeFeatures.addForestTrees(builder);
            }
            DefaultBiomeFeatures.addDefaultFlowers(builder);
            DefaultBiomeFeatures.addForestGrass(builder);
        }
        DefaultBiomeFeatures.addDefaultMushrooms(builder);
        DefaultBiomeFeatures.addDefaultVegetation(builder);
        SpawnSettings.Builder builder2 = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addFarmAnimals(builder2);
        DefaultBiomeFeatures.addBatsAndMonsters(builder2);
        if (flower) {
            builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 4, 2, 3));
        } else if (!birch) {
            builder2.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 4));
        }
        float f = birch ? 0.6f : 0.7f;
        MusicSound musicSound = MusicType.createIngameMusic(SoundEvents.MUSIC_OVERWORLD_JUNGLE_AND_FOREST);
        if (birch) {
            return createNewBirch(Biome.Precipitation.RAIN, f, birch ? 0.6f : 0.8f, builder2, builder, musicSound);
        } else {
            return createBiome(Biome.Precipitation.RAIN, f, birch ? 0.6f : 0.8f, builder2, builder, musicSound);

        }
    }
}
