package fit.edu.tmdt.shoes_store_api.controller.client;

import fit.edu.tmdt.shoes_store_api.Utils.ResponseUtil;
import fit.edu.tmdt.shoes_store_api.constant.Message;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginDTO;
import fit.edu.tmdt.shoes_store_api.dto.Authen.LoginResponse;
import fit.edu.tmdt.shoes_store_api.dto.User.AccountDTO;
import fit.edu.tmdt.shoes_store_api.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("${api.prefix}/client-api/auth")
@Log4j2
public class ClientAuthController {
    @Autowired
    AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody AccountDTO accountDTO) {
        String response = accountService.register(accountDTO);
        return ResponseUtil.getResponseWithMessage(response, Message.ACCOUNT_EXIST, OK);
    }

    @PatchMapping("/confirm-OTP/{id}")
    public ResponseEntity<String> confirmOTP(@PathVariable Long id, @RequestParam(name = "otp", defaultValue = "000000") String otp, @RequestParam(name = "reset-password", defaultValue = "true") boolean resetPassword) {
        String token = accountService.checkToken(id, otp, resetPassword);
        return ResponseUtil.getResponseWithMessage(token, Message.ACCOUNT_NOT_EXIST, OK);
    }

    @PatchMapping("/reset-OTP/{id}")
    public ResponseEntity resetOTP(@PathVariable Long id, @RequestParam(name = "reset-password", defaultValue = "true") boolean resetPassword) {
        return ResponseUtil.getResponse(accountService.resetToken(id, resetPassword), OK);
    }

    @PutMapping("/forgot/{username}/{email}")
    public ResponseEntity<Serializable> forgotPassword(@PathVariable String username, @PathVariable String email) {
        Long accountId = accountService.forgotPassword(username, email);
        return ResponseUtil.getResponseWithMessage(accountId, Message.ACCOUNT_NOT_EXIST, OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDTO) {
        return ResponseUtil.getResponse(accountService.login(loginDTO), OK);
    }

}
