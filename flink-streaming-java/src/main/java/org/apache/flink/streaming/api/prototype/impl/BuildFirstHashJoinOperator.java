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

import org.apache.flink.streaming.api.prototype.operator.twoinput.AbstractTwoInputElementOperator;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.prototype.input.FixedInputOrder;

public class BuildFirstHashJoinOperator<IN1, IN2, OUT>
		extends AbstractTwoInputElementOperator<IN1, IN2, OUT>
		implements FixedInputOrder
{

	@Override
	public int[] getInputOrder() {
		return new int[] {0, 1};
	}

	@Override
	protected Processor<IN1> getProcessor1() {
		return new Processor<IN1>() {
			@Override
			public void process(IN1 record) throws Exception {

			}
		};
	}

	@Override
	protected Processor<IN2> getProcessor2() {
		return new Processor<IN2>() {
			@Override
			public void process(IN2 record) throws Exception {

			}
		};
	}
}

