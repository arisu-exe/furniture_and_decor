package io.github.fallOut015.furniture_and_decor.inventory.container;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainersFurnitureAndDecor {
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<ContainerType<UpholsteryTableContainer>> UPHOLSTERY_TABLE = CONTAINERS.register("upholstery_table", () -> new ContainerType<>(UpholsteryTableContainer::new));



    public static void register(IEventBus bus) {
        CONTAINERS.register(bus);
    }
}