package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.MalumColors;
import com.sammy.malum.core.init.blocks.MalumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.RegistryObject;

public class MalumCompat implements ICeilingTorchCompat
{
	public static Block orangeEtherCeilingTorch;
	public static Block magentaEtherCeilingTorch;
	public static Block lightBlueEtherCeilingTorch;
	public static Block yellowEtherCeilingTorch;
	public static Block limeEtherCeilingTorch;
	public static Block pinkEtherCeilingTorch;
	public static Block cyanEtherCeilingTorch;
	public static Block purpleEtherCeilingTorch;
	public static Block blueEtherCeilingTorch;
	public static Block brownEtherCeilingTorch;
	public static Block greenEtherCeilingTorch;
	public static Block redEtherCeilingTorch;
	public static final RegistryObject<TileEntityType<?>> ETHER_CEILING_TORCH = CeilingTorch.TILE_ENTITIES.register("malum_ether_torch", () -> TileEntityType.Builder.of(CeilingLightingTileEntity::new,
			orangeEtherCeilingTorch,
			magentaEtherCeilingTorch,
			lightBlueEtherCeilingTorch,
			yellowEtherCeilingTorch,
			limeEtherCeilingTorch,
			pinkEtherCeilingTorch,
			cyanEtherCeilingTorch,
			purpleEtherCeilingTorch,
			blueEtherCeilingTorch,
			brownEtherCeilingTorch,
			greenEtherCeilingTorch,
			redEtherCeilingTorch).build(null));
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(orangeEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.ORANGE, MalumBlocks.ORANGE_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_orange_ether_torch")));
		event.getRegistry().register(magentaEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.MAGENTA, MalumBlocks.MAGENTA_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_magenta_ether_torch")));
		event.getRegistry().register(lightBlueEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.LIGHT_BLUE, MalumBlocks.LIGHT_BLUE_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_light_blue_ether_torch")));
		event.getRegistry().register(yellowEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.YELLOW, MalumBlocks.YELLOW_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_yellow_ether_torch")));
		event.getRegistry().register(limeEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.LIME, MalumBlocks.LIME_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_lime_ether_torch")));
		event.getRegistry().register(pinkEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.PINK, MalumBlocks.PINK_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_pink_ether_torch")));
		event.getRegistry().register(cyanEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.CYAN, MalumBlocks.CYAN_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_cyan_ether_torch")));
		event.getRegistry().register(purpleEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.PURPLE, MalumBlocks.PURPLE_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_purple_ether_torch")));
		event.getRegistry().register(blueEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.BLUE, MalumBlocks.BLUE_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_blue_ether_torch")));
		event.getRegistry().register(brownEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.BROWN, MalumBlocks.BROWN_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_brown_ether_torch")));
		event.getRegistry().register(greenEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.GREEN, MalumBlocks.GREEN_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_green_ether_torch")));
		event.getRegistry().register(redEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(),
				MalumColors.RED, MalumBlocks.RED_ETHER_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "malum_red_ether_torch")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(MalumBlocks.ORANGE_ETHER_TORCH.get().getRegistryName(), orangeEtherCeilingTorch)
					.put(MalumBlocks.MAGENTA_ETHER_TORCH.get().getRegistryName(), magentaEtherCeilingTorch)
					.put(MalumBlocks.LIGHT_BLUE_ETHER_TORCH.get().getRegistryName(), lightBlueEtherCeilingTorch)
					.put(MalumBlocks.YELLOW_ETHER_TORCH.get().getRegistryName(), yellowEtherCeilingTorch)
					.put(MalumBlocks.LIME_ETHER_TORCH.get().getRegistryName(), limeEtherCeilingTorch)
					.put(MalumBlocks.PINK_ETHER_TORCH.get().getRegistryName(), pinkEtherCeilingTorch)
					.put(MalumBlocks.CYAN_ETHER_TORCH.get().getRegistryName(), cyanEtherCeilingTorch)
					.put(MalumBlocks.PURPLE_ETHER_TORCH.get().getRegistryName(), purpleEtherCeilingTorch)
					.put(MalumBlocks.BLUE_ETHER_TORCH.get().getRegistryName(), blueEtherCeilingTorch)
					.put(MalumBlocks.BROWN_ETHER_TORCH.get().getRegistryName(), brownEtherCeilingTorch)
					.put(MalumBlocks.GREEN_ETHER_TORCH.get().getRegistryName(), greenEtherCeilingTorch)
					.put(MalumBlocks.RED_ETHER_TORCH.get().getRegistryName(), redEtherCeilingTorch).build();
		}

		return placeEntries;
	}

	private Block.Properties getProperties()
	{
		return MalumBlocks.RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel(state -> 14);
	}
}
