package info.partonetrain.bouncyanimals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class JumpRandomlyGoal extends Goal {
    private final Mob owner;
    private final LevelReader levelReader;

    public JumpRandomlyGoal(Mob owner){
        this.owner = owner;
        levelReader = owner.level();
    }

    @Override
    public boolean canUse() {
        return true;
    }

    @Override
    public boolean requiresUpdateEveryTick() { //runs every other tick
        return false;
    }

    @Override
    public void tick() {
        if(owner.getRandom().nextDouble() < Bouncyanimals.config.jumpRandomlyChance){
            owner.getJumpControl().jump();
            if(Bouncyanimals.config.debugShow){
                Bouncyanimals.LOGGER.info(String.valueOf(owner.getCustomName()) + " jumped randomly");
            }
        }
    }


}
