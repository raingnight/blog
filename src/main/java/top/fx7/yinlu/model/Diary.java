package top.fx7.yinlu.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author 人家故里
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("diary")
@Accessors(chain = true)
public class Diary implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotBlank(message = "内容不能为空")
    @TableField("content")
    private String content;

    @TableField("created")
    private LocalDateTime created;

    @TableField("viewCount")
    private Integer viewCount;

    @TableField("status")
    private Integer status;

    @TableField("user_id")
    private Integer userId;

}
