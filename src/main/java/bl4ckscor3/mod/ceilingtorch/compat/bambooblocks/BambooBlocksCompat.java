package bl4ckscor3.mod.ceilingtorch.compat.bambooblocks;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.bamboo_blocks.core.BambooBlocksRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class BambooBlocksCompat implements ICeilingTorchCompat
{
	public static Block bambooCeilingTorch;
	public static Block soulBambooCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(bambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.strength(0.0F)
				.lightLevel(state -> 14)
				.sound(SoundType.BAMBOO),
				ParticleTypes.FLAME, BambooBlocksRegistry.BAMBOO_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_bamboo_torch")));
		event.getRegistry().register(soulBambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.strength(0.0F)
				.lightLevel(state -> 10)
				.sound(SoundType.BAMBOO),
				ParticleTypes.SOUL_FIRE_FLAME, BambooBlocksRegistry.SOUL_BAMBOO_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_soul_bamboo_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(BambooBlocksRegistry.BAMBOO_TORCH.get().getRegistryName(), bambooCeilingTorch,
					BambooBlocksRegistry.SOUL_BAMBOO_TORCH.get().getRegistryName(), soulBambooCeilingTorch);
		}

		return placeEntries;
	}
}
