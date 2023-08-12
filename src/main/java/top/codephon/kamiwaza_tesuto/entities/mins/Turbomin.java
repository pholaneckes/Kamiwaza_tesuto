package top.codephon.kamiwaza_tesuto.entities.mins;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class Turbomin extends MinionEntity {
    public Turbomin(EntityType<? extends TamableAnimal> type, Level worldIn) {
        super(type, worldIn);
    }

    //关于实体的骑乘
    //右击实体后运行的方法
    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        //当自己没有被骑乘的时候让玩家骑上它
        if (!this.isVehicle()) {
            player.startRiding(this);
        }
        return super.mobInteract(player, hand);
    }

    //让玩家操控被骑的自己 的方法
    @Override
    public void travel(Vec3 pos) {
        //当自己活着
        if (this.isAlive()) {
            //当自己被骑
            if (this.isVehicle()) {
                //获取骑自己的乘客
                LivingEntity passenger = getControllingPassenger();

                //从这#
                this.yRotO = getYRot();
                this.xRotO = getXRot();
                setYRot(passenger.getYRot());
                setXRot(passenger.getXRot() * 0.5f);
                setRot(getYRot(), getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float x = passenger.xxa * 0.5F;
                float z = passenger.zza;
                if (z <= 0) {
                    z *= 0.25f;
                }
                //到这# 都是如何移动，一般可以直接照搬

                //设置速度
                this.setSpeed(0.3f);
                super.travel(new Vec3(x, pos.y, z));
            }
        }
    }

    //获取乘客
    @Nullable
    @Override
    public LivingEntity getControllingPassenger() {
        return getFirstPassenger() instanceof LivingEntity entity ? entity : null;
    }

    //定义自己可以被控制骑
    @Override
    public boolean isControlledByLocalInstance() {
        return true;
    }
}
