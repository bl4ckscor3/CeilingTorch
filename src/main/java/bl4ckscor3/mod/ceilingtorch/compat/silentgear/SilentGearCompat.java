package bl4ckscor3.mod.ceilingtorch.compat.silentgear;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.silentchaos512.gear.setup.SgBlocks;

public class SilentGearCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<Block> STONE_CEILING_TORCH = CeilingTorch.BLOCKS.registerBlock("silentgear_stone_torch", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, SgBlocks.STONE_TORCH),
		() -> BlockBehaviour.Properties.of()
			.noCollision()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.sound(SoundType.STONE));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(SgBlocks.STONE_TORCH.asItem()), STONE_CEILING_TORCH.get());

		return placeEntries;
	}
}
