//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.common.ForgeConfigSpec.DoubleValue;

public class CursedConfig {
    private static final Builder BUILDER = new Builder();
    public static final ForgeConfigSpec CONFIG;
    public static final IntValue DEATH;
    public static final IntValue KNOCK;
    public static final BooleanValue FIRE;
    public static final BooleanValue ANGRY;
    public static final DoubleValue ATTACK_CHANCE;
    public static final DoubleValue ATTACK_MOB_COUNT;
    public static final DoubleValue TARGET_MIGRATION_CHANCE;
    public static final DoubleValue TARGET_MIGRATION_COUNT;
    public static final BooleanValue SLEEP;
    public static final IntValue EXP;
    public static final BooleanValue DROPS;

    static {
        BUILDER.push("Curses");
        DEATH = BUILDER.comment("How much damage the player takes as a percent.").defineInRange("Damage", 200, 100, Integer.MAX_VALUE);
        KNOCK = BUILDER.comment("How much knockback the player takes as a percent.").defineInRange("Knockback", 200, 100, Integer.MAX_VALUE);
        FIRE = BUILDER.comment("Should fire last forever on the player until doused manually?").define("Burns", true);
        ANGRY = BUILDER.comment("Should neutral mobs be hostile to the player?").define("Angry", true);
        ATTACK_CHANCE = BUILDER.comment("The chance per tick, that mobs around a cursed player get angry").defineInRange("Attack Chance", 0.005,0, 1);
        ATTACK_MOB_COUNT = BUILDER.comment("How many mobs get angry at a cursed player at once, as a proportion of all mobs in the radius (~28m").defineInRange("Attack Count", 0.05, 0, 1);
        TARGET_MIGRATION_CHANCE = BUILDER.comment("The chance per tick, that mobs around a cursed player change their target to the cursed soul").defineInRange("Target Migration Chance", 0.025, 0, 1);
        TARGET_MIGRATION_COUNT = BUILDER.comment("How many mobs change their target to a cursed player at once, as a proportion of all mobs in the radius (~28m)").defineInRange("Target Migration Count", 0.3, 0, 1);
        SLEEP = BUILDER.comment("Should the player be unable go to sleep?").define("Insomnia", true);
        BUILDER.pop();
        BUILDER.push("Benefits");
        EXP = BUILDER.comment("How much experience the player gains from slain mobs as a percent").defineInRange("Experience", 300, 100, Integer.MAX_VALUE);
        DROPS = BUILDER.comment("Should the player get unique drops from slain mobs?").define("Unique Drops", true);
        BUILDER.pop();
        CONFIG = BUILDER.build();
    }
}
