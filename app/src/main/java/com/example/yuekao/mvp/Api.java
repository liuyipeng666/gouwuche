package com.example.yuekao.mvp;



import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    //http://www.qubaobei.com/ios/cf/dish_list.php?stage_id=1&limit=20&page=1
    @GET("/ios/cf/dish_list.php")
    Observable<XiaEntity> getdata(@Query("stage_id") int  stage_id,@Query("limit") int limit,@Query("page") int page);


}
