/*
 Navicat Premium Data Transfer

 Source Server         : mongodb
 Source Server Type    : MongoDB
 Source Server Version : 40203
 Source Host           : localhost:27017
 Source Schema         : platform_cloud_route_v2

 Target Server Type    : MongoDB
 Target Server Version : 40203
 File Encoding         : 65001

 Date: 06/05/2020 22:00:32
*/


// ----------------------------
// Collection structure for blackList
// ----------------------------
db.getCollection("blackList").drop();
db.createCollection("blackList");

// ----------------------------
// Documents of blackList
// ----------------------------
db.getCollection("blackList").insert([ {
    _id: ObjectId("5e1425e7a548e77106e43b40"),
    ip: "",
    requestUri: "/**/actuator/**",
    requestMethod: "ALL",
    limitFrom: "",
    limitTo: "",
    location: "",
    status: "1",
    createTime: "2020-01-07 14:32:07",
    _class: "com.plm.platform.gateway.entity.BlackList"
} ]);

// ----------------------------
// Collection structure for blockLog
// ----------------------------
db.getCollection("blockLog").drop();
db.createCollection("blockLog");

// ----------------------------
// Documents of blockLog
// ----------------------------

// ----------------------------
// Collection structure for rateLimitLog
// ----------------------------
db.getCollection("rateLimitLog").drop();
db.createCollection("rateLimitLog");

// ----------------------------
// Documents of rateLimitLog
// ----------------------------

// ----------------------------
// Collection structure for rateLimitRule
// ----------------------------
db.getCollection("rateLimitRule").drop();
db.createCollection("rateLimitRule");

// ----------------------------
// Documents of rateLimitRule
// ----------------------------
db.getCollection("rateLimitRule").insert([ {
    _id: ObjectId("5e1abc9ef51708123d94b1f8"),
    requestUri: "/auth/captcha",
    requestMethod: "GET",
    limitFrom: "06:00:00",
    limitTo: "22:30:00",
    count: "3",
    intervalSec: "10",
    status: "1",
    createTime: "2020-01-12 14:28:46",
    _class: "com.plm.platform.gateway.enhance.entity.RateLimitRule"
} ]);

// ----------------------------
// Collection structure for routeLog
// ----------------------------
db.getCollection("routeLog").drop();
db.createCollection("routeLog");

// ----------------------------
// Documents of routeLog
// ----------------------------

// ----------------------------
// Collection structure for routeUser
// ----------------------------
db.getCollection("routeUser").drop();
db.createCollection("routeUser");

// ----------------------------
// Documents of routeUser
// ----------------------------
db.getCollection("routeUser").insert([ {
    _id: ObjectId("5eb27ea5eacdfa5aa46ccabb"),
    username: "admin",
    password: "$2a$10$ugcEMF8GFpCvNg2aB5Jee.v36PH2YZX0e6y1TqziXEJTrcBTsidf6",
    roles: "admin",
    createTime: "2020-05-06 17:08:53",
    _class: "com.plm.platform.gateway.enhance.entity.RouteUser"
} ]);
db.getCollection("routeUser").insert([ {
    _id: ObjectId("5eb27ec3eacdfa5aa46ccabc"),
    username: "scott",
    password: "$2a$10$IsFYNg1oRVcAW.5zU/Gr.OlTUtAWEWbKBNbQku.Arfw8DFaWMS5oG",
    roles: "user",
    createTime: "2020-05-06 17:09:23",
    _class: "com.plm.platform.gateway.enhance.entity.RouteUser"
} ]);
