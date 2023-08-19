package bl4ckscor3.mod.ceilingtorch.compat.tofucraft;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import baguchan.tofucraft.registry.TofuBlocks;
import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;

public class TofuCraftCompat implements ICeilingTorchCompat {
	public static Block tofuCeilingTorchMomen;
	public static Block tofuCeilingTorchIshi;
	public static Block tofuCeilingTorchMetal;
	public static Block tofuCeilingTorchKinu;
	public static Block tofuCeilingTorchGrilled;
	public static Block tofuCeilingTorchZunda;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(tofuCeilingTorchMomen = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_MOMEN).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_momen")));
		event.getRegistry().register(tofuCeilingTorchIshi = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 6.0F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.STONE),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ISHI).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_ishi")));
		event.getRegistry().register(tofuCeilingTorchMetal = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 7.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.METAL),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_METAL).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_metal")));
		event.getRegistry().register(tofuCeilingTorchKinu = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_KINU).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_kinu")));
		event.getRegistry().register(tofuCeilingTorchGrilled = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_GRILLED).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_grilled")));
		event.getRegistry().register(tofuCeilingTorchZunda = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.strength(0.0F, 0.5F)
				.lightLevel(state -> 14)
				.noCollission()
				.sound(SoundType.SNOW),
				ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ZUNDA).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "tofucraft_tofutorch_zunda")));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(TofuBlocks.TOFUTORCH_MOMEN.get().getRegistryName(), tofuCeilingTorchMomen,
					TofuBlocks.TOFUTORCH_ISHI.get().getRegistryName(), tofuCeilingTorchIshi,
					TofuBlocks.TOFUTORCH_METAL.get().getRegistryName(), tofuCeilingTorchMetal,
					TofuBlocks.TOFUTORCH_KINU.get().getRegistryName(), tofuCeilingTorchKinu,
					TofuBlocks.TOFUTORCH_GRILLED.get().getRegistryName(), tofuCeilingTorchGrilled,
					TofuBlocks.TOFUTORCH_ZUNDA.get().getRegistryName(), tofuCeilingTorchZunda);
			//@formatter:on
		}

		return placeEntries;
	}
}
