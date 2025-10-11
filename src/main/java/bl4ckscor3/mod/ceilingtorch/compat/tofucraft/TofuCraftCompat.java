package bl4ckscor3.mod.ceilingtorch.compat.tofucraft;

import java.util.Map;

import baguchi.tofucraft.registry.TofuBlocks;
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
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_MOMEN = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_momen", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_MOMEN),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_ISHI = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_ishi", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ISHI),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 6.0F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.STONE));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_METAL = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_metal", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_METAL),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 7.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.METAL));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_KINU = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_kinu", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_KINU),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_GRILLED = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_grilled", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_GRILLED),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_ZUNDA = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_zunda", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_ZUNDA),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_HELL = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_hell", p -> new CeilingTorchBlock(p, ParticleTypes.FLAME, TofuBlocks.TOFUTORCH_HELL),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	public static final DeferredBlock<CeilingTorchBlock> TOFU_CEILING_TORCH_SOUL = CeilingTorch.BLOCKS.registerBlock("tofucraft_tofutorch_soul", p -> new CeilingTorchBlock(p, ParticleTypes.SOUL_FIRE_FLAME, TofuBlocks.TOFUTORCH_SOUL),
			BlockBehaviour.Properties.of()
			.strength(0.0F, 0.5F)
			.lightLevel(state -> 14)
			.noCollision()
			.noOcclusion()
			.sound(SoundType.SNOW));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = Map.of(getRegistryName(TofuBlocks.TOFUTORCH_MOMEN.get()), TOFU_CEILING_TORCH_MOMEN.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_ISHI.get()), TOFU_CEILING_TORCH_ISHI.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_METAL.get()), TOFU_CEILING_TORCH_METAL.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_KINU.get()), TOFU_CEILING_TORCH_KINU.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_GRILLED.get()), TOFU_CEILING_TORCH_GRILLED.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_ZUNDA.get()), TOFU_CEILING_TORCH_ZUNDA.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_HELL.get()), TOFU_CEILING_TORCH_HELL.get(),
					getRegistryName(TofuBlocks.TOFUTORCH_SOUL.get()), TOFU_CEILING_TORCH_SOUL.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
