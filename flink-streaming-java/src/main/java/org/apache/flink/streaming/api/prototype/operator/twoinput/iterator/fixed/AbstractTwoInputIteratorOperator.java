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

package org.apache.flink.streaming.api.prototype.operator.twoinput.iterator.fixed;

import org.apache.flink.streaming.api.prototype.operator.AbstractOperator;
import org.apache.flink.streaming.api.prototype.processor.ControlElementProcessor;
import org.apache.flink.streaming.api.prototype.processor.Processor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.runtime.streamrecord.LatencyMarker;

public abstract class AbstractTwoInputIteratorOperator<OUT>
		extends AbstractOperator<OUT>
		implements TwoInputIteratorOperator
{

	@Override
	public int[] getInputOrder() {
		return new int[]{0, 1};
	}

	@Override
	public ControlElementProcessor getControlElementProcessor1() {
		return new ControlElementProcessor() {

			@Override
			public Processor<Watermark> getWatermarkProcessor() throws Exception {
				return AbstractTwoInputIteratorOperator.this.getWatermarkProcessor1();
			}

			@Override
			public Processor<LatencyMarker> getLatencyMarkerProcessor() throws Exception {
				return AbstractTwoInputIteratorOperator.this.getLatencyMarkerProcessor1();
			}
		};
	}

	@Override
	public ControlElementProcessor getControlElementProcessor2() {
		return new ControlElementProcessor() {

			@Override
			public Processor<Watermark> getWatermarkProcessor() throws Exception {
				return AbstractTwoInputIteratorOperator.this.getWatermarkProcessor2();
			}

			@Override
			public Processor<LatencyMarker> getLatencyMarkerProcessor() throws Exception {
				return AbstractTwoInputIteratorOperator.this.getLatencyMarkerProcessor2();
			}
		};
	}

	protected Processor<Watermark> getWatermarkProcessor1() throws Exception {
		return new Processor<Watermark>() {
			@Override
			public void process(Watermark watermark) throws Exception {
				// watermark process logic can be reused here
			}
		};
	}

	protected Processor<Watermark> getWatermarkProcessor2() throws Exception {
		return new Processor<Watermark>() {
			@Override
			public void process(Watermark watermark) throws Exception {
				// watermark process logic can be reused here
			}
		};
	}

	protected Processor<LatencyMarker> getLatencyMarkerProcessor1() throws Exception {
		return new Processor<LatencyMarker>() {
			@Override
			public void process(LatencyMarker latencyMarker) throws Exception {
				// latency marker process logic can be reused here
			}
		};
	}

	protected Processor<LatencyMarker> getLatencyMarkerProcessor2() throws Exception {
		return new Processor<LatencyMarker>() {
			@Override
			public void process(LatencyMarker latencyMarker) throws Exception {
				// latency marker process logic can be reused here
			}
		};
	}
}
