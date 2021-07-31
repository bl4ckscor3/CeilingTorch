package bl4ckscor3.mod.ceilingtorch.compat.torchmaster;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraftforge.event.RegistryEvent;
import net.xalcon.torchmaster.common.ModBlocks;
import net.xalcon.torchmaster.common.blocks.EntityBlockingLightBlock;
import net.xalcon.torchmaster.common.logic.entityblocking.megatorch.MegatorchEntityBlockingLight;

public class TorchmasterCompat implements ICeilingTorchCompat
{
	public static Block megaCeilingTorch;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(megaCeilingTorch = new EntityBlockingLightBlock(Block.Properties.of(Material.WOOD)
				.lootFrom(() -> ModBlocks.blockMegaTorch)
				.strength(1.0F, 1.0F)
				.lightLevel(state -> 15),
				pos -> CeilingTorch.MODID + ":MCT_" + pos.getX() + "_" + pos.getY() + "_" + pos.getZ(),
				MegaCeilingTorchEntityBlockingLight::new, -0.025F, MegatorchEntityBlockingLight.SHAPE) {
			@Override
			public ItemStack getPickBlock(BlockState state, HitResult target, BlockGetter world, BlockPos pos, Player player)
			{
				return new ItemStack(ModBlocks.blockMegaTorch);
			}
		}.setRegistryName(new ResourceLocation(CeilingTorch.MODID, "torchmaster_megatorch")));
	}


	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
			placeEntries = ImmutableMap.of(ModBlocks.itemMegaTorch.getRegistryName(), megaCeilingTorch);

		return placeEntries;
	}
}
