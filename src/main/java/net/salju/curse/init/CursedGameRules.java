package net.salju.curse.init;

import net.minecraft.world.level.GameRules;

import static net.minecraft.world.level.GameRules.register;

public class CursedGameRules {
    public static GameRules.Key<GameRules.IntegerValue> ATTACK_CHANCE = null;
    public static GameRules.Key<GameRules.IntegerValue> ATTACK_MOB_COUNT = null;
    public static GameRules.Key<GameRules.IntegerValue> TARGET_MIGRATION_CHANCE = null;
    public static GameRules.Key<GameRules.IntegerValue> TARGET_MIGRATION_COUNT = null;

    public static void init() {
        ATTACK_CHANCE = register("attackChance", GameRules.Category.MISC, GameRules.IntegerValue.create(25));
        ATTACK_MOB_COUNT = register("attackMobCount", GameRules.Category.MISC, GameRules.IntegerValue.create(50));
        TARGET_MIGRATION_CHANCE = register("targetMigrationChance", GameRules.Category.MISC, GameRules.IntegerValue.create(500));
        TARGET_MIGRATION_COUNT = register("targetMigrationCount", GameRules.Category.MISC, GameRules.IntegerValue.create(100));
    }
}
