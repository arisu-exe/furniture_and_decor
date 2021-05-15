package io.github.fallOut015.furniture_and_decor.client.gui.screen.inventory;

import com.mojang.blaze3d.matrix.MatrixStack;
import io.github.fallOut015.furniture_and_decor.MainFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.inventory.container.UpholsteryTableContainer;
import io.github.fallOut015.furniture_and_decor.item.ItemsFurnitureAndDecor;
import io.github.fallOut015.furniture_and_decor.util.SoundEventsFurnitureAndDecor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SimpleSound;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.Tags;

import javax.annotation.Nullable;
import java.util.function.Predicate;

@OnlyIn(Dist.CLIENT)
public class UpholsteryTableScreen extends ContainerScreen<UpholsteryTableContainer> {
    private static final ResourceLocation UPHOLSTERY_TABLE_GUI_TEXTURE = new ResourceLocation(MainFurnitureAndDecor.MODID, "textures/gui/container/upholstery_table.png");

//	private ItemStack stacktop = ItemStack.EMPTY;
//	private ItemStack stackmiddle = ItemStack.EMPTY;
//	private ItemStack stackbottom = ItemStack.EMPTY;
//	private ItemStack stackoutput = ItemStack.EMPTY;

    private boolean showUsableIcons = true;
    //	private float field_214126_x;
    private int field_214128_z = 1;

    public static final Predicate<Item> PLANKS = item -> item.getTags().contains(ItemTags.PLANKS.getName());
    public static final Predicate<Item> WOOL = item -> item.getTags().contains(ItemTags.WOOL.getName());
    public static final Predicate<Item> GLASS = item -> item.getTags().contains(Tags.Items.GLASS.getName());
    public static final Predicate<Item> INGOTS = item -> item.getTags().contains(Tags.Items.INGOTS.getName());
    public static final Predicate<Item> STORAGE_BLOCKS = item -> item.getTags().contains(Tags.Items.STORAGE_BLOCKS.getName());

    public static final Predicate<Item> PLANKS_STORAGE_BLOCKS = item -> PLANKS.or(STORAGE_BLOCKS).test(item);
    public static final Predicate<Item> PLANKS_WOOL_STORAGE_BLOCKS = item -> PLANKS_STORAGE_BLOCKS.or(WOOL).test(item);

    public enum Furniture {
        CHAIR(ItemsFurnitureAndDecor.CHAIR.get(), PLANKS_WOOL_STORAGE_BLOCKS, PLANKS_WOOL_STORAGE_BLOCKS, PLANKS_STORAGE_BLOCKS);//,
//		EDGE_STOOL  (ItemsTwo.EDGE_STOOL , PLANKS_WOOL_STORAGE_BLOCKS, PLANKS_STORAGE_BLOCKS,      PLANKS_STORAGE_BLOCKS),
//		ROUND_STOOL (ItemsTwo.ROUND_STOOL, PLANKS_WOOL_STORAGE_BLOCKS, PLANKS_STORAGE_BLOCKS,      PLANKS_STORAGE_BLOCKS),
//		EDGE_TABLE  (ItemsTwo.EDGE_TABLE , PLANKS_STORAGE_BLOCKS     , PLANKS_STORAGE_BLOCKS,      PLANKS_STORAGE_BLOCKS),
//		ROUND_TABLE (ItemsTwo.ROUND_TABLE, PLANKS_STORAGE_BLOCKS     , PLANKS_STORAGE_BLOCKS,      PLANKS_STORAGE_BLOCKS),
//		CUPBOARD    (ItemsTwo.CUPBOARD   , PLANKS_STORAGE_BLOCKS     , INGOTS,                     PLANKS_STORAGE_BLOCKS),
//		LAMP        (ItemsTwo.LAMP       , WOOL                      , GLASS     ,                 PLANKS_STORAGE_BLOCKS),
//		BED         (ItemsTwo.BED        , WOOL                      , WOOL      ,                 PLANKS_STORAGE_BLOCKS),
//		COUCH       (ItemsTwo.COUCH      , WOOL                      , WOOL      ,                 PLANKS_STORAGE_BLOCKS),
//		SHELF       (ItemsTwo.SHELF      , PLANKS_STORAGE_BLOCKS     , PLANKS_STORAGE_BLOCKS,      PLANKS_STORAGE_BLOCKS);

        private Integer id;
        private Item item;
        private Predicate<Item> toppredicate;
        private Predicate<Item> middlepredicate;
        private Predicate<Item> bottompredicate;

        Furniture(Item item, Predicate<Item> toppredicate, Predicate<Item> middlepredicate, Predicate<Item> bottompredicate) {
            this.id = this.ordinal() + 1;
            this.item = item;
            this.toppredicate = toppredicate;
            this.middlepredicate = middlepredicate;
            this.bottompredicate = bottompredicate;
        }

        public Integer getID() {
            return this.id;
        }

        public Item getItem() {
            return this.item;
        }

        public Predicate<Item> getTopPredicate() {
            return this.toppredicate;
        }

        public Predicate<Item> getMiddlePredicate() {
            return this.middlepredicate;
        }

        public Predicate<Item> getBottomPredicate() {
            return this.bottompredicate;
        }
    }

    @Nullable
    private Furniture selectedFurniture = null;

    public UpholsteryTableScreen(UpholsteryTableContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
        screenContainer.registerUpdateListener(this::containerChanged);
    }

    @Override
    public void render(MatrixStack stack, int x, int y, float partialTicks) {
        super.render(stack, x, y, partialTicks);
//		this.func_230459_a_(stack, x, partialTicks);
    }
    @Override
    protected void renderBg(MatrixStack stack, float partialTicks, int x, int y) {
        this.renderBackground(stack);
        this.minecraft.getTextureManager().bind(UPHOLSTERY_TABLE_GUI_TEXTURE);

        int guiLeft = this.leftPos;
        int guiTop = this.topPos;
		this.blit(stack, guiLeft, guiTop, 0, 0, this.width, this.height);

        Slot top = this.menu.top();
        Slot middle = this.menu.middle();
        Slot bottom = this.menu.bottom();

        if (selectedFurniture != null && !top.hasItem())
//			this.blit(i + top.xPos, j + top.yPos, this.xSize, (selectedFurniture.ordinal()) * 16, 16, 16);
            if (selectedFurniture != null && !middle.hasItem())
//			this.blit(i + middle.xPos, j + middle.yPos, this.xSize + 16, (selectedFurniture.ordinal()) * 16, 16, 16);
                if (selectedFurniture != null && !bottom.hasItem())
//			this.blit(i + bottom.xPos, j + bottom.yPos, this.xSize + 32, (selectedFurniture.ordinal()) * 16, 16, 16);

//		int k = (int)(41.0F * this.field_214126_x);
//		this.blit(i + 119, j + 13 + k, 232 + (this.showUsableIcons ? 0 : 12), 0, 12, 15);
                    RenderHelper.setupForFlatItems();
//		IRenderTypeBuffer.Impl irendertypebuffer$impl = this.minecraft.getRenderTypeBuffers().getBufferSource();
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.translate((double) (guiLeft + 139), (double) (guiTop + 52), 0.0D);
        matrixstack.scale(24.0F, -24.0F, 1.0F);
        matrixstack.translate(0.5D, 0.5D, 0.5D);
        matrixstack.scale(0.6666667F, -0.6666667F, -0.6666667F);
//		irendertypebuffer$impl.finish();
        if (this.showUsableIcons) {
            int i2 = guiLeft + 60;
            int k2 = guiTop + 13;
            int i3 = this.field_214128_z + 10;

            for (int l = this.field_214128_z; l < i3; ++l) {
                int i1 = l - this.field_214128_z;
                int j1 = i2 + i1 % 4 * 14;
                int k1 = k2 + i1 / 4 * 14;
                this.minecraft.getTextureManager().bind(UPHOLSTERY_TABLE_GUI_TEXTURE);
//	            int yblittingstart = this.ySize;

//	            this.blit(j1, k1, 14 * (l - 1), yblittingstart + (this.selectedFurniture != null && l == this.selectedFurniture.getID() ? 14 : 0), 14, 14);
                this.renderMatrix(l, j1, k1);
            }
        }

        RenderHelper.setupFor3DItems();
    }
    //@Override
    private void renderMatrix(int p_228190_1_, int p_228190_2_, int p_228190_3_) {
        MatrixStack matrixstack = new MatrixStack();
        matrixstack.pushPose();
        matrixstack.translate((double) ((float) p_228190_2_ + 0.5F), (double) (p_228190_3_ + 16), 0.0D);
        matrixstack.scale(6.0F, -6.0F, 1.0F);
        matrixstack.translate(0.5D, 0.5D, 0.0D);
        matrixstack.translate(0.5D, 0.5D, 0.5D);
        matrixstack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        IRenderTypeBuffer.Impl irendertypebuffer$impl = this.minecraft.renderBuffers().bufferSource();
        matrixstack.popPose();
        irendertypebuffer$impl.endBatch();
    }
    @Override
    public boolean mouseClicked(double mousex, double mousey, int mousez) {
        if (this.showUsableIcons) {
            int i = this.leftPos + 60;
            int j = this.topPos + 13;
            int k = this.field_214128_z + 10;

            for (int l = this.field_214128_z; l < k; ++l) {
                int i1 = l - this.field_214128_z;
                double d0 = mousex - (double) (i + i1 % 4 * 14);
                double d1 = mousey - (double) (j + i1 / 4 * 14);
                if (d0 >= 0.0D && d1 >= 0.0D && d0 < 14.0D && d1 < 14.0D) {
                    switch (l) {
                        case 1:
                            this.selectedFurniture = Furniture.CHAIR;
                            break;
//	            		case 2:
//	            			this.selectedFurniture = Furniture.EDGE_STOOL;
//	            			break;
//	            		case 3:
//	            			this.selectedFurniture = Furniture.ROUND_STOOL;
//	            			break;
//	            		case 4:
//	            			this.selectedFurniture = Furniture.EDGE_TABLE;
//	            			break;
//	            		case 5:
//	            			this.selectedFurniture = Furniture.ROUND_TABLE;
//	            			break;
//	            		case 6:
//	            			this.selectedFurniture = Furniture.CUPBOARD;
//	            			break;
//	            		case 7:
//	            			this.selectedFurniture = Furniture.LAMP;
//	            			break;
//	            		case 8:
//	            			this.selectedFurniture = Furniture.BED;
//	            			break;
//	            		case 9:
//	            			this.selectedFurniture = Furniture.COUCH;
//	            			break;
//	            		case 10:
//	            			this.selectedFurniture = Furniture.SHELF;
//	            			break;
                        default:
                            this.selectedFurniture = null;
                    }
                    if (this.menu.clickMenuButton(this.minecraft.player, this.selectedFurniture)) {
                        this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, this.selectedFurniture == null ? 0 : this.selectedFurniture.getID());
                    }
                    Minecraft.getInstance().getSoundManager().play(SimpleSound.forUI(SoundEventsFurnitureAndDecor.GUI_UPHOLSTERY_TABLE_SELECT_TEMPLATE.get(), 1.0F));
                    return true;
                }
            }

            i = this.leftPos + 119;
            j = this.topPos + 9;
        }
        return super.mouseClicked(mousex, mousey, mousez);
    }
    @Override
    protected boolean hasClickedOutside(double d1, double d3, int i5, int i6, int i7) {
        return d1 < (double) i5 || d3 < (double) i6 || d1 >= (double) (i5 + this.width) || d3 >= (double) (i6 + this.height);
    }
    //@Override
    private void containerChanged() {
        // TODO
//		this.stacktop = this.container.top().getStack();
//		this.stackmiddle = this.container.middle().getStack();
//		this.stackbottom = this.container.bottom().getStack();
//		this.stackoutput = this.container.output().getStack();
    }
}