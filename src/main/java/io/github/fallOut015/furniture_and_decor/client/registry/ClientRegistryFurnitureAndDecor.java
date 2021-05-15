package io.github.fallOut015.furniture_and_decor.client.registry;

import io.github.fallOut015.furniture_and_decor.client.renderer.tileentity.ChairRenderer;
import io.github.fallOut015.furniture_and_decor.tileentity.TileEntitiesFurnitureAndDecor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientRegistryFurnitureAndDecor {
    public static void doClientStuff(final FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(TileEntitiesFurnitureAndDecor.CHAIR.get(), ChairRenderer::new);
    }
}