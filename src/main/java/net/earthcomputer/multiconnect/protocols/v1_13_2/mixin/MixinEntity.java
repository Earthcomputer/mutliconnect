package net.earthcomputer.multiconnect.protocols.v1_13_2.mixin;

import net.earthcomputer.multiconnect.api.Protocols;
import net.earthcomputer.multiconnect.impl.ConnectionInfo;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Entity.class)
public class MixinEntity {

    @ModifyConstant(method = "getAbsoluteMotion", constant = @Constant(doubleValue = 1E-7))
    private static double fixVelocityEpsilon(double epsilon) {
        if (ConnectionInfo.protocolVersion <= Protocols.V1_13_2)
            return 1E-4;
        return epsilon;
    }

}
