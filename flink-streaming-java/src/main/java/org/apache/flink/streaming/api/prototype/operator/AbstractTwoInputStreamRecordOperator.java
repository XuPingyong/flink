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

package org.apache.flink.streaming.api.prototype.operator;

import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.prototype.processor.StreamRecordProcessorWrapper;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;

public abstract class AbstractTwoInputStreamRecordOperator<IN1, IN2, OUT>
		extends AbstractTwoInputOperator<OUT> {

	@Override
	protected Processor<DataInputView> getDataProcessor1() {
		return new StreamRecordProcessorWrapper<>(getProcessor1());
	}

	@Override
	protected Processor<DataInputView> getDataProcessor2() {
		return new StreamRecordProcessorWrapper<>(getProcessor2());
	}

	protected abstract Processor<StreamRecord<IN1>> getProcessor1();

	protected abstract Processor<StreamRecord<IN2>> getProcessor2();

}
