package org.top.countrydictionaryapiapp.model;

// Country - государство мира
public class Country {
    private String alpha2Code;
    private String shortName;
    private String officialName;

    public Country() {}

    public Country(Country country) {
        this.alpha2Code = country.getAlpha2Code();
        this.shortName = country.getShortName();
        this.officialName = country.getOfficialName();
    }

    public Country(String alpha2Code, String shortName, String officialName) {
        this.alpha2Code = alpha2Code;
        this.shortName = shortName;
        this.officialName = officialName;
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

    @Override
    public String toString() {
        return alpha2Code + " - " + shortName + " - " + officialName;
    }
}
