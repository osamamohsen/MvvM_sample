package com.app.mvvmsample.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("user_id")
    @Expose
    public Integer userId;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("phone")
    @Expose
    public String phone;
    @SerializedName("image")
    @Expose
    public String image;
    @SerializedName("lat")
    @Expose
    public String lat;
    @SerializedName("lng")
    @Expose
    public String lng;
    @SerializedName("formatted_address")
    @Expose
    public String formattedAddress;
    @SerializedName("promo_code")
    @Expose
    public String promoCode;
    @SerializedName("shop")
    @Expose
    public Shop shop;
    @SerializedName("city_id")
    @Expose
    public Integer cityId;
    @SerializedName("jwt_token")
    @Expose
    public String jwtToken;
    @SerializedName("activation_status")
    @Expose
    public String activationStatus;
    @SerializedName("user_points")
    @Expose
    public Integer userPoints;

    public class Shop {

        @SerializedName("shop_id")
        @Expose
        public Integer shopId;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("image")
        @Expose
        public String image;
        @SerializedName("minimum_charge")
        @Expose
        public String minimumCharge;
        @SerializedName("type")
        @Expose
        public Integer type;
        @SerializedName("category_id")
        @Expose
        public Integer categoryId;
        @SerializedName("admin_status")
        @Expose
        public Integer adminStatus;
        @SerializedName("available")
        @Expose
        public Integer available;
        @SerializedName("personal_identity_no")
        @Expose
        public String personalIdentityNo;
        @SerializedName("portfolio_image")
        @Expose
        public String portfolioImage;

        @SerializedName("portfolio_images_count")
        @Expose
        public Integer portfolioImagesCount;


    }
}
