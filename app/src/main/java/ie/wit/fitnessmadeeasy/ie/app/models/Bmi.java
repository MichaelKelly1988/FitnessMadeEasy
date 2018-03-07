package ie.wit.fitnessmadeeasy.ie.app.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mikel_000 on 05/03/2017.
 */

public class Bmi {
    //public double height;
    // public double weight;
    public String weight;
    public String height;
    public String bmiLabel;
   public String bmiDate;




    public Bmi(){


    }


  //  public Bmi ( String bmiLabel, String bmiDate)
  //  {

       // this.height = height;
       // this.weight = weight;
       // this.bmiLabel = bmiLabel;
      //  this.bmiDate = bmiDate;
  //  }

    public Bmi(String bmiLabel, String bmiDate) {
;
        this.bmiLabel = bmiLabel;
       this.bmiDate = bmiDate;

    }

    public void addAll(String bmiLabel, String bmiDate) {

        this.bmiLabel = bmiLabel;
        this.bmiDate = bmiDate;
    }


    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setHeight(String height) {
        this.height = height;
    }



    public void setBmiLabel(String bmiLabel) {
        this.bmiLabel = bmiLabel;
    }

    public void setBmiDate(String bmiDate) {
        this.bmiDate = bmiDate;
    }


}











