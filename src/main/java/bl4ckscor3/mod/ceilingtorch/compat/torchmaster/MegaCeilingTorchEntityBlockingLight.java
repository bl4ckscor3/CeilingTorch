package bl4ckscor3.mod.ceilingtorch.compat.torchmaster;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.xalcon.torchmaster.common.logic.entityblocking.megatorch.MegatorchEntityBlockingLight;

public class MegaCeilingTorchEntityBlockingLight extends MegatorchEntityBlockingLight
{
	public static final VoxelShape SHAPE = Block.makeCuboidShape(6.0D, 0.0D, 6.0D, 10.0D, 16.0D, 10.0D);

	public MegaCeilingTorchEntityBlockingLight(BlockPos pos)
	{
		super(pos);
	}

	@Override
	public boolean cleanupCheck(World world)
	{
		return world.isBlockPresent(getPos()) && world.getBlockState(getPos()).getBlock() != TorchmasterCompat.megaCeilingTorch;
	}

	@Override
	public String getName()
	{
		return "Mega Ceiling Torch";
	}
}
