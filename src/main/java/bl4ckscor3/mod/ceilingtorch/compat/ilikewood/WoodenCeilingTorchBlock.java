package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
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
import yamahari.ilikewood.registry.WoodenBlocks;
import yamahari.ilikewood.util.IWooden;
import yamahari.ilikewood.util.WoodType;
import yamahari.ilikewood.util.WoodenObjectType;

public class WoodenCeilingTorchBlock extends CeilingTorchBlock implements IWooden
{
	protected static final VoxelShape CEILING_SHAPE = Block.makeCuboidShape(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final WoodType woodType;

	public WoodenCeilingTorchBlock(WoodType woodType)
	{
		super(Block.Properties.from(Blocks.TORCH));
		this.woodType = woodType;
		setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ilikewood_" + woodType.toString() + "_torch"));
	}

	@Override
	public WoodType getWoodType()
	{
		return woodType;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !isValidPosition(state, world, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(state, facing, facingState, world, currentPos, facingPos);
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
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.1D;
		double d2 = pos.getZ() + 0.5D;

		world.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return WoodenBlocks.getBlock(WoodenObjectType.TORCH, getWoodType()).getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(WoodenBlocks.getBlock(WoodenObjectType.TORCH, getWoodType()));
	}
}
