package bl4ckscor3.mod.ceilingtorch.compat.xycraftworld;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import tv.soaryn.xycraft.world.content.registries.WorldContent;

public class XyCraftWorldCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<CeilingTorchBlock> CEILING_ALUMINUM_TORCH = CeilingTorch.BLOCKS.registerBlock("xycraft_world_aluminum_torch", p -> new XyCraftCeilingTorchBlock(p, new DustParticleOptions(8947848, 0.8F), () -> WorldContent.Block.AluminumTorch.block()), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).mapColor(MapColor.TERRACOTTA_WHITE));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_COPPER_TORCH = CeilingTorch.BLOCKS.registerBlock("xycraft_world_copper_torch", p -> new XyCraftCeilingTorchBlock(p, new DustParticleOptions(4713022, 0.8F), () -> WorldContent.Block.CopperTorch.block()), () -> BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).mapColor(MapColor.COLOR_LIGHT_GREEN));
	private Map<Identifier, Block> placeEntries;

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = Map.of(getRegistryName(WorldContent.Block.AluminumTorch.block()), CEILING_ALUMINUM_TORCH.get(),
					getRegistryName(WorldContent.Block.CopperTorch.block()), CEILING_COPPER_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
