package bl4ckscor3.mod.ceilingtorch.compat.secretrooms;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.wynprice.secretrooms.server.items.SecretItems;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class SecretRoomsCompat implements ICeilingTorchCompat
{
	public static Block ceilingTorchLever;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(ceilingTorchLever = new CeilingTorchLeverBlock(Block.Properties.create(Material.WOOD).setLightLevel(state -> 14).notSolid()).setRegistryName("secretroomsmod_torch_lever"));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(SecretItems.TORCH_LEVER.get().getRegistryName(), ceilingTorchLever);

		return placeEntries;
	}
}