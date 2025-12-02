package info.partonetrain.bouncyanimals;

import info.partonetrain.bouncyanimals.mixin.MobAccessor;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.ModInitializer;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.ai.goal.GoalSelector;
import net.minecraft.world.entity.animal.Animal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Bouncyanimals implements ModInitializer {
	public static final String MOD_ID = "bouncyanimals";
	public static BouncyanimalsConfig config;

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final String[] defaultAnimals = new String[]{"minecraft:chicken", "minecraft:cow", "minecraft:mooshroom", "minecraft:pig", "minecraft:sheep", "minecraft:wolf"};

	@Override
	public void onInitialize() {
		AutoConfig.register(BouncyanimalsConfig.class, JanksonConfigSerializer::new);
		config = AutoConfig.getConfigHolder(BouncyanimalsConfig.class).getConfig();

		ServerEntityEvents.ENTITY_LOAD.register(((entity, serverLevel) -> {
			if(entity instanceof Animal animal) {
				if(config.jumpWhileMovingChance > 0){
					if(Arrays.asList(config.jumpWhileMovingAnimals).contains(BuiltInRegistries.ENTITY_TYPE.getKey(animal.getType()).toString())){
						addJumpWhileMovingGoal(animal);
						if(config.debugShow){
							LOGGER.info("added JumpWhileMoving goal to " + animal.getCustomName());
						}
					}
				}

				if(config.jumpRandomlyChance > 0){
					if(Arrays.asList(config.jumpRandomlyAnimals).contains(BuiltInRegistries.ENTITY_TYPE.getKey(animal.getType()).toString())){
						addJumpRandomlyGoal(animal);
						if(config.debugShow){
							LOGGER.info("added JumpRandomly goal to " + animal.getCustomName());
						}
					}
				}


			}
			else {
				if(Arrays.asList(config.jumpWhileMovingAnimals).contains(entity.getType().toString())){
					LOGGER.info(entity.getName().toString() + " is not an Animal");
				}
			}
		}));
	}

	public void addJumpWhileMovingGoal(Animal animal){
		GoalSelector mobGoalSelector = ((MobAccessor) animal).bouncyanimals$getMobGoalSelector();
		mobGoalSelector.addGoal(config.jumpWhileMovingPriority, new JumpWhileMovingGoal(animal));
	}

	public void addJumpRandomlyGoal(Animal animal){
		GoalSelector mobGoalSelector = ((MobAccessor) animal).bouncyanimals$getMobGoalSelector();
		mobGoalSelector.addGoal(config.jumpRandomlyPriority, new JumpRandomlyGoal(animal));
	}
}