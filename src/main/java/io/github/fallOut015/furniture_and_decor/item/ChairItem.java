package io.github.fallOut015.furniture_and_decor.item;

import io.github.fallOut015.furniture_and_decor.block.BlocksFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.block.ChairBlock;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;

public class ChairItem extends BlockItem {
    public ChairItem(Properties builder) {
        super(BlocksFurnitureAndDecor.CHAIR.get(), builder);
    }
    @Override
    public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> items) {
        if(this.allowdedIn(group)) {
            items.add(new ItemStack(ItemsFurnitureAndDecor.CHAIR.get()));
        }
		/*if(this.isInGroup(group)) {
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.WHITE_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.ORANGE_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.MAGENTA_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.LIGHT_BLUE_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.YELLOW_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.LIME_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.PINK_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.GRAY_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.LIGHT_GRAY_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.CYAN_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.PURPLE_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.BLUE_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.BROWN_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.GREEN_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.RED_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
			items.add(ChairItem.from(BlocksTwo.CHAIR.getStateContainer().getBaseState().with(ChairBlock.BACK, FurnitureProperties.PlanksWool.OAK_PLANKS).with(ChairBlock.SEAT, FurnitureProperties.PlanksWool.BLACK_WOOL).with(ChairBlock.LEGS, FurnitureProperties.Planks.OAK_PLANKS)));
		}*/
    }
    /*@Override
    public ITextComponent getDisplayName(ItemStack stack) {
        return new TranslationTextComponent(this.getTranslationKey(stack));
    }*/

    public static ItemStack from(BlockState state) {
        ItemStack stack = ItemStack.EMPTY;
        if(state.getBlock() instanceof ChairBlock) {
            CompoundNBT nbt = new CompoundNBT();

//			nbt.putString("top", state.pos);
//			nbt.putString("middle", state.get(ChairBlock.SEAT).toString());
//			nbt.putString("bottom", state.get(ChairBlock.LEGS).toString());
            stack.setTag(nbt);
        }
        return stack;
    }
}