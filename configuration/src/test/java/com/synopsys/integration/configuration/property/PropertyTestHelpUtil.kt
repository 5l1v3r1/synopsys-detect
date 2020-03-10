/**
 * configuration
 *
 * Copyright (c) 2020 Synopsys, Inc.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.synopsys.integration.configuration.property

import com.synopsys.integration.configuration.property.base.NullableProperty
import com.synopsys.integration.configuration.property.base.TypedProperty
import com.synopsys.integration.configuration.property.base.ValuedListProperty
import com.synopsys.integration.configuration.property.base.ValuedProperty
import org.junit.jupiter.api.Assertions

/**
 * Ensure's the Property classes are providing necessary property descriptions for help doc.
 */
public class PropertyTestHelpUtil {
    companion object {
        //#region Recommended Usage

        @JvmStatic
        fun assertAllHelpValid(property: NullableProperty<*>) {
            assertAllHelpValid(property, null)
        }

        @JvmStatic
        fun assertAllHelpValid(property: NullableProperty<*>, expectedExampleValues: List<String>?) {
            assertValidTypeDescription(property)
            assertHasExampleValues(property, expectedExampleValues)
        }

        @JvmStatic
        fun assertAllHelpValid(property: ValuedProperty<*>) {
            assertAllHelpValid(property, null)
        }

        @JvmStatic
        fun assertAllHelpValid(property: ValuedProperty<*>, expectedExampleValues: List<String>?) {
            assertValidTypeDescription(property)
            assertHasDefaultDescription(property)
            assertHasExampleValues(property, expectedExampleValues)
        }

        @JvmStatic
        fun assertAllHelpValid(property: ValuedListProperty<*>) {
            assertAllHelpValid(property, null)
        }

        @JvmStatic
        fun assertAllHelpValid(property: ValuedListProperty<*>, expectedExampleValues: List<String>?) {
            assertValidTypeDescription(property)
            assertHasDefaultDescription(property)
            assertHasExampleValues(property, expectedExampleValues)
        }

        //#endregion Recommended Usage

        //#region Advanced Usage

        private const val nullableTypeDescriptionPrefix = "Optional "
        private const val valuedTypeDescriptionPostfix = " List"

        @JvmStatic
        fun assertValidTypeDescription(property: NullableProperty<*>) {
            assertHasTypeDescription(property)
            Assertions.assertTrue(property.describeType()!!.startsWith(nullableTypeDescriptionPrefix), "${property.javaClass.simpleName} is a ${NullableProperty::class.java.simpleName} so its type description should start with '$nullableTypeDescriptionPrefix'.")
        }

        @JvmStatic
        fun assertValidTypeDescription(property: ValuedProperty<*>) {
            assertHasTypeDescription(property)
            Assertions.assertFalse(property.describeType()!!.startsWith(nullableTypeDescriptionPrefix), "${property.javaClass.simpleName} is a ${ValuedProperty::class.java.simpleName} so its type description should not start with '$nullableTypeDescriptionPrefix'.")
            Assertions.assertFalse(property.describeType()!!.endsWith(valuedTypeDescriptionPostfix), "${property.javaClass.simpleName} is a ${ValuedProperty::class.java.simpleName} so its type description should not start with '$valuedTypeDescriptionPostfix'.")
        }

        @JvmStatic
        fun assertValidTypeDescription(property: ValuedListProperty<*>) {
            assertHasTypeDescription(property)
            Assertions.assertTrue(property.describeType()!!.endsWith(valuedTypeDescriptionPostfix), "${property.javaClass.simpleName} is a ${ValuedListProperty::class.java.simpleName} so its type description should end with '$valuedTypeDescriptionPostfix'.")
            Assertions.assertNotNull(property.describeType(), "${property.javaClass.simpleName} is a typed property such and should describe its type.")
        }

        @JvmStatic
        private fun assertHasTypeDescription(property: TypedProperty<*>) {
            Assertions.assertNotNull(property.describeType(), "${property.javaClass.simpleName} is a typed property such and should describe its type.")
        }

        @JvmStatic
        fun assertHasExampleValues(property: TypedProperty<*>, expectedExampleValues: List<String>?) {
            if (expectedExampleValues != null) {
                Assertions.assertNotNull(property.listExampleValues(), "A ${property.describeType()} property has a limited number of values that should be described.")
                Assertions.assertEquals(expectedExampleValues.sorted(), property.listExampleValues()!!.sorted(), "The ${property.javaClass.simpleName} provided unexpected example values.")
            }
        }

        @JvmStatic
        fun assertHasDefaultDescription(property: ValuedProperty<*>) {
            Assertions.assertNotNull(property.describeDefault(), "${property.javaClass.simpleName} is a non-null typed property and should describe its default value.")
        }

        //#endregion Advanced Usage
    }
}