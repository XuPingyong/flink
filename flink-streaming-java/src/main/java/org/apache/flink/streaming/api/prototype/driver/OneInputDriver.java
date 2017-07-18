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
import org.apache.flink.streaming.api.prototype.operator.OneInputOperator;
import org.apache.flink.streaming.api.prototype.processor.InputProcessor;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.runtime.io.CheckpointBarrierHandler;
import org.apache.flink.streaming.runtime.io.StreamInputProcessor;

/**
 * This class should acting almost like current {@link StreamInputProcessor}
 */
public class OneInputDriver {

	private final OneInputOperator operator;

	private final CheckpointBarrierHandler barrierHandler;

	public OneInputDriver() {
		this.operator = null;
		this.barrierHandler = null;
	}

	public boolean run() throws Exception {
		InputProcessor processor = operator.getInputProcessor();

		Processor<DataInputView> recordProcessor = processor.getDataProcessor();
		Processor<Watermark> watermarkProcessor = processor.getWatermarkProcessor();

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
				recordProcessor.process(data);
			}
		}
	}
}
