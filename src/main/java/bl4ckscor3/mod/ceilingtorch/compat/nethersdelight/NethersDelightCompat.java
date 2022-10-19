package bl4ckscor3.mod.ceilingtorch.compat.nethersdelight;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.RegistryObject;
import umpaz.nethersdelight.common.registry.NDBlocks;

public class NethersDelightCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<Block> CEILING_PROPELPLANT_TORCH = CeilingTorch.BLOCKS.register("nethersdelight_propelplant_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.PLANT, MaterialColor.NETHER)
			.noCollission()
			.sound(SoundType.GRASS)
			.strength(0.1F)
			.lightLevel(state -> 12),
			ParticleTypes.FLAME, NDBlocks.PROPELPLANT_TORCH));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(NDBlocks.PROPELPLANT_TORCH.get()), CEILING_PROPELPLANT_TORCH.get());

		return placeEntries;
	}
}
