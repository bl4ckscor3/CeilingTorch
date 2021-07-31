package bl4ckscor3.mod.ceilingtorch.compat.torcherino;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
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
import net.minecraftforge.registries.ForgeRegistries;
import torcherino.block.TorcherinoBlock;
import torcherino.block.tile.TorcherinoTileEntity;

public class CeilingTorcherinoBlock extends TorcherinoBlock
{
	private final ResourceLocation tierName;
	private final ResourceLocation particleID;
	private Block vanillaTorcherino = null;
	private IParticleData particle = null;

	public CeilingTorcherinoBlock(ResourceLocation tierName, ResourceLocation particleID)
	{
		super(tierName, ParticleTypes.FLAME);

		this.tierName = tierName;
		this.particleID = particleID;
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, IWorld world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public boolean canSurvive(BlockState state, IWorldReader world, BlockPos pos)
	{
		return canSupportCenter(world, pos.above(), Direction.DOWN);
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		boolean powered = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos().above());

		return super.getStateForPlacement(ctx).setValue(BlockStateProperties.POWERED, powered);
	}

	@Override
	public void neighborChanged(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean b)
	{
		if(!world.isClientSide)
		{
			boolean powered = world.hasNeighborSignal(pos.above());

			if(state.getValue(BlockStateProperties.POWERED) != powered)
			{
				world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.POWERED, powered));

				TileEntity te = world.getBlockEntity(pos);

				if(te instanceof TorcherinoTileEntity)
					((TorcherinoTileEntity)te).setPoweredByRedstone(powered);
			}
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offset = -0.25D;

		if(particle == null)
		{
			ParticleType<?> data = ForgeRegistries.PARTICLE_TYPES.getValue(particleID);

			if(data instanceof IParticleData)
				particle = (IParticleData)data;
		}

		world.addParticle(ParticleTypes.SMOKE, x, y + offset, z, 0.0D, 0.0D, 0.0D);
		world.addParticle(particle, x, y + offset, z, 0.0D, 0.0D, 0.0D);
	}

	@Override
	public ResourceLocation getLootTable()
	{
		return getVanillaTorcherino().getLootTable();
	}

	@Override
	public ItemStack getPickBlock(BlockState state, RayTraceResult target, IBlockReader world, BlockPos pos, PlayerEntity player)
	{
		return new ItemStack(getVanillaTorcherino());
	}

	@Override
	public String getDescriptionId()
	{
		return getVanillaTorcherino().getDescriptionId();
	}

	/**
	 * @return The torcherino block that is associated with this ceiling torch, Blocks.AIR if it could not be found
	 */
	public Block getVanillaTorcherino()
	{
		if(vanillaTorcherino == null) //if the vanilla torcherino has not been found, try to find it
			vanillaTorcherino = ForgeRegistries.BLOCKS.getValue(TorcherinoCompat.loc("torcherino", tierName, "torcherino"));
		else return vanillaTorcherino; //if it has already been found, return it

		//ideally, the code should only ever reach this part once, so the else statement above is to avoid unnecessarily computing the null check twice every time the method is called even after the vanilla torcherino has been found
		if(vanillaTorcherino == null) //if it could not be found, return Blocks.AIR
			return Blocks.AIR;
		else return vanillaTorcherino; //if it could be found
	}
}
