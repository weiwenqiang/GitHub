package com.hpw.myapp.ui.zhihu.model.quick;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpw on 16/11/1.
 */
public class DataServer {
    public static List<Status> getSampleData(int lenth) {
        List<Status> list = new ArrayList<>();
        for (int i = 0; i < lenth; i++) {
            Status status = new Status();
            status.setUserName("Hpw" + i);
            status.setCreatedAt("04/05/" + i);
            status.setRetweet(i % 2 == 0);
            status.setUserAvatar("http://www.easyicon.net/api/resizeApi.php?id=1124363&size=128");
            status.setText("MVP-hpw https://github.com/SuperMan42/MVP");
            list.add(status);
        }
        return list;
    }
}

