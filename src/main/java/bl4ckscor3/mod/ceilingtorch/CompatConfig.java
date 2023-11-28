package bl4ckscor3.mod.ceilingtorch;

import java.util.Map;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import bl4ckscor3.mod.ceilingtorch.compat.bonetorch.BoneTorchCompat;
import bl4ckscor3.mod.ceilingtorch.compat.tofucraft.TofuCraftCompat;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.BooleanValue;

public class CompatConfig {
	public static final String FILE_NAME = "ceiling-torch-integrations.toml";
	private static ModConfigSpec configSpec;
	private static CompatConfig config;
	private Map<String, CompatInfo> builtInCompat;

	public static void init(ModLoadingContext ctx) {
		ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
		CommentedFileConfig fileConfig;

		config = new CompatConfig(builder);
		configSpec = builder.build();
		//@formatter:off
		fileConfig = CommentedFileConfig
				.builder(FMLPaths.CONFIGDIR.get().resolve(FILE_NAME))
				.preserveInsertionOrder()
				.writingMode(WritingMode.REPLACE)
				.build();
		//@formatter:on
		fileConfig.load();
		fileConfig.save();
		configSpec.setConfig(fileConfig);
		ctx.registerConfig(ModConfig.Type.COMMON, configSpec, FILE_NAME);
	}

	CompatConfig(ModConfigSpec.Builder builder) {
		//@formatter:off
		builder.comment(
				"This configuration is meant to allow turning off mod integration that has stopped working. All integrations are enabled by default. To disable a specific integration, set the relevant config value to \"false\".",
				"Should you encounter any crashes when using Ceiling Torch, please report them to https://github.com/bl4ckscor3/CeilingTorch/issues",
				"If you turn off a mod integration using this config, you can continue playing without needing an update of Ceiling Torch; However do note that the ceiling torches from that integration will disappear from your world. Note, that if you do not place a block in a space where a ceiling torch was, and then re-enable the respective integration, the torch will reappear.",
				"Turning off integration with a mod that you are not using will not have any effect, as Ceiling Torch automatically checks for the presence of mods it integrates with.");
		builtInCompat = Map.ofEntries(
				makeEntry(builder, "bonetorch", () -> BoneTorchCompat::new),
				makeEntry(builder, "tofucraft", () -> TofuCraftCompat::new));
		//@formatter:on
	}

	private Map.Entry<String, CompatInfo> makeEntry(ModConfigSpec.Builder builder, String modid, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(modid, new CompatInfo(builder.define(modid, true), ceilingTorchCompat));
	}

	private Map.Entry<String, CompatInfo> makeEntry(ModConfigSpec.Builder builder, String modid, String comment, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {
		return Map.entry(modid, new CompatInfo(builder.comment(comment).define(modid, true), ceilingTorchCompat));
	}

	public Map<String, CompatInfo> getBuiltInCompat() {
		return builtInCompat;
	}

	public static CompatConfig getConfig() {
		return config;
	}

	public record CompatInfo(BooleanValue config, Supplier<Supplier<ICeilingTorchCompat>> ceilingTorchCompat) {}
}
