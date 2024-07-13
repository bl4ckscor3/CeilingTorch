package bl4ckscor3.mod.ceilingtorch.compat.silentgear;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.silentchaos512.gear.setup.SgBlocks;

public class SilentGearCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final DeferredBlock<Block> STONE_CEILING_TORCH = CeilingTorch.BLOCKS.register("silentgear_stone_torch", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.sound(SoundType.STONE),
			ParticleTypes.FLAME, SgBlocks.STONE_TORCH));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = Map.of(getRegistryName(SgBlocks.STONE_TORCH.asItem()), STONE_CEILING_TORCH.get());

		return placeEntries;
	}
}
