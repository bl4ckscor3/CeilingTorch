package bl4ckscor3.mod.ceilingtorch.compat.chipped;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.RedstoneCeilingTorchBlock;
import earth.terrarium.chipped.common.palette.Palette;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ChippedCompat implements ICeilingTorchCompat {
	public static final List<RegistryObject<CeilingTorchBlock>> SUPPORTED_TORCHES = new ArrayList<>();
	public static final List<RegistryObject<RedstoneCeilingTorchBlock>> SUPPORTED_REDSTONE_TORCHES = new ArrayList<>();
	private Map<ResourceLocation, Block> placeEntries;

	public ChippedCompat() {
		for (String name : Palette.TORCH.getNames()) {
			String torchName = String.format(name, "torch");
			RegistryObject<Block> chippedBlock = RegistryObject.create(new ResourceLocation("chipped", torchName), ForgeRegistries.BLOCKS);
			RegistryObject<CeilingTorchBlock> ceilingBlock = CeilingTorch.BLOCKS.register("chipped_" + torchName, () -> new CeilingTorchBlock(Block.Properties.copy(Blocks.TORCH), ParticleTypes.FLAME, chippedBlock));

			SUPPORTED_TORCHES.add(ceilingBlock);
		}

		for (String name : Palette.REDSTONE_TORCH.getNames()) {
			String torchName = String.format(name, "redstone_torch");
			RegistryObject<Block> chippedBlock = RegistryObject.create(new ResourceLocation("chipped", torchName), ForgeRegistries.BLOCKS);
			RegistryObject<RedstoneCeilingTorchBlock> ceilingBlock = CeilingTorch.BLOCKS.register("chipped_" + torchName, () -> new RedstoneCeilingTorchBlock(Block.Properties.copy(Blocks.REDSTONE_TORCH), chippedBlock));

			SUPPORTED_REDSTONE_TORCHES.add(ceilingBlock);
		}
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			ImmutableMap.Builder<ResourceLocation, Block> builder = ImmutableMap.builder();

			SUPPORTED_TORCHES.forEach(torch -> builder.put(getRegistryName(torch.get().getOriginalBlock()), torch.get()));
			SUPPORTED_REDSTONE_TORCHES.forEach(torch -> builder.put(getRegistryName(torch.get().getOriginalBlock()), torch.get()));
			placeEntries = builder.build();
		}

		return placeEntries;
	}
}
