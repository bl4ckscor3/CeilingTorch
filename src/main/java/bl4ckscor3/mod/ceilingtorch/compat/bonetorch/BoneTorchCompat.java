package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import java.util.Map;

import com.builtbroken.bonetorch.BoneTorchMod;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class BoneTorchCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<CeilingTorchBlock> CEILING_BONE_TORCH = CeilingTorch.BLOCKS.registerBlock("bonetorch_bonetorch", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, BoneTorchMod.BONETORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_SOUL_BONE_TORCH = CeilingTorch.BLOCKS.registerBlock("bonetorch_soul_bonetorch", p -> new CeilingTorchBlock(p, ParticleTypes.SOUL_FIRE_FLAME, BoneTorchMod.SOUL_BONETORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_COPPER_BONE_TORCH = CeilingTorch.BLOCKS.registerBlock("bonetorch_copper_bonetorch", p -> new CeilingTorchBlock(p, ParticleTypes.COPPER_FIRE_FLAME, BoneTorchMod.COPPER_BONETORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TORCH));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = Map.of(
				BoneTorchMod.BONETORCH.getId(), CEILING_BONE_TORCH.get(),
				BoneTorchMod.SOUL_BONETORCH.getId(), CEILING_SOUL_BONE_TORCH.get(),
				BoneTorchMod.COPPER_BONETORCH.getId(), CEILING_COPPER_BONE_TORCH.get());
		}

		return placeEntries;
	}
}
