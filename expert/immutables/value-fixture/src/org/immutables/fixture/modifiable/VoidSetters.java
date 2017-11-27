/*
   Copyright 2017 Immutables Authors and Contributors

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package org.immutables.fixture.modifiable;

import java.util.Arrays;
import java.util.List;
import org.immutables.value.Value;

@Value.Immutable
@Value.Modifiable
@Value.Style(beanFriendlyModifiables = true, create = "new")
public interface VoidSetters {
  int getAa();

  String getBb();

  List<Double> getCc();

  default void use() {
    ModifiableVoidSetters m = new ModifiableVoidSetters();
    m.setAa(2);
    m.setBb("bb");
    m.setCc(Arrays.asList(1.1, 2.2, 3.3));
  }
}
