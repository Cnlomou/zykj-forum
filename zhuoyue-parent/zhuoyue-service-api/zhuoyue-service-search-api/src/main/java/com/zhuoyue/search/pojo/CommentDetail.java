package com.zhuoyue.search.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Column;
import java.util.Date;

/**
 * @author Linmo
 * @create 2020/4/19 15:01
 */
public class CommentDetail {
    @Id
    private Long id; /*评论id*/

    @Field(type = FieldType.Keyword)
    private Long userId; /*用户id*/

    @Field(type = FieldType.Keyword)
    private Long artId; /*文章id*/

    @Field(type = FieldType.Integer)
    private Integer likeCount; /*点赞数 默认0*/

    private String content; /*发布的文本*/

    private String accUrl; /*附件，不超过5个*/

    private Long parId; /*上一级id 默认0*/
    @Field(type = FieldType.Date)
    private Date createTime; /*创建时间*/

    private Integer comCount; /*拥有子评论的数量 默认0*/
}
