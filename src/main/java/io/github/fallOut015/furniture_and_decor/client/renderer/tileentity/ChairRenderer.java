package io.github.fallOut015.furniture_and_decor.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import io.github.fallOut015.furniture_and_decor.client.renderer.tileentity.model.ChairModel;
import io.github.fallOut015.furniture_and_decor.tileentity.ChairTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;

@OnlyIn(Dist.CLIENT)
public class ChairRenderer extends TileEntityRenderer<ChairTileEntity> {
    private final ChairModel model = new ChairModel();

    public ChairRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(ChairTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.pushPose();

        String back = tileEntityIn.getTop();
        String seat = tileEntityIn.getMiddle();
        String legs = tileEntityIn.getBottom();

        this.model.back.visible = true;
        this.model.seat.visible = true;
        this.model.legs.visible = true;

        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager._enableLighting();

        RenderMaterial materialback = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("block/" + back));
        IVertexBuilder vertexbuilderback = materialback.buffer(bufferIn, RenderType::entitySolid);
        this.model.back.render(matrixStackIn, vertexbuilderback, combinedLightIn, combinedOverlayIn);

        RenderMaterial materialseat = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("block/" + seat));
        IVertexBuilder vertexbuilderseat = materialseat.buffer(bufferIn, RenderType::entitySolid);
        this.model.seat.render(matrixStackIn, vertexbuilderseat, combinedLightIn, combinedOverlayIn);

        RenderMaterial materiallegs = new RenderMaterial(AtlasTexture.LOCATION_BLOCKS, new ResourceLocation("block/" + legs));
        IVertexBuilder vertexbuilderlegs = materiallegs.buffer(bufferIn, RenderType::entitySolid);
        this.model.legs.render(matrixStackIn, vertexbuilderlegs, combinedLightIn, combinedOverlayIn);

        matrixStackIn.popPose();
    }
}