package org.top.countrydictionaryapiapp.storage;

import jakarta.persistence.*;

// CountryRow - строка в таблице стран в БД
@Entity
@Table(name="country_t")
public class CountryRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="alpha2Code", unique = true, nullable = false, length = 2)
    private String alpha2Code;

    @Column(name="shortName", nullable = false)
    private String shortName;

    @Column(name="officialName", nullable = false)
    private String officialName;

    public CountryRow() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }
}
