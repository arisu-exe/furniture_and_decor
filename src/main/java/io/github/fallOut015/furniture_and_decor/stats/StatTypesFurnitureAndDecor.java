package io.github.fallOut015.furniture_and_decor.stats;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import net.minecraft.stats.StatType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class StatTypesFurnitureAndDecor {
    private static final DeferredRegister<StatType<?>> STAT_TYPES = DeferredRegister.create(ForgeRegistries.STAT_TYPES, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<StatType<ResourceLocation>> INTERACT_WITH_UPHOLSTERY_TABLE = STAT_TYPES.register("interact_with_upholstery_table", () -> new StatType<>(Registry.CUSTOM_STAT));



    public static void register(IEventBus bus) {
        STAT_TYPES.register(bus);
    }
}