package dev.matthiesen.pc_on_a_stick.registry;

import dev.matthiesen.common.matthiesen_lib.registry.AbstractItemRegistry;
import dev.matthiesen.pc_on_a_stick.Constants;
import dev.matthiesen.pc_on_a_stick.items.PcOnAStickItem;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class ItemRegistry extends AbstractItemRegistry {
    private static final ItemRegistry INSTANCE = new ItemRegistry();

    private ItemRegistry() {
        super(Constants.MOD_ID);
    }

    public static final Supplier<Item> PC_ON_A_STICK;

    static {
        PC_ON_A_STICK = INSTANCE.register("pc_on_a_stick", PcOnAStickItem::new);
    }

    public static void init() {
        Constants.createInfoLog("Registering items for PC on a Stick...");
    }
}
