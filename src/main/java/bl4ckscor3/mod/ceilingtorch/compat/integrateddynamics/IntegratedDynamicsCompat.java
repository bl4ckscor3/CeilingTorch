package bl4ckscor3.mod.ceilingtorch.compat.integrateddynamics;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IntegratedDynamicsCompat implements ICeilingTorchCompat {
	private static final ResourceLocation MENRIL_TORCH_RL = new ResourceLocation("integrateddynamics", "menril_torch");
	private static final ResourceLocation MENRIL_TORCH_STONE_RL = new ResourceLocation("integrateddynamics", "menril_torch_stone");
	public static final RegistryObject<Block> MENRIL_CEILING_TORCH = CeilingTorch.BLOCKS.register("integrateddynamics_menril_torch", () -> new MenrilCeilingTorchBlock(getProperties(SoundType.WOOD), ParticleTypes.FLAME, IntegratedDynamicsCompat::getMenrilTorch));
	public static final RegistryObject<Block> MENRIL_CEILING_TORCH_STONE = CeilingTorch.BLOCKS.register("integrateddynamics_menril_torch_stone", () -> new MenrilCeilingTorchBlock(getProperties(SoundType.STONE), ParticleTypes.FLAME, IntegratedDynamicsCompat::getMenrilTorchStone));
	private static Block menrilTorch;
	private static Block menrilTorchStone;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			//@formatter:off
			placeEntries = ImmutableMap.of(MENRIL_TORCH_RL, MENRIL_CEILING_TORCH.get(),
					MENRIL_TORCH_STONE_RL, MENRIL_CEILING_TORCH_STONE.get());
			//@formatter:on
		}

		return placeEntries;
	}

	private static Properties getProperties(SoundType soundType) {
		return Properties.of().noCollission().strength(0.0F).lightLevel(state -> 14).sound(soundType);
	}

	private static Block getMenrilTorch() {
		if (menrilTorch == null)
			menrilTorch = ForgeRegistries.BLOCKS.getValue(MENRIL_TORCH_RL);

		return menrilTorch;
	}

	private static Block getMenrilTorchStone() {
		if (menrilTorchStone == null)
			menrilTorchStone = ForgeRegistries.BLOCKS.getValue(MENRIL_TORCH_STONE_RL);

		return menrilTorchStone;
	}
}
