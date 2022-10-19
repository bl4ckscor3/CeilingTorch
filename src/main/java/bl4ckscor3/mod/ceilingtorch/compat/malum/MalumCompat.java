package bl4ckscor3.mod.ceilingtorch.compat.malum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.sammy.malum.common.item.ether.AbstractEtherItem;
import com.sammy.malum.core.setup.content.block.BlockRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.RightClickBlock;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.registries.RegistryObject;

public class MalumCompat implements ICeilingTorchCompat {
	public static Block etherCeilingTorch;
	public static Block iridescentEtherCeilingTorch;
	public static Block blazingCeilingTorch;
	//@formatter:off
	public static final RegistryObject<BlockEntityType<?>> ETHER_CEILING_TORCH = CeilingTorch.BLOCK_ENTITIES.register("malum_ether_torch", () -> BlockEntityType.Builder.of(CeilingEtherTorchBlockEntity::new,
			etherCeilingTorch,
			iridescentEtherCeilingTorch).build(null));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	public MalumCompat() {
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MalumCompatClient::addListeners);
	}

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(etherCeilingTorch = new CeilingEtherTorchBlock(getProperties(), BlockRegistry.ETHER_TORCH).setRegistryName("malum_ether_torch"));
		event.getRegistry().register(iridescentEtherCeilingTorch = new CeilingEtherTorchBlock(getProperties(), BlockRegistry.IRIDESCENT_ETHER_TORCH).setRegistryName("malum_iridescent_ether_torch"));
		event.getRegistry().register(blazingCeilingTorch = new CeilingTorchBlock(getProperties(), ParticleTypes.FLAME, BlockRegistry.BLAZING_TORCH).setRegistryName("malum_blazing_torch"));
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(BlockRegistry.ETHER_TORCH.get().getRegistryName(), etherCeilingTorch,
					BlockRegistry.IRIDESCENT_ETHER_TORCH.get().getRegistryName(), iridescentEtherCeilingTorch,
					BlockRegistry.BLAZING_TORCH.get().getRegistryName(), blazingCeilingTorch);
			//@formatter:on
		}

		return placeEntries;
	}

	@Override
	public BlockState getStateToPlace(RightClickBlock event, Level level, BlockPos pos, BlockState state, ItemStack stack) {
		if (state.getBlock() == blazingCeilingTorch)
			return state;
		else
			return state.setValue(BlockStateProperties.WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
	}

	@Override
	public void onPlace(RightClickBlock event, BlockPos placeAt, BlockState state) {
		if (event.getWorld().getBlockEntity(placeAt) instanceof CeilingEtherTorchBlockEntity be) {
			ItemStack held = event.getItemStack();
			AbstractEtherItem item = (AbstractEtherItem) held.getItem();

			be.setFirstColor(item.getFirstColor(held));
			be.setSecondColor(item.getSecondColor(held));
		}
	}

	private Block.Properties getProperties() {
		return BlockRegistry.RUNEWOOD_PROPERTIES().noCollission().instabreak().lightLevel(state -> 14);
	}
}
