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
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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

	public UpgradeAquaticCompat()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(UpgradeAquaticCompatClient::onFMLClientSetup);
	}

	@Override
	public void registerBlocks(Register<Block> event)
	{
		event.getRegistry().register(pinkJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.PINK, UABlocks.PINK_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_pink")));
		event.getRegistry().register(purpleJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.PURPLE, UABlocks.PURPLE_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_purple")));
		event.getRegistry().register(blueJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.BLUE, UABlocks.BLUE_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_blue")));
		event.getRegistry().register(greenJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.GREEN, UABlocks.GREEN_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_green")));
		event.getRegistry().register(yellowJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.YELLOW, UABlocks.YELLOW_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_yellow")));
		event.getRegistry().register(orangeJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.ORANGE, UABlocks.ORANGE_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_orange")));
		event.getRegistry().register(redJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.RED, UABlocks.RED_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_red")));
		event.getRegistry().register(whiteJellyTorch = new JellyCeilingTorchBlock(Properties.from(Blocks.TORCH)
				.sound(SoundType.METAL),
				JellyTorchType.WHITE, UABlocks.WHITE_JELLY_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "upgrade_aquatic_jelly_torch_white")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(UABlocks.PINK_JELLY_TORCH.get().getRegistryName(), pinkJellyTorch)
					.put(UABlocks.PURPLE_JELLY_TORCH.get().getRegistryName(), purpleJellyTorch)
					.put(UABlocks.BLUE_JELLY_TORCH.get().getRegistryName(), blueJellyTorch)
					.put(UABlocks.GREEN_JELLY_TORCH.get().getRegistryName(), greenJellyTorch)
					.put(UABlocks.YELLOW_JELLY_TORCH.get().getRegistryName(), yellowJellyTorch)
					.put(UABlocks.ORANGE_JELLY_TORCH.get().getRegistryName(), orangeJellyTorch)
					.put(UABlocks.RED_JELLY_TORCH.get().getRegistryName(), redJellyTorch)
					.put(UABlocks.WHITE_JELLY_TORCH.get().getRegistryName(), whiteJellyTorch).build();
		}

		return placeEntries;
	}

	@Override
	public boolean hasCutoutMippedRenderType(Block b)
	{
		return false;
	}
}
