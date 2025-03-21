package com.start.pager.usersOnBoarding.dto;




public class UpdateUserProfileDto {
    private String address;
    private String dateOfBirth;
 ;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    @Override
    public String toString() {
        return "UpdateUserProfileDto{" +
                "address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +

                '}';
    }

    public UpdateUserProfileDto(String address, String dateOfBirth) {
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }
}
