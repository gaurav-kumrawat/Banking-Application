package com.psl.banking.ChequeMicroservice.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "cheque")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {

    @Id
    @GeneratedValue
    private long chequeId;

    @NotBlank
    private String bankName;

    @Pattern(regexp = "^[A-Z]{4}0[A-Z0-9]{6}$",message = "Invalid IFSC Code")
    private  String ifscCode;

    @Pattern(regexp = "^\\d{9}$",message = "Invalid MICR Code")
    private  String micrCode;

    @Pattern(regexp = "^\\d{6}$",message = "Invalid Cheque Number")
    private String  chequeNumber;

    @NotNull
    private double amount;

    private String date;
    private  boolean isChequeValid;
    private  boolean isChequeEnhanced;

    @Override
    public String toString() {
        return "Cheque{" +
                "chequeId=" + chequeId +
                ", bankName='" + bankName + '\'' +
                ", ifscCode='" + ifscCode + '\'' +
                ", micrCode='" + micrCode + '\'' +
                ", chequeNumber='" + chequeNumber + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", isChequeValid=" + isChequeValid +
                ", isChequeEnhanced=" + isChequeEnhanced +
                '}';
    }
}
