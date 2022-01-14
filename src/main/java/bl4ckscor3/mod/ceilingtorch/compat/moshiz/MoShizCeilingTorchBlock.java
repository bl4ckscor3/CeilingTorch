package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import java.util.Random;
import java.util.function.Supplier;

import com.ProfitOrange.MoShiz.blocks.torch.MoShizTorch;
import com.ProfitOrange.MoShiz.config.ServerConfig;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class MoShizCeilingTorchBlock extends CeilingTorchBlock
{
	public MoShizCeilingTorchBlock(Properties properties, Supplier<Block> originalBlock)
	{
		super(properties, null, originalBlock);
	}

	@Override
	public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult rayTraceResult)
	{
		ItemStack heldItem = player.getItemInHand(hand);

		if(heldItem.getItem() instanceof DyeItem)
		{
			Block torchForDye = MoShizCompat.coloredCeilingTorches.get(heldItem.getItem());

			if(state.getBlock() != torchForDye)
			{
				MoShizTorch.dyeBlock(player, heldItem, world, pos, torchForDye);
				return ActionResultType.SUCCESS;
			}
		}

		return super.use(state, world, pos, player, hand, rayTraceResult);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.45D;
		double z = pos.getZ() + 0.5D;

		if (ServerConfig.dyedSmoke.get())
			world.addParticle(CeilingParticleTypes.DYED_CEILING_SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		else
			world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);

		world.addParticle(CeilingParticleTypes.DYED_CEILING_FLAME, x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
