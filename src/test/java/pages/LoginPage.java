package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "header-container-title")
    WebElement signInTitle;

    @FindBy(id = "input-comp-signin-username")
    WebElement username;

    @FindBy(id = "input-comp-signin-password")
    WebElement password;

    @FindBy(id = "link-comp-signin-forgot-password")
    WebElement forgotpasswordLink;

    @FindBy(id = "btn-comp-signin-signin")
    WebElement signInButton;

    @FindBy(id = "img-comp-signin-password-visibility")
    WebElement eyeIcon;

    @FindBy(id = "header-container-title")
    WebElement resetPasswordTitle;

    @FindBy(id = "header-container-warning")
    WebElement incorrectCredentials;

    @FindBy(id = "input-comp-forgotpassword-username")
    WebElement forgotPwdUsername;

    @FindBy(id = "btn-comp-forgotpassword-reset-password")
    WebElement resetPasswordButton;

    @FindBy(id = "btn-comp-forgotpassword-cancel")
    WebElement forgotPwdCancelButton;

    @FindBy(id = "header-container-warning")
    WebElement usernameError;

    @FindBy(id = "input-comp-forgotpassword-code")
    WebElement forgotPwdResetcode;

    @FindBy(id = "input-comp-forgotpassword-password")
    WebElement forgotPwdNewPassword;

    @FindBy(id = "input-comp-forgotpassword-password-check")
    WebElement getForgotPwdReEnterPwd;

    @FindBy(id = "btn-comp-forgotpassword-password-change")
    WebElement changePasswordButton;

    @FindBy(id = "link-comp-forgotpassword-login-screen")
    WebElement forgotPwdBacktoLoginLik;

    @FindBy(id = "header-container-warning")
    WebElement invalidRestcodeErr;

    @FindBy(id = "header-container-warning")
    WebElement pwdDonotMatchErr;

    @FindBy(id = "header-container-warning")
    WebElement invalidResetCode;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public WebElement getSignInTitle() {
        return signInTitle;
    }

    public WebElement getUsername() {
        return username;
    }

    public WebElement getPassword() {
        return password;
    }

    public WebElement getForgotpasswordLink() {
        return forgotpasswordLink;
    }

    public WebElement getSignInButton() {
        return signInButton;
    }

    public WebElement getEyeIcon() {
        return eyeIcon;
    }

    public WebElement getIncorrectCredentials() {
        return incorrectCredentials;
    }

    public WebElement getResetPasswordTitle() {
        return resetPasswordTitle;
    }

    public WebElement getForgotPwdUsername() {
        return forgotPwdUsername;
    }

    public WebElement getResetPasswordButton() {
        return resetPasswordButton;
    }

    public WebElement getForgotPwdCancelButton() {
        return forgotPwdCancelButton;
    }

    public WebElement getUsernameError() {
        return usernameError;
    }

    public WebElement getForgotPwdResetcode() {
        return forgotPwdResetcode;
    }

    public WebElement getForgotPwdNewPassword() {
        return forgotPwdNewPassword;
    }

    public WebElement getGetForgotPwdReEnterPwd() {
        return getForgotPwdReEnterPwd;
    }

    public WebElement getChangePasswordButton() {
        return changePasswordButton;
    }

    public WebElement getForgotPwdBacktoLoginLik() {
        return forgotPwdBacktoLoginLik;
    }

    public WebElement getInvalidRestcodeErr() {
        return invalidRestcodeErr;
    }

    public WebElement getPwdDonotMatchErr() {
        return pwdDonotMatchErr;
    }

    public WebElement getInvalidResetCode() {
        return invalidResetCode;
    }


    public void login(String uname, String pwd) {
        getUsername().sendKeys(uname);
        getPassword().sendKeys(pwd);
        getSignInButton().click();
    }

}
