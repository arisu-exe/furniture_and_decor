package io.github.fallOut015.furniture_and_decor.client.gui;

import io.github.fallOut015.furniture_and_decor.client.gui.screen.inventory.UpholsteryTableScreen;
import io.github.fallOut015.furniture_and_decor.inventory.container.ContainersFurnitureAndDecor;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ScreenManagerFurnitureAndDecor {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ScreenManager.register(ContainersFurnitureAndDecor.UPHOLSTERY_TABLE.get(), UpholsteryTableScreen::new);
    }
}