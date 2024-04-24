package com.TicketBooking.phonePe;

import com.phonepe.sdk.pg.common.http.PhonePeResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.PgPaymentInstrument;
import com.phonepe.sdk.pg.payments.v1.models.response.PgTransactionStatusResponse;
import com.phonepe.sdk.pg.payments.v1.models.response.UPIPaymentInstrumentResponse;
import org.springframework.beans.factory.annotation.Autowired;

//public class TransactionStatus {
//
//    @Autowired
//    PhonePeClient phonePeClient;
//    PhonePeResponse<PgTransactionStatusResponse> statusResponsePhonePeResponse = phonePeClient.getPhonepeClient().checkStatus(phonePeClient.getMerchantId());
//    PgPaymentInstrument pgPaymentInstrument = statusResponsePhonePeResponse.getData().getPaymentInstrument();
//    final UPIPaymentInstrumentResponse upiPaymentInstrumentResponse = (UPIPaymentInstrumentResponse)pgPaymentInstrument;
//    String utr = upiPaymentInstrumentResponse.getUtr();
//    String ifsc = upiPaymentInstrumentResponse.getIfsc();
//}
