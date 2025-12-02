package info.partonetrain.bouncyanimals;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment;

@Config(name = Bouncyanimals.MOD_ID)
public class BouncyanimalsConfig implements ConfigData {

    @Comment("Whether to print debug messages and show debug particles - you probably want this to be off")
    public boolean debugShow = false;

    @Comment("Animals that should jump while moving, if there is a block in front of them. This replicates the beta behavior")
    public String[] jumpWhileMovingAnimals = Bouncyanimals.defaultAnimals;

    @Comment("The priority of the goal for jumping while moving. This should be low")
    public int jumpWhileMovingPriority = 1;

    @Comment("The chance that a mob will jump if it is moving and there is a block in front of it. 1.0 = 100%, 0.5 = 50%, 0 or lower disables the feature")
    public double jumpWhileMovingChance = 1.0;

    @Comment("Animals that should jump randomly.")
    public String[] jumpRandomlyAnimals = Bouncyanimals.defaultAnimals;

    @Comment("The priority of the goal for jumping randomly. This should be low")
    public int jumpRandomlyPriority = 1;

    @Comment("The chance that a mob will jump randomly. 1.0 = 100%, 0.5 = 50%, 0 or lower disables the feature")
    public double jumpRandomlyChance = 0;
}
