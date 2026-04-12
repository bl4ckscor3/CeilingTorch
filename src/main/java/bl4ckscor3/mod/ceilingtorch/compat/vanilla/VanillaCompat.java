package bl4ckscor3.mod.ceilingtorch.compat.vanilla;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class VanillaCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<CeilingTorchBlock> CEILING_TORCH = CeilingTorch.BLOCKS.registerBlock("torch", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, () -> Blocks.TORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH));
	public static final DeferredBlock<RedstoneCeilingTorchBlock> CEILING_REDSTONE_TORCH = CeilingTorch.BLOCKS.registerBlock("redstone_torch", p -> new RedstoneCeilingTorchBlock(p, () -> Blocks.REDSTONE_TORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.REDSTONE_TORCH));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_SOUL_TORCH = CeilingTorch.BLOCKS.registerBlock("soul_torch", p -> new CeilingTorchBlock(p, ParticleTypes.SOUL_FIRE_FLAME, () -> Blocks.SOUL_TORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.SOUL_TORCH));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_COPPER_TORCH = CeilingTorch.BLOCKS.registerBlock("copper_torch", p -> new CeilingTorchBlock(p, ParticleTypes.COPPER_FIRE_FLAME, () -> Blocks.COPPER_TORCH), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.COPPER_TORCH));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = Map.of(getRegistryName(Items.TORCH), CEILING_TORCH.get(),
				getRegistryName(Items.REDSTONE_TORCH), CEILING_REDSTONE_TORCH.get(),
				getRegistryName(Items.SOUL_TORCH), CEILING_SOUL_TORCH.get(),
				getRegistryName(Items.COPPER_TORCH), CEILING_COPPER_TORCH.get());
		}

		return placeEntries;
	}
}
