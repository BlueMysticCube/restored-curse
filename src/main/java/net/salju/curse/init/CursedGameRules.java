package net.salju.curse.init;

import net.minecraft.world.level.GameRules;

import static net.minecraft.world.level.GameRules.register;

public class CursedGameRules {
    public static final GameRules.Key<GameRules.IntegerValue> ATTACK_CHANCE;
    public static final GameRules.Key<GameRules.IntegerValue> ATTACK_MOB_COUNT;
    public static final GameRules.Key<GameRules.IntegerValue> TARGET_MIGRATION_CHANCE;
    public static final GameRules.Key<GameRules.IntegerValue> TARGET_MIGRATION_COUNT;

    static {
        ATTACK_CHANCE = register("attackChance", GameRules.Category.MISC, GameRules.IntegerValue.create(25));
        ATTACK_MOB_COUNT = register("attackMobCount", GameRules.Category.MISC, GameRules.IntegerValue.create(50));
        TARGET_MIGRATION_CHANCE = register("targetMigrationChance", GameRules.Category.MISC, GameRules.IntegerValue.create(500));
        TARGET_MIGRATION_COUNT = register("targetMigrationCount", GameRules.Category.MISC, GameRules.IntegerValue.create(100));
    }
}
