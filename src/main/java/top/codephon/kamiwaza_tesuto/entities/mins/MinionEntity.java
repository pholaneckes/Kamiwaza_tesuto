package top.codephon.kamiwaza_tesuto.entities.mins;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

//自己的实体大类
public class MinionEntity extends TamableAnimal implements GeoAnimatable {

    //动作使用必须
    public AnimatableInstanceCache factory = new SingletonAnimatableInstanceCache(this);

    protected MinionEntity(EntityType<? extends TamableAnimal> type, Level worldIn) {
        super(type, worldIn);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob ageable) {
        return null;
    }

    //注册动作的控制
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    //播放什么动作
    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> animationState) {
        //.isMoving()是移动中
        if(animationState.isMoving()){
            //animationName: 改成对应json的动作名
            animationState.getController().setAnimation(RawAnimation.begin().then("walking", Animation.LoopType.LOOP));
            //（继续）
            return PlayState.CONTINUE;
        }else {
            //更多的动作 else 处最好是闲置时的动作
            animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }
    }

    //获取动作使用的必须
    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.factory;
    }

    //必须 一般这样填就可以了
    @Override
    public double getTick(Object obj) {
        return ((Entity)obj).tickCount;
    }

    //创建实体属性 如移动速度（MOVEMENT_SPEED），攻击伤害（ATTACK_DAMAGE）等，最大生命值（MAX_HEALTH）是必须的
    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MOVEMENT_SPEED, 0.35F).add(Attributes.ATTACK_DAMAGE, 2.5D).add(Attributes.MAX_HEALTH, 100.0F);
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        //坐下 目标
        this.goalSelector.addGoal(0, new SitWhenOrderedToGoal(this));
        //跟随主人 目标
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.6, 6.5F, 2.5F, false));
        //闲走 目标
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.25));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor serverWorld, DifficultyInstance difficultyInstance, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData, @Nullable CompoundTag tag) {
        return super.finalizeSpawn(serverWorld, difficultyInstance, spawnType, spawnGroupData, tag);
    }
}
