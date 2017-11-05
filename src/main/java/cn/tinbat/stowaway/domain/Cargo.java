package cn.tinbat.stowaway.domain;

public class Cargo {
    private String uuid;
    private String filename;
    private StringBuilder goods;

    public Cargo(String uuid, String filename) {
        this.uuid = uuid;
        this.filename = filename;
        this.goods = new StringBuilder();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public StringBuilder getGoods() {
        return goods;
    }

    public void setGoods(StringBuilder goods) {
        this.goods = goods;
    }

    public void load(String goods){
        this.goods.append(goods);
    }

}
