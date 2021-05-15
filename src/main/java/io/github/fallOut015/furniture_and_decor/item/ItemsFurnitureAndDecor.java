package io.github.fallOut015.furniture_and_decor.item;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.block.BlocksFurnitureAndDecor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemsFurnitureAndDecor {
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<Item> UPHOLSTERY_TABLE = ITEMS.register("upholstery_table", () -> new BlockItem(BlocksFurnitureAndDecor.UPHOLSTERY_TABLE.get(), new Item.Properties().tab(ItemGroup.TAB_DECORATIONS).stacksTo(1))); // TODO own tab?
    public static final RegistryObject<Item> CHAIR = ITEMS.register("chair", () -> new ChairItem(new Item.Properties().tab(ItemGroup.TAB_DECORATIONS).stacksTo(1))); // TODO own tab?



    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}