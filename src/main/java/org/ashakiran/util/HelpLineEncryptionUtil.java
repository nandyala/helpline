package org.ashakiran.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.net.URLDecoder;
import java.net.URLEncoder;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public class HelpLineEncryptionUtil extends BaseEncryptionUtil implements Serializable {
  //~ Instance fields --------------------------------------------------------------------------------------------------

  /** DOCUMENT ME! */
  protected final transient Log log           = LogFactory.getLog(this.getClass());
  private String                encryptionKey;
  private String                randomKey;

  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   stringToDecrypt  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public String decrypt(String stringToDecrypt) {
    String   urlEncodedEncryptedStr = "";
    String[] str                    = stringToDecrypt.split("=");

    if ((str.length == 2) && str[0].equals(getRandomKey())) {
      try {
        stringToDecrypt = str[1];

        final SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
        final Cipher    cipher    = Cipher.getInstance("AES");

        // initialise Cipher in decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKey);


        String urlDecodedStringToDecrypt = URLDecoder.decode(stringToDecrypt, "UTF-8");

        // decode base64 string into byte format, decrypt and convert into a string
        BASE64Decoder base64decoder          = new BASE64Decoder();
        byte[]        stringToDecryptAsBytes = base64decoder.decodeBuffer(urlDecodedStringToDecrypt);
        byte[]        decryptedStrAsBytes    = cipher.doFinal(stringToDecryptAsBytes);
        urlEncodedEncryptedStr = bytes2String(decryptedStrAsBytes);

      } catch (Exception e) {
        urlEncodedEncryptedStr = stringToDecrypt;
        log.error("Exception while Decrypt:\n" + e);
      } // end try-catch

    } else {
      urlEncodedEncryptedStr = stringToDecrypt;
    } // end if-else

    return urlEncodedEncryptedStr;
  } // end method decrypt

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * @see  org.ashakiran.util.BaseEncryptionUtil#encrypt(java.lang.String)
   */
  @Override public String encrypt(String stringToEncrypt) throws BadPaddingException, IllegalBlockSizeException,
    InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException {
    String urlEncodedEncryptedStr = null;

    try {
      final SecretKey secretKey = new SecretKeySpec(encryptionKey.getBytes(), "AES");
      final Cipher    cipher    = Cipher.getInstance("AES");


      // initialise Cipher in encryption mode
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);

      // encrypt string into byte format, and encode as base64 string
      byte[]        encryptedStrAsBytes = cipher.doFinal(stringToEncrypt.getBytes());
      BASE64Encoder base64encoder       = new BASE64Encoder();
      String        encodedEncryptedStr = base64encoder.encode(encryptedStrAsBytes);
      urlEncodedEncryptedStr = getRandomKey() + "=" + URLEncoder.encode(encodedEncryptedStr, "UTF-8");
    } catch (Exception e) {
      urlEncodedEncryptedStr = stringToEncrypt;
      log.error("Exception while Encrypt:\n" + e);
    }

    return urlEncodedEncryptedStr;
  } // end method encrypt

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public String getEncryptionKey() {
    return encryptionKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   */
  public String getRandomKey() {
    return randomKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  encryptionKey  DOCUMENT ME!
   */
  public void setEncryptionKey(String encryptionKey) {
    this.encryptionKey = encryptionKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param  randomKey  DOCUMENT ME!
   */
  public void setRandomKey(String randomKey) {
    this.randomKey = randomKey;
  }

  //~ ------------------------------------------------------------------------------------------------------------------

  private static String bytes2String(byte[] byteToConvert) {
    StringBuffer stringbuffer = new StringBuffer();

    for (int i = 0; i < byteToConvert.length; i++) {
      stringbuffer.append((char) byteToConvert[i]);
    }

    return stringbuffer.toString();
  }

} // end class HelpLineEncryptionUtil
