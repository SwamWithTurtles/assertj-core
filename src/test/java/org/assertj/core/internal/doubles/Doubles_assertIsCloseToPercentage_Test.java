/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.core.internal.doubles;

import static org.assertj.core.api.Assertions.withinPercentage;
import static org.assertj.core.data.Percentage.withPercentage;
import static org.assertj.core.error.ShouldBeEqualWithinPercentage.shouldBeEqualWithinPercentage;
import static org.assertj.core.test.TestData.someInfo;
import static org.assertj.core.test.TestFailures.failBecauseExpectedAssertionErrorWasNotThrown;
import static org.assertj.core.util.FailureMessages.actualIsNull;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.AssertionInfo;
import org.assertj.core.internal.DoublesBaseTest;
import org.junit.Test;

public class Doubles_assertIsCloseToPercentage_Test extends DoublesBaseTest {

  private static final Double ZERO = 0d;
  private static final Double ONE = 1d;
  private static final Double TWO = 2d;
  private static final Double TEN = 10d;

  @Test
  public void should_fail_if_actual_is_null() {
    thrown.expectAssertionError(actualIsNull());
    doubles.assertIsCloseToPercentage(someInfo(), null, ONE, withPercentage(ONE));
  }

  @Test(expected = NullPointerException.class)
  public void should_fail_if_expected_value_is_null() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, null, withPercentage(ONE));
  }

  @Test(expected = NullPointerException.class)
  public void should_fail_if_percentage_is_null() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, ZERO, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_fail_if_percentage_is_negative() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, ZERO, withPercentage(-1.0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_fail_if_percentage_is_greater_than_one_hundred() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, ZERO, withPercentage(101.0));
  }

  @Test
  public void should_pass_if_difference_is_less_than_given_percentage() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, ONE, withPercentage(0.1));
    doubles.assertIsCloseToPercentage(someInfo(), ONE, TWO, withPercentage(100.0));
  }

  @Test
  public void should_pass_if_difference_is_equal_to_given_percentage() {
    doubles.assertIsCloseToPercentage(someInfo(), ONE, ONE, withPercentage(ZERO));
    doubles.assertIsCloseToPercentage(someInfo(), ONE, TWO, withPercentage(50.0));
  }

  @Test
  public void should_fail_if_actual_is_not_close_enough_to_expected_value() {
    AssertionInfo info = someInfo();
    try {
      doubles.assertIsCloseToPercentage(someInfo(), ONE, TEN, withPercentage(TEN));
    } catch (AssertionError e) {
      verify(failures).failure(info, shouldBeEqualWithinPercentage(ONE, TEN, withinPercentage(TEN),
                                                                   TEN - ONE));
      return;
    }
    failBecauseExpectedAssertionErrorWasNotThrown();
  }
}
