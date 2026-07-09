package bl4ckscor3.mod.ceilingtorch.compat.coloredtorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mrbysco.coloredtorches.registry.TorchRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public class ColoredTorchesCompat implements ICeilingTorchCompat {
	public static final DeferredBlock<ColoredCeilingTorchBlock> WHITE_TORCH = registerCeilingTorch(DyeColor.WHITE);
	public static final DeferredBlock<ColoredCeilingTorchBlock> ORANGE_TORCH = registerCeilingTorch(DyeColor.ORANGE);
	public static final DeferredBlock<ColoredCeilingTorchBlock> MAGENTA_TORCH = registerCeilingTorch(DyeColor.MAGENTA);
	public static final DeferredBlock<ColoredCeilingTorchBlock> LIGHT_BLUE_TORCH = registerCeilingTorch(DyeColor.LIGHT_BLUE);
	public static final DeferredBlock<ColoredCeilingTorchBlock> YELLOW_TORCH = registerCeilingTorch(DyeColor.YELLOW);
	public static final DeferredBlock<ColoredCeilingTorchBlock> LIME_TORCH = registerCeilingTorch(DyeColor.LIME);
	public static final DeferredBlock<ColoredCeilingTorchBlock> PINK_TORCH = registerCeilingTorch(DyeColor.PINK);
	public static final DeferredBlock<ColoredCeilingTorchBlock> GRAY_TORCH = registerCeilingTorch(DyeColor.GRAY);
	public static final DeferredBlock<ColoredCeilingTorchBlock> LIGHT_GRAY_TORCH = registerCeilingTorch(DyeColor.LIGHT_GRAY);
	public static final DeferredBlock<ColoredCeilingTorchBlock> CYAN_TORCH = registerCeilingTorch(DyeColor.CYAN);
	public static final DeferredBlock<ColoredCeilingTorchBlock> PURPLE_TORCH = registerCeilingTorch(DyeColor.PURPLE);
	public static final DeferredBlock<ColoredCeilingTorchBlock> BLUE_TORCH = registerCeilingTorch(DyeColor.BLUE);
	public static final DeferredBlock<ColoredCeilingTorchBlock> BROWN_TORCH = registerCeilingTorch(DyeColor.BROWN);
	public static final DeferredBlock<ColoredCeilingTorchBlock> GREEN_TORCH = registerCeilingTorch(DyeColor.GREEN);
	public static final DeferredBlock<ColoredCeilingTorchBlock> RED_TORCH = registerCeilingTorch(DyeColor.RED);
	public static final DeferredBlock<ColoredCeilingTorchBlock> BLACK_TORCH = registerCeilingTorch(DyeColor.BLACK);
	private Map<ResourceLocation, Block> placeEntries;

	private static DeferredBlock<ColoredCeilingTorchBlock> registerCeilingTorch(DyeColor color) {
		String name = color.getName() + "_torch";
		DeferredHolder<Block, Block> coloredTorch = DeferredHolder.create(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath("colored_torches", name)));

		return CeilingTorch.BLOCKS.register("colored_torches_" + name, () -> new ColoredCeilingTorchBlock(prop(), color, coloredTorch));
	}

	private static BlockBehaviour.Properties prop() {
		return BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			ImmutableMap.Builder<ResourceLocation, Block> builder = ImmutableMap.builder();

			builder.put(TorchRegistry.WHITE_TORCH_ITEM.getId(), WHITE_TORCH.get());
			builder.put(TorchRegistry.ORANGE_TORCH_ITEM.getId(), ORANGE_TORCH.get());
			builder.put(TorchRegistry.MAGENTA_TORCH_ITEM.getId(), MAGENTA_TORCH.get());
			builder.put(TorchRegistry.LIGHT_BLUE_TORCH_ITEM.getId(), LIGHT_BLUE_TORCH.get());
			builder.put(TorchRegistry.YELLOW_TORCH_ITEM.getId(), YELLOW_TORCH.get());
			builder.put(TorchRegistry.LIME_TORCH_ITEM.getId(), LIME_TORCH.get());
			builder.put(TorchRegistry.PINK_TORCH_ITEM.getId(), PINK_TORCH.get());
			builder.put(TorchRegistry.GRAY_TORCH_ITEM.getId(), GRAY_TORCH.get());
			builder.put(TorchRegistry.LIGHT_GRAY_TORCH_ITEM.getId(), LIGHT_GRAY_TORCH.get());
			builder.put(TorchRegistry.CYAN_TORCH_ITEM.getId(), CYAN_TORCH.get());
			builder.put(TorchRegistry.PURPLE_TORCH_ITEM.getId(), PURPLE_TORCH.get());
			builder.put(TorchRegistry.BLUE_TORCH_ITEM.getId(), BLUE_TORCH.get());
			builder.put(TorchRegistry.BROWN_TORCH_ITEM.getId(), BROWN_TORCH.get());
			builder.put(TorchRegistry.GREEN_TORCH_ITEM.getId(), GREEN_TORCH.get());
			builder.put(TorchRegistry.RED_TORCH_ITEM.getId(), RED_TORCH.get());
			builder.put(TorchRegistry.BLACK_TORCH_ITEM.getId(), BLACK_TORCH.get());
			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
