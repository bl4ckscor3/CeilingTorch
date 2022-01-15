package bl4ckscor3.mod.ceilingtorch.compat.tofucraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import baguchan.tofucraft.registry.TofuBlocks;
import baguchan.tofucraft.registry.TofuMaterial;
import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class TofuCraftCompat implements ICeilingTorchCompat
{
	public static Block tofuCeilingTorchMomen;
	public static Block tofuCeilingTorchIshi;
	public static Block tofuCeilingTorchMetal;
	public static Block tofuCeilingTorchKinu;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(tofuCeilingTorchMomen = new CeilingTorchBlock(Block.Properties.of(TofuMaterial.TOFU)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.noOcclusion()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, () -> TofuBlocks.TOFUTORCH_MOMEN).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_momen")));
		event.getRegistry().register(tofuCeilingTorchIshi = new CeilingTorchBlock(Block.Properties.of(TofuMaterial.TOFU)
				.strength(0.0F, 6.0F)
				.lightLevel(state -> 14)
				.noCollission()
				.noOcclusion()
				.sound(SoundType.STONE),
				ParticleTypes.FLAME, () -> TofuBlocks.TOFUTORCH_ISHI).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_ishi")));
		event.getRegistry().register(tofuCeilingTorchMetal = new CeilingTorchBlock(Block.Properties.of(TofuMaterial.TOFU)
				.strength(0.0F, 7.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.noOcclusion()
				.sound(SoundType.METAL),
				ParticleTypes.FLAME, () -> TofuBlocks.TOFUTORCH_METAL).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_metal")));
		event.getRegistry().register(tofuCeilingTorchKinu = new CeilingTorchBlock(Block.Properties.of(TofuMaterial.TOFU)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.noOcclusion()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, () -> TofuBlocks.TOFUTORCH_KINU).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_kinu")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.of(TofuBlocks.TOFUTORCH_MOMEN.getRegistryName(), tofuCeilingTorchMomen,
					TofuBlocks.TOFUTORCH_ISHI.getRegistryName(), tofuCeilingTorchIshi,
					TofuBlocks.TOFUTORCH_METAL.getRegistryName(), tofuCeilingTorchMetal,
					TofuBlocks.TOFUTORCH_KINU.getRegistryName(), tofuCeilingTorchKinu);
		}

		return placeEntries;
	}
}
