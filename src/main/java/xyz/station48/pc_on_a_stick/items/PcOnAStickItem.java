package xyz.station48.pc_on_a_stick.items;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager;
import com.cobblemon.mod.common.battles.BattleRegistry;
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket;
import com.ofekn.crafting_on_a_stick.api.IWheelItem;
import com.ofekn.crafting_on_a_stick.api.Ref;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PcOnAStickItem extends Item implements IWheelItem {
    public PcOnAStickItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        openPC(player);
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }

    public void onWheelAction(Player player, Ref<ItemStack> ref) {
        openPC(player);
    }

    public static void openPC(Player player) {
        player.playSound(CobblemonSounds.PC_ON, 0.5F, 1.0F);
        if (player instanceof ServerPlayer serverPlayer) {
             BattleRegistry br = BattleRegistry.INSTANCE;
             if (BattleRegistry.getBattleByParticipatingPlayerId(serverPlayer.getUUID()) == null) {
                 PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(serverPlayer);
                 PCLinkManager.INSTANCE.addLink(player.getUUID(), pc, (p) -> true);
                 (new OpenPCPacket(pc, 0)).sendToPlayer(serverPlayer);
             } else {
                 player.playSound(CobblemonSounds.PC_OFF, 0.5F, 1.0F);
             }
        }
    }
}
