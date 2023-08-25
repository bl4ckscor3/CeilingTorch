package bl4ckscor3.mod.ceilingtorch.compat.torchmaster;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.xalcon.torchmaster.common.logic.entityblocking.megatorch.MegatorchEntityBlockingLight;

public class MegaCeilingTorchEntityBlockingLight extends MegatorchEntityBlockingLight {
	public MegaCeilingTorchEntityBlockingLight(BlockPos pos) {
		super(pos);
	}

	@Override
	public boolean cleanupCheck(Level level) {
		return level.hasChunkAt(getPos()) && level.getBlockState(getPos()).getBlock() != TorchmasterCompat.MEGA_CEILING_TORCH.get();
	}

	@Override
	public String getName() {
		return "Mega Ceiling Torch";
	}
}
