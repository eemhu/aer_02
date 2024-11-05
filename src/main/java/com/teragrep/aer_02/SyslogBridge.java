/*
 * Teragrep Eventhub Reader as an Azure Function
 * Copyright (C) 2024 Suomen Kanuuna Oy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://github.com/teragrep/teragrep/blob/main/LICENSE>.
 *
 *
 * Additional permission under GNU Affero General Public License version 3
 * section 7
 *
 * If you modify this Program, or any covered work, by linking or combining it
 * with other code, such other code is not for that reason alone subject to any
 * of the requirements of the GNU Affero GPL version 3 as long as this Program
 * is the same Program as licensed from Suomen Kanuuna Oy without any additional
 * modifications.
 *
 * Supplemented terms under GNU Affero General Public License version 3
 * section 7
 *
 * Origin of the software must be attributed to Suomen Kanuuna Oy. Any modified
 * versions must be marked as "Modified version of" The Program.
 *
 * Names of the licensors and authors may not be used for publicity purposes.
 *
 * No rights are granted for use of trade names, trademarks, or service marks
 * which are in The Program if any.
 *
 * Licensee must indemnify licensors and authors for any liability that these
 * contractual assumptions impose on licensors and authors.
 *
 * To the extent this program is licensed as part of the Commercial versions of
 * Teragrep, the applicable Commercial License may apply to this file if you as
 * a licensee so wish it.
 */
package com.teragrep.aer_02;

import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;

import java.util.List;
import java.util.Map;

public class SyslogBridge {

   // private EventDataConsumer consumer = null;

    @FunctionName("eventHubTriggerToSyslog")
    public void eventHubTriggerToSyslog(
            @EventHubTrigger(
                    name = "event",
                    /* Name of the EVENT HUB, not the app setting. Wrapping value in %'s makes it an environment variable.
                     * This makes it configurable in app settings. */
                    eventHubName = "%EventHubName%",
                    // Name of the APPLICATION SETTING
                    connection = "EventHubConnectionString",
                    cardinality = Cardinality.MANY,
                    dataType = "string"
            ) String[] events,
            //@BindingName("PartitionContext") PartitionContext partitionContext,
            @BindingName("PropertiesArray") Map<String, Object>[] propertiesArray,
            @BindingName("SystemPropertiesArray") Map<String, Object>[] systemPropertiesArray,
            @BindingName("EnqueuedTimeUtcArray") List<Object> enqueuedTimeUtcArray,
            @BindingName("OffsetArray") List<String> offsetArray,
            @BindingName("PartitionKeyArray") List<String> partitionKeyArray,
            @BindingName("SequenceNumberArray") List<Long> sequenceNumberArray,
            ExecutionContext context
            ) {

        context.getLogger().info("Java Event Hub trigger received " + events.length + " messages");
        context.getLogger().info("message[0]=" + events[0]);
        context.getLogger().info("Properties for message[0]=" + propertiesArray[0]);
        context.getLogger().info("SystemProperties for message[0]="+ systemPropertiesArray[0]);
        context.getLogger().info("EnqueuedTimeUtc for message[0]=" + enqueuedTimeUtcArray.get(0));
        context.getLogger().info("Offset for message[0]=" + offsetArray.get(0));
        context.getLogger().info("PartitionKey for message[0]=" + partitionKeyArray.get(0));
        context.getLogger().info("SequenceNumber for message[0]=" + sequenceNumberArray.get(0));
        context.getLogger().fine("eventHubTriggerToSyslog triggered");
        context.getLogger().fine("Got events: " + events.length);

      /*  if (consumer == null) {
            final Sourceable configSource = new EnvironmentSource();
            final int prometheusPort = new MetricsConfig(configSource).prometheusPort;

            consumer = new EventDataConsumer(configSource, prometheusPort);
        }

        for(int index = 0; index < events.length; index++) {
            if (events[index] != null) {
                final ZonedDateTime et = ZonedDateTime.parse(enqueuedTimeUtcArray.get(index) + "Z"); // needed as the UTC time presented does not have a TZ
                context.getLogger().info("Accepting event: " + events[index]);
                consumer.accept(events[index],
                        partitionContext,
                        et ,
                        offsetArray.get(index),
                        partitionKeyArray.get(index),
                        sequenceNumberArray.get(index),
                        propertiesArray[0],
                        systemPropertiesArray[0]
                );
            }
            else {
                context.getLogger().warning("eventHubTriggerToSyslog event data is null");
            }
        } */
    }
}
