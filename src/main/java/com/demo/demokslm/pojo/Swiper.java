package com.demo.demokslm.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("swiper")
public class Swiper {
    //设计控制主页轮播图片的类
    @TableId(value = "swiperId",type = IdType.AUTO)
    private int swiperId;

    @NotNull
    @TableField("imageId")
    private int imageId; //静态库中这个image的id
}
