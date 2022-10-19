package bl4ckscor3.mod.ceilingtorch.compat.projecte;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import moze_intel.projecte.gameObjs.block_entities.InterdictionTorchBlockEntity;
import moze_intel.projecte.gameObjs.registries.PEBlocks;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.RegistryObject;

public class ProjectECompat implements ICeilingTorchCompat {
	public static Block interdictionCeilingTorch;
	public static final RegistryObject<BlockEntityType<? extends InterdictionTorchBlockEntity>> INTERDICTION_CEILING_TORCH_BLOCK_ENTITY = CeilingTorch.BLOCK_ENTITIES.register("projecte_interdiction_torch", () -> BlockEntityType.Builder.of(InterdictionCeilingTorchBlockEntity::new, interdictionCeilingTorch).build(null));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(interdictionCeilingTorch = new InterdictionCeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.strength(0.0F)
				.lightLevel(state -> 14)
				.randomTicks(), ParticleTypes.SOUL_FIRE_FLAME, () -> PEBlocks.INTERDICTION_TORCH.getBlock())
				.setRegistryName("projecte_interdiction_torch"));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(PEBlocks.INTERDICTION_TORCH.getBlock().getRegistryName(), interdictionCeilingTorch);

		return placeEntries;
	}
}
