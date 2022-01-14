package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import java.util.HashMap;
import java.util.Map;

import com.ProfitOrange.MoShiz.init.MoShizBlocks;
import com.ProfitOrange.MoShiz.particle.TypesParticle;
import com.ProfitOrange.MoShiz.util.ColorDye;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MoShizCompat implements ICeilingTorchCompat
{
	public static Block fouliteCeilingTorch;
	//dye,torch
	public static Map<Item,Block> coloredCeilingTorches = new HashMap<>();
	public static Map<Block,Integer> ceilingTorchParticleColors = new HashMap<>();
	private Map<ResourceLocation,Block> placeEntries;

	public MoShizCompat()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(ParticleType.class, CeilingParticleTypes::onRegisterParticleTypes);
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MoShizCompatClient::addListeners);
	}

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(fouliteCeilingTorch = new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), TypesParticle.GREEN_FLAME, () -> MoShizBlocks.foulite_torch).setRegistryName("moshiz_foulite_torch"));
		addColoredCeilingTorch(DyeColor.BLACK.getColorValue(), Items.BLACK_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.black_torch).setRegistryName("moshiz_black_torch"));
		addColoredCeilingTorch(ColorDye.red, Items.RED_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.red_torch).setRegistryName("moshiz_red_torch"));
		addColoredCeilingTorch(ColorDye.green, Items.GREEN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.green_torch).setRegistryName("moshiz_green_torch"));
		addColoredCeilingTorch(ColorDye.brown, Items.BROWN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.brown_torch).setRegistryName("moshiz_brown_torch"));
		addColoredCeilingTorch(ColorDye.blue, Items.BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.blue_torch).setRegistryName("moshiz_blue_torch"));
		addColoredCeilingTorch(ColorDye.purple, Items.PURPLE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.purple_torch).setRegistryName("moshiz_purple_torch"));
		addColoredCeilingTorch(ColorDye.cyan, Items.CYAN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.cyan_torch).setRegistryName("moshiz_cyan_torch"));
		addColoredCeilingTorch(ColorDye.light_grey, Items.LIGHT_GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.light_grey_torch).setRegistryName("moshiz_light_grey_torch"));
		addColoredCeilingTorch(ColorDye.grey, Items.GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.grey_torch).setRegistryName("moshiz_grey_torch"));
		addColoredCeilingTorch(ColorDye.pink, Items.PINK_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.pink_torch).setRegistryName("moshiz_pink_torch"));
		addColoredCeilingTorch(ColorDye.lime, Items.LIME_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.lime_torch).setRegistryName("moshiz_lime_torch"));
		addColoredCeilingTorch(ColorDye.yellow, Items.YELLOW_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.yellow_torch).setRegistryName("moshiz_yellow_torch"));
		addColoredCeilingTorch(ColorDye.light_blue, Items.LIGHT_BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.light_blue_torch).setRegistryName("moshiz_light_blue_torch"));
		addColoredCeilingTorch(ColorDye.magenta, Items.MAGENTA_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.magenta_torch).setRegistryName("moshiz_magenta_torch"));
		addColoredCeilingTorch(ColorDye.orange, Items.ORANGE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.orange_torch).setRegistryName("moshiz_orange_torch"));
		addColoredCeilingTorch(ColorDye.white, Items.WHITE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), () -> MoShizBlocks.white_torch).setRegistryName("moshiz_white_torch"));
		coloredCeilingTorches.forEach((dye, torch) -> event.getRegistry().register(torch));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			Builder<ResourceLocation, Block> builder = ImmutableMap.<ResourceLocation,Block>builder();

			builder.put(MoShizBlocks.foulite_torch.getRegistryName(), fouliteCeilingTorch);
			coloredCeilingTorches.forEach((dye, torch) -> builder.put(((CeilingTorchBlock)torch).getOriginalBlock().getRegistryName(), torch));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	private void addColoredCeilingTorch(int particleColor, Item dye, Block torch)
	{
		coloredCeilingTorches.put(dye, torch);
		ceilingTorchParticleColors.put(torch, particleColor);
	}
}
