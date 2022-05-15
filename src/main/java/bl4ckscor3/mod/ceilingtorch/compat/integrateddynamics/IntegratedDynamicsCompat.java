package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.AbstractBlock.Properties;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.ForgeRegistries;

public class IntegratedDynamicsCompat implements ICeilingTorchCompat
{
	private static final ResourceLocation MENRIL_TORCH_RL = new ResourceLocation("integrateddynamics", "menril_torch");
	private static final ResourceLocation MENRIL_TORCH_STONE_RL = new ResourceLocation("integrateddynamics", "menril_torch_stone");
	public static Block menrilCeilingTorch;
	public static Block menrilCeilingTorchStone;
	private static Block menrilTorch;
	private static Block menrilTorchStone;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(menrilCeilingTorch = new MenrilCeilingTorchBlock(getProperties(SoundType.WOOD), ParticleTypes.FLAME, this::getMenrilTorch).setRegistryName("integrateddynamics_menril_torch"));
		event.getRegistry().register(menrilCeilingTorchStone = new MenrilCeilingTorchBlock(getProperties(SoundType.STONE), ParticleTypes.FLAME, this::getMenrilTorchStone).setRegistryName("integrateddynamics_menril_torch_stone"));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(MENRIL_TORCH_RL, menrilCeilingTorch,
					MENRIL_TORCH_STONE_RL, menrilCeilingTorchStone);
		}

		return placeEntries;
	}

	private Properties getProperties(SoundType soundType)
	{
		return Properties.of(Material.DECORATION).noCollission().strength(0.0F).lightLevel(state -> 14).sound(soundType);
	}

	private Block getMenrilTorch() {
		if(menrilTorch == null)
			menrilTorch = ForgeRegistries.BLOCKS.getValue(MENRIL_TORCH_RL);

		return menrilTorch;
	}
	private Block getMenrilTorchStone() {
		if(menrilTorchStone == null)
			menrilTorchStone = ForgeRegistries.BLOCKS.getValue(MENRIL_TORCH_STONE_RL);

		return menrilTorchStone;
	}
}
