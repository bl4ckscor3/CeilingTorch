package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.block.Block;
import net.minecraft.block.Block.Properties;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent.Register;

public class UpgradeAquaticCompat implements ICeilingTorchCompat
{
	public static Block pinkJellyTorch;
	public static Block purpleJellyTorch;
	public static Block blueJellyTorch;
	public static Block greenJellyTorch;
	public static Block yellowJellyTorch;
	public static Block orangeJellyTorch;
	public static Block redJellyTorch;
	public static Block whiteJellyTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(pinkJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.PINK).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_pink")));
		event.getRegistry().register(purpleJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.PURPLE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_purple")));
		event.getRegistry().register(blueJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.BLUE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_blue")));
		event.getRegistry().register(greenJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.GREEN).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_green")));
		event.getRegistry().register(yellowJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.YELLOW).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_yellow")));
		event.getRegistry().register(orangeJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.ORANGE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_orange")));
		event.getRegistry().register(redJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.RED).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_red")));
		event.getRegistry().register(whiteJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.WHITE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_white")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(UABlocks.JELLY_TORCH_PINK.getRegistryName(), pinkJellyTorch)
					.put(UABlocks.JELLY_TORCH_PURPLE.getRegistryName(), purpleJellyTorch)
					.put(UABlocks.JELLY_TORCH_BLUE.getRegistryName(), blueJellyTorch)
					.put(UABlocks.JELLY_TORCH_GREEN.getRegistryName(), greenJellyTorch)
					.put(UABlocks.JELLY_TORCH_YELLOW.getRegistryName(), yellowJellyTorch)
					.put(UABlocks.JELLY_TORCH_ORANGE.getRegistryName(), orangeJellyTorch)
					.put(UABlocks.JELLY_TORCH_RED.getRegistryName(), redJellyTorch)
					.put(UABlocks.JELLY_TORCH_WHITE.getRegistryName(), whiteJellyTorch).build();
		}

		return placeEntries;
	}
}
