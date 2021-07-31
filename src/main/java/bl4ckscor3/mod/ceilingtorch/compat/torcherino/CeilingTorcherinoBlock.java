package bl4ckscor3.mod.ceilingtorch.compat.torcherino;

import java.util.Random;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.Level;
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
	private ParticleOptions particle = null;

	public CeilingTorcherinoBlock(ResourceLocation tierName, ResourceLocation particleID)
	{
		super(tierName, ParticleTypes.FLAME);

		this.tierName = tierName;
		this.particleID = particleID;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return CeilingTorchBlock.CEILING_SHAPE;
	}

	@Override
	public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos)
	{
		return facing == Direction.UP && !canSurvive(state, world, currentPos) ? Blocks.AIR.defaultBlockState() : state;
	}

	@Override
	public boolean canSurvive(BlockState state, LevelReader world, BlockPos pos)
	{
		return canSupportCenter(world, pos.above(), Direction.DOWN);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext ctx)
	{
		boolean powered = ctx.getLevel().hasNeighborSignal(ctx.getClickedPos().above());

		return super.getStateForPlacement(ctx).setValue(BlockStateProperties.POWERED, powered);
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean b)
	{
		if(!world.isClientSide)
		{
			boolean powered = world.hasNeighborSignal(pos.above());

			if(state.getValue(BlockStateProperties.POWERED) != powered)
			{
				world.setBlockAndUpdate(pos, state.setValue(BlockStateProperties.POWERED, powered));

				BlockEntity te = world.getBlockEntity(pos);

				if(te instanceof TorcherinoTileEntity)
					((TorcherinoTileEntity)te).setPoweredByRedstone(powered);
			}
		}
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, Level world, BlockPos pos, Random rand)
	{
		double x = pos.getX() + 0.5D;
		double y = pos.getY() + 0.7D;
		double z = pos.getZ() + 0.5D;
		double offset = -0.25D;

		if(particle == null)
		{
			ParticleType<?> data = ForgeRegistries.PARTICLE_TYPES.getValue(particleID);

			if(data instanceof ParticleOptions)
				particle = (ParticleOptions)data;
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
	public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
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
