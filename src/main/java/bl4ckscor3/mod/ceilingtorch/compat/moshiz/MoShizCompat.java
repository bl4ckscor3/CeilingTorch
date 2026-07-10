package bl4ckscor3.mod.ceilingtorch.compat.moshiz;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import com.profitorange.moshiz.init.DeferredBlocks;
import com.profitorange.moshiz.init.MoShizParticles;
import com.profitorange.moshiz.util.ColorDye;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.RegisterEvent;

public class MoShizCompat implements ICeilingTorchCompat {
	public static Block fouliteCeilingTorch;
	//dye, torch
	public static Map<Item, MoShizCeilingTorchBlock> coloredCeilingTorches = new HashMap<>();
	public static Map<Block, Integer> ceilingTorchParticleColors = new HashMap<>();
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DYED_CEILING_FLAME = CeilingTorch.PARTICLE_TYPES.register("dyed_ceiling_flame", () -> new SimpleParticleType(false));
	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> DYED_CEILING_SMOKE = CeilingTorch.PARTICLE_TYPES.register("dyed_ceiling_smoke", () -> new SimpleParticleType(false));
	private Map<ResourceLocation, Block> placeEntries;

	public MoShizCompat() {
		ModLoadingContext.get().getActiveContainer().getEventBus().addListener(this::registerBlocks);
	}

	public void registerBlocks(RegisterEvent event) {
		event.register(Registries.BLOCK, helper -> {
			helper.register(ResourceLocation.fromNamespaceAndPath(CeilingTorch.MODID, "moshiz_foulite_torch"), fouliteCeilingTorch = new CeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.FOULITE_TORCH, MoShizParticles.GREEN_FLAME) {
				@Override
				public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
					double x = pos.getX() + 0.5D;
					double y = pos.getY() + 0.45D;
					double z = pos.getZ() + 0.5D;

					level.addParticle(ParticleTypes.SMOKE, x, y, z, 0.0D, 0.0D, 0.0D);
					level.addParticle(MoShizParticles.GREEN_FLAME.get(), x, y, z, 0.0D, 0.0D, 0.0D);
				}
			});
			addColoredCeilingTorch(0x1D1D21, Items.BLACK_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.BLACK_TORCH, "moshiz_black_torch"));
			addColoredCeilingTorch(ColorDye.red, Items.RED_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.RED_TORCH, "moshiz_red_torch"));
			addColoredCeilingTorch(ColorDye.green, Items.GREEN_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.GREEN_TORCH, "moshiz_green_torch"));
			addColoredCeilingTorch(ColorDye.brown, Items.BROWN_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.BROWN_TORCH, "moshiz_brown_torch"));
			addColoredCeilingTorch(ColorDye.blue, Items.BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.BLUE_TORCH, "moshiz_blue_torch"));
			addColoredCeilingTorch(ColorDye.purple, Items.PURPLE_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.PURPLE_TORCH, "moshiz_purple_torch"));
			addColoredCeilingTorch(ColorDye.cyan, Items.CYAN_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.CYAN_TORCH, "moshiz_cyan_torch"));
			addColoredCeilingTorch(ColorDye.light_grey, Items.LIGHT_GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.LIGHT_GREY_TORCH, "moshiz_light_grey_torch"));
			addColoredCeilingTorch(ColorDye.grey, Items.GRAY_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.GREY_TORCH, "moshiz_grey_torch"));
			addColoredCeilingTorch(ColorDye.pink, Items.PINK_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.PINK_TORCH, "moshiz_pink_torch"));
			addColoredCeilingTorch(ColorDye.lime, Items.LIME_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.LIME_TORCH, "moshiz_lime_torch"));
			addColoredCeilingTorch(ColorDye.yellow, Items.YELLOW_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.YELLOW_TORCH, "moshiz_yellow_torch"));
			addColoredCeilingTorch(ColorDye.light_blue, Items.LIGHT_BLUE_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.LIGHT_BLUE_TORCH, "moshiz_light_blue_torch"));
			addColoredCeilingTorch(ColorDye.magenta, Items.MAGENTA_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.MAGENTA_TORCH, "moshiz_magenta_torch"));
			addColoredCeilingTorch(ColorDye.orange, Items.ORANGE_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.ORANGE_TORCH, "moshiz_orange_torch"));
			addColoredCeilingTorch(ColorDye.white, Items.WHITE_DYE, new MoShizCeilingTorchBlock(Block.Properties.ofFullCopy(Blocks.TORCH), DeferredBlocks.WHITE_TORCH, "moshiz_white_torch"));
			coloredCeilingTorches.forEach((dye, torch) -> helper.register(ResourceLocation.fromNamespaceAndPath(CeilingTorch.MODID, torch.name), torch));
		});
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			Builder<ResourceLocation, Block> builder = ImmutableMap.<ResourceLocation, Block>builder();

			builder.put(DeferredBlocks.FOULITE_TORCH.getId(), fouliteCeilingTorch);
			coloredCeilingTorches.forEach((dye, torch) -> builder.put(getRegistryName(torch.getOriginalBlock()), torch));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	private void addColoredCeilingTorch(int particleColor, Item dye, MoShizCeilingTorchBlock torch) {
		coloredCeilingTorches.put(dye, torch);
		ceilingTorchParticleColors.put(torch, particleColor);
	}
}
