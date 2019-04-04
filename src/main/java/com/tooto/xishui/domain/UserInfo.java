package com.tooto.xishui.domain;

import java.util.List;

import com.alipay.api.AlipayObject;
import com.alipay.api.internal.mapping.ApiField;
import com.alipay.api.internal.mapping.ApiListField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "UserInfo", description = "Sample model for the documentation")
public class UserInfo extends AlipayObject {

    public UserInfo(String a, String b, String c, String d) {

    }

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4767604747954841921L;

    @ApiField("out_id")
    private Integer outId;

    @ApiField("point")
    @ApiModelProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
    private Point point;

    @ApiField("point")
    @ApiListField("list_point")
    @ApiModelProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
    private List<Point> listPoint;

    public String toString() {
        return "{outId:" + outId + ",point:" + point + ",listPoint:" + listPoint + "}";
    }

    @ApiModelProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
    public Integer getOutId() {
        return outId;
    }

    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    @ApiModelProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @ApiModelProperty(value = "pet status in the store", allowableValues = "available,pending,sold")
    public List<Point> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<Point> listPoint) {
        this.listPoint = listPoint;
    }

}
