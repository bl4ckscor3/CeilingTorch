package bl4ckscor3.mod.ceilingtorch.compat.bambooeverything;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import dev.wuffs.blocks.Blocks;
import dev.wuffs.items.Items;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;

public class BambooEverythingCompat implements ICeilingTorchCompat {
	public static Block ceilingBambooTorch, dryCeilingBambooTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(ceilingBambooTorch = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel(state -> 14)
				.sound(SoundType.BAMBOO)
				.noOcclusion(),
				ParticleTypes.FLAME, Blocks.TORCH)
				.setRegistryName("bambooeverything_bamboo_torch"));
		event.getRegistry().register(dryCeilingBambooTorch = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.instabreak()
				.lightLevel(state -> 14)
				.sound(SoundType.BAMBOO)
				.noOcclusion(),
				ParticleTypes.FLAME, Blocks.DRY_TORCH)
				.setRegistryName("bambooeverything_dry_bamboo_torch"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(Items.TORCH.get().getRegistryName(), ceilingBambooTorch,
					Items.DRY_TORCH.get().getRegistryName(), dryCeilingBambooTorch);
			//@formatter:on
		}

		return placeEntries;
	}
}
