package com.qinjiangbo.ofo;

import java.util.HashMap;
import java.util.Map;

/**
 * @date: 12/12/2016 6:43 PM
 * @author: qinjiangbo@github.io
 */
public class DataBase {

    public static Map<Integer, Address> getData() {
        Map<Integer, Address> database = new HashMap<>();
        database.put(1, new Address("信息学部青楼", 114.366408, 30.532767));
        database.put(2, new Address("信息学部图书馆", 114.367593, 30.535411));
        database.put(3, new Address("文理学部生科院", 114.367351, 30.539294));
        database.put(4, new Address("文理学部新图", 114.368878, 30.541949));
        database.put(5, new Address("文理学部第四教学楼", 114.36806, 30.543256));
        database.put(6, new Address("文理学部计算机学院", 114.363416, 30.545059));
        database.put(7, new Address("文理学部信息管理学院", 114.381131, 30.543007));
        database.put(8, new Address("文理学部法学院", 114.37849, 30.543559));
        database.put(9, new Address("工学部图书馆", 114.367521, 30.550203));
        database.put(10, new Address("文理学部桂园食堂", 114.365469, 30.544848));
        database.put(11, new Address("文理学部湖滨食堂", 114.376792, 30.547894));
        database.put(12, new Address("文理学部枫园食堂", 114.37849, 30.543559));
        database.put(13, new Address("文理学部梅园食堂", 114.371169, 30.542056));
        database.put(14, new Address("信息学部二食堂", 114.364728, 30.533475));
        database.put(15, new Address("信息学部一食堂", 114.365577, 30.534708));
        database.put(16, new Address("工学部一食堂", 114.37849, 30.543559));
        database.put(17, new Address("文理学部枫园14舍", 114.375804, 30.543551));
        database.put(18, new Address("文理学部桂园桂3舍", 114.365042, 30.545876));
        database.put(19, new Address("文理学院梅园6舍", 114.370472, 30.542569));
        database.put(20, new Address("信息学部十二栋", 114.364508, 30.534451));
        database.put(21, new Address("信息学部学生宿舍三栋", 114.364508, 30.534451));
        database.put(22, new Address("工学部7舍", 114.364813, 30.547822));
        database.put(23, new Address("工学部5舍", 114.364813, 30.547822));
        return database;
    }
}
