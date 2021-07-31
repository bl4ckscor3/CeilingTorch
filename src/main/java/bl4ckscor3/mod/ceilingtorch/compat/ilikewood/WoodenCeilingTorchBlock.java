package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenCeilingTorchBlock extends CeilingTorchBlock implements IWooden
{
	protected static final VoxelShape CEILING_SHAPE = Block.box(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	private final IWoodType woodType;

	public WoodenCeilingTorchBlock(IWoodType woodType)
	{
		super(Block.Properties.copy(Blocks.TORCH), ParticleTypes.SMOKE, () -> ILikeWood.getBlock(woodType, WoodenBlockType.TORCH));
		this.woodType = woodType;
		setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ilikewood_" + woodType.getName() + "_torch"));
	}

	@Override
	public IWoodType getWoodType()
	{
		return woodType;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		double d0 = pos.getX() + 0.5D;
		double d1 = pos.getY() + 0.1D;
		double d2 = pos.getZ() + 0.5D;

		world.addParticle(flameParticle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
	}
}
