package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import wallywhip.InvisibleTorches.block.SoulTorchRenderer;
import wallywhip.InvisibleTorches.block.SoulTorchTile;

public class InvisibleCeilingSoulTorchRenderer extends SoulTorchRenderer {
	public InvisibleCeilingSoulTorchRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	public void render(SoulTorchTile tile, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
		matrixStack.pushPose();
		matrixStack.translate(0.5, 0.5, 0.5);
		matrixStack.rotateAround(Axis.XP.rotationDegrees(180), 0, 1, 0);
		matrixStack.translate(-0.5, 1.5, -0.5);
		super.render(tile, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
		matrixStack.popPose();
	}
}
