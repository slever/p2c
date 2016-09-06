/**
 * Copyright 2016 sebastien lever
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package fr.slever.p2c.entity.converter;

import java.security.InvalidParameterException;
import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.springframework.util.StringUtils;

import fr.slever.p2c.exception.TechnicalException;

/**
 * Crypto converter
 * 
 * @author sebastienlever
 *
 */
@Converter(autoApply = false)
public class CryptoConverter implements AttributeConverter<String, String> {

  private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
  private static final byte[] KEY = "7a1996bf-a72f-40".getBytes();

  @Override
  public String convertToDatabaseColumn(String ccNumber) {
    if (StringUtils.isEmpty(ccNumber)) {
      throw new InvalidParameterException("parameter cannot be null");
    }
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.ENCRYPT_MODE, key);
      return Base64.getEncoder().encodeToString(c.doFinal(ccNumber.getBytes()));
    } catch (Exception e) {
      throw new TechnicalException("Error converting to database :" + ccNumber, e);
    }
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (StringUtils.isEmpty(dbData)) {
      throw new InvalidParameterException("parameter cannot be null");
    }
    Key key = new SecretKeySpec(KEY, "AES");
    try {
      Cipher c = Cipher.getInstance(ALGORITHM);
      c.init(Cipher.DECRYPT_MODE, key);
      return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
    } catch (Exception e) {
      throw new TechnicalException("Error converting from database :" + dbData, e);
    }
  }
}
