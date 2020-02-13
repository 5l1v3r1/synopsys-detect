/**
 * detector
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
package com.synopsys.integration.detector.result

import com.synopsys.integration.detectable.Detectable
import com.synopsys.integration.detectable.detectable.result.FailedDetectableResult
import com.synopsys.integration.detector.rule.DetectorRule

open class DetectorResult(val passed: Boolean, val description: String)

open class FailedDetectorResult(description: String) : DetectorResult(false, description)

open class PassedDetectorResult(description: String = "Passed.") : DetectorResult(true, description)

class ExcludedDetectorResult : FailedDetectorResult("Detector type was excluded.")

class ForcedNestedPassedDetectorResult : PassedDetectorResult("Forced to pass because nested forced by user.")

class MaxDepthExceededDetectorResult(depth: Int, maxDepth: Int) : FailedDetectorResult("Max depth of $maxDepth exceeded by $depth")

class NotNestableDetectorResult : FailedDetectorResult("Not nestable and a detector already applied in parent directory.")

class NotSelfNestableDetectorResult : FailedDetectorResult("Nestable but this detector already applied in a parent directory.")

class YieldedDetectorResult(yieldedTo: Set<String>) : FailedDetectorResult("Yielded to detectors: ${yieldedTo.joinToString(",")}")

class FallbackNotNeededDetectorResult(private val passingDetector: DetectorRule<Detectable>) : FailedDetectableResult() {
    override fun toDescription(): String {
        return "No fallback needed, detector passed: " + passingDetector.getDescriptiveName()
    }
}