package bl4ckscor3.mod.ceilingtorch.compat.invisibletorches;

import java.util.function.Supplier;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class InvisibleCeilingTorchBlock extends CeilingTorchBlock implements EntityBlock {
	public InvisibleCeilingTorchBlock(Properties properties, ParticleOptions particleData, Supplier<? extends Block> originalBlock) {
		super(properties, particleData, originalBlock);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return context.isHoldingItem(getOriginalBlock().asItem()) ? CEILING_SHAPE : Shapes.empty();
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.INVISIBLE;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		Player player = Minecraft.getInstance().player;
		if (player != null && player.distanceToSqr(pos.getX(), pos.getY(), pos.getZ()) < 1024.0) {
			ItemStack itemStack = player.getItemInHand(InteractionHand.MAIN_HAND);
			if (itemStack.is(getOriginalBlock().asItem())) {
				super.animateTick(state, level, pos, rand);
			}
		}
	}
}
