package com.example.yuekao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Gouwuche {
    @Id(autoincrement = true)
    private Long id;

    private String shuliang;

    private String pic;

    private String title;



    @Generated(hash = 513097214)
    public Gouwuche(Long id, String shuliang, String pic, String title) {
        this.id = id;
        this.shuliang = shuliang;
        this.pic = pic;
        this.title = title;
    }

    @Generated(hash = 1421966843)
    public Gouwuche() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getShuliang() {
        return this.shuliang;
    }

    public void setShuliang(String shuliang) {
        this.shuliang = shuliang;
    }

    public String getPic() {
        return this.pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    


}
