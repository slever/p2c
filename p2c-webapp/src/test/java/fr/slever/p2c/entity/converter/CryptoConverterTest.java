package fr.slever.p2c.entity.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fr.slever.p2c.data.entity.converter.CryptoConverter;

public class CryptoConverterTest {

  @Test
  public void test() {
    CryptoConverter converter = new CryptoConverter();
    String value = "pwd";
    String encoded = converter.convertToDatabaseColumn(value);
    assertNotNull(encoded);
    assertEquals(value, converter.convertToEntityAttribute(encoded));

  }

}
