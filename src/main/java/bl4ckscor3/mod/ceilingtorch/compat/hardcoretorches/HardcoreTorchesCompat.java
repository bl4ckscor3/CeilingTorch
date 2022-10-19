package bl4ckscor3.mod.ceilingtorch.compat.hardcoretorches;

import java.util.Map;

import com.github.wolfiewaffle.hardcore_torches.config.Config;
import com.github.wolfiewaffle.hardcore_torches.init.BlockInit;
import com.github.wolfiewaffle.hardcore_torches.util.ETorchState;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.RegistryObject;

public class HardcoreTorchesCompat implements ICeilingTorchCompat {
	public static CeilingTorchGroup ceilingTorches = new CeilingTorchGroup();
	//@formatter:off
	public static final RegistryObject<HardcoreCeilingTorchBlock> LIT_CEILING_TORCH = CeilingTorch.BLOCKS.register("hardcore_torches_lit_torch", () -> new HardcoreCeilingTorchBlock(
			properties(14),
			ParticleTypes.FLAME,
			ETorchState.LIT,
			ceilingTorches,
			() -> Config.defaultTorchFuel.get(),
			BlockInit.LIT_TORCH));
	public static final RegistryObject<HardcoreCeilingTorchBlock> UNLIT_CEILING_TORCH = CeilingTorch.BLOCKS.register("hardcore_torches_unlit_torch", () -> new HardcoreCeilingTorchBlock(
			properties(0),
			null,
			ETorchState.UNLIT,
			ceilingTorches,
			() -> Config.defaultTorchFuel.get(),
			BlockInit.UNLIT_TORCH));
	public static final RegistryObject<HardcoreCeilingTorchBlock> SMOLDERING_CEILING_TORCH = CeilingTorch.BLOCKS.register("hardcore_torches_smoldering_torch", () -> new HardcoreCeilingTorchBlock(
			properties(3),
			ParticleTypes.SMOKE,
			ETorchState.SMOLDERING,
			ceilingTorches,
			() -> Config.defaultTorchFuel.get(),
			BlockInit.SMOLDERING_TORCH));
	public static final RegistryObject<HardcoreCeilingTorchBlock> BURNT_CEILING_TORCH = CeilingTorch.BLOCKS.register("hardcore_torches_burnt_torch", () -> new HardcoreCeilingTorchBlock(
			properties(0),
			null,
			ETorchState.BURNT,
			ceilingTorches,
			() -> Config.defaultTorchFuel.get(),
			BlockInit.BURNT_TORCH));
	public static final RegistryObject<BlockEntityType<HardcoreCeilingTorchBlockEntity>> CEILING_TORCH_BLOCK_ENTITY	= CeilingTorch.BLOCK_ENTITIES.register("hardcore_torches_ceiling_torch",
			() -> BlockEntityType.Builder.of(HardcoreCeilingTorchBlockEntity::new, LIT_CEILING_TORCH.get(), UNLIT_CEILING_TORCH.get(), SMOLDERING_CEILING_TORCH.get(), BURNT_CEILING_TORCH.get()).build(null));
	//@formatter:on
	private Map<ResourceLocation, Block> placeEntries;

	public HardcoreTorchesCompat() {
		ceilingTorches.add(ETorchState.LIT, LIT_CEILING_TORCH);
		ceilingTorches.add(ETorchState.UNLIT, UNLIT_CEILING_TORCH);
		ceilingTorches.add(ETorchState.SMOLDERING, SMOLDERING_CEILING_TORCH);
		ceilingTorches.add(ETorchState.BURNT, BURNT_CEILING_TORCH);
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(getRegistryName(BlockInit.LIT_TORCH.get()), LIT_CEILING_TORCH.get(),
					getRegistryName(BlockInit.UNLIT_TORCH.get()), UNLIT_CEILING_TORCH.get(),
					getRegistryName(BlockInit.SMOLDERING_TORCH.get()), SMOLDERING_CEILING_TORCH.get(),
					getRegistryName(BlockInit.BURNT_TORCH.get()), BURNT_CEILING_TORCH.get());
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
