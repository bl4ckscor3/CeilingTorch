package bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches;

import java.util.Map;

import com.github.wolfiewaffle.hardcore_torches.config.Config;
import com.github.wolfiewaffle.hardcore_torches.init.BlockInit;
import com.github.wolfiewaffle.hardcore_torches.util.ETorchState;
import com.github.wolfiewaffle.hardcore_torches.util.TorchGroup;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.registries.RegistryObject;

public class HardcoreTorchesCompat implements ICeilingTorchCompat {
	public static TorchGroup ceilingTorches = new TorchGroup("ceiling");
	public static Block litCeilingTorch, unlitCeilingTorch, smolderingCeilingTorch, burntCeilingTorch;
	//@formatter:off
	public static final RegistryObject<BlockEntityType<HardcoreCeilingTorchBlockEntity>> CEILING_TORCH_BLOCK_ENTITY	= CeilingTorch.BLOCK_ENTITIES.register("hardcore_torches_ceiling_torch",
			() -> BlockEntityType.Builder.of(HardcoreCeilingTorchBlockEntity::new, litCeilingTorch, unlitCeilingTorch, smolderingCeilingTorch, burntCeilingTorch).build(null));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(litCeilingTorch = new HardcoreCeilingTorchBlock(
				properties(14),
				ParticleTypes.FLAME,
				ETorchState.LIT,
				ceilingTorches,
				() -> Config.defaultTorchFuel.get(),
				BlockInit.LIT_TORCH)
				.setRegistryName("hardcore_torches_lit_torch"));
		event.getRegistry().register(unlitCeilingTorch = new HardcoreCeilingTorchBlock(
				properties(0),
				null,
				ETorchState.UNLIT,
				ceilingTorches,
				() -> Config.defaultTorchFuel.get(),
				BlockInit.UNLIT_TORCH)
				.setRegistryName("hardcore_torches_unlit_torch"));
		event.getRegistry().register(smolderingCeilingTorch = new HardcoreCeilingTorchBlock(
				properties(3),
				ParticleTypes.SMOKE,
				ETorchState.SMOLDERING,
				ceilingTorches,
				() -> Config.defaultTorchFuel.get(),
				BlockInit.SMOLDERING_TORCH)
				.setRegistryName("hardcore_torches_smoldering_torch"));
		event.getRegistry().register(burntCeilingTorch = new HardcoreCeilingTorchBlock(
				properties(0),
				null,
				ETorchState.BURNT,
				ceilingTorches,
				() -> Config.defaultTorchFuel.get(),
				BlockInit.BURNT_TORCH)
				.setRegistryName("hardcore_torches_burnt_torch"));
		//@formatter:on
		ceilingTorches.add(litCeilingTorch);
		ceilingTorches.add(unlitCeilingTorch);
		ceilingTorches.add(smolderingCeilingTorch);
		ceilingTorches.add(burntCeilingTorch);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(BlockInit.LIT_TORCH.get().getRegistryName(), litCeilingTorch,
					BlockInit.UNLIT_TORCH.get().getRegistryName(), unlitCeilingTorch,
					BlockInit.SMOLDERING_TORCH.get().getRegistryName(), smolderingCeilingTorch,
					BlockInit.BURNT_TORCH.get().getRegistryName(), burntCeilingTorch);
			//@formatter:on
		}

		return placeEntries;
	}

	private static Block.Properties properties(int lightLevel) {
		Block.Properties properties = Block.Properties.of(Material.DECORATION).noCollission().instabreak().sound(SoundType.WOOD).noOcclusion();

		if (lightLevel > 0)
			properties.lightLevel(state -> lightLevel);

		return properties;
	}
}
