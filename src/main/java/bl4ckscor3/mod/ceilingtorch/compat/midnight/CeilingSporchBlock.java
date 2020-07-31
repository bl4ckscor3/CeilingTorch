package bl4ckscor3.mod.ceilingtorch.compat.midnight;

import java.util.Random;

import com.mushroom.midnight.client.particle.MidnightParticles;
import com.mushroom.midnight.common.block.SporchBlock.SporchType;
import com.mushroom.midnight.common.registry.MidnightBlocks;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class CeilingSporchBlock extends CeilingTorchBlock
{
	private final SporchType type;

	public CeilingSporchBlock(SporchType type, Block.Properties properties)
	{
		super(properties);

		this.type = type;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.3D;
		double z = pos.getZ() + 0.5D;

		MidnightParticles.SPORCH.spawn(world, x, y, z, 0.0D, 0.004D, 0.0D, type.ordinal());
	}

	@Override
	public ResourceLocation getLootTable()
	{
		switch(type)
		{
			case BOGSHROOM: return MidnightBlocks.BOGSHROOM_SPORCH.getLootTable();
			case DEWSHROOM: return MidnightBlocks.DEWSHROOM_SPORCH.getLootTable();
			case NIGHTSHROOM: return MidnightBlocks.NIGHTSHROOM_SPORCH.getLootTable();
			default: case VIRIDSHROOM: return MidnightBlocks.VIRIDSHROOM_SPORCH.getLootTable();
		}
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		switch(type)
		{
			case BOGSHROOM: return new ItemStack(MidnightBlocks.BOGSHROOM_SPORCH);
			case DEWSHROOM: return new ItemStack(MidnightBlocks.DEWSHROOM_SPORCH);
			case NIGHTSHROOM: return new ItemStack(MidnightBlocks.NIGHTSHROOM_SPORCH);
			default: case VIRIDSHROOM: return new ItemStack(MidnightBlocks.VIRIDSHROOM_SPORCH);
		}
	}
}
