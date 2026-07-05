package bl4ckscor3.mod.ceilingtorch.compat.extlights;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.polyvalord.extlights.blocks.AllBlocks;
import com.polyvalord.extlights.blocks.BulbLampBlock;
import com.polyvalord.extlights.blocks.GothicLampBlock;
import com.polyvalord.extlights.blocks.ModernLightTorchBlock;
import com.polyvalord.extlights.blocks.ModernLightTowerBlock;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.RegistryObject;

public class ExtLightsCompat implements ICeilingTorchCompat {
	public static final RegistryObject<Block> AMETHYST_GOTHIC_CEILING_LAMP_IRON = CeilingTorch.BLOCKS.register(
		"extlights_amethyst_gothic_lamp_iron",
		() -> new UpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.METAL)
				.lightLevel(state -> 15)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.AMETHYST_GOTHIC_LAMP_IRON,
			GothicLampBlock::makeShape
		)
	);
	public static final RegistryObject<Block> BULB_CEILING_LAMP_IRON = CeilingTorch.BLOCKS.register(
		"extlights_bulb_lamp_iron",
		() -> new UpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.METAL)
				.lightLevel(state -> 15)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.BULB_LAMP_IRON,
			BulbLampBlock::makeShape
		)
	);
	public static final RegistryObject<Block> MODERN_LIGHT_CEILING_TORCH_BLACK = CeilingTorch.BLOCKS.register(
		"extlights_modern_light_torch_black",
		() -> new ToggleableUpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.MODERN_LIGHT_TORCH_BLACK,
			ModernLightTorchBlock::makeShape
		)
	);
	public static final RegistryObject<Block> MODERN_LIGHT_CEILING_TORCH_WHITE = CeilingTorch.BLOCKS.register(
		"extlights_modern_light_torch_white",
		() -> new ToggleableUpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.MODERN_LIGHT_TORCH_WHITE,
			ModernLightTorchBlock::makeShape
		)
	);
	public static final RegistryObject<Block> MODERN_LIGHT_CEILING_TOWER_BLACK = CeilingTorch.BLOCKS.register(
		"extlights_modern_light_tower_black",
		() -> new ToggleableUpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.MODERN_LIGHT_TOWER_BLACK,
			ModernLightTowerBlock::makeShape
		)
	);
	public static final RegistryObject<Block> MODERN_LIGHT_CEILING_TOWER_WHITE = CeilingTorch.BLOCKS.register(
		"extlights_modern_light_tower_white",
		() -> new ToggleableUpsideDownGroundBlock(
			BlockBehaviour.Properties.of()
				.sound(SoundType.STONE)
				.lightLevel(state -> state.getValue(BlockStateProperties.LIT) ? 15 : 0)
				.noOcclusion()
				.pushReaction(PushReaction.DESTROY),
			AllBlocks.MODERN_LIGHT_TOWER_WHITE,
			ModernLightTowerBlock::makeShape
		)
	);
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			placeEntries = ImmutableMap.of(
				getRegistryName(AllBlocks.AMETHYST_GOTHIC_LAMP_IRON.get()), AMETHYST_GOTHIC_CEILING_LAMP_IRON.get(),
				getRegistryName(AllBlocks.BULB_LAMP_IRON.get()), BULB_CEILING_LAMP_IRON.get(),
				getRegistryName(AllBlocks.MODERN_LIGHT_TORCH_BLACK.get()), MODERN_LIGHT_CEILING_TORCH_BLACK.get(),
				getRegistryName(AllBlocks.MODERN_LIGHT_TORCH_WHITE.get()), MODERN_LIGHT_CEILING_TORCH_WHITE.get(),
				getRegistryName(AllBlocks.MODERN_LIGHT_TOWER_BLACK.get()), MODERN_LIGHT_CEILING_TOWER_BLACK.get(),
				getRegistryName(AllBlocks.MODERN_LIGHT_TOWER_WHITE.get()), MODERN_LIGHT_CEILING_TOWER_WHITE.get()
			);
		}

		return placeEntries;
	}
}
