package io.github.fallOut015.furniture_and_decor.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class ChairTileEntity extends TileEntity implements IFurnitureSlots {
    private String back = "oak_planks";
    private String seat = "white_wool";
    private String legs = "oak_planks";

    public ChairTileEntity() {
        super(TileEntitiesFurnitureAndDecor.CHAIR.get());
    }

    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);

        compound.putString("top", this.back.toString());
        compound.putString("middle", this.seat.toString());
        compound.putString("bottom", this.legs.toString());

        return compound;
    }
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);

        this.back = compound.getString("top");
        this.seat = compound.getString("middle");
        this.legs = compound.getString("bottom");
    }

    @Nullable
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(this.worldPosition, 9, this.getUpdateTag());
    }
    public CompoundNBT getUpdateTag() {
        return this.save(new CompoundNBT());
    }

    @Override
    public String getTop() {
        return this.back;
    }
    @Override
    public String getMiddle() {
        return this.seat;
    }
    @Override
    public String getBottom() {
        return this.legs;
    }
}