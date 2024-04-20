package bl4ckscor3.mod.ceilingtorch.compat.iceandfire;

import java.util.Map;

import com.github.alexthe666.iceandfire.block.IafBlockRegistry;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.RegistryObject;

public class IceAndFireCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<BurntCeilingTorchBlock> BURNT_CEILING_TORCH = CeilingTorch.BLOCKS.register("iceandfire_burnt_torch", () -> new BurntCeilingTorchBlock(BlockBehaviour.Properties.of()
			.mapColor(MapColor.WOOD)
			.ignitedByLava()
			.lightLevel(state -> 0)
			.sound(SoundType.WOOD)
			.noOcclusion()
			.dynamicShape()
			.noCollission(), IafBlockRegistry.BURNT_TORCH));
	public static final RegistryObject<DreadCeilingTorchBlock> DREAD_CEILING_TORCH = CeilingTorch.BLOCKS.register("iceandfire_dread_torch", () -> new DreadCeilingTorchBlock(BlockBehaviour.Properties.of()
			.mapColor(MapColor.WOOD)
			.instrument(NoteBlockInstrument.BASS)
			.ignitedByLava()
			.lightLevel(state -> 5)
			.sound(SoundType.STONE)
			.noOcclusion()
			.dynamicShape()
			.noCollission(), IafBlockRegistry.DREAD_TORCH));
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if(placeEntries == null)
			placeEntries = Map.of(IafBlockRegistry.DREAD_TORCH.getId(), DREAD_CEILING_TORCH.get(), IafBlockRegistry.BURNT_TORCH.getId(), BURNT_CEILING_TORCH.get());

		return placeEntries;
	}
}
