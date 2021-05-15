package io.github.fallOut015.furniture_and_decor.block;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlocksFurnitureAndDecor {
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<Block> UPHOLSTERY_TABLE = BLOCKS.register("upholstery_table", () -> new UpholsteryTableBlock(AbstractBlock.Properties.of(Material.WOOD).strength(2.5f).sound(SoundType.WOOD))); // TODO make dependant on material
    public static final RegistryObject<Block> CHAIR = BLOCKS.register("chair", () -> new ChairBlock(AbstractBlock.Properties.of(Material.DECORATION).strength(2.5f).sound(SoundType.WOOD))); // TODO make dependant on material



    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
