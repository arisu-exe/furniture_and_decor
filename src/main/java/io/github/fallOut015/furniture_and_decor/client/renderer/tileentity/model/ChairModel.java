package io.github.fallOut015.furniture_and_decor.client.renderer.tileentity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChairModel extends Model {
    public ModelRenderer back;
    public ModelRenderer seat;
    public ModelRenderer legs;

    public ChairModel() {
        super(RenderType::entitySolid);

        this.texWidth = 16;
        this.texHeight = 16;

        this.back = new ModelRenderer(this, 0, 0);
        this.back.setPos(0.0F, 0.0F, 0.0F);
        this.back.addBox(-8.0F, 16.0F, 6.0F, 16, 16, 2);

        this.seat = new ModelRenderer(this, 0, 0);
        this.seat.setPos(0.0F, 0.0F, 0.0F);
        this.seat.addBox(-8.0F, 14.0F, -8.0F, 16, 2, 16);

        this.legs = new ModelRenderer(this, 0, 0);
        this.legs.setPos(0.0F, 0.0F, 0.0F);
        this.legs.addBox(-8.0F, 0.0F, 6.0F, 2, 14, 2);
        this.legs.addBox(6.0F, 0.0F, 6.0F, 2, 14, 2);
        this.legs.addBox(6.0F, 0.0F, -8.0F, 2, 14, 2);
        this.legs.addBox(-8.0F, 0.0F, -8.0F, 2, 14, 2);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        // TODO move render code here
    }
}