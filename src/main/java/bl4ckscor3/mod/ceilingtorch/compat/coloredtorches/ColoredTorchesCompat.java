package bl4ckscor3.mod.ceilingtorch.compat.coloredtorches;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.mrbysco.coloredtorches.registry.TorchRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ColoredTorchesCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> WHITE_TORCH = registerCeilingTorch(DyeColor.WHITE);
	public static final RegistryObject<Block> ORANGE_TORCH = registerCeilingTorch(DyeColor.ORANGE);
	public static final RegistryObject<Block> MAGENTA_TORCH = registerCeilingTorch(DyeColor.MAGENTA);
	public static final RegistryObject<Block> LIGHT_BLUE_TORCH = registerCeilingTorch(DyeColor.LIGHT_BLUE);
	public static final RegistryObject<Block> YELLOW_TORCH = registerCeilingTorch(DyeColor.YELLOW);
	public static final RegistryObject<Block> LIME_TORCH = registerCeilingTorch(DyeColor.LIME);
	public static final RegistryObject<Block> PINK_TORCH = registerCeilingTorch(DyeColor.PINK);
	public static final RegistryObject<Block> GRAY_TORCH = registerCeilingTorch(DyeColor.GRAY);
	public static final RegistryObject<Block> LIGHT_GRAY_TORCH = registerCeilingTorch(DyeColor.LIGHT_GRAY);
	public static final RegistryObject<Block> CYAN_TORCH = registerCeilingTorch(DyeColor.CYAN);
	public static final RegistryObject<Block> PURPLE_TORCH = registerCeilingTorch(DyeColor.PURPLE);
	public static final RegistryObject<Block> BLUE_TORCH = registerCeilingTorch(DyeColor.BLUE);
	public static final RegistryObject<Block> BROWN_TORCH = registerCeilingTorch(DyeColor.BROWN);
	public static final RegistryObject<Block> GREEN_TORCH = registerCeilingTorch(DyeColor.GREEN);
	public static final RegistryObject<Block> RED_TORCH = registerCeilingTorch(DyeColor.RED);
	public static final RegistryObject<Block> BLACK_TORCH = registerCeilingTorch(DyeColor.BLACK);
	private Map<ResourceLocation, Block> placeEntries;

	private static RegistryObject<Block> registerCeilingTorch(DyeColor color) {
		String name = color.getName() + "_torch";
		RegistryObject<Block> coloredTorch = RegistryObject.create(new ResourceLocation("colored_torches", name), ForgeRegistries.BLOCKS);

		return CeilingTorch.BLOCKS.register("colored_torches_" + name, () -> new ColoredCeilingTorchBlock(prop(), color, coloredTorch));
	}

	private static BlockBehaviour.Properties prop() {
		return BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(state -> 14).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			ImmutableMap.Builder<ResourceLocation, Block> builder = ImmutableMap.builder();

			builder.put(TorchRegistry.WHITE_TORCH.getId(), WHITE_TORCH.get());
			builder.put(TorchRegistry.ORANGE_TORCH.getId(), ORANGE_TORCH.get());
			builder.put(TorchRegistry.MAGENTA_TORCH.getId(), MAGENTA_TORCH.get());
			builder.put(TorchRegistry.LIGHT_BLUE_TORCH.getId(), LIGHT_BLUE_TORCH.get());
			builder.put(TorchRegistry.YELLOW_TORCH.getId(), YELLOW_TORCH.get());
			builder.put(TorchRegistry.LIME_TORCH.getId(), LIME_TORCH.get());
			builder.put(TorchRegistry.PINK_TORCH.getId(), PINK_TORCH.get());
			builder.put(TorchRegistry.GRAY_TORCH.getId(), GRAY_TORCH.get());
			builder.put(TorchRegistry.LIGHT_GRAY_TORCH.getId(), LIGHT_GRAY_TORCH.get());
			builder.put(TorchRegistry.CYAN_TORCH.getId(), CYAN_TORCH.get());
			builder.put(TorchRegistry.PURPLE_TORCH.getId(), PURPLE_TORCH.get());
			builder.put(TorchRegistry.BLUE_TORCH.getId(), BLUE_TORCH.get());
			builder.put(TorchRegistry.BROWN_TORCH.getId(), BROWN_TORCH.get());
			builder.put(TorchRegistry.GREEN_TORCH.getId(), GREEN_TORCH.get());
			builder.put(TorchRegistry.RED_TORCH.getId(), RED_TORCH.get());
			builder.put(TorchRegistry.BLACK_TORCH.getId(), BLACK_TORCH.get());
			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
