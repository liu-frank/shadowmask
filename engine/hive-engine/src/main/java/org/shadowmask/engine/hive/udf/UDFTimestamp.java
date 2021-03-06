/**
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

package org.shadowmask.engine.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.apache.hadoop.io.IntWritable;
import org.shadowmask.core.mask.rules.generalizer.Generalizer;
import org.shadowmask.core.mask.rules.generalizer.impl.TimestampGeneralizer;

import java.sql.Timestamp;

/**
 * UDFTimestamp.
 *
 */
@Description(name = "timestamp",
             value = "_FUNC_(timestamp, mask) - returns the masked value of timestamp\n"
                + "timestamp - original timestamp type with date string and time string\n"
                + "maskLevel - hide the date components or the time components, 1 masked, 0 unmasked",
             extended = "Example:\n")
public class UDFTimestamp extends UDF {

  public TimestampWritable evaluate(TimestampWritable timestamp, IntWritable mask) {
    if (timestamp == null || mask == null) return null;
    int mode = mask.get();
    Timestamp ts = timestamp.getTimestamp();
    TimestampWritable result = new TimestampWritable();
    Generalizer<Long, Long> generalizer = new TimestampGeneralizer();
    result.set(new Timestamp(generalizer.generalize(ts.getTime(), mode)));
    return result;
  }

}
