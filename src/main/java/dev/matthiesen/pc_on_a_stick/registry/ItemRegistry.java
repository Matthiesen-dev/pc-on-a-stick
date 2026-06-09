package dev.matthiesen.pc_on_a_stick.registry;

import dev.matthiesen.common.matthiesen_lib.registry.AbstractItemRegistry;
import dev.matthiesen.pc_on_a_stick.Constants;
import dev.matthiesen.pc_on_a_stick.items.PcOnAStickItem;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class ItemRegistry extends AbstractItemRegistry {
    private static final ItemRegistry INSTANCE = new ItemRegistry();

    private ItemRegistry() {
        super(Constants.MOD_ID);
    }

    public static final List<Supplier<? extends Item>> CREATIVE_ITEMS = new ArrayList<>();

    public static final Supplier<Item> PC_ON_A_STICK;

    static {
        PC_ON_A_STICK = registerItemWithCreativeTab("pc_on_a_stick", PcOnAStickItem::new);
    }

    public static void init() {
        Constants.createInfoLog("Registering items for PC on a Stick...");
    }

    @SuppressWarnings("SameParameterValue")
    private static Supplier<Item> registerItemWithCreativeTab(String name, Supplier<Item> supplier) {
        var itemRegistration = INSTANCE.register(name, supplier);
        CREATIVE_ITEMS.add(itemRegistration);
        return itemRegistration;
    }
}
