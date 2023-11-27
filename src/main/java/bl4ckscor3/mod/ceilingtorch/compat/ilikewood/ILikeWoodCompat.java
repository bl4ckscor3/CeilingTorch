package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

public class ILikeWoodCompat implements ICeilingTorchCompat {
	public static List<Supplier<WoodenCeilingTorchBlock>> ceilingTorchList = new ArrayList<>();
	private Map<ResourceLocation, Block> placeEntries;

	static {
		List<Pair<IWoodType, WoodenBlockType>> toRegister = new ArrayList<>();

		toRegister.add(Pair.of(VanillaWoodTypes.OAK, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.SPRUCE, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.BIRCH, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.JUNGLE, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.ACACIA, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.DARK_OAK, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.WARPED, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.CRIMSON, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.MANGROVE, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.BAMBOO, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.CHERRY, WoodenBlockType.TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.OAK, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.SPRUCE, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.BIRCH, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.JUNGLE, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.ACACIA, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.DARK_OAK, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.WARPED, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.CRIMSON, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.MANGROVE, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.BAMBOO, WoodenBlockType.SOUL_TORCH));
		toRegister.add(Pair.of(VanillaWoodTypes.CHERRY, WoodenBlockType.SOUL_TORCH));
		//@formatter:off
		toRegister.stream()
		.map(pair -> CeilingTorch.BLOCKS.register("ilikewood_" + pair.getLeft().getName() + "_" + pair.getRight().getName(), () -> new WoodenCeilingTorchBlock(pair.getLeft(), pair.getRight())))
		.forEach(ceilingTorchList::add);
		//@formatter:on
		toRegister = null;
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			Builder<ResourceLocation, Block> builder = ImmutableMap.<ResourceLocation, Block>builder();

			ceilingTorchList.forEach(torchSupplier -> {
				WoodenCeilingTorchBlock torch = torchSupplier.get();

				builder.put(registryNameOf(torch.woodType, torch.blockType), torch);
			});
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	private ResourceLocation registryNameOf(IWoodType woodType, WoodenBlockType blockType) {
		return new ResourceLocation("ilikewood", Util.toRegistryName(woodType.getName(), blockType.getName()));
	}
}
