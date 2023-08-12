package top.codephon.kamiwaza_tesuto.entities.render;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import top.codephon.kamiwaza_tesuto.KamiwazaTesuto;
import top.codephon.kamiwaza_tesuto.entities.mins.MinionEntity;

//实体模型类 GeoModel<>里填自己的实体所在类
public class MinionModel extends GeoModel<MinionEntity> {

    @Override
    //模型所在路径
    public ResourceLocation getModelResource(MinionEntity animatable) {
        //模组内所有实体文件名格式建议保持一致 路径是从resources文件夹开始算起
        return new ResourceLocation(KamiwazaTesuto.MOD_ID, "geo/turbomin.geo.json");
    }

    //贴图所在路径
    @Override
    //建议放在textures/entity中，每种实体要分类也可以，路径要改一改
    public ResourceLocation getTextureResource(MinionEntity animatable) {
        //先把可能用到的变量添加到方法最前面，这样会简洁一些
        ResourceLocation min_texture;
        min_texture = new ResourceLocation(KamiwazaTesuto.MOD_ID, "textures/entity/mins/turbomin.png");
        return min_texture;
    }

    //动作文件所在处
    @Override
    public ResourceLocation getAnimationResource(MinionEntity animatable) {
        return new ResourceLocation(KamiwazaTesuto.MOD_ID, "animations/turbomin.anime.json");
    }
}
