package camscythe;

import camscythe.item.EmberglaiveItem;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.DustParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

public class Camscythe implements ModInitializer {

    @Override
    public void onInitialize() {
        ModItems.initialize();
        ModItemGroups.initialize();

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (!(world instanceof ServerWorld serverWorld)) return ActionResult.PASS;
            if (!(entity instanceof LivingEntity livingTarget)) return ActionResult.PASS;

            var heldItem = player.getStackInHand(hand).getItem();
            double x = entity.getX();
            double y = entity.getY() + entity.getHeight() * 0.5;
            double z = entity.getZ();

            if (heldItem instanceof EmberglaiveItem) {
                livingTarget.setOnFireFor(8.0f);
                serverWorld.playSound(null, x, y, z,
                        SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.PLAYERS, 1.0f, 1.0f);
                spawnSlash(serverWorld, x, y, z, 0xFFFF4400); // orange-red
            } else if (heldItem == ModItems.PLAYTHING) {
                spawnSlash(serverWorld, x, y, z, 0xFFFFD700); // gold
            } else if (heldItem == ModItems.VINECOG) {
                spawnSlash(serverWorld, x, y, z, 0xFF33CC00); // green
            }

            return ActionResult.PASS;
        });
    }

    private static void spawnSlash(ServerWorld world, double x, double y, double z, int color) {
        // Sweep arc for the slash shape
        world.spawnParticles(ParticleTypes.SWEEP_ATTACK, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
        // Colored dust cloud for the scythe's color
        world.spawnParticles(new DustParticleEffect(color, 1.8f), x, y, z, 12, 0.35, 0.35, 0.35, 0.0);
    }
}
