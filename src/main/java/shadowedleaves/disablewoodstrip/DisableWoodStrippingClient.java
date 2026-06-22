package shadowedleaves.disablewoodstrip;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.BlockPos;

public class DisableWoodStrippingClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (world.isClientSide()) {
                ItemStack itemStack = player.getItemInHand(hand);
                if (itemStack.getItem() instanceof AxeItem) {
                    BlockPos pos = hitResult.getBlockPos();
                    Block block = world.getBlockState(pos).getBlock();
                    if (isStrippableWood(block)) {
                        return InteractionResult.FAIL;
                    }
                }
            }
            return InteractionResult.PASS;
        });
    }

    private boolean isStrippableWood(Block block) {
        return block == Blocks.OAK_LOG || block == Blocks.SPRUCE_LOG || block == Blocks.BIRCH_LOG ||
                block == Blocks.JUNGLE_LOG || block == Blocks.ACACIA_LOG || block == Blocks.DARK_OAK_LOG ||
                block == Blocks.MANGROVE_LOG || block == Blocks.OAK_WOOD || block == Blocks.SPRUCE_WOOD ||
                block == Blocks.BIRCH_WOOD || block == Blocks.JUNGLE_WOOD || block == Blocks.ACACIA_WOOD ||
                block == Blocks.DARK_OAK_WOOD || block == Blocks.MANGROVE_WOOD ||
                block == Blocks.CRIMSON_STEM || block == Blocks.WARPED_STEM;
    }
}