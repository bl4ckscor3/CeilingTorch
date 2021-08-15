package bl4ckscor3.mod.ceilingtorch.compat.bambooblocks;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.minecraftabnormals.bamboo_blocks.core.registry.BBBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModList;

public class BambooBlocksCompat implements ICeilingTorchCompat
{
	public static Block bambooCeilingTorch;
	public static Block soulBambooCeilingTorch;
	public static Block enderBambooCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(bambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> 14)
				.sound(SoundType.BAMBOO),
				ParticleTypes.FLAME, BBBlocks.BAMBOO_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_bamboo_torch")));
		event.getRegistry().register(soulBambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
				.doesNotBlockMovement()
				.hardnessAndResistance(0.0F)
				.setLightLevel(state -> 10)
				.sound(SoundType.BAMBOO),
				ParticleTypes.SOUL_FIRE_FLAME, BBBlocks.SOUL_BAMBOO_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_soul_bamboo_torch")));

		if(ModList.get().isLoaded("endergetic"))
		{
			event.getRegistry().register(enderBambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS)
					.doesNotBlockMovement()
					.hardnessAndResistance(0.0F)
					.setLightLevel(state -> 14)
					.sound(SoundType.BAMBOO),
					null, BBBlocks.ENDER_BAMBOO_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_ender_bamboo_torch")));
		}
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			Builder<ResourceLocation,Block> builder = ImmutableMap.<ResourceLocation,Block>builder().put(BBBlocks.BAMBOO_TORCH.get().getRegistryName(), bambooCeilingTorch)
					.put(BBBlocks.SOUL_BAMBOO_TORCH.get().getRegistryName(), soulBambooCeilingTorch);

			if(ModList.get().isLoaded("endergetic"))
				builder.put(BBBlocks.ENDER_BAMBOO_TORCH.get().getRegistryName(), enderBambooCeilingTorch);

			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
