package bl4ckscor3.mod.ceilingtorch.compat.druidcraft;

import java.util.Random;

import com.vulp.druidcraft.blocks.FieryTorchBlock;
import com.vulp.druidcraft.registry.ItemRegistry;
import com.vulp.druidcraft.registry.ParticleRegistry;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FieryCeilingTorchBlock extends FieryTorchBlock
{
	public FieryCeilingTorchBlock(Block.Properties properties)
	{
		super(properties);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		if(state.get(WATERLOGGED))
			world.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(world));

		return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : state;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return hasEnoughSolidSide(world, pos.up(), Direction.DOWN);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		float limit = 0.05F;
		float offset0 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));
		float offset2 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));
		float offset3 = Math.min(limit, Math.max(-limit, rand.nextFloat() - 0.5F));

		world.addParticle(ParticleRegistry.fiery_spark, false, x + offset0, y + offset2 - 0.3D, z + offset3, 0.0F, 0.0F, 0.0F);
		world.addParticle(ParticleRegistry.fiery_glow, false, x, y - 0.3D, z, 0.0F, 0.0F, 0.0F);
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(ItemRegistry.fiery_torch);
	}
}
