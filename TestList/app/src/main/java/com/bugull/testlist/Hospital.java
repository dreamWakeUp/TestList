package com.bugull.testlist;

import java.io.Serializable;

/**
 * Created by fly on 2016/7/13.
 */
public class Hospital implements Serializable {
    private int diagnositicsPlaceId;
    private String doctorId;
    private String hospotial;
    private String department;
    private String status;
    private boolean showDelete;

    public boolean isShowDelete() {
        return showDelete;
    }

    public void setShowDelete(boolean showDelete) {
        this.showDelete = showDelete;
    }

    public int getDiagnositicsPlaceId() {
        return diagnositicsPlaceId;
    }

    public void setDiagnositicsPlaceId(int diagnositicsPlaceId) {
        this.diagnositicsPlaceId = diagnositicsPlaceId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getHospotial() {
        return hospotial;
    }

    public void setHospotial(String hospotial) {
        this.hospotial = hospotial;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
