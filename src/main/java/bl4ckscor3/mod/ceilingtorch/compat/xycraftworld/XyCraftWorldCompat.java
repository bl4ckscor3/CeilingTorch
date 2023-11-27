package bl4ckscor3.mod.ceilingtorch.compat.xycraftworld;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;
import tv.soaryn.xycraft.world.content.WorldContent;
import tv.soaryn.xycraft.world.content.WorldParticleContent;

public class XyCraftWorldCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<CeilingTorchBlock> CEILING_ALUMINUM_TORCH = CeilingTorch.BLOCKS.register("xycraft_world_aluminum_torch", () -> new XyCraftCeilingTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).mapColor(MapColor.TERRACOTTA_WHITE).noOcclusion().lightLevel(state -> 15), WorldParticleContent.AluminumFlame, () -> WorldContent.AluminumTorch.block()));
	public static final RegistryObject<CeilingTorchBlock> CEILING_COPPER_TORCH = CeilingTorch.BLOCKS.register("xycraft_world_copper_torch", () -> new XyCraftCeilingTorchBlock(BlockBehaviour.Properties.copy(Blocks.TORCH).mapColor(MapColor.COLOR_LIGHT_GREEN).noOcclusion().lightLevel(state -> 15), WorldParticleContent.CopperFlame, () -> WorldContent.CopperTorch.block()));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(WorldContent.AluminumTorch.block()), CEILING_ALUMINUM_TORCH.get(),
					getRegistryName(WorldContent.CopperTorch.block()), CEILING_COPPER_TORCH.get());
			//@formatter:on
		}

		return placeEntries;
	}
}
