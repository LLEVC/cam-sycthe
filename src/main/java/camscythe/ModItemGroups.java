package camscythe;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final RegistryKey<ItemGroup> CAMSCYTHES_GROUP_KEY = RegistryKey.of(
            RegistryKeys.ITEM_GROUP, Identifier.of("camscythe", "camscythes")
    );

    public static void initialize() {
        Registry.register(Registries.ITEM_GROUP, CAMSCYTHES_GROUP_KEY,
                FabricItemGroup.builder()
                        .displayName(Text.translatable("itemGroup.camscythe.camscythes"))
                        .icon(() -> new ItemStack(ModItems.VINECOG))
                        .entries((context, entries) -> {
                            entries.add(ModItems.EMBERGLAIVE);
                            entries.add(ModItems.PLAYTHING);
                            entries.add(ModItems.VINECOG);
                        })
                        .build()
        );
    }
}
