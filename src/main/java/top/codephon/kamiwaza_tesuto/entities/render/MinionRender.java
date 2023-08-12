package top.codephon.kamiwaza_tesuto.entities.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import top.codephon.kamiwaza_tesuto.entities.mins.MinionEntity;
import top.codephon.kamiwaza_tesuto.entities.mins.Turbomin;

//实体渲染类 GeoEntityRenderer<>里填自己的实体所在类
public class MinionRender extends GeoEntityRenderer<MinionEntity> {
    public MinionRender(EntityRendererProvider.Context renderManager) {
        //super一定要这样写，上方的第二项可以删去 new后面接刚才的 实体模型类()
        super(renderManager, new MinionModel());
        //实体影子的大小
        this.shadowRadius = 0.7f;
    }

    //渲染主方法
    @Override
    public void render(MinionEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public void preRender(PoseStack poseStack, MinionEntity animatable, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        //animatable instanceof 实体所在类，多个实体也是如此继续往后面添加
        if(animatable instanceof Turbomin) {
            //用于更改实体的大小 x y z 倍数
            poseStack.scale(1.05f,1.05f,1.05f);
        }
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }


}
