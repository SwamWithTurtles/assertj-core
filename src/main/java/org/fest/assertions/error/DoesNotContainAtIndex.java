/*
 * Created on Nov 20, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010-2011 the original author or authors.
 */
package org.fest.assertions.error;

import java.util.List;

import org.fest.assertions.data.Index;

/**
 * Creates an error message indicating that an assertion that verifies a group of elements contains a value at a given
 * index failed. A group of elements can be a collection, an array or a {@code String}.
 *
 * @author Alex Ruiz
 */
public class DoesNotContainAtIndex extends BasicErrorMessage {

  /**
   * Creates a new </code>{@link DoesNotContainAtIndex}</code>.
   * @param actual the actual value in the failed assertion.
   * @param expected value expected to be in {@code actual}.
   * @param index the index of the expected value.
   * @return the created {@code ErrorMessage}.
   */
  public static ErrorMessage doesNotContainAtIndex(List<?> actual, Object expected, Index index) {
    return new DoesNotContainAtIndex(actual, expected, index, actual.get(index.value));
  }

  private DoesNotContainAtIndex(Object actual, Object expected, Index index, Object found) {
    super("expecting <%s> at index <%s> but found <%s> in <%s>", expected, index.value, found, actual);
  }
}
