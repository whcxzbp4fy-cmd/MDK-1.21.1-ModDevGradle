package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.phys.BlockHitResult;

public class LockableVoidDoorBlock extends DoorBlock {
    public LockableVoidDoorBlock(Properties properties) {
        // -1.0F makes it completely unbreakable (bedrock rules)
        super(BlockSetType.IRON, properties.strength(-1.0F, 3600000.0F));
    }

    @Override
    
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (state.getValue(OPEN)) {
            return super.useWithoutItem(state, level, pos, player, hitResult);
        }

        ItemStack heldItem = player.getMainHandItem();
        boolean hasCorrectKey = heldItem.getDescriptionId().equals("item.minecraft.tripwire_hook") 
                && heldItem.getHoverName().getString().equalsIgnoreCase("Void Key");

        if (hasCorrectKey) {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("§aThe mechanism clicks open...§r"), true);
            }
            return super.useWithoutItem(state, level, pos, player, hitResult);
        } else {
            if (!level.isClientSide) {
                player.displayClientMessage(Component.literal("§cThe door remains bound by the void. It requires a specific key...§r"), true);
                level.playSound(null, pos, SoundEvents.CHEST_LOCKED, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
            return InteractionResult.SUCCESS;
        }
    }
}
