package bl4ckscor3.mod.ceilingtorch.compat.secretrooms;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.wynprice.secretrooms.server.blocks.SecretBlocks;
import com.wynprice.secretrooms.server.items.SecretItems;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;

public class SecretRoomsCompat implements ICeilingTorchCompat {
	public static Block ceilingTorchLever;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(ceilingTorchLever = new CeilingTorchLeverBlock(Block.Properties.of(Material.WOOD)
				.lootFrom(SecretBlocks.TORCH_LEVER)
				.lightLevel(state -> 14)
				.noOcclusion())
				.setRegistryName("secretroomsmod_torch_lever"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(SecretItems.TORCH_LEVER.get().getRegistryName(), ceilingTorchLever);

		return placeEntries;
	}
}