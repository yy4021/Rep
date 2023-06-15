package com.example.test613;

public class recTool {
    String fd_Nm;           //이름
    String fd_Wgh;          //중량
    String food_Cnt;        //필요한 재료 수

    //String food_Nm;         //재료 이름
    String food_Wgh;        //중량
    String food_Image_Address;  //이미지
    //String allrgy_Info;     //알러지 정보
    String ckry_Sumry_Info;     //레시피 설명

    public recTool(){

    }

    public recTool(String fd_Nm, String fd_Wgh, String food_Cnt,
                   String food_Wgh, String food_Image_Address, String ckry_Sumry_Info){
        this.fd_Nm = fd_Nm;
        this.fd_Wgh = fd_Wgh;
        this.food_Cnt = food_Cnt;

        this.food_Wgh = food_Wgh;
        this.food_Image_Address = food_Image_Address;
        this.ckry_Sumry_Info = ckry_Sumry_Info;
    }

    public String getFd_Nm(){return fd_Nm;}
    public void setFd_Nm(String fd_Nm){this.fd_Nm=fd_Nm;}

    public String getFd_Wgh(){return fd_Wgh;}
    public void setFd_Wgh(String fd_Wgh){this.fd_Wgh=fd_Wgh;}

    public String getFood_Cnt(){return food_Cnt;}
    public void setFood_Cnt(String food_Cnt){this.food_Cnt=food_Cnt;}

    public String getFood_Wgh(){return food_Wgh;}
    public void setFood_Wgh(String food_Wgh){this.food_Wgh = food_Wgh;}

    public String getFood_Image_Address(){return food_Image_Address;}
    public void setFood_Image_Address(String food_Image_Address){this.food_Image_Address=food_Image_Address;}

    public String getCkry_Sumry_Info(){return ckry_Sumry_Info;}
    public void setCkry_Sumry_Info(String ckry_Sumry_Info){this.ckry_Sumry_Info=ckry_Sumry_Info;}
}
