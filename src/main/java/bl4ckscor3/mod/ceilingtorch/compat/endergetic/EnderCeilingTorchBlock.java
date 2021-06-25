package bl4ckscor3.mod.ceilingtorch.compat.endergetic;

import java.util.Random;

import com.minecraftabnormals.endergetic.client.particles.EEParticles;
import com.minecraftabnormals.endergetic.core.registry.EEBlocks;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class EnderCeilingTorchBlock extends CeilingTorchBlock
{
	public EnderCeilingTorchBlock(Properties properties)
	{
		super(properties, null);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(EEBlocks.ENDER_TORCH.get());
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.4D;
		double z = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(EEParticles.ENDER_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
	}
}
