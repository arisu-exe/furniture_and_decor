package io.github.fallOut015.furniture_and_decor.inventory.container;

import io.github.fallOut015.furniture_and_decor.block.BlocksFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.client.gui.screen.inventory.UpholsteryTableScreen;
import io.github.fallOut015.furniture_and_decor.util.SoundEventsFurnitureAndDecor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class UpholsteryTableContainer extends Container {
    private final IWorldPosCallable worldPos;

    @Nullable
    private UpholsteryTableScreen.Furniture furniture = null;

    private Runnable update = () -> { };

    private final Slot slotTop;
    private final Slot slotMiddle;
    private final Slot slotBottom;
    private final Slot slotOutput;

    private long time;

    private final IInventory inputs = new Inventory(3) {
        public void setChanged() {
            super.setChanged();
            UpholsteryTableContainer.this.slotsChanged(this);
            UpholsteryTableContainer.this.update.run();
        }
    };
    private final IInventory outputs = new Inventory(1) {
        public void setChanged() {
            super.setChanged();
            UpholsteryTableContainer.this.update.run();
        }
    };

    public UpholsteryTableContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, IWorldPosCallable.NULL);
    }
    public UpholsteryTableContainer(int id, PlayerInventory playerInventory, final IWorldPosCallable worldPosCallable) {
        super(ContainersFurnitureAndDecor.UPHOLSTERY_TABLE.get(), id);

        this.worldPos = worldPosCallable;

        this.slotTop = this.addSlot(new Slot(this.inputs, 0, 23, 15) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if(furniture != null) {
                    return furniture.getTopPredicate().test(stack.getItem());
                }
                return false;
            }
        });
        this.slotMiddle = this.addSlot(new Slot(this.inputs, 1, 23, 33) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if(furniture != null)
                    return furniture.getMiddlePredicate().test(stack.getItem());
                return false;
            }
        });
        this.slotBottom = this.addSlot(new Slot(this.inputs, 2, 23, 51) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                if(furniture != null)
                    return furniture.getBottomPredicate().test(stack.getItem());
                return false;
            }
        });

        this.slotOutput = this.addSlot(new Slot(this.outputs, 0, 138, 32) {
            @Override
            public boolean mayPlace(ItemStack stack) {
                return false;
            }
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                UpholsteryTableContainer.this.slotTop.remove(1);
                UpholsteryTableContainer.this.slotMiddle.remove(1);
                UpholsteryTableContainer.this.slotBottom.remove(1);

                worldPosCallable.execute((worldIn, pos) -> {
                    long l = worldIn.getGameTime();
                    if (UpholsteryTableContainer.this.time != l) {
                        worldIn.playSound((PlayerEntity) null, pos, SoundEventsFurnitureAndDecor.GUI_UPHOLSTERY_TABLE_TAKE_RESULT.get(), SoundCategory.BLOCKS, 1.0F, 1.0F);
                        UpholsteryTableContainer.this.time = l;
                    }
                });
                return super.onTake(thePlayer, stack);
            }
        });

        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventory, k, 8 + k * 18, 142));
        }
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return stillValid(this.worldPos, playerIn, BlocksFurnitureAndDecor.UPHOLSTERY_TABLE.get());
    }
    //@Override
    public boolean clickMenuButton(PlayerEntity playerIn, @Nullable UpholsteryTableScreen.Furniture furniture) {
        if(furniture != null) {
            this.furniture = furniture;
////			this.craft();
            return true;
        }
        return false;
    }
    @Override
    public void slotsChanged(IInventory inventoryIn) {
//		ItemStack top = this.slotTop.getStack();
//		ItemStack middle = this.slotMiddle.getStack();
//		ItemStack bottom = this.slotBottom.getStack();
//		ItemStack output = this.slotOutput.getStack();

		/*if (itemstack3.isEmpty() || !itemstack.isEmpty() && !itemstack1.isEmpty() && this.intReferenceHolder.get() > 0 && !itemstack2.isEmpty()) {
			if (!itemstack2.isEmpty()) {
				CompoundNBT compoundnbt = itemstack.getOrCreateChildTag("BlockEntityTag");
	            boolean flag = compoundnbt.contains("Patterns", 9) && !itemstack.isEmpty() && compoundnbt.getList("Patterns", 10).size() >= 6;
	            if (flag) {
	            	this.intReferenceHolder.set(0);
	            } else {
	            	//this.intReferenceHolder.set(((BannerPatternItem)itemstack2.getItem()).func_219980_b().ordinal());
	            }
			}
		} else {
			this.slotOutput.putStack(ItemStack.EMPTY);
			this.intReferenceHolder.set(0);
		}*/

        this.setupResultSlot();
        this.broadcastChanges();
    }
    //@Override
    @OnlyIn(Dist.CLIENT)
    public void registerUpdateListener(Runnable update) {
        this.update = update;
    }
    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == this.slotOutput.index) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != this.slotTop.index && index != this.slotMiddle.index && index != this.slotBottom.index) {
	            /*if (PLANKS.test((BlockItem) itemstack1.getItem())) {
	            	if (!this.mergeItemStack(itemstack1, this.slotLegs.slotNumber, this.slotLegs.slotNumber + 1, false)) {
	            		return ItemStack.EMPTY;
	            	}
	            } else if (PLANKS_WOOL.test((BlockItem) itemstack1.getItem())) {
	            	if (!this.mergeItemStack(itemstack1, this.slotSeat.slotNumber, this.slotSeat.slotNumber + 1, false)) {
	            		return ItemStack.EMPTY;
	            	} else if(!this.mergeItemStack(itemstack1, this.slotBack.slotNumber, this.slotBack.slotNumber + 1, false)) {
	            		return ItemStack.EMPTY;
	            	}
	            } else if (index >= 4 && index < 31) {
	            	if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
	            		return ItemStack.EMPTY;
	            	}
	            } else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
	            	return ItemStack.EMPTY;
	            }*/
            } else if (!this.moveItemStackTo(itemstack1, 4, 40, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
    @Override
    public void removed(PlayerEntity playerIn) {
        super.removed(playerIn);
        this.worldPos.execute((worldIn, pos) -> {
            this.clearContainer(playerIn, playerIn.level, this.inputs);
        });
    }
    //@Override
    private void setupResultSlot() {
        if(!this.slotTop.mayPlace(this.slotTop.getItem())) {
            // placeItemBackInInventory
        }

        ItemStack stack = ItemStack.EMPTY;
        if(this.furniture != null && this.slotTop.hasItem() && this.slotMiddle.hasItem() && this.slotBottom.hasItem()) {
            stack = new ItemStack(this.furniture.getItem());
            CompoundNBT nbt = new CompoundNBT();
            nbt.putString("top", this.slotTop.getItem().getItem().toString());
            nbt.putString("middle", this.slotMiddle.getItem().getItem().toString());
            nbt.putString("bottom", this.slotBottom.getItem().getItem().toString());
            stack.setTag(nbt);
        }

        this.slotOutput.set(stack);

        // Add code for ejecting values that don't apply to the predicates when a new furniture is selected.
    }

    @OnlyIn(Dist.CLIENT)
    public Slot top() {
        return this.slotTop;
    }
    @OnlyIn(Dist.CLIENT)
    public Slot middle() {
        return this.slotMiddle;
    }
    @OnlyIn(Dist.CLIENT)
    public Slot bottom() {
        return this.slotBottom;
    }
    @OnlyIn(Dist.CLIENT)
    public Slot output() {
        return this.slotOutput;
    }
}