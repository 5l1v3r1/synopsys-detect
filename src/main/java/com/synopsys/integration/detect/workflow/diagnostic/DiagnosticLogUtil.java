/**
 * synopsys-detect
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
package com.synopsys.integration.detect.workflow.diagnostic;

import org.slf4j.LoggerFactory;

public class DiagnosticLogUtil {
    public static ch.qos.logback.classic.Logger getLogger(String named) {
        final ch.qos.logback.classic.Logger logger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(named);
        return logger;
    }

    public static ch.qos.logback.classic.Logger getOurLogger() {
        return getLogger("com.synopsys.integration");
    }

    public static ch.qos.logback.classic.Logger getRootLogger() {
        return getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
    }
}
