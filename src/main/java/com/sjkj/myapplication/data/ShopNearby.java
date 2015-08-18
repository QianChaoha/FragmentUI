
package com.sjkj.myapplication.data;

import com.sjkj.myapplication.http.parser.BackResult;

import java.io.Serializable;

/**
 * 根据位置获取商家列表转至元数据结尾转至元数据起始URLGET /app/seller/nearby
 *
 * @author cx
 */

public class ShopNearby extends BackResult {

    private String sellerId;// "548542cca09cbd3620000013",
    private int category;// 0,
    private String shopSpecial;// "bbbbbbbbb",
    private int orderNum;// 0

    private boolean isTop; // 是否置顶
    private boolean hasBid; // 是否竞价过节目
    private boolean approveState; // 是否认证
    private boolean hasCoupon; // 是否有优惠券
    private String broadcastMessage; // 广播消息
    private int preferentialCategory;// 优惠类型0：无，1：抵用券，2：折扣券，3：赠券，4：公告
    private String preferential; // 优惠信息
    private long preferentialUpdated;// 优惠更新时间
    private String shopName; // 商店名称
    private String shopLogo; // 商店logo
    private String shopAddress; // 商店地址
    private double consumeAmount; // 人均消费金额
    private double distance; // 距离 ，以当前坐标搜索时显示
    private int concernNum; // 关注数量
    private long createTime; // 创建时间
    private Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isHasBid() {
        return hasBid;
    }

    public void setHasBid(boolean hasBid) {
        this.hasBid = hasBid;
    }

    public String getBroadcastMessage() {
        return broadcastMessage;
    }

    public void setBroadcastMessage(String broadcastMessage) {
        this.broadcastMessage = broadcastMessage;
    }

    public int getPreferentialCategory() {
        return preferentialCategory;
    }

    public void setPreferentialCategory(int preferentialCategory) {
        this.preferentialCategory = preferentialCategory;
    }

    public String getPreferential() {
        return preferential;
    }

    public void setPreferential(String preferential) {
        this.preferential = preferential;
    }

    public long getPreferentialUpdated() {
        return preferentialUpdated;
    }

    public void setPreferentialUpdated(long preferentialUpdated) {
        this.preferentialUpdated = preferentialUpdated;
    }

    public double getConsumeAmount() {
        return consumeAmount;
    }

    public void setConsumeAmount(double consumeAmount) {
        this.consumeAmount = consumeAmount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public boolean isApproveState() {
        return approveState;
    }

    public void setApproveState(boolean approveState) {
        this.approveState = approveState;
    }

    public boolean isHasCoupon() {
        return hasCoupon;
    }

    public void setHasCoupon(boolean hasCoupon) {
        this.hasCoupon = hasCoupon;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean isTop) {
        this.isTop = isTop;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopLogo() {
        return shopLogo;
    }

    public void setShopLogo(String shopLogo) {
        this.shopLogo = shopLogo;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopSpecial() {
        return shopSpecial;
    }

    public void setShopSpecial(String shopSpecial) {
        this.shopSpecial = shopSpecial;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getConcernNum() {
        return concernNum;
    }

    public void setConcernNum(int concernNum) {
        this.concernNum = concernNum;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

}
