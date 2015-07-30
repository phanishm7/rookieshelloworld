package demo.domain;

/**
 * Created by z077430 on 4/16/15.
 */
public class PhonePin {
    public PhonePin(String phoneNumber, String authenticationStatus) {
        this.phoneNumber = phoneNumber;
        this.authenticationStatus = authenticationStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAuthenticationStatus() {
        return authenticationStatus;
    }

    private String phoneNumber;
    private String authenticationStatus;
}
