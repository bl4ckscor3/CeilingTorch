package bl4ckscor3.mod.ceilingtorch.compat.reliquary;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.RegistryObject;
import reliquary.init.ModBlocks;

public class ReliquaryCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> INTERDICTION_CEILING_TORCH = CeilingTorch.BLOCKS.register("reliquary_interdiction_torch", InterdictionCeilingTorchBlock::new);
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(ModBlocks.INTERDICTION_TORCH.get()), INTERDICTION_CEILING_TORCH.get());

		return placeEntries;
	}
}
