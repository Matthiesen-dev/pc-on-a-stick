package dev.matthiesen.pc_on_a_stick.items;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.CobblemonSounds;
import com.cobblemon.mod.common.api.storage.pc.PCStore;
import com.cobblemon.mod.common.api.storage.pc.link.PCLink;
import com.cobblemon.mod.common.api.storage.pc.link.PCLinkManager;
import com.cobblemon.mod.common.net.messages.client.storage.pc.OpenPCPacket;
import com.cobblemon.mod.common.util.PlayerExtensionsKt;
import com.ofekn.crafting_on_a_stick.api.IWheelItem;
import com.ofekn.crafting_on_a_stick.api.Ref;
import dev.matthiesen.common.matthiesen_lib_api.utility.SoundsPlayer;
import dev.matthiesen.pc_on_a_stick.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import javax.annotation.ParametersAreNonnullByDefault;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public final class PcOnAStickItem extends Item implements IWheelItem {
    public PcOnAStickItem() {
        super(new Item.Properties());
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        openPCStorage(player);
        return InteractionResultHolder.sidedSuccess(player.getItemInHand(usedHand), level.isClientSide);
    }

    @Override
    public void onWheelAction(Player player, Ref<ItemStack> ref) {
        openPCStorage(player);
    }

    private static void openPCStorage(Player player) {
        if (!(player instanceof ServerPlayer serverPlayer)) return;
        // Check if player is in Battle
        if(PlayerExtensionsKt.isInBattle(serverPlayer)) {
            player.sendSystemMessage(Component.translatable("cobblemon.pc.inbattle").withStyle(ChatFormatting.RED));
            return;
        }

        // Not in a Battle? Open PC
        try {
            PCStore pc = Cobblemon.INSTANCE.getStorage().getPC(serverPlayer);
            PCLinkManager.INSTANCE.addLink(new PCLink(pc, player.getUUID()));
            new SoundsPlayer(CobblemonSounds.PC_ON).play(serverPlayer);
            new OpenPCPacket(pc).sendToPlayer(serverPlayer);
        } catch (RuntimeException e) {
            Constants.createErrorLog("Failed to open PC for player " + player.getName().getString() + ": " + e.getMessage(), e);
            player.sendSystemMessage(Component.translatable("pc_on_a_stick.error.failed_to_open_pc").withStyle(ChatFormatting.RED));
        }
    }
}
