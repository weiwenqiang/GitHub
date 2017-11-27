/*
   Copyright 2014 Immutables Authors and Contributors

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
package org.immutables.value.processor;

import com.google.common.collect.FluentIterable;
import com.google.common.collect.Multimap;
import java.util.Set;
import javax.annotation.processing.SupportedAnnotationTypes;
import org.immutables.generator.AbstractGenerator;
import org.immutables.value.processor.encode.EncodingMirror;
import org.immutables.value.processor.encode.Generator_Encodings;
import org.immutables.value.processor.meta.CustomImmutableAnnotations;
import org.immutables.value.processor.meta.EnclosingMirror;
import org.immutables.value.processor.meta.FConstructorMirror;
import org.immutables.value.processor.meta.FIncludeMirror;
import org.immutables.value.processor.meta.FactoryMirror;
import org.immutables.value.processor.meta.ImmutableMirror;
import org.immutables.value.processor.meta.ImmutableRound;
import org.immutables.value.processor.meta.IncludeMirror;
import org.immutables.value.processor.meta.ModifiableMirror;
import org.immutables.value.processor.meta.Proto.DeclaringPackage;
import org.immutables.value.processor.meta.Round;
import org.immutables.value.processor.meta.ValueType;
import org.immutables.value.processor.meta.ValueUmbrellaMirror;

@SupportedAnnotationTypes({
    ImmutableMirror.QUALIFIED_NAME,
    EnclosingMirror.QUALIFIED_NAME,
    IncludeMirror.QUALIFIED_NAME,
    ModifiableMirror.QUALIFIED_NAME,
    ValueUmbrellaMirror.QUALIFIED_NAME,
    FactoryMirror.QUALIFIED_NAME,
    FConstructorMirror.QUALIFIED_NAME,
    FIncludeMirror.QUALIFIED_NAME,
    EncodingMirror.QUALIFIED_NAME,
})
public final class Processor extends AbstractGenerator {
  @Override
  protected void process() {

    Round round = ImmutableRound.builder()
        .addAllAnnotations(annotations())
        .processing(processing())
        .addAllCustomImmutableAnnotations(CustomImmutableAnnotations.annotations())
        .round(round())
        .build();

    Multimap<DeclaringPackage, ValueType> values = round.collectValues();

    invoke(new Generator_Immutables().usingValues(values).generate());
    invoke(new Generator_Modifiables().usingValues(values).generate());

    if (round.environment().hasGsonLib()) {
      invoke(new Generator_Gsons().usingValues(values).generate());
    }
    if (round.environment().hasMongoModule()) {
      invoke(new Generator_Repositories().usingValues(values).generate());
    }
    if (round.environment().hasFuncModule()) {
      invoke(new Generator_Funcs().usingValues(values).generate());
    }
    if (round.environment().hasTreesModule()) {
      invoke(new Generator_Transformers().usingValues(values).generate());
      invoke(new Generator_Visitors().usingValues(values).generate());
    }
    if (round.environment().hasAstModule()) {
      invoke(new Generator_Asts().usingValues(values).generate());
    }
    if (round.environment().hasEncodeModule()) {
      invoke(new Generator_Encodings().generate());
    }
  }

  @Override
  public Set<String> getSupportedAnnotationTypes() {
    return FluentIterable.from(super.getSupportedAnnotationTypes())
        .append(CustomImmutableAnnotations.annotations())
        .toSet();
  }
}
