package bl4ckscor3.mod.ceilingtorch.compat.adorn;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import juuxel.adorn.block.AdornBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class AdornCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final DeferredBlock<CeilingTorchBlock> STONE_CEILING_TORCH = CeilingTorch.BLOCKS.register("adorn_stone_torch", () -> new CeilingTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH)
			.sound(SoundType.STONE)
			.lightLevel(state -> 15), ParticleTypes.FLAME, AdornBlocks.STONE_TORCH_GROUND));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = Map.of(getRegistryName(AdornBlocks.STONE_TORCH_GROUND.get()), STONE_CEILING_TORCH.get());

		return placeEntries;
	}
}
