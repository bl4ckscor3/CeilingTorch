package bl4ckscor3.mod.ceilingtorch.compat.atum;

import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.teammetallurgy.atum.api.God;
import com.teammetallurgy.atum.init.AtumBlocks;

import bl4ckscor3.mod.ceilingtorch.CeilingTorch;
import bl4ckscor3.mod.ceilingtorch.ICeilingTorchCompat;
import net.minecraft.world.level.block.Block;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;

public class AtumCompat implements ICeilingTorchCompat
{
	public static Block palmCeilingTorch;
	public static Block deadwoodCeilingTorch;
	public static Block limestoneCeilingTorch;
	public static Block boneCeilingTorch;
	public static Block nebuCeilingTorch;
	public static Block ceilingTorchOfAnput;
	public static Block ceilingTorchOfAnubis;
	public static Block ceilingTorchOfAtem;
	public static Block ceilingTorchOfGeb;
	public static Block ceilingTorchOfHorus;
	public static Block ceilingTorchOfIsis;
	public static Block ceilingTorchOfMontu;
	public static Block ceilingTorchOfNepthys;
	public static Block ceilingTorchOfNuit;
	public static Block ceilingTorchOfOsiris;
	public static Block ceilingTorchOfPtah;
	public static Block ceilingTorchOfRa;
	public static Block ceilingTorchOfSeth;
	public static Block ceilingTorchOfShu;
	public static Block ceilingTorchOfTefnut;
	private Map<ResourceLocation,Block> placeEntries;

	@Override
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().register(palmCeilingTorch = new AtumCeilingTorchBlock(14, () -> AtumBlocks.PALM_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_palm_torch")));
		event.getRegistry().register(deadwoodCeilingTorch = new AtumCeilingTorchBlock(14, () -> AtumBlocks.DEADWOOD_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_deadwood_torch")));
		event.getRegistry().register(limestoneCeilingTorch = new AtumCeilingTorchBlock(14, () -> AtumBlocks.LIMESTONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_limestone_torch")));
		event.getRegistry().register(boneCeilingTorch = new AtumCeilingTorchBlock(14, () -> AtumBlocks.BONE_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_bone_torch")));
		event.getRegistry().register(nebuCeilingTorch = new AtumCeilingTorchBlock(null, () -> AtumBlocks.NEBU_TORCH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_nebu_torch")));
		event.getRegistry().register(ceilingTorchOfAnput = new AtumCeilingTorchBlock(God.ANPUT, () -> AtumBlocks.TORCH_OF_ANPUT).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_anput")));
		event.getRegistry().register(ceilingTorchOfAnubis = new AtumCeilingTorchBlock(God.ANUBIS, () -> AtumBlocks.TORCH_OF_ANUBIS).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_anubis")));
		event.getRegistry().register(ceilingTorchOfAtem = new AtumCeilingTorchBlock(God.ATEM, () -> AtumBlocks.TORCH_OF_ATEM).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_atem")));
		event.getRegistry().register(ceilingTorchOfGeb = new AtumCeilingTorchBlock(God.GEB, () -> AtumBlocks.TORCH_OF_GEB).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_geb")));
		event.getRegistry().register(ceilingTorchOfHorus = new AtumCeilingTorchBlock(God.HORUS, () -> AtumBlocks.TORCH_OF_HORUS).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_horus")));
		event.getRegistry().register(ceilingTorchOfIsis = new AtumCeilingTorchBlock(God.ISIS, () -> AtumBlocks.TORCH_OF_ISIS).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_isis")));
		event.getRegistry().register(ceilingTorchOfMontu = new AtumCeilingTorchBlock(God.MONTU, () -> AtumBlocks.TORCH_OF_MONTU).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_montu")));
		event.getRegistry().register(ceilingTorchOfNepthys = new AtumCeilingTorchBlock(God.NEPTHYS, () -> AtumBlocks.TORCH_OF_NEPTHYS).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_nepthys")));
		event.getRegistry().register(ceilingTorchOfNuit = new AtumCeilingTorchBlock(God.NUIT, () -> AtumBlocks.TORCH_OF_NUIT).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_nuit")));
		event.getRegistry().register(ceilingTorchOfOsiris = new AtumCeilingTorchBlock(God.OSIRIS, () -> AtumBlocks.TORCH_OF_OSIRIS).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_osiris")));
		event.getRegistry().register(ceilingTorchOfPtah = new AtumCeilingTorchBlock(God.PTAH, () -> AtumBlocks.TORCH_OF_PTAH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_ptah")));
		event.getRegistry().register(ceilingTorchOfRa = new AtumCeilingTorchBlock(God.RA, () -> AtumBlocks.TORCH_OF_RA).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_ra")));
		event.getRegistry().register(ceilingTorchOfSeth = new AtumCeilingTorchBlock(God.SETH, () -> AtumBlocks.TORCH_OF_SETH).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_seth")));
		event.getRegistry().register(ceilingTorchOfShu = new AtumCeilingTorchBlock(God.SHU, () -> AtumBlocks.TORCH_OF_SHU).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_shu")));
		event.getRegistry().register(ceilingTorchOfTefnut = new AtumCeilingTorchBlock(God.TEFNUT, () -> AtumBlocks.TORCH_OF_TEFNUT).setRegistryName(new ResourceLocation(CeilingTorch.MODID, "atum_torch_of_tefnut")));
	}

	@Override
	public Map<ResourceLocation,Block> getPlaceEntries()
	{
		if(placeEntries == null)
		{
			placeEntries = ImmutableMap.<ResourceLocation,Block>builder()
					.put(AtumBlocks.PALM_TORCH.getRegistryName(), palmCeilingTorch)
					.put(AtumBlocks.DEADWOOD_TORCH.getRegistryName(), deadwoodCeilingTorch)
					.put(AtumBlocks.LIMESTONE_TORCH.getRegistryName(), limestoneCeilingTorch)
					.put(AtumBlocks.BONE_TORCH.getRegistryName(), boneCeilingTorch)
					.put(AtumBlocks.NEBU_TORCH.getRegistryName(), nebuCeilingTorch)
					.put(AtumBlocks.TORCH_OF_ANPUT.getRegistryName(), ceilingTorchOfAnput)
					.put(AtumBlocks.TORCH_OF_ANUBIS.getRegistryName(), ceilingTorchOfAnubis)
					.put(AtumBlocks.TORCH_OF_ATEM.getRegistryName(), ceilingTorchOfAtem)
					.put(AtumBlocks.TORCH_OF_GEB.getRegistryName(), ceilingTorchOfGeb)
					.put(AtumBlocks.TORCH_OF_HORUS.getRegistryName(), ceilingTorchOfHorus)
					.put(AtumBlocks.TORCH_OF_ISIS.getRegistryName(), ceilingTorchOfIsis)
					.put(AtumBlocks.TORCH_OF_MONTU.getRegistryName(), ceilingTorchOfMontu)
					.put(AtumBlocks.TORCH_OF_NEPTHYS.getRegistryName(), ceilingTorchOfNepthys)
					.put(AtumBlocks.TORCH_OF_NUIT.getRegistryName(), ceilingTorchOfNuit)
					.put(AtumBlocks.TORCH_OF_OSIRIS.getRegistryName(), ceilingTorchOfOsiris)
					.put(AtumBlocks.TORCH_OF_PTAH.getRegistryName(), ceilingTorchOfPtah)
					.put(AtumBlocks.TORCH_OF_RA.getRegistryName(), ceilingTorchOfRa)
					.put(AtumBlocks.TORCH_OF_SETH.getRegistryName(), ceilingTorchOfSeth)
					.put(AtumBlocks.TORCH_OF_SHU.getRegistryName(), ceilingTorchOfShu)
					.put(AtumBlocks.TORCH_OF_TEFNUT.getRegistryName(), ceilingTorchOfTefnut)
					.build();
		}

		return placeEntries;
	}
}
