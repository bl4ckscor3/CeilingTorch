package bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches;

import java.util.HashMap;
import java.util.function.Supplier;

import com.github.wolfiewaffle.hardcore_torches.block.HardcoreFloorTorchBlock;
import com.github.wolfiewaffle.hardcore_torches.block.HardcoreWallTorchBlock;
import com.github.wolfiewaffle.hardcore_torches.util.ETorchState;
import com.github.wolfiewaffle.hardcore_torches.util.TorchGroup;

import net.minecraft.world.level.block.Block;

public class CeilingTorchGroup extends TorchGroup {
	private HashMap<ETorchState, Supplier<HardcoreCeilingTorchBlock>> ceilingTorches = new HashMap<>();

	public CeilingTorchGroup() {
		super("ceiling");
	}

	public void add(ETorchState burnState, Supplier<HardcoreCeilingTorchBlock> block) {
		ceilingTorches.put(burnState, block);
	}

	@Override
	public void add(Block block) {
		if (block instanceof HardcoreCeilingTorchBlock hctb)
			add(hctb.burnState, () -> hctb);
	}

	@Override
	public void add(HardcoreFloorTorchBlock block) {
		if (block instanceof HardcoreCeilingTorchBlock hctb)
			add(hctb.burnState, () -> hctb);
	}

	@Override
	public void add(HardcoreWallTorchBlock block) {}

	@Override
	public HardcoreFloorTorchBlock getStandingTorch(ETorchState state) {
		for (ETorchState key : ceilingTorches.keySet()) {
			if (key == state)
				return ceilingTorches.get(key).get();
		}

		return null;
	}

	@Override
	public HardcoreWallTorchBlock getWallTorch(ETorchState state) {
		return null;
	}
}
