//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package net.salju.curse.events;

import net.minecraft.advancements.Advancement;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.RandomSource;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent.Phase;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.Clone;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.salju.curse.CurseManager;
import net.salju.curse.gui.CurseGuiMenu;
import net.salju.curse.init.CursedConfig;
import net.salju.curse.CursedTags;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber
public final class CurseEvents {

    private static final float PERCENTAGE_FACTOR = 0.01f;
    private static final int SLEEP_INTERRUPT_TIMER_TICKS = 95;
    private static final int FIRE_TIMER_RESET_TICKS = 120;
    // This isn't really a radius. The bounding box of the player will be expanded in every direction by this amount.
    // Neutral mobs in the resulting box will be upset
    private static final float ANGRY_RADIUS = 28.0f;
    // If the player gets this close or closer to a neutral mob, it will be upset regardless of line of sight
    private static final double FORCED_ANGER_DISTANCE = 4.0;
    // If this advancement is completed, then the GUI will not be shown anymore
    private static final ResourceLocation QUERY_ENDING_ADVANCEMENT_LOCATION = ResourceLocation.withDefaultNamespace("story/root");

    private CurseEvents() {
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onHurt(LivingHurtEvent event) {
        LivingEntity target = event.getEntity();
        if (target instanceof Player player) {
            if (CurseManager.isCursed(player)) {
                event.setAmount(event.getAmount() * ((float) CursedConfig.DEATH.get() * PERCENTAGE_FACTOR));
            }
        }

    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onKnockKnock(LivingKnockBackEvent event) {
        LivingEntity target = event.getEntity();
        if (target instanceof Player player) {
            if (CurseManager.isCursed(player)) {
                event.setStrength(event.getStrength() * ((float) CursedConfig.KNOCK.get() * PERCENTAGE_FACTOR));
            }
        }

    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent event) {
        if (event.phase == Phase.END) {
            Player player = event.player;
            if (experiencesCursedEffects(player) && !player.level().isClientSide()) {
                if (CursedConfig.FIRE.get()) {
                    executeFireEffect(player);
                }
                if (CursedConfig.ANGRY.get()) {
                    executeAngryEffect(player);
                }
                if (CursedConfig.SLEEP.get()) {
                    executeSleepEffect(player);
                }
            }
        }
    }

    private static boolean experiencesCursedEffects(Player player) {
        return CurseManager.isCursed(player) && !player.isCreative() && !player.isSpectator();
    }

    private static void executeFireEffect(Player player) {
        if (player.getRemainingFireTicks() == 1) {
            player.setSecondsOnFire(FIRE_TIMER_RESET_TICKS);
        }
    }

    private static void executeAngryEffect(Player player) {
        List<Mob> attackCandidates = new ArrayList<>();
        List<Mob> targetMigrationCandidates = new ArrayList<>();
        for(Mob mob : player.level().getEntitiesOfClass(Mob.class, player.getBoundingBox().inflate(ANGRY_RADIUS))) {
            if (canGetUpsetByPlayer(mob, player)) {
                if (mob.getTarget() == null) {
                    attackCandidates.add(mob);
                } else {
                    if (!CurseManager.isCursed(mob.getTarget())) {
                        targetMigrationCandidates.add(mob);
                    }
                }
            }
        }

        if (!attackCandidates.isEmpty() && player.getRandom().nextDouble() < CursedConfig.ATTACK_CHANCE.get()) {
            // neutral mobs attack the player now
            int count = (int) Math.ceil(CursedConfig.ATTACK_MOB_COUNT.get() * attackCandidates.size());
            for (Mob mob : chooseRandomEntities(player.getRandom(), count, attackCandidates)) {
                mob.setTarget(player);
            }
        }
        if (!targetMigrationCandidates.isEmpty() && player.getRandom().nextDouble() < CursedConfig.TARGET_MIGRATION_CHANCE.get()) {
            // neutral mobs attack the player now
            int count = (int) Math.ceil(CursedConfig.TARGET_MIGRATION_COUNT.get() * targetMigrationCandidates.size());
            for (Mob mob : chooseRandomEntities(player.getRandom(), count, targetMigrationCandidates)) {
                mob.setTarget(player);
            }
        }
    }

    private static List<Mob> chooseRandomEntities(RandomSource random, int maxCount, List<Mob> originalList) {
        List<Mob> output = new ArrayList<>();
        List<Mob> copy = new ArrayList<>(originalList);
        while (output.size() < maxCount && !copy.isEmpty()) {
            int randomIndex = random.nextIntBetweenInclusive(0, copy.size() - 1);
            output.add(copy.get(randomIndex));
            copy.remove(randomIndex);
        }
        return output;
    }

    private static boolean canGetUpsetByPlayer(Mob mob, Player player) {
        if (mob instanceof IronGolem ironGolem) {
            if (ironGolem.isPlayerCreated()) {
                return false;
            }
        }
        if (mob instanceof TamableAnimal pet) {
            if (pet.isTame()) {
                return false;
            }
        }

        return !mob.getType().is(CursedTags.NOT_ANGRY)
                && (player.hasLineOfSight(mob) || player.distanceTo(mob) <= FORCED_ANGER_DISTANCE);
    }

    private static void executeSleepEffect(Player player) {
        if (player.isSleeping() && player.getSleepTimer() >= SLEEP_INTERRUPT_TIMER_TICKS) {
            player.stopSleeping();
            player.displayClientMessage(Component.translatable("gui.curse.sleep_message"), true);
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(Clone event) {
        if (CurseManager.isCursed(event.getOriginal())) {
            CurseManager.setCursed(event.getEntity(), true);
        }

    }

    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            if (shouldShowGui(serverPlayer)) {
                serverPlayer.openMenu(new SimpleMenuProvider(
                        (containerId, playerInventory, player) -> new CurseGuiMenu(containerId, playerInventory),
                        CurseGuiMenu.TITLE_TEXT
                ));
            }
        }
    }

    private static boolean shouldShowGui(ServerPlayer player) {
        if (CurseManager.isCursed(player)) {
            // Cursed players should not be asked again. No second chance!
            return false;
        }
        Advancement queryStoppingAdvancement = player.server.getAdvancements().getAdvancement(QUERY_ENDING_ADVANCEMENT_LOCATION);
        if (queryStoppingAdvancement == null) {
            // Should not happen: advancements might be broken -> show the GUI to be sure
            return true;
        }
        // Show GUI if the player is not considered 'new' (aka has the advancement)
        return !player.getAdvancements().getOrStartProgress(queryStoppingAdvancement).isDone();
    }
}
