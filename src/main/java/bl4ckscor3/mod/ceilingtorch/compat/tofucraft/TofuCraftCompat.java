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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class TofuCraftCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_MOMEN = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_momen", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.SNOW),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_MOMEN));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_ISHI = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_ishi", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 6.0F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.STONE),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ISHI));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_METAL = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_metal", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 7.5F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.METAL),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_METAL));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_KINU = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_kinu", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.SNOW),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_KINU));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_GRILLED = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_grilled", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.SNOW),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_GRILLED));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_ZUNDA = CeilingTorch.BLOCKS.register("tofucraft_tofutorch_zunda", () -> new CeilingTorchBlock(BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollission()
			.noOcclusion()
			.sound(SoundType.SNOW),
			ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ZUNDA));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(TofuBlocks.TOFUTORCH_MOMEN.get()), TOFU_CEILING_TORCH_MOMEN.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_ISHI.get()), TOFU_CEILING_TORCH_ISHI.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_METAL.get()), TOFU_CEILING_TORCH_METAL.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_KINU.get()), TOFU_CEILING_TORCH_KINU.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_GRILLED.get()), TOFU_CEILING_TORCH_GRILLED.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_ZUNDA.get()), TOFU_CEILING_TORCH_ZUNDA.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
