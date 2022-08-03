package com.mango.anosk.mosk.mixin;

import net.minecraft.client.sound.MusicType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.sound.MusicSound;
import net.minecraft.sound.SoundEvents;
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

    @Shadow
    protected static int getSkyColor(float temperature) {
        return 0;
    }

    @Shadow
    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return null;
    }
    @Shadow
    private static Biome createBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return null;
    }


    /**
     * Create a new birch biome with the given precipitation, temperature, downfall, grass color, foliage color, spawn
     * settings, generation settings, and music.
     *
     * @param precipitation The type of precipitation that occurs in this biome.
     * @param temperature The temperature of the biome.
     * @param downfall The amount of rain/snowfall in the biome.
     * @param spawnSettings The spawn settings for the biome.
     * @param generationSettings The generation settings for the biome.
     * @param music The music that plays in the biome.
     * @return A biome
     */
    private static Biome createNewBirch(Biome.Precipitation precipitation, float temperature, float downfall, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return createBirchBiome(precipitation, temperature, downfall, 4159204, 329011, spawnSettings, generationSettings, music);
    }
    /**
     * It creates a new birch biome with a more 'vibrant' grass color
     *
     * @param precipitation The type of precipitation that occurs in this biome.
     * @param temperature The temperature of the biome.
     * @param downfall The amount of rain/snowfall in the biome.
     * @param waterColor The color of the water in the biome.
     * @param waterFogColor The color of the fog in the water.
     * @param spawnSettings The spawn settings for the biome.
     * @param generationSettings This is the biome's generation settings.
     * @param music The music that plays in the biome.
     * @return A biome.
     */
    private static Biome createBirchBiome(Biome.Precipitation precipitation, float temperature, float downfall, int waterColor, int waterFogColor, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings, @Nullable MusicSound music) {
        return new Biome.Builder().precipitation(precipitation).temperature(temperature).downfall(downfall).effects(new BiomeEffects.Builder().waterColor(waterColor).waterFogColor(waterFogColor).fogColor(12638463).skyColor(getSkyColor(temperature)).grassColor(9423669).moodSound(BiomeMoodSound.CAVE).music(music).build()).spawnSettings(spawnSettings.build()).generationSettings(generationSettings.build()).build();
    }


    @Shadow
    private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {}


    /**
     * @author Just_a_Mango
     * @reason Modify the birch biome's grass color
     *
     * It creates a normal forest biome
     *
     * @param birch Whether the biome is a birch biome.
     * @param oldGrowth Whether the forest is old growth.
     * @param flower Whether the biome should have flowers.
     * @return A biome.
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
                // DefaultBiomeFeatures.addTallBirchTrees(builder);
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
            // If it's a birch biome, use our own createNewBirch method. Else, use the regular createBiome method.
            return createNewBirch(Biome.Precipitation.RAIN, f, birch ? 0.6f : 0.8f, builder2, builder, musicSound);
        } else {
            return createBiome(Biome.Precipitation.RAIN, f, birch ? 0.6f : 0.8f, builder2, builder, musicSound);

        }
    }
}
