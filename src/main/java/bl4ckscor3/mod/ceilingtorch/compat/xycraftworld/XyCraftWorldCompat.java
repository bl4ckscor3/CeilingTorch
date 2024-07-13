package bl4ckscor3.mod.ceilingtorch.compat.xycraftworld;

import java.util.Map;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import tv.soaryn.xycraft.world.content.registries.WorldContent;
import tv.soaryn.xycraft.world.content.registries.WorldParticles;

public class XyCraftWorldCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final DeferredBlock<CeilingTorchBlock> CEILING_ALUMINUM_TORCH = CeilingTorch.BLOCKS.register("xycraft_world_aluminum_torch", () -> new XyCraftCeilingTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).mapColor(MapColor.TERRACOTTA_WHITE).noOcclusion().lightLevel(state -> 15), WorldParticles.AluminumFlame, () -> WorldContent.Block.AluminumTorch.block()));
	public static final DeferredBlock<CeilingTorchBlock> CEILING_COPPER_TORCH = CeilingTorch.BLOCKS.register("xycraft_world_copper_torch", () -> new XyCraftCeilingTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).mapColor(MapColor.COLOR_LIGHT_GREEN).noOcclusion().lightLevel(state -> 15), WorldParticles.CopperFlame, () -> WorldContent.Block.CopperTorch.block()));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = Map.of(getRegistryName(WorldContent.Block.AluminumTorch.block()), CEILING_ALUMINUM_TORCH.get(),
					getRegistryName(WorldContent.Block.CopperTorch.block()), CEILING_COPPER_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
