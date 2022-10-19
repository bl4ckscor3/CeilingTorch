package bl4ckscor3.mod.ceilingtorch.compat.ilikewood;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.event.RegistryEvent;
import yamahari.ilikewood.plugin.vanilla.VanillaWoodTypes;
import yamahari.ilikewood.registry.objecttype.WoodenBlockType;
import yamahari.ilikewood.registry.woodtype.IWoodType;
import yamahari.ilikewood.util.Util;

public class ILikeWoodCompat implements ICeilingTorchCompat {
	public static List<WoodenCeilingTorchBlock> ceilingTorchList = new ArrayList<>();
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.OAK, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.SPRUCE, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.BIRCH, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.JUNGLE, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.ACACIA, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.DARK_OAK, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.WARPED, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.CRIMSON, WoodenBlockType.TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.OAK, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.SPRUCE, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.BIRCH, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.JUNGLE, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.ACACIA, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.DARK_OAK, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.WARPED, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.add(new WoodenCeilingTorchBlock(VanillaWoodTypes.CRIMSON, WoodenBlockType.SOUL_TORCH));
		ceilingTorchList.forEach(event.getRegistry()::register);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			Builder<ResourceLocation, Block> builder = ImmutableMap.<ResourceLocation, Block> builder();

			ceilingTorchList.forEach(torch -> builder.put(registryNameOf(torch.woodType, torch.blockType), torch));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	private ResourceLocation registryNameOf(IWoodType woodType, WoodenBlockType blockType) {
		return new ResourceLocation("ilikewood", Util.toRegistryName(woodType.getName(), blockType.getName()));
	}
}
