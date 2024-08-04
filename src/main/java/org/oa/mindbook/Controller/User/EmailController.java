package org.oa.mindbook.Controller.User;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.oa.mindbook.Dto.request.User.EmailVerificationDto;
import org.oa.mindbook.Service.User.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/user")
@Tag(name = "이메일 인증 API", description = "이메일 인증 API입니다.")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/emailCheck")
    @Operation(summary = "이메일 인증 요청", description = "Request : 파라미터로 email을 담아서 보내면 해당 이메일로 전송 메세지가 전송됨 \n Response : Https Status 200")
    public ResponseEntity<?> requestEmailValidation(@RequestParam String email) {
        try {
            emailService.sendMessage(email);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/verification")
    @Operation(summary = "이메일 유효성 확인", description = "Request Body : email , verification \n 성공 / 실패는 Response Status로 구분함. 성공했을 경우 :200, 실패했을 경우 : 202")
    public ResponseEntity<?> checkEmailValidation(@RequestBody EmailVerificationDto verificationDto) {
        try {
            if (emailService.ValidationCheck(verificationDto.getEmail(), verificationDto.getVerification())){
                return new ResponseEntity<>(HttpStatus.OK); //유효성 검사 통과
            } else {
                return new ResponseEntity<>(HttpStatus.ACCEPTED); //유효성 검사 실패
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}