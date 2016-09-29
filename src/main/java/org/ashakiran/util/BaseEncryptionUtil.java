package org.ashakiran.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


/**
 * DOCUMENT ME!
 *
 * @author   $author$
 * @version  $Revision$, $Date$
 */
public abstract class BaseEncryptionUtil implements Serializable {
  //~ Methods ----------------------------------------------------------------------------------------------------------

  /**
   * DOCUMENT ME!
   *
   * @param   stringToEncrypt  DOCUMENT ME!
   *
   * @return  DOCUMENT ME!
   *
   * @throws  BadPaddingException           DOCUMENT ME!
   * @throws  IllegalBlockSizeException     DOCUMENT ME!
   * @throws  InvalidKeyException           DOCUMENT ME!
   * @throws  UnsupportedEncodingException  DOCUMENT ME!
   * @throws  NoSuchAlgorithmException      DOCUMENT ME!
   * @throws  NoSuchPaddingException        DOCUMENT ME!
   */
  public abstract String encrypt(String stringToEncrypt) throws BadPaddingException, IllegalBlockSizeException,
    InvalidKeyException, UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException;
}
