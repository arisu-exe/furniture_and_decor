package io.github.fallOut015.furniture_and_decor;

import io.github.fallOut015.furniture_and_decor.block.BlocksFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.client.gui.ScreenManagerFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.client.registry.ClientRegistryFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.inventory.container.ContainersFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.item.ItemsFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.stats.StatTypesFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.tileentity.TileEntitiesFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.util.SoundEventsFurnitureAndDecor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MainFurnitureAndDecor.MODID)
public class MainFurnitureAndDecor {
    public static final String MODID = "furniture_and_decor";

    public MainFurnitureAndDecor() {
        BlocksFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());
        ItemsFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());
        ContainersFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());
        SoundEventsFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());
        StatTypesFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());
        TileEntitiesFurnitureAndDecor.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::processIMC);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {
    }
    private void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistryFurnitureAndDecor.doClientStuff(event);
        ScreenManagerFurnitureAndDecor.doClientStuff(event);
    }
    private void enqueueIMC(final InterModEnqueueEvent event) {
    }
    private void processIMC(final InterModProcessEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
    }
}