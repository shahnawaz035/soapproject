package com.shah.soap.securewithcertservice.dto;
import jakarta.xml.bind.annotation.*;

@XmlType(name = "CalculateSumResponse")
@XmlAccessorType(XmlAccessType.FIELD)
public class CalculateSumResponse {
    @XmlElement(name = "response")
    private Integer result;


    public CalculateSumResponse(){

    }
    public CalculateSumResponse(int result) {
        this.result = result;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
