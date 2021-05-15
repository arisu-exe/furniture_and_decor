package io.github.fallOut015.furniture_and_decor.block;

import io.github.fallOut015.furniture_and_decor.inventory.container.UpholsteryTableContainer;
import io.github.fallOut015.furniture_and_decor.stats.StatTypesFurnitureAndDecor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class UpholsteryTableBlock extends Block {
    private static final TranslationTextComponent CONTAINER_UPHOLSTERY_TABLE = new TranslationTextComponent("container.upholstery_table");

    protected UpholsteryTableBlock(Block.Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            player.openMenu(state.getMenuProvider(worldIn, pos));
            //player.awardStat(StatTypesFurnitureAndDecor.INTERACT_WITH_UPHOLSTERY_TABLE.get().getRegistryName()); // TODO?
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }
    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, playerInventory, playerEntity) ->
            new UpholsteryTableContainer(id, playerInventory, IWorldPosCallable.create(worldIn, pos))
        , CONTAINER_UPHOLSTERY_TABLE);
    }
}