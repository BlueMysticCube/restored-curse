package net.salju.curse.loot;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParam;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.salju.curse.CurseManager;
import net.salju.curse.init.CursedLootConditions;

import java.util.Set;

public class CurseCondition implements LootItemCondition {
    private static final CurseCondition INSTANCE = new CurseCondition();

    public CurseCondition() {
    }

    @Override
    public LootItemConditionType getType() {
        return CursedLootConditions.CURSE_CONDITION.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        Player killer = getKiller(lootContext);
        if (killer == null) return false;
        return CurseManager.isCursed(killer);
    }

    @Override
    public Set<LootContextParam<?>> getReferencedContextParams() {
        return Set.of(LootContextParams.KILLER_ENTITY, LootContextParams.THIS_ENTITY);
    }

    private Player getKiller(LootContext lootContext) {
        if (lootContext.hasParam(LootContextParams.KILLER_ENTITY)
                && lootContext.getParam(LootContextParams.KILLER_ENTITY) instanceof Player player) {
			return player;
		}
        return null;
    }

    public static class Serializer implements net.minecraft.world.level.storage.loot.Serializer<CurseCondition> {

        @Override
        public void serialize(JsonObject jsonObject, CurseCondition condition, JsonSerializationContext context) {
        }

        @Override
        public CurseCondition deserialize(JsonObject jsonObject, JsonDeserializationContext context) {
            return INSTANCE;
        }
    }
}
