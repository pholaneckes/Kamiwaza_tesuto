package top.codephon.kamiwaza_tesuto.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;
import top.codephon.kamiwaza_tesuto.entities.mins.Turbomin;

public class EntityReg {

    //实体的注册 主字段
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, KamiwazaTesuto.MOD_ID);

    //注册单个实体
    //格式：public static final RegistryObject<EntityType<实体所在类>> 实体字段取名 =
    public static final RegistryObject<EntityType<Turbomin>> turboMin =
            //格式 ENTITIES（主字段）.register("实体注册名", ()->EntityType.Builder.of(实体所在类::new, MobCategory.实体类型)
            //实体类型有：MONSTER怪物 AMBIENT装饰环境的生物 如蝙蝠 AXOLOTLS蝾螈 UNDERGROUND_WATER_CREATURE地下水生物
            //WATER_CREATURE水生物 WATER_AMBIENT水下装饰环境的生物 如小丑鱼 MISC非生物 CREATURE不属于其它8项的
            ENTITIES.register("turbomin", ()->EntityType.Builder.of(Turbomin::new, MobCategory.CREATURE)
                    //sized:碰撞箱大小 底面边长,高度  .build(实体注册名，和上面的保持一致)
                    .sized(0.7f,1.1f).build("turbomin"));
}
