package io.github.fallOut015.furniture_and_decor.tileentity;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.block.BlocksFurnitureAndDecor;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.TypeReferences;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntitiesFurnitureAndDecor {
    private static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<TileEntityType<ChairTileEntity>> CHAIR = TILE_ENTITIES.register("chair", () -> TileEntityType.Builder.of(ChairTileEntity::new, BlocksFurnitureAndDecor.CHAIR.get()).build(Util.fetchChoiceType(TypeReferences.BLOCK_ENTITY, "chair")));



    public static void register(IEventBus bus) {
        TILE_ENTITIES.register(bus);
    }
}
