package bl4ckscor3.mod.ceilingtorch.compat.bonetorch;

import java.util.Map;

import com.builtbroken.bonetorch.BoneTorchMod;
import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.event.RegistryEvent;

public class BoneTorchCompat implements ICeilingTorchCompat {
	public static Block ceilingBoneTorch;
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event) {
		//@formatter:off
		event.getRegistry().register(ceilingBoneTorch = new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
				.noCollission()
				.strength(0.0F)
				.lightLevel(state -> 14)
				.sound(SoundType.WOOD),
				ParticleTypes.FLAME, () ->  BoneTorchMod.blockTorch).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "bonetorch_bonetorch")));
		//@formatter:on
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(BoneTorchMod.blockTorch.getRegistryName(), ceilingBoneTorch);

		return placeEntries;
	}
}
