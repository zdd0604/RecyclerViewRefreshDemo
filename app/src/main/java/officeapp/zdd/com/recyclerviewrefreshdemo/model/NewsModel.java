package officeapp.zdd.com.recyclerviewrefreshdemo.model;

import java.io.Serializable;

/**
 * Created by Admin on 2017/8/8.
 */

public class NewsModel implements Serializable {
    private static final long serialVersionUID = 1245106267537901076L;

    /**
     * age : 20
     * id : 0
     * img : cs.jpg
     * imgpath : img/cs.jpg
     * money : 456
     * name : 程序员1
     * needjob : android开发攻城狮
     * other : 820727502@qq.com
     * phone : 18139722828
     * sex : 女
     * time : 2017-01-06 00:00:00.0
     * word : 简历1
     * wordpath : word/jl_1.pdf
     * years : 4
     */

    private int age;
    private int id;
    private String img;
    private String imgpath;
    private String money;
    private String name;
    private String needjob;
    private String other;
    private String phone;
    private String sex;
    private String time;
    private String word;
    private String wordpath;
    private String years;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNeedjob() {
        return needjob;
    }

    public void setNeedjob(String needjob) {
        this.needjob = needjob;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getWordpath() {
        return wordpath;
    }

    public void setWordpath(String wordpath) {
        this.wordpath = wordpath;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "age=" + age +
                ", id=" + id +
                ", img='" + img + '\'' +
                ", imgpath='" + imgpath + '\'' +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", needjob='" + needjob + '\'' +
                ", other='" + other + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", time='" + time + '\'' +
                ", word='" + word + '\'' +
                ", wordpath='" + wordpath + '\'' +
                ", years='" + years + '\'' +
                '}';
    }
}
