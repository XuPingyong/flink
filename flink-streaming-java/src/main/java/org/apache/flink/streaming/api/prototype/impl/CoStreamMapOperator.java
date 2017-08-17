/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.streaming.api.prototype.impl;

import org.apache.flink.streaming.api.prototype.operator.twoinput.AbstractTwoInputStreamRecordOperator;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.prototype.input.ArbitraryInputOrder;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;

public class CoStreamMapOperator<IN1, IN2, OUT>
		extends AbstractTwoInputStreamRecordOperator<IN1, IN2, OUT>
		implements ArbitraryInputOrder {

	@Override
	protected Processor<StreamRecord<IN1>> getProcessor1() {
		return new Processor<StreamRecord<IN1>>() {
			@Override
			public void process(StreamRecord<IN1> record) throws Exception {

			}
		};
	}

	@Override
	protected Processor<StreamRecord<IN2>> getProcessor2() {
		return new Processor<StreamRecord<IN2>>() {
			@Override
			public void process(StreamRecord<IN2> record) throws Exception {

			}
		};
	}
}
