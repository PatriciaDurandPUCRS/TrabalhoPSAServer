package br.com.trabalhoPSA.trabalhoPSA.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class HashingService {

    protected static final String ALGORITHM_PASS = "SHA-256";
    protected static final String salt = "Tr4b4lh0P54";
    private static Logger log = LogManager.getLogger(HashingService.class);

    public String toSHA256(String password)
    {
        String encryptedPassword = "";
        try
        {
            password = salt + password;
            MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_PASS);
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
        catch(NoSuchAlgorithmException e)
        {
            log.error("Exceção: NoSuchAlgorithmException na senha: [" + e.getLocalizedMessage() + "]");
        }
        return encryptedPassword;
    }

}
