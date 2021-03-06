package com.jean.mybatis.demo.mode;

/**
 * @ClassName TChannelEntity
 * @Description 
 * @author jinshubao
 * @date 2017-07-27 18:13:30
 * @version 1.0 
 */
public class TChannelEntity {

    //主键ID
    private Integer id;
    //渠道名称
    private String name;
    //创建时间
    private Long create_time;
    //创建人ID
    private Integer create_op_id;
    //创建人名称
    private String create_op_name;
    //修改时间
    private Long update_time;
    //更新人ID
    private Integer update_op_id;
    //更新人名称
    private String update_op_name;
    //是否有效[1:有效,2:无效]
    private Byte is_active;
    //扩展字段1
    private String ext1;
    //扩展字段2
    private String ext2;
    //扩展字段3
    private String ext3;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
    public Integer getCreate_op_id() {
        return create_op_id;
    }
    public void setCreate_op_id(Integer create_op_id) {
        this.create_op_id = create_op_id;
    }
    public String getCreate_op_name() {
        return create_op_name;
    }
    public void setCreate_op_name(String create_op_name) {
        this.create_op_name = create_op_name;
    }
    public Long getUpdate_time() {
        return update_time;
    }
    public void setUpdate_time(Long update_time) {
        this.update_time = update_time;
    }
    public Integer getUpdate_op_id() {
        return update_op_id;
    }
    public void setUpdate_op_id(Integer update_op_id) {
        this.update_op_id = update_op_id;
    }
    public String getUpdate_op_name() {
        return update_op_name;
    }
    public void setUpdate_op_name(String update_op_name) {
        this.update_op_name = update_op_name;
    }
    public Byte getIs_active() {
        return is_active;
    }
    public void setIs_active(Byte is_active) {
        this.is_active = is_active;
    }
    public String getExt1() {
        return ext1;
    }
    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }
    public String getExt2() {
        return ext2;
    }
    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }
    public String getExt3() {
        return ext3;
    }
    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

}