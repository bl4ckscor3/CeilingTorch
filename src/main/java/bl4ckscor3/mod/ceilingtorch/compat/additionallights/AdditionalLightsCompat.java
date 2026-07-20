package bl4ckscor3.mod.ceilingtorch.compat.additionallights;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableMap;
import com.mgen256.al.AdditionalLightsNeoForge;
import com.mgen256.al.FloorTorchSpec;
import com.mgen256.al.NeoForgeFireTypesAdapter;
import com.mgen256.al.blocks.NeoForgeFireTrait;
import com.mgen256.al.items.FireTypeWandTrait;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.neoforged.neoforge.registries.DeferredHolder;

public class AdditionalLightsCompat implements ICeilingTorchCompat {
	private static final HashMap<Supplier<Block>, Supplier<Block>> CEILING_TO_NORMAL = new HashMap<>();
	private Map<Identifier, Block> placeEntries;

	static {
		Supplier<BlockBehaviour.Properties> properties = () -> BlockBehaviour.Properties.of().noCollision().instabreak().lightLevel(state -> state.getValue(NeoForgeFireTrait.FIRE_TYPE).toCore().getLuminance());

		for (FloorTorchSpec block : FloorTorchSpec.values()) {
			DeferredHolder<Block, Block> originalBlock = AdditionalLightsNeoForge.CORE.getBlock(block);
			DeferredHolder<Block, Block> registeredBlock;
			String registryName = "additional_lights_" + block.regName();

			registeredBlock = CeilingTorch.BLOCKS.registerBlock(registryName, p -> new ALCeilingTorchBlock(p, originalBlock), properties);
			CEILING_TO_NORMAL.put(registeredBlock, originalBlock);
		}
	}

	@Override
	public Map<Identifier, Block> getPlaceEntries() {
		if (placeEntries == null) {
			ImmutableMap.Builder<Identifier, Block> builder = ImmutableMap.builder();

			CEILING_TO_NORMAL.forEach((ceiling, normal) -> builder.put(getRegistryName(normal.get()), ceiling.get()));
			placeEntries = builder.build();
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		if (event.getEntity().getOffhandItem().getItem() instanceof FireTypeWandTrait item) {
			if (item.supportsAutomaticPlacementOnTorch()) {
				return state.setValue(NeoForgeFireTrait.FIRE_TYPE, NeoForgeFireTypesAdapter.fromCore(item.getTargetFireType()));
			}
		}

		return state;
	}
}
