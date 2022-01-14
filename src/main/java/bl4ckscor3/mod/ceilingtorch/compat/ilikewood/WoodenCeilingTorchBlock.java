package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yamahari.ilikewood.ILikeWood;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.IWooden;

public class WoodenCeilingTorchBlock extends CeilingTorchBlock implements IWooden
{
	protected static final VoxelShape CEILING_SHAPE = Block.box(6.0D, 3.0D, 6.0D, 10.0D, 16.0D, 10.0D);
	protected final IWoodType woodType;
	protected final WoodenBlockType blockType;

	public WoodenCeilingTorchBlock(IWoodType woodType, WoodenBlockType blockType)
	{
		super(Block.Properties.copy(getVanillaTorchForWoodenBlockType(blockType)), ParticleTypes.SMOKE, () -> ILikeWood.getBlock(woodType, blockType));
		this.woodType = woodType;
		this.blockType = blockType;
		setRegistryName(new ResourceLocation(CeilingTorch.MODID, "ilikewood_" + woodType.getName() + "_" + blockType.getName()));
	}

	@Override
	public IWoodType getWoodType()
	{
		return woodType;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context)
	{
		return CEILING_SHAPE;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level level, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.1D;
		double z = pos.getZ() + 0.5D;

		level.addParticle(flameParticle, x, y, z, 0.0D, 0.0D, 0.0D);
	}

	private static Block getVanillaTorchForWoodenBlockType(WoodenBlockType type) {
		if(type == WoodenBlockType.TORCH)
			return Blocks.TORCH;
		else if(type == WoodenBlockType.SOUL_TORCH)
			return Blocks.SOUL_TORCH;
		else
			return Blocks.AIR;
	}
}
