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
import net.minecraftforge.registries.RegistryObject;

public class BoneTorchCompat implements ICeilingTorchCompat
{
	public static final RegistryObject<CeilingTorchBlock> CEILING_BONE_TORCH = CeilingTorch.BLOCKS.register("bonetorch_bonetorch", () -> new CeilingTorchBlock(Block.Properties.of(Material.DECORATION)
			.noCollission()
			.strength(0.0F)
			.lightLevel(state -> 14)
			.sound(SoundType.WOOD),
			ParticleTypes.FLAME, BoneTorchMod.BONETORCH));
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(BoneTorchMod.BONETORCH.get()), CEILING_BONE_TORCH.get());

		return placeEntries;
	}
}
