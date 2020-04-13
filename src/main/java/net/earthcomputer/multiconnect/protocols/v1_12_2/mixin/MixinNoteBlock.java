package net.earthcomputer.multiconnect.protocols.v1_12_2.mixin;

import net.earthcomputer.multiconnect.api.Protocols;
import net.earthcomputer.multiconnect.impl.ConnectionInfo;
import net.earthcomputer.multiconnect.protocols.v1_12_2.NoteBlockBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.NoteBlock;
import net.minecraft.state.properties.NoteBlockInstrument;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(NoteBlock.class)
public class MixinNoteBlock extends Block implements ITileEntityProvider {

    public MixinNoteBlock(Properties settings) {
        super(settings);
    }

    @Override
    public boolean hasTileEntity() {
        return ConnectionInfo.protocolVersion <= Protocols.V1_12_2;
    }

    @Override
    public TileEntity createNewTileEntity(IBlockReader view) {
        return hasTileEntity() ? new NoteBlockBlockEntity() : null;
    }

    @ModifyVariable(method = "eventReceived", ordinal = 0, at = @At("HEAD"))
    private BlockState onOnBlockAction(BlockState localState, BlockState state, World world, BlockPos pos, int type, int data) {
        if (ConnectionInfo.protocolVersion > Protocols.V1_12_2)
            return localState;
        NoteBlockInstrument instrument = type < 0 || type >= 10 ? NoteBlockInstrument.HARP : NoteBlockInstrument.values()[type];
        state = state.with(NoteBlock.INSTRUMENT, instrument).with(NoteBlock.NOTE, MathHelper.clamp(data, 0, 24));
        world.setBlockState(pos, state, 18);
        return state;
    }

}
