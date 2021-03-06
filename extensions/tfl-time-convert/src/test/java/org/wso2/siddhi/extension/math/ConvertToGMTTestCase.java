/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.siddhi.extension.math;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.query.output.callback.QueryCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;

import java.util.Calendar;

public class ConvertToGMTTestCase {
    private static Logger logger = Logger.getLogger(ConvertToGMTTestCase.class);
    protected static SiddhiManager siddhiManager;

    @Test
    public void testProcess() throws Exception {
        logger.info("ConvertToGMT TestCase");

        siddhiManager = new SiddhiManager();
        String inValueStream = "@config(async = 'true')define stream InStream (timestamp long);";

        String eventFuseExecutionPlan = ("@info(name = 'query1') from InStream "
                + "select tfl:toGMT(timestamp) as timestamp "
                + "insert into OutStream;");
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(inValueStream + eventFuseExecutionPlan);

        final Long timestamp = 1450516511000l;
        final Long expected = timestamp - Calendar.getInstance().get(Calendar.ZONE_OFFSET);
        executionPlanRuntime.addCallback("query1", new QueryCallback() {
            @Override
            public void receive(long timeStamp, Event[] inEvents, Event[] removeEvents) {
                EventPrinter.print(timeStamp, inEvents, removeEvents);
                Long result;
                for (Event event : inEvents) {
                    result = (Long) event.getData(0);
                    Assert.assertEquals(expected, result);
                }
            }
        });
        InputHandler inputHandler = executionPlanRuntime.getInputHandler("InStream");
        executionPlanRuntime.start();
        inputHandler.send(new Long[]{timestamp});
        Thread.sleep(100);
        executionPlanRuntime.shutdown();
    }
}
