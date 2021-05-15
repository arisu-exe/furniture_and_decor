package io.github.fallOut015.furniture_and_decor.util;

import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import net.minecraft.data.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundEventsFurnitureAndDecor {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MainFurnitureAndDecor.MODID);



    public static final RegistryObject<SoundEvent> GUI_UPHOLSTERY_TABLE_SELECT_TEMPLATE = SOUND_EVENTS.register("gui_upholstery_table_select_template", () -> new SoundEvent(new ResourceLocation(MainFurnitureAndDecor.MODID, "gui.upholstery_table.select_template")));
    public static final RegistryObject<SoundEvent> GUI_UPHOLSTERY_TABLE_TAKE_RESULT = SOUND_EVENTS.register("gui_upholstery_table_take_result", () -> new SoundEvent(new ResourceLocation(MainFurnitureAndDecor.MODID, "gui.upholstery_table.take_result")));



    public static void register(IEventBus bus) {
        SOUND_EVENTS.register(bus);
    }
}