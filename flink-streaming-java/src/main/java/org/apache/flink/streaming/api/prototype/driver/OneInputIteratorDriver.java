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

package org.apache.flink.streaming.api.prototype.driver;

import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.runtime.io.network.partition.consumer.BufferOrEvent;
import org.apache.flink.streaming.api.prototype.operator.oneinput.iterator.OneInputIteratorOperator;
import org.apache.flink.streaming.api.prototype.processor.ControlElementProcessor;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.runtime.io.CheckpointBarrierHandler;
import org.apache.flink.streaming.runtime.io.StreamInputProcessor;
import org.apache.flink.util.MutableObjectIterator;

import java.io.IOException;

/**
 * This class should acting almost like current {@link StreamInputProcessor}
 */
public class OneInputIteratorDriver {

	private final OneInputIteratorOperator operator;

	private final CheckpointBarrierHandler barrierHandler;

	public OneInputIteratorDriver() {
		this.operator = null;
		this.barrierHandler = null;
	}

	public void run() throws Exception {
		MutableObjectIterator<DataInputView> input = new DataInputViewIterator(operator, barrierHandler);
		operator.run(input);
	}

	private static class DataInputViewIterator implements MutableObjectIterator<DataInputView> {

		private final CheckpointBarrierHandler barrierHandler;
		private final Processor<Watermark> watermarkProcessor;

		public DataInputViewIterator(OneInputIteratorOperator operator, CheckpointBarrierHandler barrierHandler) throws Exception {
			this.barrierHandler = barrierHandler;
			ControlElementProcessor processor = operator.getControlElementProcessor();
			watermarkProcessor = processor.getWatermarkProcessor();
		}

		@Override
		public DataInputView next(DataInputView reuse) throws IOException {
			return next();
		}

		@Override
		public DataInputView next() throws IOException {
			try {
				while (true) {
					final BufferOrEvent bufferOrEvent = barrierHandler.getNextNonBlocked();
					// use deserializer to detect the boundary of each possible message
					//
					// ----+-----------+-----------+-------------------+-------
					//  .. + watermark |  record1  |      record2      | ....
					// ----+-----------+-----------+-------------------+-------
					//                 ^           ^
					//                 |           |
					//
					//  we need mechanism to detect the length of each event or user record,
					//  not sure current TypeSerializer can do this.

					boolean isWatermark = false;
					boolean isUserRecord = false;
					if (isWatermark) {
						Watermark watermark = null;
						watermarkProcessor.process(watermark);

					} else if (isUserRecord) {
						DataInputView data = null;
						return data;
					}
				}
			} catch (Exception ex) {
				throw new IOException(ex);
			}
		}
	}
}
