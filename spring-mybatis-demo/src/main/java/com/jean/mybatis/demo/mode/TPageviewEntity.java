package com.jean.mybatis.demo.mode;

/**
 * @ClassName TPageviewEntity
 * @Description 
 * @author jinshubao
 * @date 2017-07-27 18:13:30
 * @version 1.0 
 */
public class TPageviewEntity {

    //主键ID
    private Integer id;
    //广告位ID
    private Integer ad_id;
    //用户ID
    private Integer user_id;
    //IP地址
    private String ip_addr;
    //创建时间
    private Long create_time;
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
    public Integer getAd_id() {
        return ad_id;
    }
    public void setAd_id(Integer ad_id) {
        this.ad_id = ad_id;
    }
    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
    public String getIp_addr() {
        return ip_addr;
    }
    public void setIp_addr(String ip_addr) {
        this.ip_addr = ip_addr;
    }
    public Long getCreate_time() {
        return create_time;
    }
    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
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