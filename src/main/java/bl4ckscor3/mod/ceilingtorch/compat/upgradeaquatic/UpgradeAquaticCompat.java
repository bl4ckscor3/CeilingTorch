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
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;

public class UpgradeAquaticCompat implements ICeilingTorchCompat {
	protected static final List<DeferredBlock<JellyCeilingTorchBlock>> CEILING_TORCHES = new ArrayList<>();
	private Map<ResourceLocation, Block> placeEntries;

	public UpgradeAquaticCompat() {
		for (JellyTorchType type : JellyTorchType.values()) {
			String typeName = type.name().toLowerCase();
			DeferredBlock<JellyCeilingTorchBlock> ceilingTorch = CeilingTorch.BLOCKS.register(String.format("upgrade_aquatic_%s_jelly_torch", typeName), () -> new JellyCeilingTorchBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.TORCH).sound(SoundType.METAL), type, type.torch));

			CEILING_TORCHES.add(ceilingTorch);
		}
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			ImmutableMap.Builder<ResourceLocation, Block> builder = ImmutableMap.builder();

			CEILING_TORCHES.forEach(ro -> builder.put(ResourceLocation.parse(ro.getId().getPath().replace("upgrade_aquatic_", "upgrade_aquatic:")), ro.get()));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	@Override
	public TorchRenderType renderType(Block block) {
		return TorchRenderType.TRANSLUCENT;
	}
}
