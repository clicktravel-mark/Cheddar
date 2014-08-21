/*
 * Copyright 2014 Click Travel Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.clicktravel.common.random;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.junit.Test;

import com.boxbe.pub.email.EmailAddress;

public class RandomsTest {

    private static final int SAMPLE_SIZE = 20;

    @Test
    public void shouldReturnRandomString_givenLength() {
        final Set<String> randomStrings = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final String randomString = Randoms.randomString(25);
            assertNotNull(randomString);
            assertEquals(25, randomString.length());
            randomStrings.add(randomString);
        }
        assertEquals("Random sample strings should be unique", SAMPLE_SIZE, randomStrings.size());
    }

    @Test
    public void shouldReturnRandomString() {
        final Set<String> randomStrings = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final String randomString = Randoms.randomString();
            assertNotNull(randomString);
            assertFalse(randomString.isEmpty());
            randomStrings.add(randomString);
        }
        assertEquals("Random sample strings should be unique", SAMPLE_SIZE, randomStrings.size());
    }

    @Test
    public void shouldReturnRandomEmailAddress() {
        final Set<String> randomEmailAddresses = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final String randomEmailAddress = Randoms.randomEmailAddress();
            assertNotNull(randomEmailAddress);
            assertTrue(EmailAddress.isValidMailbox(randomEmailAddress));
            randomEmailAddresses.add(randomEmailAddress);
        }
        assertEquals("Random sample e-mail addresses should be unique", SAMPLE_SIZE, randomEmailAddresses.size());
    }

    @Test
    public void shouldReturnRandomDateTime() {
        final Set<DateTime> randomDateTimes = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final DateTime randomDateTime = Randoms.randomDateTime();
            assertNotNull(randomDateTime);
            randomDateTimes.add(randomDateTime);
        }
        assertEquals("Random sample DateTimes should be unique", SAMPLE_SIZE, randomDateTimes.size());
    }

    @Test
    public void shouldReturnRandomLocalDateTime() {
        final Set<LocalDateTime> randomLocalDateTimes = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final LocalDateTime randomLocalDateTime = Randoms.randomLocalDateTime();
            assertNotNull(randomLocalDateTime);
            randomLocalDateTimes.add(randomLocalDateTime);
        }
        assertEquals("Random sample LocalDateTimes should be unique", SAMPLE_SIZE, randomLocalDateTimes.size());
    }

    @Test
    public void shouldReturnRandomLocalDate() {
        final Set<LocalDate> randomLocalDates = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final LocalDate randomLocalDate = Randoms.randomLocalDate();
            assertNotNull(randomLocalDate);
            randomLocalDates.add(randomLocalDate);
        }
        assertEquals("Random sample LocalDates should be unique", SAMPLE_SIZE, randomLocalDates.size());
    }

    @Test
    public void shouldReturnRandomLocalTime() {
        final Set<LocalTime> randomLocalTimes = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final LocalTime randomLocalTime = Randoms.randomLocalTime();
            assertNotNull(randomLocalTime);
            randomLocalTimes.add(randomLocalTime);
        }
        assertEquals("Random sample LocalTimes should be unique", SAMPLE_SIZE, randomLocalTimes.size());
    }

    @Test
    public void shouldReturnRandomEnum_givenEnumClass() {
        assertTrue("Need at least 5 possible enum values for viable test", TestEnum.values().length >= 5);

        final Set<TestEnum> randomEnums = new HashSet<>();
        for (int n = 0; n < 100; n++) {
            final TestEnum randomEnum = Randoms.randomEnum(TestEnum.class);
            assertNotNull(randomEnum);
            randomEnums.add(randomEnum);
        }

        assertTrue("100 random samples should not all be same enum value: " + randomEnums.iterator().next(),
                randomEnums.size() > 1);
    }

    @Test
    public void shouldReturnNotEmptyRandomEnumSet_givenEnumClass() {
        assertTrue("Need at least 5 possible enum values for viable test", TestEnum.values().length >= 5);

        final Set<Set<TestEnum>> randomEnumSets = new HashSet<>();
        for (int n = 0; n < 100; n++) {
            final Set<TestEnum> randomEnumSet = Randoms.randomEnumSet(TestEnum.class);
            assertNotNull(randomEnumSet);
            assertFalse(randomEnumSet.isEmpty());
            randomEnumSets.add(randomEnumSet);
        }

        assertTrue("100 random samples should not all be same enum set: " + randomEnumSets.iterator().next(),
                randomEnumSets.size() > 1);
    }

    @Test
    public void shouldReturnRandomSubset_givenSet() {
        final Set<Object> originalSet = new HashSet<>();
        for (int n = 0; n < 30; n++) {
            originalSet.add(new Object());
        }

        final Set<Set<Object>> randomSubets = new HashSet<>();
        for (int n = 0; n < 100; n++) {
            final Set<Object> randomSubset = Randoms.randomSubset(originalSet);
            assertNotNull(randomSubset);
            assertTrue(originalSet.containsAll(randomSubset));
            randomSubets.add(randomSubset);
        }

        assertTrue("100 random samples should not all be same enum set: " + randomSubets.iterator().next(),
                randomSubets.size() > 1);
    }

    @Test
    public void shouldReturnRandomIntInRange_givenMax() {
        final Set<Integer> randomInts = new HashSet<>();
        for (int n = 0; n < 100; n++) {
            final int randomInt = Randoms.randomInt(Integer.MAX_VALUE);
            assertTrue(randomInt >= 0 && randomInt < Integer.MAX_VALUE);
            randomInts.add(randomInt);
        }

        assertTrue("100 random samples should not all be same int: " + randomInts.iterator().next(),
                randomInts.size() > 1);
    }

    @Test
    public void shouldReturnZero_givenRandomIntMaxZero() {
        // Given
        final int max = 0;

        // When
        final int result = Randoms.randomInt(max);

        // Then
        assertThat(result, is(0));
    }

    @Test
    public void shouldReturnRandomLong() {
        final Set<Long> randomLongs = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final long randomLong = Randoms.randomLong();
            randomLongs.add(randomLong);
        }
        assertEquals("Random sample Longs should be unique", SAMPLE_SIZE, randomLongs.size());
    }

    @Test
    public void shouldReturnRandomBoolean() {
        final Set<Boolean> randomBooleans = new HashSet<>();
        for (int n = 0; n < 100; n++) {
            final boolean randomBoolean = Randoms.randomBoolean();
            randomBooleans.add(randomBoolean);
        }
        assertTrue("100 random samples should not all be same boolean: " + randomBooleans.iterator().next(),
                randomBooleans.size() > 1);
    }

    @Test
    public void shouldReturnRandomChar() {
        final Set<Character> randomChars = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final char randomChar = Randoms.randomChar();
            randomChars.add(randomChar);
        }
        assertTrue("Random sample Chars should be unique: " + randomChars, randomChars.size() > SAMPLE_SIZE / 2);
    }

    @Test
    public void shouldReturnRandomDouble() {
        final Set<Double> randomDoubles = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final double randomDouble = Randoms.randomDouble();
            randomDoubles.add(randomDouble);
        }
        assertEquals("Random sample Doubles should be unique", SAMPLE_SIZE, randomDoubles.size());
    }

    @Test
    public void shouldReturnRandomFloat() {
        final Set<Float> randomFloats = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final float randomFloat = Randoms.randomFloat();
            randomFloats.add(randomFloat);
        }
        assertEquals("Random sample Floats should be unique", SAMPLE_SIZE, randomFloats.size());
    }

    @Test
    public void shouldReturnRandomBigDecimal_withRandomMaxValueAndPrecision() {
        final Set<BigDecimal> randomBigDecimals = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final int precision = Randoms.randomInt(5);
            final int positiveMaxValue = Randoms.randomInt(Integer.MAX_VALUE / (int) Math.pow(10, precision)) + 1;
            final BigDecimal randomBigDecimal = Randoms.randomBigDecimal(positiveMaxValue, precision);
            if (randomBigDecimal.intValue() < positiveMaxValue && randomBigDecimal.scale() == precision) {
                randomBigDecimals.add(randomBigDecimal);
            }
        }
        assertEquals("Random sample BigDecimals should be unique", SAMPLE_SIZE, randomBigDecimals.size());
    }

    @Test
    public void shouldReturnRandomPositiveBigDecimal_withRandomMaxValueAndPrecision() {
        final Set<BigDecimal> randomBigDecimals = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final int precision = Randoms.randomInt(5);
            final int positiveMaxValue = Randoms.randomInt(Integer.MAX_VALUE / (int) Math.pow(10, precision)) + 1;
            final BigDecimal randomBigDecimal = Randoms.randomPositiveBigDecimal(positiveMaxValue, precision);
            if (randomBigDecimal.compareTo(BigDecimal.ZERO) == 1 && randomBigDecimal.intValue() < positiveMaxValue
                    && randomBigDecimal.scale() == precision) {
                randomBigDecimals.add(randomBigDecimal);
            }
        }
        assertEquals("Random sample BigDecimals should be unique and positive", SAMPLE_SIZE, randomBigDecimals.size());
    }

    @Test
    public void shouldReturnPositiveBigDecimal_withMaxValueOfTwoAndPrecisionOfZero() {
        final Set<BigDecimal> randomBigDecimals = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final BigDecimal randomBigDecimal = Randoms.randomPositiveBigDecimal(2, 0);
            randomBigDecimals.add(randomBigDecimal);
        }
        assertEquals("Random sample BigDecimals should only contain 1's", 1, randomBigDecimals.size());
        assertTrue("Should not contain a non-positive value", !randomBigDecimals.contains(BigDecimal.ZERO));
    }

    @Test
    public void shouldReturnRandomId() {
        final Set<String> randomIds = new HashSet<>();
        for (int n = 0; n < SAMPLE_SIZE; n++) {
            final String randomId = Randoms.randomId();
            assertNotNull(randomId);
            randomIds.add(randomId);
        }
        assertEquals("Random sample ids should be unique", SAMPLE_SIZE, randomIds.size());
    }

}