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

package org.apache.flink.streaming.api.prototype.operator.twoinput.iterator.arbitary;

import org.apache.flink.core.memory.DataInputView;
import org.apache.flink.streaming.api.prototype.input.ArbitraryInputOrder;
import org.apache.flink.streaming.api.prototype.input.FixedInputOrder;
import org.apache.flink.streaming.api.prototype.processor.ControlElementProcessor;
import org.apache.flink.util.MutableObjectIterator;

public interface ArbitaryOrderTwoInputIteratorOperator extends ArbitraryInputOrder {

	ControlElementProcessor getControlElementProcessor1();

	ControlElementProcessor getControlElementProcessor2();

	void run(MutableObjectIterator<DataInputView> input1, MutableObjectIterator<DataInputView> input2);

}
