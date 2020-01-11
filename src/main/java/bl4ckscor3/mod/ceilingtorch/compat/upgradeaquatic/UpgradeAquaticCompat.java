package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import com.teamabnormals.upgrade_aquatic.common.blocks.BlockJellyTorch.JellyTorchType;
import com.teamabnormals.upgrade_aquatic.core.registry.UABlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.PlaceHandler;
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

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(pinkJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.PINK).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_pink")));
		event.getRegistry().register(purpleJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.PURPLE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_purple")));
		event.getRegistry().register(blueJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.BLUE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_blue")));
		event.getRegistry().register(greenJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.GREEN).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_green")));
		event.getRegistry().register(yellowJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.YELLOW).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_yellow")));
		event.getRegistry().register(orangeJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.ORANGE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_orange")));
		event.getRegistry().register(redJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.RED).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_red")));
		event.getRegistry().register(whiteJellyTorch = new BlockJellyCeilingTorch(Properties.from(Blocks.TORCH).sound(SoundType.METAL), JellyTorchType.WHITE).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_white")));
	}

	@Override
	public void registerPlaceEntries()
	{
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_PINK.getRegistryName(), pinkJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_PURPLE.getRegistryName(), purpleJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_BLUE.getRegistryName(), blueJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_GREEN.getRegistryName(), greenJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_YELLOW.getRegistryName(), yellowJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_ORANGE.getRegistryName(), orangeJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_RED.getRegistryName(), redJellyTorch);
		PlaceHandler.registerPlaceEntry(UABlocks.JELLY_TORCH_WHITE.getRegistryName(), whiteJellyTorch);
	}
}
