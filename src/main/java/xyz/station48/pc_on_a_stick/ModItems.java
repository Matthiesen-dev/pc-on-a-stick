package xyz.station48.pc_on_a_stick;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import xyz.station48.pc_on_a_stick.items.PcOnAStickItem;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(PcOnAStick.MOD_ID);
    public static final DeferredItem<Item> PC_ON_A_STICK;

    static {
        PC_ON_A_STICK = ITEMS.register("pc_on_a_stick", () -> new PcOnAStickItem(new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
