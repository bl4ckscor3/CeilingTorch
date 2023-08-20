package bl4ckscor3.mod.ceilingtorch.compat.secretrooms;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.wynprice.secretrooms.server.blocks.SecretBlocks;
import com.wynprice.secretrooms.server.items.SecretItems;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class SecretRoomsCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<Block> CEILING_TORCH_LEVER = CeilingTorch.BLOCKS.register("secretroomsmod_torch_lever", () -> new CeilingTorchLeverBlock(Block.Properties.of(Material.WOOD)
				.lootFrom(SecretBlocks.TORCH_LEVER)
				.lightLevel(state -> 14)
				.noOcclusion()));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(SecretItems.TORCH_LEVER.getId(), CEILING_TORCH_LEVER.get());

		return placeEntries;
	}
}