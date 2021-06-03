package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Random;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.block.IDreadBlock;
import com.github.alexthe666.iceandfire.block.IafBlockRegistry;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.RedstoneParticleData;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class DreadCeilingTorchBlock extends CeilingTorchBlock implements IDreadBlock
{
	public DreadCeilingTorchBlock(Properties properties)
	{
		super(properties, RedstoneParticleData.REDSTONE_DUST);
	}

	@Override
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		IceAndFire.PROXY.spawnParticle("dread_torch", pos.getX() + 0.5D, pos.getY() + 0.4D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return IafBlockRegistry.DREAD_TORCH.getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(IafBlockRegistry.DREAD_TORCH);
	}
}
