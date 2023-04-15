package com.gyunf.domain;

/**
 * @author gaoyf
 * @site WWW.java.com
 * @create 2021-03-22 19:08
 */
public class EmPloyees {
//    CREATE TABLE `EmPloyees` (
//            `姓名` varchar(20) DEFAULT NULL,
//  `性别` varchar(2) DEFAULT NULL,
//  `学历` varchar(4) DEFAULT NULL,
//  `兴趣爱好` varchar(50) DEFAULT NULL
//) ENGINE=InnoDB DEFAULT CHARSET=utf8
    private String e_name;
    private String e_gender;
    private String e_education;
    private String e_hoby;

    public EmPloyees() {
    }

    public EmPloyees(String e_name, String e_gender, String e_education, String e_hoby) {
        this.e_name = e_name;
        this.e_gender = e_gender;
        this.e_education = e_education;
        this.e_hoby = e_hoby;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getE_gender() {
        return e_gender;
    }

    public void setE_gender(String e_gender) {
        this.e_gender = e_gender;
    }

    public String getE_education() {
        return e_education;
    }

    public void setE_education(String e_education) {
        this.e_education = e_education;
    }

    public String getE_hoby() {
        return e_hoby;
    }

    public void setE_hoby(String e_hoby) {
        this.e_hoby = e_hoby;
    }

    @Override
    public String toString() {
        return "EmPloyees{" +
                "e_name='" + e_name + '\'' +
                ", e_gender='" + e_gender + '\'' +
                ", e_education='" + e_education + '\'' +
                ", e_hoby='" + e_hoby + '\'' +
                '}';
    }
}
