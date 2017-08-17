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

package org.apache.flink.streaming.api.prototype.operator.oneinput.iterator;

import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.streaming.api.prototype.operator.oneinput.AbstractOneInputOperator;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.prototype.processor.StreamRecordProcessorWrapper;
import org.apache.flink.streaming.runtime.streamrecord.StreamRecord;
import org.apache.flink.util.MutableObjectIterator;

import java.io.IOException;

public abstract class AbstractOneInputStreamRecordIteratorOperator<IN, OUT>
		extends AbstractOneInputOperator<IN, OUT>
{
	protected void run(MutableObjectIterator<DataInputView> input) {
		runWithStreamRecord(new MutableObjectIterator<StreamRecord<IN>>() {

			@Override
			public StreamRecord<IN> next(StreamRecord<IN> reuse) throws IOException {
				StreamRecord<IN> element = null; // FIXME: deserialize user element]
				return element;
			}

			@Override
			public StreamRecord<IN> next() throws IOException {
				StreamRecord<IN> element = null; // FIXME: deserialize user element]
				return element;
			}
		});
	}

	public abstract void runWithStreamRecord(MutableObjectIterator<StreamRecord<IN>> input);

	@Override
	protected Processor<DataInputView> getDataProcessor() {
		return new StreamRecordProcessorWrapper<>(getProcessor());
	}

	protected abstract Processor<StreamRecord<IN>> getProcessor();
}
