package com.TicketBooking.QRCode;

import com.TicketBooking.InstitutionRegistration.Institution;
import com.TicketBooking.InstitutionRegistration.InstitutionRepo;
import com.TicketBooking.InstitutionRegistration.InstitutionService;
import com.TicketBooking.userRegistration.User;
import com.TicketBooking.userRegistration.UserRepo;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class QRService {
    @Autowired
    QRCodeService qrCodeService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    private InstitutionRepo institutionRepo;

    @Autowired
    InstitutionService institutionService;

    @Autowired
    private JavaMailSender mailSender;


    public QRCodeResponse generateAndFetchQrCode(QRData qrData) throws WriterException, IOException {
        String paymentId = qrData.getPaymentId();

        User user = findUserDetails(paymentId);

        if (user!=null){
            String qrCodeDetails = createBookingInfo(user);
            //return generateQrCodeResponse(qrCodeDetails);
            QRCodeResponse response = generateQrCodeResponse(qrCodeDetails);
            sendEmail(user.getEmail(),response.getUserDetails(),response.getQrCodeImage());
            return response;
        }

        Institution institution = findInstitutionDetails(paymentId);
        if (institution !=null){
            String qrCodeDetails = createBookingInfo(institution);
            return generateQrCodeResponse(qrCodeDetails);
        }

        return generateErrorResponse("Details Not Found");



    }

    private void sendEmail(String email, String userDetails, byte[] qrCodeImage) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("museumaksharam@gmail.com");

        message.setTo(email);
        message.setSubject("Aksharam Museum");

        StringBuffer htmlContent = new StringBuffer();
        htmlContent.append("<html><body><h3> Aksharam Museum:</h3><p>").append(userDetails).append("</p><br>");
        htmlContent.append("<img src=\"data:image/png;base64,").append(Base64.getEncoder().encodeToString(qrCodeImage)).append("\" alt=\"QR Code\">");
        htmlContent.append("</body></html>");

        message.setText(htmlContent.toString());
        mailSender.send(message);
    }


    private User findUserDetails(String paymentId) {
        return userRepo.findByPaymentId(paymentId);
    }

    private Institution findInstitutionDetails(String paymentId) {
        return institutionRepo.findByPaymentId(paymentId);
    }
    private String createBookingInfo(User user) {
        return String.format("Name: %s, Adults: %d, Children: %d, Date: %s, Amount: %d, Payment ID: %s",
                user.getName(),user.getAdult(),user.getChild(),user.getBookDate(),user.getTotal()
                ,user.getPaymentId());
    }

    private String createBookingInfo(Institution institution) {
        return String.format("Name: %s, Adults: %d, Children: %d, Date: %s, Amount: %d, Payment ID: %s",
                institution.getName(),institution.getTeachers(),institution.getStudent(),
                institution.getBookDate(),institution.getTotal(),institution.getPaymentId());
    }



    private QRCodeResponse generateQrCodeResponse(String qrCodeDetails) throws WriterException,IOException{
        byte[] qrCodeImage = qrCodeService.generateQrCode(qrCodeDetails);

        QRCodeResponse response = new QRCodeResponse();
        response.setQrCodeImage(qrCodeImage);
        response.setUserDetails(qrCodeDetails);
        return response;
    }

    private QRCodeResponse generateErrorResponse(String errorMessage) {
        QRCodeResponse qrCodeResponse = new QRCodeResponse();
        qrCodeResponse.setErrorVariable(errorMessage);
        return qrCodeResponse;
    }


}
