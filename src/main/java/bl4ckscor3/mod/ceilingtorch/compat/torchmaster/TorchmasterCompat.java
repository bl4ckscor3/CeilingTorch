package bl4ckscor3.mod.ceilingtorch.compat.torchmaster;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.HitResult;
import net.neoforged.neoforge.registries.RegistryObject;
import net.xalcon.torchmaster.common.ModBlocks;
import net.xalcon.torchmaster.common.blocks.EntityBlockingLightBlock;
import net.xalcon.torchmaster.common.logic.entityblocking.megatorch.MegatorchEntityBlockingLight;

public class TorchmasterCompat implements ICeilingTorchCompat {
	//@formatter:off
	public static final RegistryObject<EntityBlockingLightBlock> MEGA_CEILING_TORCH = CeilingTorch.BLOCKS.register("torchmaster_megatorch", () -> new EntityBlockingLightBlock(Block.Properties.of()
			.lootFrom(ModBlocks.blockMegaTorch)
			.strength(1.0F, 1.0F)
			.lightLevel(state -> 15)
			.sound(SoundType.WOOD),
			pos -> CeilingTorch.MODID + ":MCT_" + pos.getX() + "_" + pos.getY() + "_" + pos.getZ(),
			MegaCeilingTorchEntityBlockingLight::new, -0.025F, MegatorchEntityBlockingLight.SHAPE) {
	//@formatter:on
		@Override
		public ItemStack getCloneItemStack(BlockState state, HitResult target, BlockGetter level, BlockPos pos, Player player) {
			return new ItemStack(ModBlocks.blockMegaTorch.get());
		}
	});
	private Map<ResourceLocation, Block> placeEntries;

	@Override
	public Map<ResourceLocation, Block> getPlaceEntries() {
		if (placeEntries == null)
			placeEntries = ImmutableMap.of(getRegistryName(ModBlocks.itemMegaTorch.get()), MEGA_CEILING_TORCH.get());

		return placeEntries;
	}
}
