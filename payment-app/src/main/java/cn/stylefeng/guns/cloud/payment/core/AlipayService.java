package cn.stylefeng.guns.cloud.payment.core;

import cn.stylefeng.guns.cloud.payment.entity.Payment;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

/**
 * @program: guns-cloud-parent
 * @description
 * @author: wzx
 * @create: 2021-05-08 14:31
 **/
public class AlipayService implements PayService{

    @Override
    public Object submitOrder(Payment payment) throws AlipayApiException {
        String private_key="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQChXGVcNSy+lflaxN1hrax2wCNewHd2p8pgivWYK1OHxsig2io6xKr+eVv9U3gp/DAIT35SiBS6KmUD/EgHrcDC0tE+A5V49F01UB5rWFWmqGDjrfZvCS2sPv9QWYDYWx8W0/CkJrYP39TZ1YH11HaaMtcGSopAtbhTbvJYzVaX+mggp/5y/IAPCyMV4efQG4dhBJp8gKYiZ0tkkrQR/xTfQGzyAsW5DTLGoFGoZaEJug/D5Y7DijeJfolw/+X8+pT91W7LXWVBdRjuG/EvAmmtxQlBeaU5sM1PnUpUaN8wdQRq90SfozW8yyfnwnAy/BIC6GC+VLj5Dw//u6irRWqrAgMBAAECggEAMIf4mF4J4hx7TdiN+PXiPCkXukTK6W4XySeiLC1Dxz97Uh2ru9cw0q6vgyaaqo4F6En4ZVw2V7OuhB9Zjq2snywG8M7lIubkLce2DnUX9bMIyRGmX8jz6q1b3s+8l9X4KMZ/xu85rNaWTxoPJ1/KeCA4WdULdeDx2m4xB5Jiq/ySyS6xQcfby1zZQ42a5EieoFoNWdmZezHH5cHNukraQ6QKLV8S7AX7E/QjlWclW+oMIC2KPYF+JDle5OoOZcGjIMbkPZXZvT5bHoRdVkAldJpi8IU42+Q8VIsTkIeERKF/16KgZuS4NcCx/3OIB2xUHiomz26KWjpDG71eVXo/wQKBgQDaQ17xtwLslS1MUXRwv/E+OErMtcEENiqZOgZS3xAEThdggHMviNQSNHa9BZR5JKA2S2vbbgekkaJdY1HYrRZtXO9VskfSWTaoqKNw8wWhkdVskClN07ScjmH763HUe8rhCJONXM04ekBYNYR8e40W4/dZQWJ7zPPYmTRXrhD8ywKBgQC9QnP+iFjv5LiEaEF7qYerFiUFWhTTzFKu0kZopxM3FQHaq2RILuL93mWxjXFcoxekHURuwi5uef1n75aNofkNr870KveUw2I3AqflE1sQIQd586pulHQq7RyaHsqWcCuvIUNjmclqd5XKBMw5yR46xFyr4OG/VnbQaIJumlxtoQKBgFrcBOA2wV7fUvYcSo7NOI4gPPMvDmTEb8NNpmhFN5OzNoQTtN+q5C2X7X4Onpo3pwIzL3kvOrtFbQPXXrEGjPmF4JQaHU/xl5Fqcm+Im2WBIgmJkcg2Q0+Q7jVC9CXySqiXO2VzpnLGYZbW4Io5nKhUyhk74NWMok4eqO1/Js81AoGBAIAF78kCGodMRLTsgT9RkoBc3nYcVXJgCJ5Rh2dLfWgJQdPVlGRvswTf6BKPB1wOxvMxCRWTLS12P8CLBWCRbJOhlXD9j7SF5m4n0cLXsQXcEd6gwW1D5sES3AI1Sf0xq5sv+RHq+aCXfC/Hxw5fyZ8fLUDnQk3YA08/ItwGdveBAoGAB+FRA9SFpL1g1/340mQ8plR+fmN777pjWs8RMjdSgcQljvWGQrP7YCM5Ib+piDwF1o2lzOjS4FjU2Sblk5gT0JbduwOyt7eozn+pVEm4/Zbe2+0D6G2d7QnPqZi9K9h0dR027dlTvF4AX5n2dLHXScPbFmceb+U/QyP+FZ9bC6E=";
        String public_key="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoVxlXDUsvpX5WsTdYa2sdsAjXsB3dqfKYIr1mCtTh8bIoNoqOsSq/nlb/VN4KfwwCE9+UogUuiplA/xIB63AwtLRPgOVePRdNVAea1hVpqhg4632bwktrD7/UFmA2FsfFtPwpCa2D9/U2dWB9dR2mjLXBkqKQLW4U27yWM1Wl/poIKf+cvyADwsjFeHn0BuHYQSafICmImdLZJK0Ef8U30Bs8gLFuQ0yxqBRqGWhCboPw+WOw4o3iX6JcP/l/PqU/dVuy11lQXUY7hvxLwJprcUJQXmlObDNT51KVGjfMHUEavdEn6M1vMsn58JwMvwSAuhgvlS4+Q8P/7uoq0VqqwIDAQAB";
        String app_id="2021002138693017";
        AlipayClient alipayClient=new DefaultAlipayClient(
                "https://openapi.alipay.com/gateway.do",
                app_id,
                private_key,
                "json",
                "utf-8",
                public_key,
                "RSA2"
        );

        AlipayTradeAppPayRequest request=new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model=new AlipayTradeAppPayModel();
        model.setBody(payment.getPayBody());
        model.setSubject(payment.getPaySubject());
        model.setOutTradeNo(payment.getOrderNo());
        //model.setTimeExpire("30m");
        model.setTotalAmount(payment.getPayAmount().toString());
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl("http://www.baidu.com/notify");
        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
        if(response.isSuccess())
        {
            System.out.println("调用成功");
            return response.getBody();
        }
        else {
            return null;
        }
    }

    @Override
    public void orderNotify() {

    }
}
