package io.github.fallOut015.furniture_and_decor.block;

import io.github.fallOut015.furniture_and_decor.item.ChairItem;
import io.github.fallOut015.furniture_and_decor.tileentity.ChairTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LadderBlock;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ChairBlock extends Block {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape LEG_A_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 2.0D, 14.0D, 2.0D);
    private static final VoxelShape LEG_B_SHAPE = Block.box(14.0D, 0.0D, 0.0D, 16.0D, 14.0D, 2.0D);
    private static final VoxelShape LEG_C_SHAPE = Block.box(14.0D, 0.0D, 14.0D, 16.0D, 14.0D, 16.0D);
    private static final VoxelShape LEG_D_SHAPE = Block.box(0.0D, 0.0D, 14.0D, 2.0D, 14.0D, 16.0D);
    private static final VoxelShape LEGS_SHAPE = VoxelShapes.or(LEG_A_SHAPE, LEG_B_SHAPE, LEG_C_SHAPE, LEG_D_SHAPE);
    private static final VoxelShape SEAT_SHAPE = Block.box(0, 14, 0, 16, 16, 16);
    private static final VoxelShape BACK_NORTH_SHAPE = Block.box(0, 16.0D, 14.0D, 16.0D, 32.0D, 16.0D);
    private static final VoxelShape BACK_EAST_SHAPE = Block.box(0.0D, 16.0D, 0.0D, 2.0D, 32.0D, 16.0D);
    private static final VoxelShape BACK_SOUTH_SHAPE = Block.box(0, 16.0D, 0.0D, 16.0D, 32.0D, 2.0D);
    private static final VoxelShape BACK_WEST_SHAPE = Block.box(14.0D, 16.0D, 0.0D, 16.0D, 32.0D, 16.0D);
    private static final VoxelShape NORTH_AABB = VoxelShapes.or(LEGS_SHAPE, SEAT_SHAPE, BACK_NORTH_SHAPE);
    private static final VoxelShape EAST_AABB = VoxelShapes.or(LEGS_SHAPE, SEAT_SHAPE, BACK_EAST_SHAPE);
    private static final VoxelShape SOUTH_AABB = VoxelShapes.or(LEGS_SHAPE, SEAT_SHAPE, BACK_SOUTH_SHAPE);
    private static final VoxelShape WEST_AABB = VoxelShapes.or(LEGS_SHAPE, SEAT_SHAPE, BACK_WEST_SHAPE);

    public ChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch((Direction)state.getValue(FACING)) {
            case NORTH:
                return NORTH_AABB;
            case SOUTH:
                return SOUTH_AABB;
            case WEST:
                return WEST_AABB;
            case EAST:
            default:
                return EAST_AABB;
        }
    }
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        if (!context.replacingClickedOnBlock()) {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().relative(context.getClickedFace().getOpposite()));
            if (blockstate.getBlock() == this && blockstate.getValue(FACING) == context.getClickedFace())
                return null;
        }

        BlockState blockstate1 = ChairBlock.from(context.getItemInHand());

        for(Direction direction : context.getNearestLookingDirections())
            if (direction.getAxis().isHorizontal())
                if((blockstate1 = blockstate1.setValue(FACING, direction.getOpposite())).canSurvive(context.getLevel(), context.getClickedPos()))
                    return blockstate1;

        if(context.getLevel().getBlockEntity(context.getClickedPos()) instanceof ChairTileEntity) {
//	    	context.getWorld().getTileEntity(context.getPos()).read(context.getItem().getTag());
            // because i removed it from ChairTileEntity TODO
        }

        return null;
    }
    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }
    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack itemstack = ChairItem.from(state);

        ItemEntity itementity = new ItemEntity(worldIn, (double)pos.getX(), (double)pos.getY(), (double)pos.getZ(), itemstack);
        itementity.setDefaultPickUpDelay();
        worldIn.addFreshEntity(itementity);

        super.playerWillDestroy(worldIn, pos, state, player);
    }
    @Override
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        // TODO chair sitting code
//		player.startRiding(worldIn.getTileEntity(pos));

        return super.use(state, worldIn, pos, player, handIn, hit);
    }
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ChairTileEntity();
    }
    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    public static BlockState from(ItemStack stack) {
//		String back = stack.getTag().getString("top");
//		String seat = stack.getTag().getString("middle");
//		String legs = stack.getTag().getString("bottom");
//		BlockState chair = BlocksTwo.CHAIR.getStateContainer().getBaseState();
        return null;
    }
}