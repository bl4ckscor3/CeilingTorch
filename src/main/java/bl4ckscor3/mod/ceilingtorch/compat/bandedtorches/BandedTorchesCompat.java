package bl4ckscor3.mod.ceilingtorch.compat.bandedtorches;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.util.TriConsumer;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.vanilla.CeilingTorchBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;
import pixelgamewizard.pgwbandedtorches.common.Constants;
import pixelgamewizard.pgwbandedtorches.common.Constants.TorchProperties;

public class BandedTorchesCompat implements ICeilingTorchCompat {
	private Map<ResourceLocation, Block> placeEntries;

	public BandedTorchesCompat() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerBlocks);
	}

	public void registerBlocks(RegisterEvent event) {
		event.register(Registries.BLOCK, helper -> {
			forEachBandedTorch((properties, torchName, bandedTorch) -> {
				helper.register(ceilingTorchId(torchName), new CeilingTorchBlock(prop(properties), properties.particleType, bandedTorch));
			});
		});
	}

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null) {
			Map<ResourceLocation, Block> temp = new HashMap<>();

			forEachBandedTorch((properties, torchName, bandedTorch) -> {
				RegistryObject<Block> ceilingTorch = RegistryObject.create(ceilingTorchId(torchName), ForgeRegistries.BLOCKS);

				temp.put(bandedTorchId(torchName), ceilingTorch.get());
			});
			placeEntries = ImmutableMap.copyOf(temp);
		}

		return placeEntries;
	}

	private void forEachBandedTorch(TriConsumer<TorchProperties, String, RegistryObject<Block>> consumer) {
		for (TorchProperties properties : Constants.TORCH_PROPERTIES_ARRAY) {
			for (String color : Constants.COLOUR_ARRAY) {
				String torchName = String.format("banded_%s_%s", properties.name, color);
				RegistryObject<Block> bandedTorch = RegistryObject.create(bandedTorchId(torchName), ForgeRegistries.BLOCKS);

				consumer.accept(properties, torchName, bandedTorch);
			}
		}
	}

	private ResourceLocation bandedTorchId(String torchName) {
		return new ResourceLocation("pgwbandedtorches", torchName);
	}

	private ResourceLocation ceilingTorchId(String torchName) {
		return new ResourceLocation(CeilingTorch.MODID, "pgwbandedtorches_" + torchName);
	}

	private BlockBehaviour.Properties prop(TorchProperties properties) {
		return BlockBehaviour.Properties.of().noCollission().instabreak().lightLevel(state -> properties.lightLevel).sound(SoundType.WOOD);
	}
}
