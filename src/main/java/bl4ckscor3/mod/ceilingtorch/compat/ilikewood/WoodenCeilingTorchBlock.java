package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenCeilingTorchBlock extends CeilingTorchBlock implements IWooden
{
	protected static final VoxelShape CEILING_SHAPE = Block.makeCuboidShape(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final IWoodType woodType;

	public WoodenCeilingTorchBlock(IWoodType woodType)
	{
		super(Block.Properties.from(Blocks.TORCH), ParticleTypes.SMOKE, () -> ILikeWood.getBlock(woodType, WoodenBlockType.TORCH));
		this.woodType = woodType;
		setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ilikewood_" + woodType.getName() + "_torch"));
	}

	@Override
	public IWoodType getWoodType()
	{
		return woodType;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.1D;
		double d2 = pos.getZ() + 0.5D;

		world.addParticle(particleData, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
