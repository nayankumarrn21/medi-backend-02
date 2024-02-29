package com.neudesic.MediAssistsPolicyService.modules;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "insuredAmount cannot be blank")
    private String insuredAmount;

    @NotBlank(message = "companyName cannot be blank")
    private String companyName;


    @NotBlank(message = "description cannot be blank")
    @Column(columnDefinition = "TEXT")
    private String description;

//    @NotBlank(message = "beneficiariesList cannot be blank")
//    private String beneficiariesList;

    @ElementCollection
    private List<String> beneficiariesList = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInsuredAmount() {
        return insuredAmount;
    }

    public void setInsuredAmount(String insuredAmount) {
        this.insuredAmount = insuredAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getBeneficiariesList() {
        return beneficiariesList;
    }

    public void setBeneficiariesList(List<String> beneficiariesList) {
        this.beneficiariesList = beneficiariesList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Policy{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", insuredAmount='" + insuredAmount + '\'' +
                ", companyName='" + companyName + '\'' +
                ", description='" + description + '\'' +
                ", beneficiariesList=" + beneficiariesList +
                '}';
    }
}
