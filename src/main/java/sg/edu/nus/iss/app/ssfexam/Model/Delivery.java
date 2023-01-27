package sg.edu.nus.iss.app.ssfexam.Model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class Delivery {

    @NotNull(message = "Name cannot be empty")
    @Length(min = 3, message = "min 3 characters")
    private String name;

    @NotNull
    private String address;

    @NotNull(message = "Phone number cannot be empty")
    @Pattern(regexp = "^[0-9]{8}", message = "Phone number must be 8 digits")
    private String phone;

    private Boolean isRush;

    private String comments;

    public Delivery(
            @NotNull(message = "Name cannot be empty") @Length(min = 3, message = "min 3 characters") String name,
            @NotNull String address,
            @NotNull(message = "Phone number cannot be empty") @Pattern(regexp = "[0-9]{8}", message = "Phone number must be 8 digits") String phone,
            Boolean isRush, String comments) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.isRush = isRush;
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Delivery [name=" + name + ", address=" + address + ", phone=" + phone + ", isRush=" + isRush
                + ", comments=" + comments + "]";
    }

    public Delivery() {
        // noArgsConstructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIsRush() {
        return isRush;
    }

    public void setIsRush(Boolean isRush) {
        if (isRush == null) {
            isRush = false;
        }
        this.isRush = isRush;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        if (comments == null) {
            comments = "";
        }
        this.comments = comments;
    }

}
