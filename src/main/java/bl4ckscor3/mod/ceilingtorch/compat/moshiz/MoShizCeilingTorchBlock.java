package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import java.util.Random;
import java.util.function.Supplier;

import com.ProfitOrange.MoShiz.blocks.torch.MoShizTorch;
import com.ProfitOrange.MoShiz.config.ServerConfig;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MoShizCeilingTorchBlock extends CeilingTorchBlock
{
	public MoShizCeilingTorchBlock(Properties properties, Supplier<Block> originalBlock)
	{
		super(properties, null, originalBlock);
	}

	@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTraceResult)
	{
		ItemStack heldItem = player.getItemInHand(hand);

		if(heldItem.getItem() instanceof DyeItem dye)
		{
			Block torchForDye = MoShizCompat.coloredCeilingTorches.get(dye);

			if(state.getBlock() != torchForDye)
			{
				MoShizTorch.dyeBlock(player, heldItem, level, pos, torchForDye);
				return InteractionResult.SUCCESS;
			}
		}

		return super.use(state, level, pos, player, hand, rayTraceResult);
	}

	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;

		if (ServerConfig.dyedSmoke.get())
			level.addParticle(CeilingParticleTypes.DYED_CEILING_SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		else
			level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);

		level.addParticle(CeilingParticleTypes.DYED_CEILING_FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
