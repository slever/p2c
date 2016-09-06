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
