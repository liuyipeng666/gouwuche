package com.example.yuekao.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class liulan {

    @Id(autoincrement = true)
    private Long id;

    private String title;
    @NotNull
    private String pic;
    @Generated(hash = 2134519703)
    public liulan(Long id, String title, @NotNull String pic) {
        this.id = id;
        this.title = title;
        this.pic = pic;
    }
    @Generated(hash = 64995024)
    public liulan() {
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



}
