package com.example.yuekao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public
class Gouwucheche {
    @Id(autoincrement = true)
    private Long id;

    private String title;
    private String pic;

    private int shuliang;


    private boolean check;


    @Generated(hash = 498914065)
    public Gouwucheche(Long id, String title, String pic, int shuliang,
            boolean check) {
        this.id = id;
        this.title = title;
        this.pic = pic;
        this.shuliang = shuliang;
        this.check = check;
    }


    @Generated(hash = 358691306)
    public Gouwucheche() {
    }


    public Long getId() {
        return this.id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getTitle() {
        return this.title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getPic() {
        return this.pic;
    }


    public void setPic(String pic) {
        this.pic = pic;
    }


    public int getShuliang() {
        return this.shuliang;
    }


    public void setShuliang(int shuliang) {
        this.shuliang = shuliang;
    }


    public boolean getCheck() {
        return this.check;
    }


    public void setCheck(boolean check) {
        this.check = check;
    }
}
