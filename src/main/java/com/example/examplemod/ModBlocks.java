package com.example.examplemod;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, "voiddoor");
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, "voiddoor");

    public static final DeferredHolder<Block, LockableVoidDoorBlock> LOCKABLE_VOID_DOOR = BLOCKS.register("lockable_void_door",
            () -> new LockableVoidDoorBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_DOOR)));

    public static final DeferredHolder<Item, BlockItem> LOCKABLE_VOID_DOOR_ITEM = ITEMS.register("lockable_void_door",
            () -> new BlockItem(LOCKABLE_VOID_DOOR.get(), new Item.Properties()));

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
    }
}