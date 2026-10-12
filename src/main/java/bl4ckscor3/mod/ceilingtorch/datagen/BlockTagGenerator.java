package bl4ckscor3.mod.ceilingtorch.datagen;

import java.util.concurrent.CompletableFuture;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.registries.DeferredHolder;

public class BlockTagGenerator extends VanillaBlockTagsProvider {
	public BlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(output, lookupProvider);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(BlockTags.WASHED_AWAY_BY_FLUIDS).addAll(CeilingTorch.BLOCKS.getEntries().stream().map(DeferredHolder::key));
	}
}
