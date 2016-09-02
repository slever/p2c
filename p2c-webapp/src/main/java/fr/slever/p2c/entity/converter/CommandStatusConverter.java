package fr.slever.p2c.entity.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import fr.slever.p2c.constant.CommandStatus;

/**
 * Command status converter
 * 
 * @author sebastienlever
 *
 */
@Converter(autoApply = true)
public class CommandStatusConverter implements AttributeConverter<CommandStatus, Integer> {

  @Override
  public Integer convertToDatabaseColumn(CommandStatus status) {
    return status.code;
  }

  @Override
  public CommandStatus convertToEntityAttribute(Integer code) {
    return CommandStatus.valueOf(code);
  }
}
