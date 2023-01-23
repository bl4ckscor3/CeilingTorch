package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.ProfitOrange.MoShiz.init.DeferredBlocks;
import com.ProfitOrange.MoShiz.init.DeferredItems;
import com.ProfitOrange.MoShiz.init.MoShizParticles;
import com.ProfitOrange.MoShiz.util.ColorDye;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MoShizCompat implements ICeilingTorchCompat {
	public static Block fouliteCeilingTorch;
	//dye, torch
	public static Map<Item, Block> coloredCeilingTorches = new HashMap<>();
	public static Map<Block, Integer> ceilingTorchParticleColors = new HashMap<>();
	private Map<ResourceLocation, Block> placeEntries;

	public MoShizCompat() {
		FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(ParticleType.class, CeilingParticleTypes::onRegisterParticleTypes);
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MoShizCompatClient::addListeners);
	}

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(fouliteCeilingTorch = new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), null, DeferredBlocks.FOULITE_TORCH) {
			@Override
			@OnlyIn(Dist.CLIENT)
			public void animateTick(BlockState state, Level level, BlockPos pos, Random rand)
			{
				double x = pos.getX() + 0.5D;
				double y = pos.getY() + 0.45D;
				double z = pos.getZ() + 0.5D;

				level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
				level.addParticle(MoShizParticles.GREEN_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
			}
		}.setRegistryName("moshiz_foulite_torch"));
		//@formatter:on
		addColoredCeilingTorch(0x1D1D21, Items.BLACK_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.BLACK_TORCH).setRegistryName("moshiz_black_torch"));
		addColoredCeilingTorch(ColorDye.red, Items.RED_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.RED_TORCH).setRegistryName("moshiz_red_torch"));
		addColoredCeilingTorch(ColorDye.green, Items.GREEN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.GREEN_TORCH).setRegistryName("moshiz_green_torch"));
		addColoredCeilingTorch(ColorDye.brown, Items.BROWN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.BROWN_TORCH).setRegistryName("moshiz_brown_torch"));
		addColoredCeilingTorch(ColorDye.blue, Items.BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.BLUE_TORCH).setRegistryName("moshiz_blue_torch"));
		addColoredCeilingTorch(ColorDye.purple, Items.PURPLE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.PURPLE_TORCH).setRegistryName("moshiz_purple_torch"));
		addColoredCeilingTorch(ColorDye.cyan, Items.CYAN_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.CYAN_TORCH).setRegistryName("moshiz_cyan_torch"));
		addColoredCeilingTorch(ColorDye.light_grey, Items.LIGHT_GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.LIGHT_GREY_TORCH).setRegistryName("moshiz_light_grey_torch"));
		addColoredCeilingTorch(ColorDye.grey, Items.GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.GREY_TORCH).setRegistryName("moshiz_grey_torch"));
		addColoredCeilingTorch(ColorDye.pink, Items.PINK_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.PINK_TORCH).setRegistryName("moshiz_pink_torch"));
		addColoredCeilingTorch(ColorDye.lime, Items.LIME_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.LIME_TORCH).setRegistryName("moshiz_lime_torch"));
		addColoredCeilingTorch(ColorDye.yellow, Items.YELLOW_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.YELLOW_TORCH).setRegistryName("moshiz_yellow_torch"));
		addColoredCeilingTorch(ColorDye.light_blue, Items.LIGHT_BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.LIGHT_BLUE_TORCH).setRegistryName("moshiz_light_blue_torch"));
		addColoredCeilingTorch(ColorDye.magenta, Items.MAGENTA_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.MAGENTA_TORCH).setRegistryName("moshiz_magenta_torch"));
		addColoredCeilingTorch(ColorDye.orange, Items.ORANGE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.ORANGE_TORCH).setRegistryName("moshiz_orange_torch"));
		addColoredCeilingTorch(ColorDye.white, Items.WHITE_DYE, new MoShizCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), DeferredBlocks.WHITE_TORCH).setRegistryName("moshiz_white_torch"));
		coloredCeilingTorches.forEach((dye, torch) -> event.getRegistry().register(torch));
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			Builder<ResourceLocation, Block> builder = ImmutableMap.<ResourceLocation, Block>builder();

			builder.put(DeferredItems.FOULITE_TORCH.get().getRegistryName(), fouliteCeilingTorch);
			coloredCeilingTorches.forEach((dye, torch) -> builder.put(((CeilingTorchBlock) torch).getOriginalBlock().getRegistryName(), torch));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	private void addColoredCeilingTorch(int particleColor, Item dye, Block torch) {
		coloredCeilingTorches.put(dye, torch);
		ceilingTorchParticleColors.put(torch, particleColor);
	}
}
