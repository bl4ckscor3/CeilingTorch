package bl4ckscor3.mod.ceilingtorch.compat.upgradeaquatic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teamabnormals.upgrade_aquatic.common.block.JellyTorchBlock.JellyTorchType;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class UpgradeAquaticCompat implements ICeilingTorchCompat {
	protected static final List<JellyCeilingTorchBlock> CEILING_TORCHES = new ArrayList<>();
	private Map<ResourceLocation, Block> placeEntries;

	public UpgradeAquaticCompat() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(UpgradeAquaticCompatClient::onFMLClientSetup);
	}

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		ImmutableMap.Builder<ResourceLocation, Block> builder = ImmutableMap.builder();

		for (JellyTorchType type : JellyTorchType.values()) {
			JellyCeilingTorchBlock ceilingTorch = new JellyCeilingTorchBlock(Block.Properties.copy(Blocks.TORCH).sound(SoundType.METAL), type, type.torch);
			String typeName = type.name().toLowerCase();

			ceilingTorch.setRegistryName(new ResourceLocation(CeilingTorch.MODID, String.format("upgrade_aquatic_%s_jelly_torch", typeName)));
			builder.put(new ResourceLocation("upgrade_aquatic", typeName + "_jelly_torch"), ceilingTorch);
			event.getRegistry().register(ceilingTorch);
			CEILING_TORCHES.add(ceilingTorch);
		}

		placeEntries = builder.build();
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		return placeEntries;
	}

	@Override
	public boolean hasCutoutMippedRenderType(Block block) {
		return false;
	}
}
