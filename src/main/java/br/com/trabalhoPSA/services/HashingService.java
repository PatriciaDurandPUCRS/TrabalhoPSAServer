package br.com.trabalhoPSA.services;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class HashingService {

    private static Logger log = LogManager.getLogger(HashingService.class);

    public String toSHA256(String password)
    {
        String encryptedPassword = "";
        try
        {
            password = "trabalhopsapucrs" + password;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(password.getBytes());
            byte byteData[] = messageDigest.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i < byteData.length; i++)
            {
                String hex=Integer.toHexString(0xff & byteData[i]);
                if(hex.length()==1)
                {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            encryptedPassword =  hexString.toString();
        }
        catch(Exception e)
        {
            log.error("Ocorreu um erro ao gerar o hash da senha.");
            log.error("[" + e.getLocalizedMessage() + "]");
        }
        return encryptedPassword;
    }

}

