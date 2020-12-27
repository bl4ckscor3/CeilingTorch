package bl4ckscor3.mod.ceilingtorch.compat.bambooblocks;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.minecraftabnormals.bamboo_blocks.core.BambooBlocksRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class BambooBlocksCompat implements ICeilingTorchCompat
{
	public static Block bambooCeilingTorch;
	public static Block soulBambooCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(bambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).setLightLevel(state -> 14).sound(SoundType.BAMBOO), ParticleTypes.FLAME).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_bamboo_torch")));
		event.getRegistry().register(soulBambooCeilingTorch = new BambooCeilingTorchBlock(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.0F).setLightLevel(state -> 10).sound(SoundType.BAMBOO), ParticleTypes.SOUL_FIRE_FLAME).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bambooblocks_soul_bamboo_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(BambooBlocksRegistry.BAMBOO_TORCH.get().getRegistryName(), bambooCeilingTorch, BambooBlocksRegistry.SOUL_BAMBOO_TORCH.get().getRegistryName(), soulBambooCeilingTorch);

		return placeEntries;
	}
}
