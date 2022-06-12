package bl4ckscor3.mod.ceilingtorch.compat.magicaltorches;

import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import de.geheimagentnr1.magical_torches.elements.blocks.ModBlocks;
import de.geheimagentnr1.magical_torches.elements.capabilities.ModCapabilities;
import de.geheimagentnr1.magical_torches.elements.capabilities.sound_muffling.sound_mufflers.SoundMufflingTorchSoundMuffler;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SoundMufflingCeilingTorchBlock extends CeilingTorchBlock
{
	private static final VoxelShape SHAPE = Block.box(6.5D, 6.0D, 6.5D, 9.5D, 16.0D, 9.5D);

	public SoundMufflingCeilingTorchBlock(Properties properties)
	{
		super(properties, null, () -> ModBlocks.SOUND_MUFFLING_TORCH);
	}

	@Override
	public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource rand) {}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context)
	{
		return SHAPE;
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
	{
		return Shapes.empty();
	}

	@Override
	public PushReaction getPistonPushReaction(BlockState state)
	{
		return PushReaction.DESTROY;
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.SOUND_MUFFLING).ifPresent(capability -> capability.addSoundMuffler(world.dimension(), new SoundMufflingTorchSoundMuffler(pos)));
	}

	@Override
	public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving)
	{
		world.getCapability(ModCapabilities.SOUND_MUFFLING).ifPresent(capability -> capability.removeSoundMuffler(world.dimension(), new SoundMufflingTorchSoundMuffler(pos)));
		super.onRemove(state, world, pos, newState, isMoving);
	}
}
