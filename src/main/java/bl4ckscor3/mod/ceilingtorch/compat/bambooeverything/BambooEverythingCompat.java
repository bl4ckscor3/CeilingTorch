package bl4ckscor3.mod.ceilingtorch.compat.bambooeverything;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import dev.wuffs.blocks.Blocks;
import dev.wuffs.items.Items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class BambooEverythingCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<CeilingTorchBlock> CEILING_BAMBOO_TORCH = CeilingTorch.BLOCKS.register("bambooeverything_bamboo_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.instabreak()
			.lightLevel(state -> 14)
			.sound(SoundType.BAMBOO)
			.noOcclusion(),
			ParticleTypes.FLAME, Blocks.TORCH));
	public static final RegistryObject<CeilingTorchBlock> DRY_CEILING_BAMBOO_TORCH = CeilingTorch.BLOCKS.register("bambooeverything_dry_bamboo_torch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.instabreak()
			.lightLevel(state -> 14)
			.sound(SoundType.BAMBOO)
			.noOcclusion(),
			ParticleTypes.FLAME, Blocks.DRY_TORCH));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(Items.TORCH.get()), CEILING_BAMBOO_TORCH.get(),
					getRegistryName(Items.DRY_TORCH.get()), DRY_CEILING_BAMBOO_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
