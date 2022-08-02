package com.mango.anosk.mosk.config;

import com.mango.anosk.mosk.Mosk;
import com.mojang.datafixers.util.Pair;

public class MoskConfig {
    public static SimpleConfig CONFIG;
    private static MoskConfigProvider configs;
    public static boolean skip_experimental_warning_screen;

    /**
     * It creates a new config provider, creates the configs, and assigns the configs to the config provider
     */
    public static void registerConfigs() {
        configs = new MoskConfigProvider();
        createConfigs();
        CONFIG = SimpleConfig.of(Mosk.MOD_ID).provider(configs).request();
        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("skip_experimental_warning_screen", true), "Should skip the 'Here be dragons!' screens ?");
    }


    private static void assignConfigs() {
        skip_experimental_warning_screen = CONFIG.getOrDefault("skip_experimental_warning_screen", true);

        System.out.println(skip_experimental_warning_screen);
    }
}
